import axios from "axios";
import { useTrashcanStore } from "@/dashboard/stores/trashcan";
import { useUserStore } from "@/dashboard/stores/user";

const trashcanStore = useTrashcanStore();
const userStore = useUserStore();

export function getUserInfo(reload) {
  if (userStore.userName == null || reload) {
    axios
      .get("/api/v1/user/profile", {
        headers: {
          "Content-Type": "application/json",
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

export function getProductList(reload) {
  if (trashcanStore.trashcanList.length == 0 || reload) {
    axios
      .get("/api/v1/trash/user/trashcan", {
        headers: {
          Authorization: sessionStorage.getItem("teongbinToken"),
        },
      })
      .then((res) => {
        trashcanStore.trashcanList = res.data.data;
      })
      .catch((error) => {
        console.log(error);
      });
  }
}

function getRandomNum(seed) {
  return ((seed * seed * seed + 9 * seed * seed + seed) * 29) % 32 * 6;
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
