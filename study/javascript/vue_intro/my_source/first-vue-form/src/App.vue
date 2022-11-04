<template>
  <div>
    <h1>Hi</h1>
    <form action="" v-on:submit.prevent>
      <div>
        <label for="username">id :</label>
        <input id="username" type="text" v-model="username">
      </div>
      <div>
        <label for="userpw">pw : </label>
        <input id="userpw" type="password" v-model="userpw">
      </div>
      <button v-on:click="sendInfo">Login</button>

    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      username : "",
      userpw : ""
    }
  },
  
  methods : {  
    sendInfo() {
      var url = "https://jsonplaceholder.typicode.com/users";
      var data = {
        "username": this.username ,
        "password": this.userpw
      }

      axios.post( url, data )
        .then( (resp) => console.log(resp) )
        .catch( (err) => console.log(err) )
    }
  }
}
</script>

<style>

</style>