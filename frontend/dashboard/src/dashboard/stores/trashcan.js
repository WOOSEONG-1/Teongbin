import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useTrashcanStore = defineStore('trashcan', () => {
  const trashcanList = ref([]);

  return { trashcanList }
})
