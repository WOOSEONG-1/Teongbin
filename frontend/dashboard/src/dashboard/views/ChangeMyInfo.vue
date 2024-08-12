<script setup>
import { onMounted, ref, watch } from "vue";
import MyPageLayout from "@/dashboard/layouts/MyPageLayout.vue";
import { useUserStore } from "@/dashboard/stores/user";
import { changeUserInfo, getUserInfo } from "@/dashboard/js/remote";

const userStore = useUserStore();
const username = ref();
const password = ref();
const checkPassword = ref();
 
watch(
  () => userStore.userName,
  (newUsername) => {
    username.value = newUsername;
  }
);

onMounted(() => {
  username.value = userStore.userInfo.name;
})

getUserInfo();

function changeInfo() {
  if(checkPassword.value == password.value) {
    changeUserInfo(username.value, password.value);
  }
}
</script>

<template>
  <MyPageLayout>
    <div class="pwd-notice">
      ※ 비밀번호는 8~20자의 영문, 숫자 및 특수문자로 설정해주세요.
    </div>
    <div class="pwd-table">
      <form name="changeInfo">
        <table>
          <tbody>
            <tr>
              <th>사명</th>
              <td><input type="text" v-model="username" /></td>
            </tr>
            <tr>
              <th>신규 비밀번호</th>
              <td><input type="password" v-model="password" autocomplete="off"/></td>
            </tr>
            <tr>
              <th>신규 비밀번호 확인</th>
              <td><input type="password" v-model="checkPassword" autocomplete="off"/></td>
            </tr>
          </tbody>
        </table>
        <button class="post-button" @click="changeInfo">변경</button>
      </form>
    </div>
  </MyPageLayout>
</template>

<style scoped>
.pwd-notice {
  padding: 3rem 0 0.5rem 2rem;
  font-size: 1rem;
  text-align: left;
}
.pwd-form {
  margin: 1rem;
}
th {
  padding: 1rem;
  width: 20rem;
  border-top: 1px solid black;
  border-bottom: 1px solid black;
  text-align: left;
}
td {
  padding: 1rem;
  width: 40rem;
  border-top: 1px solid black;
  border-bottom: 1px solid black;
}
input {
  width: 100%;
  height: 3rem;
  padding-left: 0.5rem;
}
.input-container {
  display: flex;
}
.post-button {
  padding: 1rem;
}
button {
  background: none;
  border: none;
}
</style>