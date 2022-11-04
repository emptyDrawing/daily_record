<template>
  <!-- 최상위 DOM 하나만 있어야됨.-->
  <div>
    <first-component v-bind:message="str" v-on:sendNum="test"></first-component>
    <div>Emit : {{ count }}</div>
  </div>
</template>

<script>
import FirstComponent from "./components/FirstComponent.vue"

export default {
  data() {
    return {
      str : 'hi',
      count : 0
    }
  },
  components : {
    'first-component': FirstComponent
  },
  methods : {
    'test'(sendNum) {
      return this.count += sendNum;
    }
  }
}
</script>

<style>

</style>