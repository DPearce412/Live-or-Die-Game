<template>
  <div>
    <scene-edit :sceneId="currentScene.sceneId" />
  </div>
</template>

<script>
import SceneEdit from "../components/SceneEdit.vue";

export default {
  components: {
    SceneEdit,
  },
};
</script>

<style>
</style>