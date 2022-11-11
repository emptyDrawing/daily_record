<template>
  <div id="app">
    <fetch-data url="https://jsonplaceholder.typicode.com/users">
      <!-- ... ---> 
      <div slot-scope="{ response, loading }">
        <div v-if="!loading">
          {{ response }}
        </div>
        <div v-else>
          Loading...
        </div>
      </div>

    </fetch-data>
  </div>
</template>

<script>
import FetchData from './components/FetchData.vue'

export default {
  name : 'app',
  components: {
    FetchData
  }
}
</script>

<style>

</style>
