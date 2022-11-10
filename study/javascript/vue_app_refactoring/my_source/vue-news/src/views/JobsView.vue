<template>
  <div>
    <Common-list></Common-list>
  </div>
</template>

<script>
import CommonList from '../components/CommonList.vue'
import ListMixin from '../mixins/ListMixin.js'

export default {
    components: {
        CommonList,
    },
    mixins: [ ListMixin ]
}
</script>

<style>

</style>