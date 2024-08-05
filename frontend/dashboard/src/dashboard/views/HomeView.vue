<script setup>
import { ref } from "vue";
import DashboardLayout from "@/dashboard/layouts/DashboardLayout.vue";
import Shortcut from "@/dashboard/components/home/Shortcut.vue";
import NaverMap from "@/dashboard/components/home/NaverMap.vue";

import { getTrashcanList, getTrashcanRest, getUserInfo } from "@/dashboard/js/remote";

const $mapRef = ref();
const center = ref();

function getSetting() {
  center.value = $mapRef.value.getSetting();
}

function changeSetting(setting) {
  $mapRef.value.changeSetting(setting);
}

getUserInfo(false);

async function getTrashcan() {
  const success = await getTrashcanList(false);
  if(success) {
    getTrashcanRest();
    setInterval(() => getTrashcanRest(), 600000);
  }
}
getTrashcan();
</script>

<template>
  <DashboardLayout>
    <Shortcut
      @get-setting="getSetting"
      @changeSetting="changeSetting"
      :shortcut="center"
    />
    <div class="info-container">
      <NaverMap ref="$mapRef" />
    </div>
  </DashboardLayout>
</template>

<style scoped>
.info-container {
  display: flex;
  padding: 1rem;
}
</style>