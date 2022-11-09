<template>
  <div>
    <section>
      <div class="user-container">
        <div><i class="fas fa-user"></i></div>
        <div class="user-description">
            <router-link :to="`/user/${item.user}`">{{ item.user }}</router-link>
            <div class="time">{{ item.time_ago }}</div>
        </div>
      </div>
      <h2>{{ item.title}}</h2>
    </section>
    <section>
      <div v-html="item.content"/>
    </section>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  computed : {
    ...mapGetters(['getItem']),
    item() {
      return this.getItem({id : this.$route.params.id})
    }
  },
  created(){
    const itemId = this.$route.params.id;
    this.$store.dispatch('FETCH_ITEM', { id : itemId });
  }

}
</script>

<style scoped>
.user-container {
  display: flex;
  align-items: center;
  padding: 0.5rem;
}
.fa-user {
  font-size: 2.3rem;
}
.user-description{
  padding-left: 8px;
}
.time {
  font-size: 0.7rem;
}
</style>