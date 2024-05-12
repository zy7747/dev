<!--  -->
<template>
  <div class="backGround">
    <video class="video" ref="video" muted loop autoplay>
      <source
        src="https://my-1307762674.cos.ap-guangzhou.myqcloud.com/video/%E9%BB%84%E9%87%91%E9%93%83.mp4"
        type="video/mp4"
      />
    </video>
    <div class="login">
      <Login />
    </div>
  </div>
</template>

<script lang="ts" setup>
import Login from "./login.vue";
</script>

<style lang="scss" scoped>
.backGround {
  position: relative;
  height: 100vh;
  width: 100vw;
  overflow: hidden;
  .video {
    position: absolute;
    transform: scale(1.2);
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
  .login {
    position: absolute;
    z-index: 1;
  }
}
</style>
