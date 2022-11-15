<template>
  <div class="contents">
    <div class="form-wrapper form-wrapper-sm">
      <form @submit.prevent="loginTry" class="form">
        <div>
          <label for="username">id: </label>
          <input id="username" type="text" v-model="username" />
          <p class="validation-text">
            <span class="warning" v-if="!isUsernameValid && username">
              Please enter an email address
            </span>
          </p>
        </div>
        <div>
          <label for="password">pw: </label>
          <input id="password" type="text" v-model="password" />
        </div>
        <button
          :disabled="!(isUsernameValid && password)"
          type="submit"
          class="btn"
        >
          로그인
        </button>
      </form>
      <p class="log">{{ logMessage }}</p>
    </div>
  </div>
</template>

<script>
import { loginUser } from '@/api/index';
import { validateEmail } from '@/utils/validation';

export default {
  data() {
    return {
      username: '',
      password: '',
      logMessage: '',
    };
  },
  computed: {
    isUsernameValid() {
      return validateEmail(this.username);
    },
  },
  methods: {
    async loginTry() {
      try {
        const userData = {
          username: this.username,
          password: this.password,
        };

        const { data } = await loginUser(userData);
        // this.logMessage = `${data.user?.username}님 환영합니다.`;
        // 로그인이 되면 페이지 이동
        this.$router.push('/main');
        this.$store.commit('setUsername', { username: data.user.username });
      } catch ({ response }) {
        this.logMessage = response.data;
      } finally {
        this.initForm();
      }
    },
    initForm() {
      this.username = '';
      this.password = '';
    },
  },
};
</script>

<style>
.btn {
  color: white;
}
</style>
