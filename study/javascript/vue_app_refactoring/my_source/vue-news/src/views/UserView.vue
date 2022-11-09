<template>
  <div>
    <p>userName : {{user.id}}</p>
    <p>karma : {{user.karma}}</p>
    <p>created: {{user.created}}</p>
    <p v-if="user.about"> about : {{user.about}} </p>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';


export default {
  computed : {
    ...mapGetters([
      'getUserInfo'
    ])
    ,user() {
      const userName = this.$route.params.id ?? '';
      return this.getUserInfo({ id : userName });
    }
  }
  ,created() {
    const userName = this.$route.params.id ?? '';
    if(userName) {
      this.$store.dispatch('FETCH_USER', {name: userName})
    }
  }

}
</script>

<style>

</style>