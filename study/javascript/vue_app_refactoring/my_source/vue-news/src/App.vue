<template>
  <div id="app"> 
    <tool-bar></tool-bar>
    <transition name="page">
      <router-view></router-view>
    </transition>   
  </div>
</template>

<script>
import ToolBar from "./components/ToolBar.vue"

export default {
  components: {
    ToolBar
  }
}
</script>

<style>
body {
  padding: 0;
  margin: 0;
}

a {
 text-decoration: none;
 color: #34495e;
}

a:hover {
  color: #42b883;
  text-decoration: underline;
}


a.router-link-exact-active {
  text-decoration: underline;
}

/** router transition */
.page-enter-active, .page-leave-active {
  transition: opacity 0.5s ease;
}

.page-enter-from, .page-leave-to {
  opacity: 0;
}
</style>
