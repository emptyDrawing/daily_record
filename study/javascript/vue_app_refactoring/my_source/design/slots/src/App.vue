<template>
  <div id="app">
    <ul>
      <ItemSlot>아이템 1</ItemSlot>
      <ItemSlot>아이템 2 <button>슬롯장점</button> </ItemSlot>
      <ItemSlot>아이템 3
        <div></div>
        <img src="./assets/logo.png" width="100px"/>
      </ItemSlot>
      <ItemSlot>아이템 4</ItemSlot>
      <ItemSlot>아이템 5</ItemSlot>
    </ul>
  </div>
</template>

<script>
import ItemSlot from './ItemSlot.vue'

export default {
  components: {
    ItemSlot,
  },
}
</script>

<style>

</style>
