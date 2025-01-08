<template>
    <section id="score">
      <h3 id="scoreTitle">Score</h3>
      <p id="points">
      <span class="currentScore">{{ this.$store.state.currentScore }}</span>
      </p>
    </section>
</template>

<script>
export default {

};
</script>

<style>

h3 {
 
  font-size: 35px;

}

#score {
  grid-area: score;
  background-color: black;
}

#points {
  text-align: center;
  font-size: 85px;
}

.currentScore {
  font-size: 45px;
  padding: 0.5em;
}
</style>