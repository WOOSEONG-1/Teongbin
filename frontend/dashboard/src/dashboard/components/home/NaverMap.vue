<script setup>
import { ref, onMounted } from "vue";

const map = ref();

function initMap() {
  const mapOptions = {
    center: new naver.maps.LatLng(35.20389, 126.8069),
    zoom: 15,
  };

  map.value = new naver.maps.Map("map", mapOptions);
}

onMounted(() => {
  initMap();
});

function getSetting() {
  return {
    center: map.value.getCenter(),
    zoom: map.value.getZoom(),
  };
}

function changeSetting(shortcut) {
  map.value.setCenter(new naver.maps.LatLng(
    shortcut.latitude,
    shortcut.longitude
  ));
  map.value.setZoom(shortcut.zoom_level);
}

function moveCenter(trashcan) {
  map.value.setCenter(
    new naver.maps.LatLng(trashcan.latitude, trashcan.longitude)
  );
}

defineExpose({ getSetting, changeSetting, moveCenter });
</script>

<template>
  <section class="map-section">
    <div id="map" class="map-size"></div>
  </section>
</template>

<style scoped>
.map-section {
  padding: 40px;
  float: left;
  padding-right: 1rem;
}
.map-size {
  width: 860px;
  height: 500px;
}
</style>
