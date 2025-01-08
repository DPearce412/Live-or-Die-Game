<template>
  <div>
    <p>{{ choice.consequence }}</p>
    <button @click="advanceScene" v-if="!isLastScene">NEXT SCENE</button>
    <button @click="startOver">START OVER</button>
  </div>
</template>

<script>
export default {
  data() {
    return { choice: {} };
  },

  computed: {
    isLastScene() {
      console.log(this.$store.state.currentSceneIndex);
      console.log(this.$store.state.scenes.length);
      return this.$store.state.lastScene;
      //   return this.$store.state.currentSceneIndex >= this.$store.state.scenes.length-1
    },
  },

  methods: {
    advanceScene() {
      this.$store.commit(
        "SET_CURRENT_SCENE_INDEX",
        this.$store.state.currentSceneIndex + 1
      );
      this.$router.push({ name: "home" });
    },

    startOver() {
      this.$store.commit("SET_CURRENT_SCENE_INDEX", 0);
      this.$router.go(- 1);
      this.$store.commit("SET_CURRENT_SCORE", 0);
      }
    },

  created() {
    this.choice = this.$store.state.choices.find((choice) => {
      return choice.choiceId == this.$route.params.choiceId;
    });
  },
};
</script>

<style>
p {
  color: rgb(82, 209, 36);
  gap: 3px;
  margin: 8px 20px;
  font-size: 1em;
}
</style>