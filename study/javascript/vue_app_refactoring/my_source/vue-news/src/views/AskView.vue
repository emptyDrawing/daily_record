<template>
  <div>
    <p v-for="ask in askList" :key='ask.id'>
      <a :href="ask.url">
        {{ ask.title }}
      </a>
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