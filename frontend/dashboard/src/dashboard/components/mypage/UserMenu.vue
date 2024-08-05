<script setup>
import AddTrashcan from "@/dashboard/components/mypage/AddTrashcan.vue";
import RenameTrashcan from "@/dashboard/components/mypage/RenameTrashcan.vue";
import RemoveTrashcan from "@/dashboard/components/mypage/RemoveTrashcan.vue";

import axios from "axios";
import { onMounted, ref } from "vue";
import { useTrashcanStore } from "@/dashboard/stores/trashcan";

const trashcanStore = useTrashcanStore();
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
      })
      .catch((error) => {
        console.log(error);
      });
  }
}

onMounted(() => {
  getUserInfo();
});
</script>

<template>
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
  </div>
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
</style>
