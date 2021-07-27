<template>
  <v-card class="mx-auto mb-3" max-width="800" outlined shaped>
    <v-list-item three-line>
      <v-list-item-content>
        <div class="overline mb-4">{{ medicationParameter.id }}</div>
        
        <v-list-item-title class="display-1 mb-6">
           
            {{ medicationParameter.name }}
        </v-list-item-title>
        <v-list-item-subtitle>
            Side effects:
            {{ medicationParameter.sideEffects }}
        </v-list-item-subtitle>
        <v-list-item-subtitle>
            Available dosage:
            {{ medicationParameter.availableDosage }}
        </v-list-item-subtitle>
      </v-list-item-content>
    </v-list-item>
    <v-card-actions>
        <router-link :to="`medications/${medicationParameter.id}`">
        <v-btn color="#f4f1ea">
            Edit
        </v-btn>
      </router-link>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
    name: "MedicationCard",
    data() {
        return {
        }
    },
    props: {
       medicationParameter: {
          type: Object,
          required: true
        }
    }
}
</script>