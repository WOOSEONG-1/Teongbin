import axios from "axios";
import router from "@/router";

export async function signup(data) {
  let success = true;
  await axios
    .post("/api/v1/user/signup", data)
    .then((res) => {})
    .catch((error) => {
      success = false;
    });

  return success;
}

export async function login(data, saveEmail) {
  await axios
    .post("/api/v1/user/login", data)
    .then((res) => {
      sessionStorage.setItem("teongbinToken", res.headers.authorization);
      sessionStorage.setItem("teongbinLoginState", true);
      if (saveEmail) {
        localStorage.setItem("teongbinEmail", data.email);
      } else {
        localStorage.removeItem("teongbinEmail");
      }

      router.push("/");
    })
    .catch((error) => {
      console.log(error);
    });
}
