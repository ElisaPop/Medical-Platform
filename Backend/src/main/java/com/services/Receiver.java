package com.services;

import com.entities.MonitoredData;
import com.entities.Patient;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.repositories.MonitoredDataRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class Receiver{
    @Autowired MessageDispatcher md;

    static final String QUEUE_NAME = "lifeline";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private final PatientService patientService;

    @Autowired
    private MonitoredDataRepository monitoredDataRepository;

    public Receiver(PatientService patientService) {
        this.patientService = patientService;
    }

    public void start() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqps://ygrdludg:Po3fQWPc9p-Mo-u1Mh6bxBMOUW9JUQLP@roedeer.rmq.cloudamqp.com/ygrdludg");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            try {
                buildActivity(message);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

    }

    private void buildActivity(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        UUID patientId = UUID.fromString(jsonObject.getString("patient_id"));
        String activity = jsonObject.getString("activity");
        LocalDateTime startDate = LocalDateTime.parse(jsonObject.getString("start"),formatter);
        LocalDateTime endDate = LocalDateTime.parse(jsonObject.getString("end"),formatter);

        long minutes = ChronoUnit.MINUTES.between(startDate, endDate);
        String problem = "";

        int sleepingMinutesLimit = 7*60;
        int leavingMinutesLimit = 5*60;
        int bathroomMinutesLimit = 30;

        switch(activity){
            case "Sleeping":
                if(minutes > sleepingMinutesLimit){
                    problem = "Sleep period longer than 7 hours";
                }
                break;
            case "Leaving":
                if(minutes > leavingMinutesLimit){
                    problem = "The leaving activity (outdoor) is longer than 5 hours";
                }
                break;
            case "Grooming":
                if(minutes > bathroomMinutesLimit){
                    problem = "Period spent in bathroom is longer than 30 minutes";
                }
                break;
            default: break;
        }

        if(!problem.isEmpty()){
            Patient patient = patientService.findPatientById(patientId);
            if(patient.getCaregiver() != null)
                md.sendToClient(patient.getCaregiver().getId() + "","Tha patient " + patient.getName() +
                        " has a suspicious activity:  " + problem);
            monitoredDataRepository.save(new MonitoredData(patientId,startDate,endDate,activity,problem));
        }
    }
}
