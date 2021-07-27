<template>
    <div>
        <v-card
        class="mx-auto"
        max-width="600"
        flat
        tile>
        <v-container>
            <h2 v-if="isEdit"> Caregiver #{{this.caregiver.id}}</h2>
            <h2 v-if="isEdit === false"> Add caregiver</h2>
            <div id="app">
                <v-app id="inspire">
                    <v-form
                    ref="form"
                    v-model="valid"
                    lazy-validation
                    >
                    <v-text-field
                    v-model="caregiver.username"
                    :counter="20"
                    :rules="nameRules"
                    label="Username"
                    required
                    v-if="isEdit === false"
                ></v-text-field>

                <v-text-field
                    v-model="caregiver.name"
                    :counter="20"
                    :rules="nameRules"
                    label="Name"
                    required
                ></v-text-field>

                <v-text-field
                    v-model="caregiver.password"
                    :rules="passwordRules"
                    label="Password"
                    required
                    v-if="isEdit === false"
                ></v-text-field>

                <v-text-field
                    v-model="caregiver.address"
                    label="Address"
                ></v-text-field>

                <span v-if="isEdit === false">Birthdate:</span>
                <b-form-datepicker id="example-datepicker" v-model="caregiver.birthdate" class="mb-2" v-if="isEdit === false"></b-form-datepicker>

                <br>
                <span>Gender:</span>
                <select v-model="caregiver.gender">
                    <option disabled value="">SELECT</option>
                    <option>MALE</option>
                    <option>FEMALE</option>
                    <option>OTHER</option>
                </select>
                <br>
                <br>
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
                        @click="deleteCaregiver"
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
            caregiver: {
            },
            password1: '',
            password2: '',
            accountCreated: null,
            samePassword: null,
            usernameTaken: null,
            nameRules: [
            v => !!v || 'Name is required',
            v => (v && v.length <= 20) || 'Name must be less than 20 characters',
            ],
            password: '',
            passwordRules: [
                v => !!v || 'Password is required',
            ]
        }
    },
   created(){
       if(this.$route.params.id != -1){
            this.isEdit = true;
            this.$http.get(process.env.VUE_APP_BACKEND_API + "/person/caregiver/" + this.$route.params.id, {headers: this.$store.getters.getAuthorization}).then( result => {
             this.caregiver = result.data
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
                this.$http.put(process.env.VUE_APP_BACKEND_API + "/person/caregiver/", this.caregiver, {headers: this.$store.getters.getAuthorization}).then(result =>{
                    this.caregiver = result.data;
                })
            } else {
                this.caregiver.role = 'CAREGIVER',
                this.$http.post(process.env.VUE_APP_BACKEND_API + "/person/caregiver/", this.caregiver, {headers: this.$store.getters.getAuthorization}).then(result =>{
                    Object.assign(this.caregiver, result.data);
                })
            }
            this.$router.push({ name: 'Caregivers' })
        },
        deleteCaregiver(){
            this.$http.delete(process.env.VUE_APP_BACKEND_API + "/person/caregiver/" + this.caregiver.username, {headers: this.$store.getters.getAuthorization}).then(()=>{this.$router.push("/caregivers")});
        }
    }
}
</script>
