<template>
  <h2>로그인 테스트</h2>
  <!-- 로그인 안 되었을 때 화면 -->
  <div v-if="!isLoggedIn">
    <form @submit.prevent="handleLogin">
      <div>
        username :
        <input type="text" v-model="loginForm.username" />
      </div>
      <div>
        password :
        <input type="password" v-model="loginForm.password" />
      </div>
      <div>
        <button type="submit">로그인</button>
      </div>
    </form>
  </div>

  <!-- 로그인 되었을 때 화면 -->
  <div v-else>
    <h1>환영합니다.</h1>
    <button @click="handleLogout">로그아웃</button>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

//로그인 상태 반응형 데이터
const isLoggedIn = ref(false);
// 사용자 정보 반응형 데이터
const userInfo = ref({});
const temp = false;

//로그인 입력 상태 반응형 데이터(객체)
const loginForm = ref({
  username: '',
  password: '',
});

//로그인 처리 함수
const handleLogin = async () => {
  try {
    const response = await axios.post('/api/auth/login', loginForm.value);
    console.log('response.data : ', response.data);

    const { totken, user } = response.data;

    localStorage.setItem('authToken');
  } catch (e) {
    console.error(e);
  }
};
const handleLogout = async () => {
  localStorage.
  isLoggedIn.value = false;
};
// // 로그인 상태 확인 함수
const checkLoginStatus = () => {
  const token = localStorage.getItem('authToken');
  const savedUserInfo = localStorage.getItem('userInfo');

  if (token && savedUserInfo) {
    isLoggedIn.value = true;
    userInfo.value = JSON.parse(savedUserInfo);
  } else {
    isLoggedIn.value = false;
    userInfo.value = {};
  }
};

// // 컴포넌트 마운트 시 로그인 상태 확인
onMounted(() => {
  checkLoginStatus();
});
</script>
