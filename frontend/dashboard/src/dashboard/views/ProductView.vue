<script setup>
import MyPageLayout from "@/dashboard/layouts/MyPageLayout.vue";
import AddTrashcan from "@/dashboard/components/mypage/AddTrashcan.vue";
import RenameTrashcan from "@/dashboard/components/mypage/RenameTrashcan.vue";
import RemoveTrashcan from "@/dashboard/components/mypage/RemoveTrashcan.vue";
import AllProduct from "@/dashboard/components/mypage/AllProduct.vue";
import axios from "axios";
import { ref } from "vue";

const userName = ref();

function getUserInfo() {
  userName.value = sessionStorage.getItem("teongbinUserName");

  if (userName.value == null) {
    axios
      .get("/api/v1/user/profile", {
        headers: {
          "Content-Type": "application/json",
          Authorization: sessionStorage.getItem("teongbinToken"),
        },
      })
      .then((res) => {
        userName.value = res.data.data.name;
        sessionStorage.setItem("teongbinUserName");
      })
      .catch((error) => {
        console.log(error);
      });
  }
}

getUserInfo();

</script>

<template>
  <MyPageLayout>
    <div class="info-container">
      <div>
        <table>
          <tbody>
            <tr>
              <th>사명</th>
            </tr>
            <tr>
              <td>{{ userName }}</td>
            </tr>
            <tr>
              <th>제품 수</th>
            </tr>
            <tr>
              <td>{{ trashcanStore.trashcanList.length }}개</td>
            </tr>
            <tr>
              <th>
                <AddTrashcan />
              </th>
            </tr>
            <tr>
              <th>
                <RenameTrashcan />
              </th>
            </tr>
            <tr>
              <th>
                <RemoveTrashcan />
              </th>
            </tr>
          </tbody>
        </table>
        <table></table>
      </div>
      <AllProduct />
    </div>
  </MyPageLayout>
</template>

<style scoped>
table {
  width: 8rem;
}
th {
  font-size: 1.5rem;
  padding: 2rem 0.5rem;
  padding-bottom: 0rem;
  background-color: inherit;
}
td {
  font-size: 1.5rem;
  padding: 0.5rem;
}
.btn {
  background-color: none;
  border: none;
  font-size: 1rem;
}
.info-container {
  display: flex;
}
.product-container {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  height: min-content;
}
.trashcan-info-container {
  border: 1px solid black;
  margin: 1rem 0 0 1rem;
}
.trashcan-info {
  display: flex;
  padding: 0.5rem 0;
  align-items: center;
  margin: 0 0.5rem;
}
.horizontal-line {
  border-bottom: 1px double lightgrey;
}
.trashcan-info-label {
  width: 4rem;
  justify-content: center;
  border-right: 1px double silver;
}
.trashcan-info-data {
  width: 10rem;
}
.trashcan-serial-number {
  font-size: 1rem;
}
</style>
