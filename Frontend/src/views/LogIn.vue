<template>
  <div class="login">
    <v-card
        class="mx-auto"
        max-width="600"
        flat
        tile>
        <v-container>

        <h1> Log In </h1>

        <v-alert type="error" v-if="this.loginSuccessful === false">
            Username or password incorrect
        </v-alert>
  
        <div id="app">
            <v-app id="inspire">
                <v-form
                ref="form"
                lazy-validation
                >
                <v-text-field
                    v-model="name"
                    :counter="20"
                    :rules="nameRules"
                    label="Name"
                    required
                ></v-text-field>
            
                <v-text-field
                    v-model="password"
                    :type="false ? 'text' : 'password'"
                    :rules="passwordRules"
                    label="Password"
                    required
                ></v-text-field>
            
                <v-btn
                    color="#1d3557"
                    outlined
                    class="mr-4"
                    @click="login"
                >
                  Log In
            
                </v-btn>
            
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
  name: 'LogIn',
  data: () => ({
    id: -1,
    user: [],
    loginDTO: {
      name: '',
      password: ''
    },
    loginSuccessful: null,
    name: '',
    nameRules: [
      v => !!v || 'Username is required',
      v => (v && v.length <= 20) || 'Username must be less than 20 characters',
    ],
    password: '',
    passwordRules: [
      v => !!v || 'Password is required',
    ],
    select: null,
  }),      

  methods: {
    login () {
        this.loginDTO.name = this.name;
        this.loginDTO.password = this.password;
        this.$http.post(process.env.VUE_APP_BACKEND_API + "/person/auth/login?username="+ this.name + "&password=" + this.password, {headers: this.$store.getters.getAuthorization}).then( result => {
        if(result.data !== ''){
            this.loginSuccessful = true
            this.$store.commit('setUserData', result.data.person)
            
            this.user = this.$store.getters.userData
            if(this.user.role == "PATIENT") this.$store.commit('setUserRole', 0)
            else if (this.user.role == "CAREGIVER") this.$store.commit('setUserRole', 1)
            else if(this.user.role == "DOCTOR") this.$store.commit('setUserRole', 2)
            this.$store.commit('setAuthorization', ' ' + result.data.token)
            console.log(this.$store.getters.getAuthorization)
            this.$router.push({ name: 'Home' })
            console.log("Success: " + this.$store.getters.userData.id +" " + this.$store.getters.userData.role + " " + this.$store.getters.userRole)
        } else {
            this.loginSuccessful = false;
        }})
    },
    signup () {
        this.$router.push({ name: 'SignUp' })
    },
  }
}
</script>
