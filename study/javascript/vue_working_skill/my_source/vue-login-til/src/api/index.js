import axios from 'axios';

const instance = axios.create({
  baseURL: process.env.VUE_APP_API_URL,
});

const registerUser = userData => instance.post('signup', userData);

const loginUser = userData => instance.post('login', userData);

export { registerUser, loginUser };
