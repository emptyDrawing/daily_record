<template>
  <div>
    <ul class="news-list">
      <li v-for="item in itemList" :key='item.id' class="post">
        <!-- 포인트영역 -->
        <div class="points">
          {{item.points || 0}}
        </div>
        <!-- 기타정보영역 -->
        <div>
          <!-- 타이틀 영역 -->
          <p class="news-title">
            <template v-if="item.domain">
              <a :href="item.url">{{item.title}}</a>
            </template>  
            <template v-else>
              <router-link :to="`/item/${item.id}`">{{item.title}}</router-link>
            </template>
          </p>
          <small class="link-text"> 
            <router-link 
              v-if= "item.user"
              :to="`/user/${item.user}`" 
              class="link-text"
            >
              by {{ item.user }}
            </router-link>
            <a v-else :href="item.url" >by {{item.domain || 'anonymous'}}</a>
          </small>
        </div>
     </li>
    </ul>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {

  computed : {
    ...mapGetters({
        getList : 'getAPIList'
    }),
    itemList(){
        const routerName = this.$route.name ?? '';
        return this.getList({name : routerName});
    }
  },
}
</script>

<style scoped>
.news-list {
  margin: 0;
  padding: 0;
}

.post {
    list-style: none;
    display: flex;
    align-items: center;
    border-bottom: 1px solid #eee;
}

.points{
  width: 80px;
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #42b883;
}
.news-title{
  margin: 0;
}
.link-text{
  color : #828282;
}
</style>