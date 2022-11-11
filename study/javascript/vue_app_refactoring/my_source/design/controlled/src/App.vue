<template>
  <div id="app">
    <Test-check-box v-model="checked"></Test-check-box>
  </div>
</template>

<script>
import TestCheckBox from "./components/TestCheckBox.vue"

export default {
  components: {
    TestCheckBox
  },
  data(){
    return {
      checked: false,
    }
  }
}
</script>

<style>

</style>
