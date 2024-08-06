import { ref } from "vue";
import { defineStore } from "pinia";

export const useMapStore = defineStore("map", () => {
  const shortcutMarkerList = ref([]);

  return { shortcutMarkerList };
});
