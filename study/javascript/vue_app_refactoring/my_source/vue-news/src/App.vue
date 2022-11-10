<template>
  <div id="app"> 
    <tool-bar></tool-bar>
    <transition name="page">
      <router-view></router-view>
    </transition>
    <Load-spinner :loading="loadingStatus"></Load-spinner>
  </div>
</template>

<script>
import ToolBar from "./components/ToolBar.vue"
import LoadSpinner from "./components/LoadSpinner.vue"
import bus from "./utils/bus.js"


export default {
  components: {
    ToolBar, LoadSpinner,
  },
  data(){
    return {
      loadingStatus : false,
    }
  },
  methods: {
    startSpinner() {
      this.loadingStatus = true;
    },
    endSpinner() {
      this.loadingStatus = false;
    }
  },
  created() {
    bus.$on('start:spinner', this.startSpinner);
    bus.$on('end:spinner', this.endSpinner);
  },
  // 컴포넌트가 사라질때 off 를 해서 이벤트 객체를 삭제시킴.
  beforeDestroy() {
    bus.$off('start:spinner', this.startSpinner);
    bus.$off('end:spinner', this.endSpinner);
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
