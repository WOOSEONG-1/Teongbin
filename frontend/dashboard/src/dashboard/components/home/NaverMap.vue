<script setup>
import { onMounted } from "vue";

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
