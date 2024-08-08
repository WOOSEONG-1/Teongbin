<template >
  <section style="position:relative;" class="mb-5">
    <!-- <img src="@/assets/main_image2.webp" alt="main_img" data-aos="zoom-out" data-aos-duration="800"> -->
    <img src="@/assets/main_image2.webp" alt="main_img" data-aos-duration="800">
    <div class="overlay-text">
      <h1>텅빈과 함께</h1>
      <h1>스마트한 쓰레기통 관리를 시작하세요</h1>
      <h4>
        <a href="https://i11c101.p.ssafy.io/main/user/login" class="join-button btn btn-success">
          가입하기
        </a>
      </h4>
    </div>

    <!-- <div class="overlay-text" data-aos="fade-down" data-aos-duration="1500">
    </div> -->
    <!-- <h4 data-aos="fade-down" data-aos-duration="1500">쓰레기통 관리를 더욱 간편하게 ! </h4>
    <h4 data-aos="fade-down" data-aos-duration="1500">한 번에, 손 쉽게 !</h4> -->
  </section>
  <div class="container-content">
    <ArticleComponent />
  </div>
</template>

<script setup>
import { ref } from 'vue'
import ArticleComponent from '@/components/ArticleComponent.vue';

</script>

<style scoped>
.brand-name {
  background: linear-gradient(45deg, #FFD700, #FFFFFF 25%, #FFD700);
  background-clip: text;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  font-size: 100px;
  font-weight: bold;
  text-shadow: 0 0 0 rgba(255, 215, 0, 0.7), 0 0 5px rgba(255, 215, 0, 0.5), 0 0 5px rgba(255, 215, 0, 0.3);
}

.brand-sub-name {
  background: linear-gradient(45deg, #FFD700, #FFFFFF 25%, #FFD700);
  background-clip: text;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  font-size: 80px;
  text-shadow: 0 0 0 #3CB371, 0 0 5px #3CB371, 0 0 0px #3CB371;
}

.overlay-text {
  position: absolute;
  top: 40%; /* 원하는 위치로 조정 */
  left: 30%; /* 원하는 위치로 조정 */
  color: white;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5); /* 텍스트 가독성 향상을 위한 그림자 */
}

.overlay-text h1, .overlay-text h4 {
  margin: 5px 0;
}


.join-button {
  /* text-decoration: none; */
  font-size: 1em;
}


</style>