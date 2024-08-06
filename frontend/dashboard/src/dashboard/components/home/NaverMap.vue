<script setup>
import { ref, onMounted } from "vue";
import { useMapStore } from "@/dashboard/stores/map";
import { useShortcutStore } from "@/dashboard/stores/shortcut";

const mapStore = useMapStore();
const shortcutStore = useShortcutStore();

const map = ref();

function initMap() {
  const mapOptions = {
    center: new naver.maps.LatLng(35.20389, 126.8069),
    zoom: 16,
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
  map.value.setCenter(
    new naver.maps.LatLng(shortcut.latitude, shortcut.longitude)
  );
  map.value.setZoom(shortcut.zoom_level);
}

function moveCenter(trashcan) {
  map.value.setCenter(
    new naver.maps.LatLng(trashcan.latitude, trashcan.longitude)
  );
}

function createShortcutMarker(shortcut) {
  const position = new naver.maps.LatLng(shortcut.latitude, shortcut.longitude);
  const marker = new naver.maps.Marker({
    position: position,
    map: map.value,
    icon: {
      content: [
        `<i class="bi bi-flag-fill" style="font-size: 1.5rem;"></i>`,
      ].join(""),
      size: new naver.maps.Size(32, 32),
      origin: new naver.maps.Point(0, 0),
      anchor: new naver.maps.Point(8, 32),
    },
    draggable: false,
  });

  return marker;
}

shortcutStore.$subscribe((mutation, state) => {
  if (state.shortcutList) {
    mapStore.shortcutMarkerList.forEach((marker) => {
      marker.setMap(null);
    });
    mapStore.shortcutMarkerList = [];

    const shortcutList = mutation.events.target._value;

    shortcutList.forEach((shortcut) => {
      const marker = createShortcutMarker(shortcut);
      mapStore.shortcutMarkerList.push(marker);
    });
  }
});

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
