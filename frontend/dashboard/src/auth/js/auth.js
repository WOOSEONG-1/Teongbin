import axios from "axios";
import router from "@/router";

export async function sendAuthCode(email) {
  const data = { email: email };
  return await axios
    .post("/api/v1/user/email", data)
    .then((res) => {
      return true;
    })
    .catch((error) => {
      return false;
    });
}

export async function verifyAuthCode(email, authCode) {
  const data = { email: email, code: authCode };
  return await axios
    .post("/api/v1/user/verify", data)
    .then((res) => {
      return true;
    })
    .catch((error) => {
      return false;
    });
}

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

export async function changePassword(email, password) {
  const data = {
    email: email,
    password: password,
  };
  await axios
    .post("/api/v1/user/passwordchange", data)
    .then((res) => {
      router.push("/");
    })
    .catch((error) => {});
}
