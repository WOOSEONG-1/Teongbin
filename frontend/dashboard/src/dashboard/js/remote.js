import axios from "axios";
import { useShortcutStore } from "@/dashboard/stores/shortcut";
import { useTrashcanStore } from "@/dashboard/stores/trashcan";
import { useUserStore } from "@/dashboard/stores/user";
import { cloneDeep } from "lodash";

const shortcutStore = useShortcutStore();
const trashcanStore = useTrashcanStore();
const userStore = useUserStore();

export function getUserInfo(reload) {
  if (userStore.userName == null || reload) {
    axios
      .get("/api/v1/user/profile", {
        headers: {
          Authorization: sessionStorage.getItem("teongbinToken"),
        },
      })
      .then((res) => {
        userStore.userName = res.data.data.name;
      })
      .catch((error) => {
        console.log(error);
      });
  }
}

export function addTrashcan(data) {
  axios
    .post("/api/v1/trash/new", data, {
      headers: {
        Authorization: sessionStorage.getItem("teongbinToken"),
      },
    })
    .then((res) => {
      getTrashcanList();
    })
    .catch((error) => {
      console.log(error);
    });
}

export async function getTrashcanList() {
  let success = true;
  await axios
    .get("/api/v1/trash/user/trashcan", {
      headers: {
        Authorization: sessionStorage.getItem("teongbinToken"),
      },
    })
    .then((res) => {
      trashcanStore.trashcanList = res.data.data;
      getTrashcanRest();
    })
    .catch((error) => {
      console.log(error);
      success = false;
    });

  return success;
}

export function getTrashcanRest() {
  axios
    .get("/api/v1/trash/user/trashcan/rest", {
      headers: {
        Authorization: sessionStorage.getItem("teongbinToken"),
      },
    })
    .then((res) => {
      const trashcanStateList = res.data.data;
      if (trashcanStateList.length == trashcanStore.trashcanList.length) {
        trashcanStateList.forEach((trashcanState) => {
          const trashcan = trashcanStore.trashcanList.find(
            (trashcan) => trashcan.trashcanId === trashcanState.trashcanId
          );
          if (trashcan) {
            trashcan.restPercent = trashcanState.restPercent;
          }
        });
      }
    })
    .catch((error) => {
      console.log(error);
    });
}

export function modifyTrashcanInfo() {
  const data = cloneDeep(trashcanStore.trashcanInfo);

  const postData = {
    latitude: data.latitude,
    longitude: data.longitude,
    nickname: data.nickname,
  };

  axios
    .post(`/api/v1/trash/${data.trashcanId}/update`, postData, {
      headers: {
        Authorization: sessionStorage.getItem("teongbinToken"),
      },
    })
    .then((res) => {
      getTrashcanList();
      trashcanStore.selectTrashcanList.splice(0, 1);
    })
    .catch((error) => {});
}

function getRandomNum(seed) {
  return (((seed * seed * seed + 9 * seed * seed + seed) * 29) % 32) * 6;
}

export async function addShortcut(setting) {
  const success = await axios
    .post("/api/v1/user/shortcut/new", setting, {
      headers: {
        Authorization: sessionStorage.getItem("teongbinToken"),
      },
    })
    .then((res) => {
      return true;
    })
    .catch((error) => {
      console.log(error);
      return false;
    });

  return success;
}

export function getShortcutList() {
  axios
    .get("/api/v1/user/shortcut", {
      headers: {
        Authorization: sessionStorage.getItem("teongbinToken"),
      },
    })
    .then((res) => {
      shortcutStore.shortcutList = res.data.data;
      shortcutStore.shortcutList.forEach((shortcut, idx) => {
        const r = getRandomNum(idx);
        const g = getRandomNum(r);
        const b = getRandomNum(g);
        shortcutStore.colorList.push({
          red: r,
          green: g,
          blue: b,
        });
      });
    })
    .catch((error) => {
      console.log(error);
    });
}
