<template>
    <div>
        <v-container>
        <v-tabs v-model="tab" background-color="#f1faee" max-width="800" centered>
            <v-text-field v-model="searchText" label="Search" >
            </v-text-field>
            <v-tabs-slider></v-tabs-slider>
            <v-btn color="#1d3557" icon >
            <v-icon>mdi-magnify </v-icon>
            </v-btn>
            <router-link :to="`patients/-1`">
                <v-btn color="#1d3557" icon>
                    <v-icon>mdi-plus-circle </v-icon>
                </v-btn>
            </router-link>

            <v-btn color="#457b9d" style="color:#ffffff">
                Patients
            </v-btn>
            <v-btn color="#1d3557" dark @click ="$router.push({ name: 'Caregivers' })" style="color:#a8dadc" v-if="this.$store.getters.userRole == 2">
                Caregivers
            </v-btn>
             <v-btn color="#1d3557" dark @click ="$router.push({ name: 'Medications' })"  style="color:#a8dadc" v-if="this.$store.getters.userRole == 2">
                Medications
            </v-btn>
        
        </v-tabs>
        <v-tabs-items v-model="tab">
        <v-tab-item>
            <v-row no-gutters class="mt-4">
            <v-col cols="1" sm="4" v-for="patient in patients" :key="patient.id">
                <PatientCard :patientParameter="patient" />
            </v-col>
            </v-row>
        </v-tab-item>
        </v-tabs-items>
        </v-container>

    </div>
</template>


<script>
import PatientCard from "../components/PatientCard";
export default {
    name: "Patients",
    components: {
        PatientCard
    },
    data () {
        return {
            searchText: '',
            tab: 0,
            patients:[]

        }
    },
    created(){
            if(this.$store.getters.userRole == 2){
                this.$http.get(process.env.VUE_APP_BACKEND_API + "/person/patient", {headers: this.$store.getters.getAuthorization}).then( result => {
                this.patients = result.data;
                console.log(this.patients)
                })
            }
            if(this.$store.getters.userRole == 1) {
                this.$http.get(process.env.VUE_APP_BACKEND_API + "/person/patient/bycg/"+ this.$store.getters.userData.id, {headers: this.$store.getters.getAuthorization}).then( result => {
                this.patients = result.data;
                console.log(this.patients)
                })
            }
    }
}
</script>