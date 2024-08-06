<script setup>
import { ref, onMounted } from "vue";
import { useMapStore } from "@/dashboard/stores/map";
import { useShortcutStore } from "@/dashboard/stores/shortcut";
import { useTrashcanStore } from "@/dashboard/stores/trashcan";

const mapStore = useMapStore();
const shortcutStore = useShortcutStore();
const trashcanStore = useTrashcanStore();

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

function createShortcutMarker(shortcut, color) {
  const position = new naver.maps.LatLng(shortcut.latitude, shortcut.longitude);
  const marker = new naver.maps.Marker({
    position: position,
    map: map.value,
    title: shortcut.nickname,
    icon: {
      content: [
        `<i class="bi bi-flag-fill" style="font-size: 1.5rem; color: rgb(${color.red}, ${color.green}, ${color.blue});"></i>`,
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

    shortcutList.forEach((shortcut, idx) => {
      const marker = createShortcutMarker(
        shortcut,
        shortcutStore.colorList.at(idx)
      );
      mapStore.shortcutMarkerList.push(marker);
    });
  }
});

function createTrashcanMarker(trashcan) {
  const restPercent = trashcan.restPercent;
  let rest;
  if (restPercent >= 70) {
    rest = "color: red";
  } else if (restPercent >= 30) {
    rest = "color: yellow";
  } else {
    rest = "color: greenyellow";
  }

  const position = new naver.maps.LatLng(trashcan.latitude, trashcan.longitude);
  const marker = new naver.maps.Marker({
    position: position,
    map: map.value,
    title: trashcan.nickname,
    icon: {
      content: [
        `<i class="bi bi-trash2-fill" style="font-size: 2rem; ${rest};"></i>`,
      ].join(""),
      size: new naver.maps.Size(32, 32),
      origin: new naver.maps.Point(0, 0),
      anchor: new naver.maps.Point(16, 32),
    },
    draggable: false,
  });

  return marker;
}

trashcanStore.$subscribe((mutation, state) => {
  if (state.trashcanList) {
    mapStore.trashcanMarkerList.forEach((marker) => {
      marker.setMap(null);
    });
    mapStore.trashcanMarkerList = [];

    const trashcanList = mutation.events.target._value;

    trashcanList.forEach((trashcan) => {
      const marker = createTrashcanMarker(trashcan);

      mapStore.trashcanMarkerList.push(marker);
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
