<template>
  <div>
    <p v-for="ask in askList" :key='ask.id'>
      <router-link :to="`/item/${ask.id}`">
        {{ ask.title }}
      </router-link>

      <small>{{ask.time_ago}} by {{ ask.user }}</small>
    </p>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  computed : {
    ...mapGetters({
      askList : 'getAskList'
    })
  },
  created() {
    this.$store.dispatch('FETCH_DATA',{'name' : 'ask'})     
  }
}
</script>

<style>

</style>