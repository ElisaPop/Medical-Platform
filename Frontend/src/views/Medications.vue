<template>
    <div>
        <v-container>
        <v-tabs v-model="tab" background-color="#f1faee" max-width="800" centered>
            <v-text-field v-model="searchText" label="Search"></v-text-field>
            <v-tabs-slider></v-tabs-slider>
            <v-btn color="#1d3557" icon @click="search">
            <v-icon>mdi-magnify </v-icon>
            </v-btn>
            <router-link :to="`medications/-1`">
                <v-btn color="#1d3557" icon>
                    <v-icon>mdi-plus-circle </v-icon>
                </v-btn>
            </router-link>

            <v-btn color="#1d3557" dark @click ="$router.push({ name: 'Patients' })"  style="color:#a8dadc">
                Patients
            </v-btn>
            <v-btn color="#1d3557" dark @click ="$router.push({ name: 'Caregivers' })" style="color:#a8dadc">
                Caregivers
            </v-btn>
            <v-btn color="#457b9d" style="color:#ffffff">
                Medications
            </v-btn>
        
        </v-tabs>
        <v-tabs-items v-model="tab">
        <v-tab-item>
            <v-row no-gutters class="mt-4">
            <v-col cols="3" sm="4" v-for="medication in medications" :key="medication.id">
                <MedicationCard :medicationParameter="medication" />
            </v-col>
            </v-row>
        </v-tab-item>
        </v-tabs-items>
        </v-container>

    </div>
</template>


<script>
import MedicationCard from "../components/MedicationCard";
export default {
    name: "Medications",
    components: {
        MedicationCard
    },
    props: ['currentMedication'],
    data () {
        return {
            searchText: '',
            tab:0,
            medications:[]
        }
    },
   created(){

        this.$http.get(process.env.VUE_APP_BACKEND_API + "/medication", {headers: this.$store.getters.getAuthorization}).then( result => {
            this.medications = result.data;
        })
    },
    methods: {
        search(){
            if(this.searchText !== ''){
                this.$http.get(process.env.VUE_APP_BACKEND_API + "/medication/search?name="+ this.searchText, {headers: this.$store.getters.getAuthorization}).then( result => {
                this.medications = result.data;
                })
            }
        }
    }
}
</script>