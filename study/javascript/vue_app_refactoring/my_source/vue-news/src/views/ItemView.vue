<template>
  <div>
    <section>
      <User-profile :info="item">
          <router-link slot="username" :to="`/user/${item.user}`">
            {{ item.user }}
          </router-link>
          <template slot="time">{{ 'Posted ' + item.time_ago }}</template>
      </User-profile>
    </section>
    <section>
      <h2>{{ item.title}}</h2>
    </section>
    <section>
      <div v-html="item.content"/>
    </section>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import UserProfile from '../components/UserProfile.vue'

export default {
  components: {
    UserProfile
  }
  ,computed : {
    ...mapGetters(['getItem']),
    item() {
      return this.getItem({id : this.$route.params.id})
    }
  },
  created(){
    const itemId = this.$route.params.id;
    this.$store.dispatch('FETCH_ITEM', { id : itemId });
  }

}
</script>

<style scoped>

</style>
