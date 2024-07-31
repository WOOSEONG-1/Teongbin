<script setup>
import AuthLayout from "@/auth/layouts/AuthLayout.vue";
import { cloneDeep } from "lodash";
import { ref, watch } from "vue";
import { useRouter } from "vue-router";
import { Modal } from "bootstrap";
import axios from "axios";
import "@/auth/assets/css/account.css";

const router = useRouter();
const signupData = ref({ email: "", name: "", password: "" });
const passwordConfirm = ref("");
const signupModal = ref();

function signup() {
  if (signupData.value.password != passwordConfirm.value) {
    return false;
  }
  const data = cloneDeep(signupData.value);
  axios
    .post("/api/v1/user/signup", data, {
      headers: { "Content-Type": "application/json" },
    })
    .then((res) => {
      const modalInstance = new Modal(signupModal.value);
      modalInstance.show();
    })
    .catch((error) => {});
}

function gotoLogin() {
  router.push("/user/login");
}
</script>

<template>
  <AuthLayout>
    <div class="account-header">
      <div>Sign Up to TeongBin Monitoring Platform</div>
    </div>
    <div class="account-main">
      <form>
        <div class="input-item">
          <input
            type="text"
            inputmode="email"
            v-model="signupData.email"
            class="box input-field"
            placeholder=""
          />
          <label class="input-label">이메일 주소</label>
        </div>
        <div class="input-item input-error">
          <input
            type="password"
            inputmode="text"
            v-model="signupData.password"
            class="box input-field"
            placeholder=""
            autoComplete="off"
          />
          <label class="input-label">비밀번호</label>
        </div>
        <div class="input-item">
          <input
            type="password"
            inputmode="text"
            v-model="passwordConfirm"
            class="box input-field"
            placeholder=""
            autoComplete="off"
            :class="{
              'input-error':
                0 < passwordConfirm.length &&
                signupData.password != passwordConfirm,
            }"
          />
          <label class="input-label">비밀번호 재입력</label>
        </div>
        <div class="input-item">
          <input
            type="text"
            inputmode="text"
            v-model="signupData.name"
            class="box input-field"
            placeholder=""
          />
          <label class="input-label">회사명</label>
        </div>
        <button type="button" @click="signup" class="box submit-btn">
          Create Account
        </button>

        <div
          class="modal fade"
          id="signupAlert"
          data-bs-backdrop="static"
          data-bs-keyboard="false"
          tabindex="-1"
          aria-labelledby="signupAlertLabel"
          aria-hidden="true"
          ref="signupModal"
        >
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h1 class="modal-title fs-5" id="signupAlertLabel">
                  회원가입 성공
                </h1>
                <button
                  type="button"
                  class="btn-close"
                  data-bs-dismiss="modal"
                  aria-label="Close"
                ></button>
              </div>
              <div class="modal-body">
                {{ signupData.email }}의 회원가입이 완료되었습니다
              </div>
              <div class="modal-body">
                확인을 눌러 로그인 페이지로 이동하세요
              </div>
              <div class="modal-footer">
                <button
                  type="button"
                  class="btn btn-primary"
                  data-bs-dismiss="modal"
                  @click="gotoLogin"
                >
                  확인
                </button>
              </div>
            </div>
          </div>
        </div>
      </form>
    </div>
  </AuthLayout>
</template>

<style scoped>
.input-error {
  color: red;
}
</style>
