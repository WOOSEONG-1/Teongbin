<script setup>
import { useTrashcanStore } from "@/dashboard/stores/trashcan";

const trashcanStore = useTrashcanStore();
</script>

<template>
  <div class="product-container scroll-container">
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
  height: 500px;
}
.scroll-container {
  overflow-y: scroll;
  position: relative;
  margin-right: 8px;
  padding-right: 8px;
}
.scroll-container::-webkit-scrollbar {
  width: 8px;
}
.scroll-container::-webkit-scrollbar-track {
  background: transparent;
}
.scroll-container::-webkit-scrollbar-thumb {
  background: transparent;
}
.scroll-container:hover::-webkit-scrollbar-thumb {
  background: #888;
}
.scroll-container:hover::-webkit-scrollbar-thumb:hover {
  background: #555;
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
  width: 8.5rem;
  overflow: hidden;
  padding: 0 0.5rem;
  font-size: 1.2rem;
}
.trashcan-serial-number {
  font-size: 1rem;
}
</style>
