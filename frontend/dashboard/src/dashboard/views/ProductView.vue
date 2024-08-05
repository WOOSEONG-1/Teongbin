<script setup>
import MyPageLayout from "@/dashboard/layouts/MyPageLayout.vue";
import UserMenu from "../components/mypage/UserMenu.vue";
import AllProduct from "@/dashboard/components/mypage/AllProduct.vue";

import { getProductList, getUserInfo } from "@/dashboard/js/remote";

getUserInfo(false);
getProductList(false);
</script>

<template>
  <MyPageLayout>
    <div class="info-container">
      <UserMenu />
      <AllProduct />
    </div>
  </MyPageLayout>
</template>

<style scoped>
.info-container {
  display: flex;
}
</style>
