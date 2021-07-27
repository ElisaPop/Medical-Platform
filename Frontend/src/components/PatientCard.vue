<template>
  <v-card class="mx-auto mb-3" max-width="800" outlined shaped>
    <v-list-item three-line>
      <v-list-item-content>
        <div class="overline mb-4">{{ patientParameter.id }}</div>
        
        <v-list-item-title class="display-1 mb-6">
            <span class="font-weight-bold">Username:</span>
          {{ patientParameter.username }}
        </v-list-item-title>
        <v-list-item-subtitle class="mb-3 mt-2">
          <span class="font-weight-bold">Name:</span>
          {{ patientParameter.name }}
        </v-list-item-subtitle>
        <v-list-item-subtitle class="mb-2 mt-2">
          <span class="font-weight-bold">Address:</span>
          {{ patientParameter.address }}
        </v-list-item-subtitle>
        <v-list-item-subtitle class="mb-2 mt-2">
          <span class="font-weight-bold">Gender:</span>
          {{ patientParameter.gender }}
        </v-list-item-subtitle>
        <v-list-item-subtitle class="mb-2 mt-2">
          <span class="font-weight-bold">Role:</span>
          {{ patientParameter.role }}
        </v-list-item-subtitle>
        <v-list-item-subtitle class="mb-2 mt-2">
          <span class="font-weight-bold">Medical Record:</span>
          {{ patientParameter.medicalRecord}}
        </v-list-item-subtitle>
        <v-list-item-subtitle class="mb-2 mt-2" v-if="this.patientParameter.caregiver!= null">
          <span class="font-weight-bold">Caregiver:</span>
          {{ patientParameter.caregiver.username}}
        </v-list-item-subtitle>
      </v-list-item-content>
        
    </v-list-item>
    <v-list-item>
      <v-card-actions v-if="this.$store.getters.userData!= null">
          <router-link :to="`patients/${patientParameter.id}`">
          <v-btn 
            v-if="this.$store.getters.userRole == 2"
          color="#f4f1ea">
              Edit
          </v-btn>
        </router-link>
      </v-card-actions>

      <v-card-actions v-if="this.$store.getters.userData!= null && isCaregiver">
          <router-link :to="`medicationplans/${patientParameter.id}`">
          <v-btn 
          color="#f4f1ea">
              Medication plans
          </v-btn>
        </router-link>
      </v-card-actions>

      <v-card-actions v-if="this.$store.getters.userData!= null && isDoctor">
          <router-link :to="`medicationplans/add/${patientParameter.id}`">
          <v-btn 
          color="#f4f1ea">
              Medication plans
          </v-btn>
        </router-link>
      </v-card-actions>
      
      <!-- <v-card-actions v-if="this.$store.getters.userData!= null">
          <v-btn 
            v-if="this.$store.getters.userRole == 2"
          color="#f4f1ea" @click="deletePatient()">
              Delete
          </v-btn>
      </v-card-actions> -->
    </v-list-item>
  </v-card>
</template>

<script>
export default {
    name: "PatientCard",
    props: {
      patientParameter: {
          type: Object,
          required: true
      }
    },
    data() {
        return {
          isCaregiver: false,
          isDoctor: false
        }
    },
    created(){
            //console.log("Caregiver id:" + $patientParameter.caregiverid)
            if(this.$store.getters.userRole == 1){
                this.isCaregiver = true
            } 
            if(this.$store.getters.userRole == 2){
                this.isDoctor = true
            }
            console.log(this.$store.getters.userRole == 1)
            console.log(this.$store.getters.userRole == 2)
            this.$http.get(process.env.VUE_APP_BACKEND_API + "/person/caregiver", {headers: this.$store.getters.getAuthorization}).then( result => {
            this.caregiver = result.data;
        })
    }
    
    // methods: {
    //     deletePatient() {
    //       console.log(this.patientParameter.id),
    //       this.$http.delete("https://lifeline-medapp-backend.herokuapp.com/person/patient/"+ this.patientParameter.username).then(()=>{
    //       })
    //     }
    // }
}
</script>