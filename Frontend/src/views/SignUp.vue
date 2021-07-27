<template>
  <div class="signup">
    <v-card
        class="mx-auto"
        max-width="600"
        flat
        tile>
        <v-container>
        <h1> Sign Up </h1>

        <v-alert type="success" v-if="this.accountCreated === true">
            The account was created!
        </v-alert>
        <v-alert type="error" v-if="this.samePassword === false">
            Passwords are not the same
        </v-alert>
         <v-alert type="error" v-if="this.usernameTaken === true">
            The username is already being used
        </v-alert>

        <div id="app">
            <v-app id="inspire">
                <v-form
                ref="form"
                v-model="valid"
                lazy-validation
                >
                <v-text-field
                    v-model="user.username"
                    :counter="20"
                    :rules="nameRules"
                    label="Username"
                    required
                ></v-text-field>

                <v-text-field
                    v-model="user.name"
                    :counter="20"
                    :rules="nameRules"
                    label="Name"
                    required
                ></v-text-field>

                <v-text-field
                    v-model="password1"
                    :type="false ? 'text' : 'password'"
                    :rules="passwordRules"
                    label="Password"
                    required
                ></v-text-field>

                <v-text-field
                    v-model="password2"
                    :type="false ? 'text' : 'password'"
                    :rules="passwordRules"
                    label="Verify Password"
                    required
                ></v-text-field>

                <v-text-field
                    v-model="user.address"
                    label="Address"
                ></v-text-field>

                <span>Birthdate:</span>
                <b-form-datepicker id="example-datepicker" v-model="user.birthdate" class="mb-2"></b-form-datepicker>

                <br>
                <span>Gender:</span>
                <select v-model="user.gender">
                    <option disabled value="">SELECT</option>
                    <option>MALE</option>
                    <option>FEMALE</option>
                    <option>OTHER</option>
                </select>

                <br>
                <br>
                <v-btn
                    color="#1d3557"
                    outlined
                    @click="signup"
                >
                    Sign Up
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
  name: 'SignUp',
  data: () => ({
    valid: true,
    user: {
        id: '',
        username: '',
        address: '',
        password: '',
        name: '',
        birthdate: '',
        gender: '',
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
    ],
    select: null,
  }),

  methods: {
    signup () {
        if(this.password1 === this.password2){
            this.samePassword = true;
            this.user.password = this.password1
            console.log(this.user)
            this.$http.post(process.env.VUE_APP_BACKEND_API + "/person/auth/signup", this.user, {headers: this.$store.getters.getAuthorization}).then( result => {
            if(result.data.username == null){
                //alert(result.data.username)
                if(result.data.username == null) this.usernameTaken = true
                this.accountCreated = false
            } else {
                this.accountCreated = true
            }})
        } else{
            this.samePassword = false;
        }
    },
  }
}
</script>
