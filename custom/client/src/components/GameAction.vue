<template>
  <section>
    <div class="content">
      <p id="sceneContent">
        {{ scene.description }}
      </p>
      <div>
        <ul id="choices">
          <li>
            <a @click="clickedChoice(choices[0])" id="Choice1">{{
              choices[0].description
            }}</a>
          </li>
          <li>
            <a @click="clickedChoice(choices[1])" id="Choice2">{{
              choices[1].description
            }}</a>
          </li>
          <li>
            <a @click="clickedChoice(choices[2])" id="Choice3">{{
              choices[2].description
            }}</a>
          </li>
        </ul>
      </div>
    </div>
    <button @click="startOver">START OVER</button>
  </section>
</template>



<script>
export default {
  props: ["scene", "choices"],

  methods: {
    clickedChoice(choice) {
      if (choice == this.choices[2]) {
        Math.round(Math.random()) == 0
          ? // round a random to 0 or 1
            (choice = this.choices[0])
          : // if it's zero, I pick Choice 1
            (choice = this.choices[1]);
        // if it's not, pick Choice 2
      }
      if (choice.safe) {
        this.$store.commit("INCREMENT_CURRENT_SCORE");
      } else {
        this.$store.commit("IS_LAST_SCENE");
      }
      this.$router.push({
        name: "consequence",
        params: { choiceId: choice.choiceId },
      });
    },
    startOver() {
      this.$store.commit("SET_CURRENT_SCENE_INDEX", 0);
      this.$store.commit("SET_CURRENT_SCORE", 0);
      this.$store.commit("IS_LAST_SCENE");
      }
  },
};
</script>

<style>
p#sceneContent {
  color: rgb(82, 209, 36);
  font-size: larger;
  text-align: justify;
  padding: 0.5em;
  margin-right: 1em;
  margin-left: 1.1em;
}

#choices {
  grid-area: choice;
  display: flex;
  flex-grow: 1;
}

#choices li {
  font-family: "Handjet", "Lucida Sans", "Lucida Sans Regular", sans-serif;
  display: flex;
  font-weight: 600;
  margin: 1em;
  flex-direction: row;
  flex-grow: 1;
  justify-content: center;
  align-items: flex-end;
  width: 30%;
  background-color: rgb(82, 209, 36);
  border-radius: 5px;
  text-align: center;
}

#choices li a {
  padding-top: 13px;
  text-align: center;
  margin-bottom: 10%;
}

#choice1:hover {
  color: #ba1616;
  cursor: pointer;
}

#Choice1:hover {
  color: #ba1616;
  cursor: pointer;
}

#Choice2:hover {
  color: #ba1616;
  cursor: pointer;
}

#Choice3:hover {
  color: #ba1616;
  cursor: pointer;
}

.content > h2 {
  padding: 0.5em;
}
</style>