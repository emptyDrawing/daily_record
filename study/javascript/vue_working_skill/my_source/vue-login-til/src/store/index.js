import Vue from 'vue';
import Vuex from 'vuex';
import {
  getAuthFromCookie,
  getUserFromCookie,
  saveAuthToCookie,
  saveUserToCookie,
} from '@/utils/cookies';

import { loginUser } from '@/api/index';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    username: getUserFromCookie() || '',
    token: getAuthFromCookie() || '',
  },
  getters: {
    isLogin(state) {
      return state.username !== '';
    },
  },
  mutations: {
    setToken(state, { token }) {
      state.token = token;
    },
    setUsername(state, { username }) {
      state.username = username;
    },
    clearUsername(state) {
      state.username = '';
    },
  },
  actions: {
    async LOGIN({ commit }, { userData }) {
      const { data } = await loginUser(userData);

      commit('setToken', { token: data.token });
      commit('setUsername', { username: data.user.username });
      // 쿠키저장
      saveAuthToCookie(data.token);
      saveUserToCookie(data.user.username);

      return data;
    },
  },
});
