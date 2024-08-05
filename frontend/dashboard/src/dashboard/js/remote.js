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
