<template>
    <div>
        <v-container>
        <v-tabs v-model="tab" background-color="#f1faee" max-width="800">
            <v-text-field v-model="searchText" label="Search" >
            </v-text-field>
            <v-tabs-slider></v-tabs-slider>
            <v-btn color="#1d3557" icon @click="search">
            <v-icon>mdi-magnify </v-icon>
            </v-btn>
            <router-link :to="`caregivers/-1`">
                <v-btn color="#1d3557" icon>
                    <v-icon>mdi-plus-circle </v-icon>
                </v-btn>
            </router-link>
            <v-btn color="#1d3557" dark @click ="$router.push({ name: 'Patients' })"  style="color:#a8dadc">
                Patients
            </v-btn>
            <v-btn color="#457b9d" style="color:#ffffff">
                Caregivers
            </v-btn>
            <v-btn color="#1d3557" dark @click ="$router.push({ name: 'Medications' })" style="color:#a8dadc">
                Medications
            </v-btn>
        
        </v-tabs>
        <v-tabs-items v-model="tab">
        <v-tab-item>
            <v-row no-gutters class="mt-4">
                <v-col cols="7" sm="4" v-for="caregiver in caregivers" :key="caregiver.id">
                    <CaregiverCard :caregiverParameter="caregiver" />
                </v-col>  
            </v-row>
        </v-tab-item>
        </v-tabs-items>
        </v-container>

    </div>
</template>

<script>
import CaregiverCard from "../components/CaregiverCard";
export default {
    name: "Caregivers",
    components: {
        CaregiverCard
    },
    data () {
        return {
            searchText: '',
            tab: 0,
            caregivers:[],
        }
    },
    created(){
        
            this.$http.get(process.env.VUE_APP_BACKEND_API + "/person/caregiver", {headers: this.$store.getters.getAuthorization}).then( result => {
            this.caregivers = result.data;
        })
    },
    methods: {
        search(){
            if(this.searchText !== ''){
                this.$http.get(process.env.VUE_APP_BACKEND_API + "/person/caregiver/search?title="+ this.searchText, {headers: this.$store.getters.getAuthorization}).then( result => {
                this.caregivers = result.data;
                })
            }
        }
    }
}
</script>