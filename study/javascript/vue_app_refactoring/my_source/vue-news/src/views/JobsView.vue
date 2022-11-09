<template>
  <div>
    <p v-for="job in jobsList" :key='job.id'>
      <a :href="job.url">
        {{ job.title }}
      </a>
      <small>{{job.time_ago}}, {{ job.domain }}</small>
    </p>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  computed : {
    ...mapGetters({
      jobsList : 'getJobsList'
    })
  },
  created() {
    this.$store.dispatch('FETCH_DATA',{'name' : 'jobs'})     
  }
}
</script>

<style>

</style>