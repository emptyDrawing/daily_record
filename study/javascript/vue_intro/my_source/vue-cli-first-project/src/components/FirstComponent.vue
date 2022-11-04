<template>
  <header>
    <h1 id="first">{{ message }}</h1>
    <button v-on:click="addNum">upCount</button>
  </header>
</template>

<script>
export default {
  // 인스턴스 옵션을 넣는데
  props : ['message'],
  methods: {
    addNum() { 
         console.log("addNun", this);
         return this.$emit("sendNum", 1);
      }
  }
}
</script>

<style>

</style>