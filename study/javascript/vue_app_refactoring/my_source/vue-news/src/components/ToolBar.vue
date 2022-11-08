<template>
  <div class="menu-header">
    <router-link to="/news">News</router-link> | 
    <router-link to="/ask">Ask</router-link> |
    <router-link to="/jobs">Jobs</router-link>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
.menu-header {
    color: white;
    background-color: #42b883;
    display: flex;
    padding: 8px;
}

.menu-header .router-link-exact-active {
    color: #35495e;
    font-weight: 800;
}

.menu-header a{
    color : white;
}

</style>