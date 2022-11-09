<template>
  <div>
    <p v-for="news in newsList" :key='news.id'>
      <a :href="news.url">
        {{ news.title }}
      </a>
      <small>{{news.time_ago}} by 
        <router-link :to="`/user/${news.user}`">{{ news.user }}</router-link>
      </small>
    </p>
  </div>
</template>

<script>

import { mapGetters } from 'vuex';

export default {
  computed : {
    // ...mapState({
    //   news : (state, getters) => state.data.newsList
    // })
    ...mapGetters({
      newsList : 'getNewsList'
    })
  },

  created() {
    this.$store.dispatch('FETCH_DATA',{'name' : 'news'})     
  }
}
</script>

<style>

</style>