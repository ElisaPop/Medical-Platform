<template>
  <v-card class="mx-auto mb-3" max-width="800" outlined shaped>
    <v-list-item three-line>
      <v-list-item-content>
        <div class="overline mb-4">{{ caregiverParameter.id }}</div>
        
        <v-list-item-title class="display-1 mb-6">
            <span class="font-weight-bold">Username:</span>
          {{ caregiverParameter.username }}
        </v-list-item-title>
        <v-list-item-subtitle class="mb-3 mt-2">
          <span class="font-weight-bold">Name:</span>
          {{ caregiverParameter.name }}
        </v-list-item-subtitle>
        <v-list-item-subtitle class="mb-2 mt-2">
          <span class="font-weight-bold">Address:</span>
          {{ caregiverParameter.address }}
        </v-list-item-subtitle>
        <v-list-item-subtitle class="mb-2 mt-2">
          <span class="font-weight-bold">Gender:</span>
          {{ caregiverParameter.gender }}
        </v-list-item-subtitle>
        <v-list-item-subtitle class="mb-2 mt-2">
          <span class="font-weight-bold">Role:</span>
          {{ caregiverParameter.role }}
        </v-list-item-subtitle>
      </v-list-item-content>
        
    </v-list-item>
    <v-list-item>
      <v-card-actions v-if="this.$store.getters.userData!= null">
          <router-link :to="`caregivers/${caregiverParameter.id}`">
          <v-btn 
            v-if="this.$store.getters.userRole == 2"
          color="#f4f1ea">
              Edit
          </v-btn>
        </router-link>
      </v-card-actions>
      <!-- <v-card-actions v-if="this.$store.getters.userData!= null">
          <v-btn 
            v-if="this.$store.getters.userRole == 2"
          color="#f4f1ea" @click="deleteCaregiver()">
              Delete
          </v-btn>
      </v-card-actions> -->
    </v-list-item>
  </v-card>
</template>

<script>
export default {
    name: "CaregiverCard",
    props: {
      caregiverParameter: {
          type: Object,
          required: true
      },
    }
    // methods: {
    //     deleteCaregiver() {
    //       console.log(this.caregiverParameter.id),
    //       this.$http.delete("https://lifeline-medapp-backend.herokuapp.com/person/caregiver/"+ this.caregiverParameter.username).then(()=>{
    //       })
    //     }
    // }
}
</script>