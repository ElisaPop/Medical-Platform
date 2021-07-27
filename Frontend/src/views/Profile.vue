<template>
    <div>
        <v-card
        class="mx-auto"
        max-width="600"
        flat
        tile>
        <v-container>
            <h2 style="text-align: center"> Username: {{this.username}}</h2>
            <div id="app">
                <v-app id="inspire">
                    <v-form
                    ref="form"
                    >
                    <v-text-field
                        v-model="name"
                        label="Name"
                        disabled
                    ></v-text-field>
                    <v-text-field
                        v-model="address"
                        label="Address"
                        disabled
                    ></v-text-field>
                    <span>Birthdate:</span>
                    <b-form-datepicker id="example-datepicker" v-model="birthdate" class="mb-2" disabled></b-form-datepicker>
                    <v-text-field
                        v-model="gender"
                        label="Gender"
                        disabled
                    ></v-text-field>
                    <v-text-field
                        v-model="role"
                        label="Role"
                        disabled
                    ></v-text-field>
                    <v-text-field
                        v-model="medicalRecord"
                        label="Medical Record"
                        disabled
                        v-if="this.$store.getters.userRole == 0"
                    ></v-text-field>
                    </v-form>
                </v-app>
                </div>
            </v-container>
        </v-card>
    </div>
</template>

<script>
export default {
    data: () => ({
        name: '',
        medicalRecord: '',
        address: '',
        username: '',
        birthdate: null,
        birthdateString: '',
        gender: '',
        role: ''
    }),
    created(){
        this.name = this.$store.getters.userData.name,
        this.username = this.$store.getters.userData.username,
        this.address = this.$store.getters.userData.address,
        this.medicalRecord = this.$store.getters.userData.medicalRecord,
        this.birthdateString = '' + this.$store.getters.userData.birthdate[2] + '/' + this.$store.getters.userData.birthdate[1] + '/' + this.$store.getters.userData.birthdate[0],
        this.birthdate = new Date(this.birthdateString),
        console.log(this.birthdate),
        this.gender = this.$store.getters.userData.gender,
        this.role = this.$store.getters.userData.role
    },
}
</script>
