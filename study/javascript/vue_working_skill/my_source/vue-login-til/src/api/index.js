import axios from 'axios';
import setInterceptors from './common/interceptors';

// init
function createInstance() {
  const instance = axios.create({
    baseURL: process.env.VUE_APP_API_URL,
  });

  return setInterceptors(instance);
}

const instance = createInstance();

// 회원가입 API
const registerUser = userData => instance.post('signup', userData);

// 로그인 API
const loginUser = userData => instance.post('login', userData);

// 학습노트관련
const fetchNotes = () => instance.get('/posts');

export { registerUser, loginUser, fetchNotes };
