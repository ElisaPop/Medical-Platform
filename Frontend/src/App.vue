<template>
  <div id="app">
    <v-app id="inspire">
      <div>
        <v-tabs
          v-model="tab"
          background-color="#1d3557"
          class="elevation-2"
          dark
          :centered="centered"
          :grow="grow"
          :vertical="vertical"
          :right="right"
          :prev-icon="prevIcon ? 'mdi-arrow-left-bold-box-outline' : undefined"
          :next-icon="nextIcon ? 'mdi-arrow-right-bold-box-outline' : undefined"
          :icons-and-text="icons"
        >
          <h1> lifeline </h1>

          <v-tab v-if="this.$store.getters.userRole >= -1" :to="'/'"  style="color:#a8dadc">
            Home
          </v-tab>

          <v-tab v-if="this.$store.getters.userRole == 2" :to="'/patients'"  style="color:#a8dadc">
            Search
          </v-tab>

          <v-tab v-if="this.$store.getters.userRole == 0" :to="'/medicationplans/' + this.$store.getters.userData.id"  style="color:#a8dadc">
            Medication Plan
          </v-tab>

          <v-tab v-if="this.$store.getters.userRole >= 0" :to="'/profile'" style="color:#a8dadc">
            Profile
          </v-tab>

          <v-tab v-if="this.$store.getters.userRole == 1" :to="'/patients'" style="color:#a8dadc">
            Patients
          </v-tab>

          <v-tab v-if="this.$store.getters.userRole < 0" :to="'/login'"  style="color:#a8dadc">
            Log in
          </v-tab>

          <v-tab v-if="this.$store.getters.userRole >= 0" style="color:#a8dadc" @click="logout()">
            Log out
          </v-tab>

        </v-tabs>
        <router-view></router-view>
      </div>
    </v-app>
  </div>
</template>

<script>
import * as SockJS from 'sockjs-client'
import * as Stomp from 'stompjs'
export default {
     data () {
        return {
          tab: null,
          icons: false,
          centered: false,
          grow: false,
          vertical: false,
          prevIcon: false,
          nextIcon: false,
          right: false,
          tabs: 5
        }
      },
      watch:{
        "$store.getters.userRole"(role){
          if(role > 0){
            this.isEdit = true;
              this.$http.get(process.env.VUE_APP_BACKEND_API + "/person/" + this.$store.getters.userData.id, {headers: this.$store.getters.getAuthorization}).then( result => {
                  this.user = result.data;
              })
              this.topic = '/queue/distributor.' + this.$store.getters.userData.id
              const ws = new SockJS(process.env.VUE_APP_BACKEND_API + '/ws')
              this.stompClient = Stomp.over(ws)
              const _this = this
              _this.stompClient.connect({}, function () {
                  
                  _this.stompClient.subscribe(_this.topic, function (event) {
                      alert(event.body)
                  })
              })
              console.log("Connected " + this.$store.getters.userData.id)
        }
          else {
              this.$route.params.id = -1;
              this.isEdit = false;
          }
        }
    },
      methods: {
        logout(){
          if(this.$store.getters.role > 0){
            this.stompClient.disconnect();
          }
          this.$store.commit('setUserData',null);
          this.$store.commit('setUserRole',-1);
          this.$router.push({ name: 'LogIn' })
        }
      }
    }
</script>

<style scoped>
div{
  color:#a8dadc
}

h1{
  color:#a8dadc
}
</style>
