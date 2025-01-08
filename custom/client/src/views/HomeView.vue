<template>
  <div id="main-div">
    <game-action :scene="currentScene" :choices="currentChoices" />
  </div>
</template>

<script>
import GameAction from "../components/GameAction.vue";
import ScoreBox from "../components/ScoreBox.vue";
import ResourceService from "../services/ResourceService.js";

export default {
  components: {
    GameAction,
    ScoreBox
  },

  data() {
    return {
      scenes: [],
      choices: [],
    };
  },

  created() {
    ResourceService.getScenes().then((response) => {
      this.scenes = response.data;
      this.$store.commit("SET_SCENES", this.scenes);
      this.currentScene = this.scenes[this.$store.state.currentSceneIndex];
    });

    ResourceService.getChoices().then((response) => {
      this.choices = response.data;
      this.$store.commit("SET_CHOICES", this.choices);
      this.currentChoices = this.choices.filter((choice) => {
        return choice.sceneId == this.currentScene.sceneId;
      });
    });
  },

  computed: {
    currentScene() {
      return this.scenes[this.$store.state.currentSceneIndex];
    },

    currentChoices() {
      return this.choices.filter((choice) => {
        return choice.sceneId == this.currentScene.sceneId;
      });
    },
  },

  // Review computed properties and life cycle hooks, like the created() above
};
</script>

<style>
</style>