<template>
  <div class="home">
    <h1 v-if="this.$store.getters.userRole >= 0">
        Hello, {{this.$store.getters.userData.name}}
    </h1>
  </div>
</template>

<script>
export default {
  name: 'Home',
  data () {
        return {
          tab: 0
        }
    }
}
</script>
