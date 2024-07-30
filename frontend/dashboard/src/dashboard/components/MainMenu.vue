<script setup></script>

<template>
  <aside>
    <div class="menu-container menu-split">
      <div>
        <div class="blank-space"></div>
        <div class="menu-box">
          <RouterLink to="/" class="menu-item">Main</RouterLink>
        </div>
        <div class="menu-box">
          <RouterLink to="/myPage/product" class="menu-item"
            >My Page</RouterLink
          >
        </div>
      </div>
      <div>
        <div class="menu-box">
          <button class="menu-item logout-btn">Logout</button>
        </div>
      </div>
    </div>
  </aside>
</template>

<style scoped>
aside {
  float: left;
  width: 140px;
  height: 585px;
  border-radius: 5px;
  margin: 1rem;
}
.blank-space {
  height: 5rem;
}
.menu-split {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.menu-container {
  padding: 1rem;
  height: 100%;
}
.menu-box {
  padding: 1.5rem 0;
}
.menu-item {
  text-decoration: none;
  color: black;
}
.logout-btn {
  border: none;
  background: none;
}
</style>
