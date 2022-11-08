<template>
  <div id = "demo">
    <p>{{ this.price }}</p>
    <p>{{ this.price2 }}</p>
    <p>{{ multiplePrice(3) }}</p>
  </div>
</template>

<script>

import { mapGetters } from 'vuex';

export default {

    computed : {
        ...mapGetters({
            'price' : 'getPrice'
        }),
        ...mapGetters({
            'price2' : 'getDoublePrice'
        }),
    }
    ,methods : {
        multiplePrice(n){
            return typeof n === 'number' ?  this.price * n : "wrong Number";
        },
    }
}
</script>

<style>

</style>