<template>
  <div id="edit-div">
    <!-- <select id="sceneDropdown" v-model="newScene.sceneId">
      <option value=""></option> 
    </select> -->
    <form v-on:submit.stop.prevent="submitScene">
      <div>
        <label for="scene">Scene:</label>
        <textarea id="input-scene" type="text" v-model="newScene.description" />
      </div>
      <div>
        <label for="choices">Choice 1:</label>
        <textarea id="input-choice1" type="text" v-model="choice1.description" />
      </div>
      <div>
        <label for="choices-safety">Choice 1 Safe (Check if safe):</label>
        <input id="input-choice1" type="checkbox" v-model="choice1.isSafe" />
      </div>
      <div>
        <label for="choices">Choice 1 Consequence:</label>
        <textarea id="input-choice1" type="text" v-model="choice1.consequence" />
      </div>
      <div>
        <label for="choices">Choice 2:</label>
        <textarea id="input-choice1" type="text" v-model="choice2.description" />
      </div>
      <div>
        <label for="choices-safety">Choice 2 Safe (Check if safe):</label>
        <input id="input-choice1" type="checkbox" v-model="choice2.isSafe" />
      </div>
      <div>
        <label for="choices">Choice 2 Consequence:</label>
        <textarea id="input-choice1" type="text" v-model="choice2.consequence" />
      </div>
      <div>
        <button type="submit">Submit</button>
        <button type="button" @click="cancel">Cancel</button>
      </div>
      <div></div>
    </form>
  </div>
</template>

<script>
import ResourceService from "../services/ResourceService.js";

export default {
  data() {
    return {
      newScene: {
        description: "",
      },
      choice1: { description: "", isSafe: false, consequence: "" },
      choice2: { description: "", isSafe: false, consequence: "" },
      scene: {},
    };
  },
  methods: {
    submitScene() {
      if (this.sceneId) {
        this.$store.commit("UPDATE_SCENE", this.scene);
        // Mutation stores it in local memory, all the components can share this info
      } else { 
        ResourceService.createScene(this.newScene).then((response) => {
            const newSceneId = response.data.sceneId
            this.choice1.sceneId = newSceneId
            this.choice2.sceneId = newSceneId
            // Waiting for the new scene Id to come back then appending it to the scene Id and creating choices
            ResourceService.createChoice(this.choice1).then((responseChoice1) => {
                ResourceService.createChoice(this.choice2).then((responseChoice2) => {
                    this.$router.push({name: "home"})
                })
            })
        });
      }
    },
    cancel() {
      this.$router.push({ name: "home" });
    },
  },
  created() {
    if (this.sceneId) {
      const scene = this.$store.state.scenes.find(
        (scene) => scene.id === this.sceneId
      );
      if (scene) {
        this.newScene.description = scene.description; // Pre-fill the form with scene data
      }
    }
  },
};
</script>

<style scoped>
#edit-div {
  max-width: 400px;
  margin: auto;
  color: rgb(82, 209, 36);
}
form {
  display: flex;
  flex-direction: column;
}
label {
  margin-bottom: 0.5rem;
  font-size: 20px;
}
input {
  margin-bottom: 1rem;
  padding: 0.5rem;
}
button {
  margin: 0.5rem 0;
  padding: 0.5rem 1rem;
}
</style>