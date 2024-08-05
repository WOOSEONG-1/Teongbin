<script setup>
import { onMounted } from "vue";
import axios from "axios";
import { useTrashcanStore } from "@/dashboard/stores/trashcan";

const trashcanStore = useTrashcanStore();
function getProductList() {
  axios
    .get("/api/v1/trash/user/trashcan", {
      headers: {
        Authorization: sessionStorage.getItem("teongbinToken"),
      },
    })
    .then((res) => {
      trashcanStore.trashcanList = res.data.data;
    })
    .catch((error) => {
      console.log(error);
    });
}

onMounted(() => {
  getProductList();
});
</script>

<template>
  <div class="product-container">
    <div
      class="trashcan-info-container"
      v-for="(trashcan, idx) in trashcanStore.trashcanList"
    >
      <div class="trashcan-info horizontal-line">
        <div class="trashcan-info-label">별칭</div>
        <div class="trashcan-info-data trashcan-nickname">
          {{ trashcan.nickname }}
        </div>
      </div>
      <div class="trashcan-info horizontal-line">
        <div class="trashcan-info-label">S/N</div>
        <div class="trashcan-info-data trashcan-serial-number">
          {{ trashcan.serialNumber }}
        </div>
      </div>
      <div class="trashcan-info horizontal-line">
        <div class="trashcan-info-label">위도</div>
        <div class="trashcan-info-data trashcan-latitude">
          {{ trashcan.latitude }}
        </div>
      </div>
      <div class="trashcan-info">
        <div class="trashcan-info-label">경도</div>
        <div class="trashcan-info-data trashcan-longitude">
          {{ trashcan.longitude }}
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.product-container {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  height: min-content;
}
.trashcan-info-container {
  border: 1px solid black;
  margin: 1rem 0 0 1rem;
}
.trashcan-info {
  display: flex;
  padding: 0.5rem 0;
  align-items: center;
  margin: 0 0.5rem;
}
.horizontal-line {
  border-bottom: 1px double lightgrey;
}
.trashcan-info-label {
  width: 4rem;
  justify-content: center;
  border-right: 1px double silver;
}
.trashcan-info-data {
  width: 9.5rem;
}
.trashcan-serial-number {
  font-size: 1rem;
}
</style>
