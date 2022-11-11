<template>
  <!-- <input type="checkbox" v-model="checked"> -->

  <input type="checkbox" :checked="value" @click="toggleCheckBox">
</template>

<script>
export default {
    // v-model 은 기본적으로 
    // 받을때는 :value 이벤트는  @input 으로 emit 함.
    props: ['value'],
    methods: {
        toggleCheckBox(){
            this.$emit('input',!this.value)
        }
    }
}
</script>

<style>

</style>