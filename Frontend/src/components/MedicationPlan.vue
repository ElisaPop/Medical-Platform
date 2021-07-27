<template>
    <div>
        <v-card
        class="mx-auto"
        max-width="600"
        flat
        tile>
        <v-container>
        <h2> Medication plan for {{this.patient.id}}</h2>
        <div id="app">
            <v-app id="inspire">
                <v-form
                ref="form"
                v-model="valid"
                lazy-validation
                >
                <span>Start date:</span>
                <b-form-datepicker id="example-datepicker1" v-model="startDate" class="mb-2"></b-form-datepicker>
                <span>End date:</span>
                <b-form-datepicker id="example-datepicker2" v-model="endDate" class="mb-2"></b-form-datepicker>
                <span>Start hour:</span>
                <b-form-timepicker id="example-timepicker1" v-model="startHour" class="mb-2"></b-form-timepicker>
                <span>End hour:</span>
                <b-form-timepicker id="example-timepicker2" v-model="endHour" class="mb-2"></b-form-timepicker>
                <span>Medication:</span>
                <select v-model="selectedMedication"
                    outlined
                    dense
                    @change="updateMedication"
                >
                    <option disabled value="">SELECT</option>
                    <option v-for="medication in medications" :key="medication.id" >
                        {{medication.name}}
                    </option>
                </select>
                <br>
                <ul id="example-1">
                    <li v-for="med in selectedMedications" :key="med.id">
                        {{ med.name }}
                    </li>
                </ul>
                <br>
                <v-btn color="#f4f1ea" @click="addMedication">
                    Add Medication
                </v-btn>
                
                <br>
                
                <br>
                <v-btn color="#f4f1ea" @click="save">
                    Save
                </v-btn>

                </v-form>
            </v-app>
            </div>
        </v-container>
    </v-card>
        
    </div>
</template>

<script>

export default {
    data: function () {
        return {
            valid: true,
            nameRules: [
            v => !!v || 'Name is required',
            v => (v && v.length <= 20) || 'Name must be less than 20 characters',
            ],
            medicationplan:{
                
            },
            patient: {},
            doctor: {},
            medications: [],
            selectedMedication: '',
            medication: [],
            selectedMedications: [],
            startDate: '',
            endDate: '',
            startHour: '',
            endHour: '',
            
        }
    },
   created(){
        this.$http.get(process.env.VUE_APP_BACKEND_API + "/person/patient/" + this.$route.params.id, {headers: this.$store.getters.getAuthorization}).then( result => {
            this.patient = result.data
            this.selectedCaregiver = this.patient.caregiver
        })

        this.doctor = this.$store.getters.userData;
        this.$http.get(process.env.VUE_APP_BACKEND_API + "/medication", {headers: this.$store.getters.getAuthorization}).then( result => {
                this.medications = result.data;
                console.log(this.medications)
            })
    },
    methods: {
        save(){
            this.medicationplan.periodStart= this.startDate,
            this.medicationplan.periodEnd= this.endDate,
            this.medicationplan.intervalStart= this.startHour,
            this.medicationplan.intervalEnd= this.endHour,
            this.medicationplan.patient= this.patient,
            this.medicationplan.doctor= this.doctor,
            this.medicationplan.medications= this.selectedMedications
            this.$http.post(process.env.VUE_APP_BACKEND_API + "/medicationplan/",this.medicationplan, {headers: this.$store.getters.getAuthorization}).then(result =>{
                Object.assign(this.medicationplan, result.data);
            })
            this.$router.push({ name: 'Patients' })
        },
        updateMedication(){
             this.$http.get(process.env.VUE_APP_BACKEND_API + "/medication/cg/"+ this.selectedMedication, {headers: this.$store.getters.getAuthorization}).then( result => {
                this.medication = result.data;
                console.log(this.medication)
            })
        },
        addMedication(){
            this.selectedMedications.push(this.medication)
        }
    }
}
</script>
