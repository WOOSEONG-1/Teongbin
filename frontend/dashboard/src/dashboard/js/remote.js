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

export async function getTrashcanList(reload) {
  let success = true;

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
        success = false;
        console.log(error);
      });
  }

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
    })
    .catch((error) => {
      console.log(error);
    });
}
