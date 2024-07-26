<script setup>
import { ref, watch } from "vue";
import { useShortcutStore } from "@/dashboard/stores/shortcut";

const shortcutStore = useShortcutStore();

const shortcutInputVisible = ref(false);
const menuText = ref("숏컷등록");
const shortcutName = ref("");
const props = defineProps({
  shortcut: Object,
});

watch(
  () => props.shortcut,
  (setting) => {
    if (setting) {
      console.log(setting);
      const shortcut = {
        name: shortcutName.value,
        setting: setting,
      };

      toggleInput();
      shortcutName.value = "";
      addShortcut(shortcut);
      //postNewShortcut(shortcut);
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

function addShortcut(setting) {
  shortcutStore.shortcutList.push(setting);
}

function postShortcut() {}

function deleteShortcut(idx) {
  shortcutStore.shortcutList.splice(idx, 1);
}
</script>

<template>
  <div class="shortcut-section">
    <div class="shortcut-container">
      <button
        class="shortcut-item shortcut-btn"
        v-for="(shortcut, idx) in shortcutStore.shortcutList"
        :key="idx"
        @click="$emit('changeSetting', shortcut.setting)"
      >
        {{ shortcut.name }}
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
  padding-bottom: 0;
}
.shortcut-container {
  display: inline-block;
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
  font-size: 1rem;
  width: 8rem;
  white-space: nowrap;
  align-items: center;
  overflow-x: scroll;
  scrollbar-width: none;
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
</style>
