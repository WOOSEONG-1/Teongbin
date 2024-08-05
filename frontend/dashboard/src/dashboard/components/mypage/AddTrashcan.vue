<script setup>
import { ref } from "vue";
import { cloneDeep } from "lodash";
import { useTrashcanStore } from "@/dashboard/stores/trashcan";
import axios from "axios";

const trashcanStore = useTrashcanStore();

const trashcanData = ref({
  serialNumber: "",
  latitude: 0,
  longitude: 0,
  nickname: "",
});

function postNewTrashcan(data) {
  axios
    .post("/api/v1/trash/new", data, {
      headers: {
        Authorization: sessionStorage.getItem("teongbinToken")
      }
    })
    .then((res) => {
      console.log(res);
    })
    .catch((error) => {
      console.log(error);
    });
}

function addTrashcan() {
  const data = cloneDeep(trashcanData.value);

  postNewTrashcan(data);

  resetInput();
}

function resetInput() {
  trashcanData.value.serialNumber = "";
  trashcanData.value.nickname = "";
  trashcanData.value.latitude = 0;
  trashcanData.value.longitude = 0;
}
</script>

<template>
  <button
    type="button"
    class="btn"
    data-bs-toggle="modal"
    data-bs-target="#addProduct"
  >
    제품 등록
  </button>

  <div
    class="modal fade"
    id="addProduct"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    tabindex="-1"
    aria-labelledby="staticBackdropLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="staticBackdropLabel">
            제품 및 설치 정보
          </h1>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <div class="input-group mb-3">
            <span class="input-group-text info-input" id="serial-number-label"
              >S/N</span
            >
            <input
              type="text"
              class="form-control"
              v-model="trashcanData.serialNumber"
              aria-describedby="basic-addon1"
            />
          </div>
          <div class="input-group mb-3">
            <span class="input-group-text info-input" id="nickname-label"
              >별칭</span
            >
            <input
              type="text"
              class="form-control"
              v-model="trashcanData.nickname"
              aria-describedby="basic-addon1"
            />
          </div>
          <div class="input-group mb-3">
            <span class="input-group-text info-input" id="latitude-label"
              >위도</span
            >
            <input
              type="text"
              class="form-control"
              v-model="trashcanData.latitude"
              aria-describedby="basic-addon1"
            />
            <span class="input-group-text info-input" id="longitude-label"
              >경도</span
            >
            <input
              type="text"
              class="form-control"
              v-model="trashcanData.longitude"
              aria-describedby="basic-addon1"
            />
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn" data-bs-dismiss="modal">
            취소
          </button>
          <button
            type="button"
            class="btn"
            data-bs-dismiss="modal"
            @click="addTrashcan"
          >
            등록
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.info-input {
  width: 4rem;
  justify-content: center;
}
</style>
