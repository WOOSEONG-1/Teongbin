import { ref } from "vue";
import { defineStore } from "pinia";

export const useMapStore = defineStore("map", () => {
  const shortcutMarkerList = ref([]);
  const trashcanMarkerList = ref([]);
  const bounds = ref();

  return { shortcutMarkerList, trashcanMarkerList, bounds };
});
