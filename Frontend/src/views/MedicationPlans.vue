<template>
  <div>
    <v-card
        class="mx-auto"
        max-width="600"
        flat
        tile>
        <v-container>
        <h2> Medication plan for {{this.patientId}}</h2>
        <div id="app">
            <v-app id="inspire">
                <v-form
                ref="form"
                lazy-validation
                >
                <span>Start date:</span>
                <b-form-datepicker id="example-datepicker1" v-model="startDate" class="mb-2" disabled></b-form-datepicker>
                <span>End date:</span>
                <b-form-datepicker id="example-datepicker2" v-model="endDate" class="mb-2" disabled></b-form-datepicker>
                <span>Start hour:</span>
                <b-form-timepicker id="example-timepicker1" v-model="medicationplan.intervalStart" class="mb-2" disabled></b-form-timepicker>
                <span>End hour:</span>
                <b-form-timepicker id="example-timepicker2" v-model="medicationplan.intervalEnd" class="mb-2" disabled></b-form-timepicker>
                <span>Medication:</span>
                <ul id="example-1">
                    <li v-for="med in selectedMedications" :key="med.id">
                        {{ med.name }}
                    </li>
                </ul>
                </v-form>
            </v-app>
            </div>
        </v-container>
    </v-card>
  </div>
</template>


<script>
export default {
  name: "MedicationPlans",
  data() {
    return {
      medicationplan:{},
      patient: [],
      doctor: [],
      medications: [],
      medication: [],
      selectedMedications: [],
      startDate: null,
      startDateString: '',
      patientId: '',
      endDate: null,
      endDateString: '',
      startHour: '',
      endHour: '',
      exists: true,
      tab: 0,
      medicationPlans: []
    };
  },
  created() {
    this.$http.get(process.env.VUE_APP_BACKEND_API + "/medicationplans/"+ this.$route.params.id, {headers: this.$store.getters.getAuthorization}).then(result => {
      this.medicationplan = result.data[0];
      if(this.medicationplan == null){
        this.exists = false;
      } else {
        this.exists = true;
        this.patientId = this.medicationplan.patient.id;
        console.log(this.medicationplan);
        console.log("End date:" + this.medicationplan.periodStart),
        this.startDateString = '' + this.medicationplan.periodStart[2] + '/' + this.medicationplan.periodStart[1] + '/' + this.medicationplan.periodStart[0],
        this.startDate = new Date(this.startDateString),
        console.log("End date:" + this.medicationplan.periodEnd),
        this.endDateString = '' + this.medicationplan.periodEnd[2] + '/' + this.medicationplan.periodEnd[1] + '/' + this.medicationplan.periodEnd[0],
        console.log("End date:" + this.endDateString)
        this.endDate = new Date(this.endDateString),
        console.log("End date:" + this.endDate)
        this.selectedMedications = this.medicationplan.medicationPlans
      }
    })
    console.log(this.exists)
    if(this.exists == false & this.$store.getters.userRole == 2){
        this.exists = true;
        this.$router.push(`/medicationplans/add/${this.$route.params.id}`)
    }
    
    
  }
};
</script>