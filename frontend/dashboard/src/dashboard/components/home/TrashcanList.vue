<script setup>
import { useTrashcanStore } from "@/dashboard/stores/trashcan";

const trashcanStore = useTrashcanStore();
</script>

<template>
  <section class="product-state-section">
    <div class="product-state-container">
      <button
        v-for="(trashcan, idx) in trashcanStore.trashcanList"
        :key="idx"
        class="product-state-item"
        @click="$emit('moveCenter', trashcan)"
      >
        <div class="text-info-container">
          <div class="trashcan-text-info nickname">{{ trashcan.nickname }}</div>
          <div class="trashcan-text-info">{{ trashcan.restPercent }}</div>
        </div>
        <div>
          <i
            class="bi bi-trash2-fill trashcan-img"
            :class="{
              'trashcan-lack': trashcan.restPerceont >= 70,
              'trashcan-half': trashcan.restPercent >= 30,
              'trashcan-enough': trashcan.restPercent < 30,
            }"
          ></i>
        </div>
      </button>
    </div>
  </section>
</template>

<style scoped>
.product-state-section {
  width: 100%;
}
.product-state-container {
  height: 500px;
  overflow-y: scroll;
  scrollbar-width: none;
  ::-webkit-scrollbar {
    display: none;
    width: 0px;
  }
}
.product-state-item {
  display: flex;
  margin: 1rem;
  margin-bottom: 0;
}
button {
  background: none;
  border: none;
}
.text-info-container {
  align-content: center;
}
.trashcan-text-info {
  width: auto;
  min-width: 6rem;
  max-width: 6rem;
  white-space: nowrap;
  align-items: center;
  overflow-x: scroll;
  margin-right: 1rem;
  height: 2rem;
  display: flex;
  align-items: center;
  justify-content: center;
}
.nickname {
  font-size: 1rem;
}
.trashcan-img {
  font-size: 3rem;
}
.trashcan-enough {
  color: greenyellow;
}
.trashcan-half {
  color: yellow;
}
.trashcan-lack {
  color: red;
}
</style>