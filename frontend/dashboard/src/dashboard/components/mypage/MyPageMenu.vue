<script setup></script>

<template>
  <div class="submenu-container">
    <div class="submenu-box">
      <router-link to="/myPage/product" class="submenu-item"
        >나의 제품</router-link
      >
    </div>
    <div class="submenu-box">
      <router-link to="/myPage/pwd" class="submenu-item"
        >개인정보 수정</router-link
      >
    </div>
    <div class="submenu-box">
      <router-link to="/myPage/map" class="submenu-item">
        초기위치 설정
      </router-link>
    </div>
  </div>
</template>

<style scoped>
.submenu-container {
  display: flex;
  padding: 1rem;
}
.submenu-box {
  padding: 0 1rem;
}
.submenu-item {
  text-decoration: none;
  color: black;
}
</style>
