<template>
  <div>
    <User-profile :info='user'>
      <div slot='username'>{{user.id}}</div>
      <!-- template slot 은 태그없이 텍스트만 들어감 -->
      <span slot='time'>{{ 'Joined ' + user.created}}, </span>
      <span slot='karma'>{{user.karma}}</span>
    </User-profile>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import UserProfile from '../components/UserProfile.vue'

export default {
  computed : {
    ...mapGetters([
      'getUserInfo'
    ]),
    user() {
      const userName = this.$route.params.id ?? '';
      return this.getUserInfo({ id : userName });
    }
  },
  created() {
    const userName = this.$route.params.id ?? '';
    if(userName) {
      this.$store.dispatch('FETCH_USER', {name: userName})
    }
  },
  components: {
    UserProfile
  }
}
</script>

<style>

</style>