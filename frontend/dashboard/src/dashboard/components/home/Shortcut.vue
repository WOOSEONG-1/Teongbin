<script setup>
import { ref, watch } from "vue";
import { useShortcutStore } from "@/dashboard/stores/shortcut";
import { addShortcut, getShortcutList } from "@/dashboard/js/remote";

const shortcutStore = useShortcutStore();

const shortcutInputVisible = ref(false);
const menuText = ref("숏컷등록");
const shortcutName = ref("");
const props = defineProps({
  shortcut: Object,
});

watch(
  () => props.shortcut,
  async (setting) => {
    if (setting) {
      const shortcut = {
        nickname: shortcutName.value,
        latitude: setting.center._lat,
        longitude: setting.center._lng,
        zoom_level: setting.zoom,
      };

      toggleInput();
      shortcutName.value = "";
      const success = await addShortcut(shortcut);
      if (success) getShortcutList();
    }
  }
);

function toggleInput() {
  shortcutInputVisible.value = !shortcutInputVisible.value;
  if (shortcutInputVisible.value) {
    menuText.value = "등록취소";
  } else {
    menuText.value = "숏컷등록";
  }
}

function colorStyle(idx) {
  const color = shortcutStore.colorList.at(idx);
  return {
    color: `rgb(${color.red}, ${color.green}, ${color.blue})`,
  };
}

getShortcutList();

function deleteShortcut(idx) {
  shortcutStore.shortcutList.splice(idx, 1);
}
</script>

<template>
  <div class="shortcut-section">
    <div class="shortcut-container scroll-container">
      <button
        class="shortcut-item shortcut-btn"
        v-for="(shortcut, idx) in shortcutStore.shortcutList"
        :key="idx"
        @click="$emit('changeSetting', shortcut)"
      >
        <div :style="colorStyle(idx)">
          {{ shortcut.nickname }}
        </div>
      </button>
    </div>
    <div class="shortcut-menu-container">
      <button @click="toggleInput" class="shortcut-menu-item shortcut-btn">
        {{ menuText }}
      </button>
      <div v-if="shortcutInputVisible" class="input-group mb-3 popup">
        <input
          type="text"
          class="form-control"
          v-model="shortcutName"
          placeholder="nickname"
          aria-label="nickname"
          aria-describedby="button-addon2"
        />
        <button
          class="btn btn-outline-secondary"
          type="button"
          id="button-addon2"
          :disabled="shortcutName == ''"
          @click="$emit('getSetting')"
        >
          등록
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.shortcut-section {
  display: flex;
  justify-content: space-between;
  align-items: center;

  padding: 1rem;
}
.shortcut-container {
  display: flex;
  align-items: center;
  margin: 1rem;
  width: 860px;
  position: relative;
}
.shortcut-item {
  float: left;
  justify-content: center;
  padding: 0;
  margin-right: 1rem;
  max-width: 8rem;
  font-size: 1.2rem;
  min-width: 8rem;
  white-space: nowrap;
  align-items: center;
}
.shortcut-btn {
  border: none;
  background: none;
}
.shortcut-menu-container {
  position: relative;
  display: inline-block;
  justify-content: center;
  align-items: center;
  width: 20%;
}
.shortcut-menu-item {
  padding: 1rem 0;
}
.popup {
  position: absolute;
  top: 100%;
  left: 0;
  border: 1px solid #ccc;
  background-color: #fff;
  padding: 10px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  z-index: 10;
}

.scroll-container {
  overflow-x: scroll;
  position: relative;
  padding-bottom: 6px;
  margin-bottom: 6px;
}

.scroll-container:hover {
}

.scroll-container::-webkit-scrollbar {
  height: 4px;
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
</style>
