<template>
    <div>
        <v-card
        class="mx-auto"
        max-width="600"
        flat
        tile>
        <v-container>
        <h2 v-if="isEdit"> Medication Plan #{{this.medication.id}}</h2>
        <h2 v-if="isEdit === false"> Add medication plan</h2>
        <div id="app">
            <v-app id="inspire">
                <v-form
                ref="form"
                v-model="valid"
                lazy-validation
                >
                <v-text-field
                    v-model="medication.name"
                    :counter="20"
                    :rules="nameRules"
                    label="Medication Name"
                    required
                ></v-text-field>
                <v-text-field
                    v-model="medication.sideEffects"
                    :counter="20"
                    :rules="nameRules"
                    label="Side effects"
                    required
                ></v-text-field>
                <v-text-field
                    v-model="medication.availableDosage"
                    label="Available dosage"
                    required
                ></v-text-field>

                <v-btn
                    v-if="isEdit === true"
                    color="#f4f1ea"
                    @click="save"
                >
                    Edit
                </v-btn>
                <v-btn
                    v-else
                    color="#f4f1ea"
                    @click="save"
                >
                    Save
                </v-btn>

                <v-btn
                    v-if="isEdit === true"
                    color="#f4f1ea"
                    @click="deleteMedication"
                >
                    Delete
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
            isEdit: true,
            valid: true,
            nameRules: [
            v => !!v || 'Name is required',
            v => (v && v.length <= 20) || 'Name must be less than 20 characters',
            ],
            medication:{}
        }
    },
   created(){
       if(this.$route.params.id != -1){
            this.isEdit = true;
            this.$http.get(process.env.VUE_APP_BACKEND_API + "/medication/" + this.$route.params.id, {headers: this.$store.getters.getAuthorization}).then( result => {
            this.medication = result.data;
        })
       }
        else {
            this.$route.params.id = -1;
            this.isEdit = false;
        }
    },
    methods: {
        save(){
            if(this.isEdit){
               this.$http.put(process.env.VUE_APP_BACKEND_API + "/medication/", this.medication, {headers: this.$store.getters.getAuthorization}).then(result =>{
                    this.medication = result.data;
                })
            } else {
                 this.$http.post(process.env.VUE_APP_BACKEND_API + "/medication/", this.medication, {headers: this.$store.getters.getAuthorization}).then(result =>{
                    Object.assign(this.medication, result.data);
                })
            }
            this.$router.push({ name: 'Medications' })
        },
        editMedication(medication){
            this.editedIndex = medication.id;
            this.editedMedication = Object.assign({},medication);
        },
        deleteMedication(){
            this.$http.delete(process.env.VUE_APP_BACKEND_API + "/medication/" + this.medication.name, {headers: this.$store.getters.getAuthorization}).then(()=>{this.$router.push("/medications")});
        }
    }
}
</script>
