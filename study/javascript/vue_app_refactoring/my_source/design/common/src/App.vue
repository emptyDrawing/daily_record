<template>
  <div id="app">
    <App-header :title="appTitle"></App-header>
    <App-content :items="items" @renew="renewItems"></App-content>
  </div>
</template>

<script>
import AppContent from './components/AppContent.vue'
import AppHeader from './components/AppHeader.vue'

export default {
  components: {
    AppHeader, AppContent
  },
  data() {
    return {
      appTitle: 'Common Approach',
      items: [10, 20, 30]
    }
  },
  methods : {
    renewItems() {
      this.items = [ 40, 50, 60];
    }
  }
}
</script>

<style>

</style>
