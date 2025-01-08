<template>
  <div id="game-app">
    <header><img id="coin" alt="The Coin" src="/Coin.jpg" />
        <div>
          <h1>Live or Die:</h1>
          <h2>A Text-Based Survival Game</h2>
        </div>
    </header>
    <nav>
      <router-link v-bind:to="{ name: 'home' }">
          <button>Home</button>
        </router-link>

        <router-link v-bind:to="{ name: 'contact' }">
          <button>Contact</button>
        </router-link>

        <router-link v-if="isAdmin" v-bind:to="{ name: 'addScene' }">
          <button>Edit</button>
        </router-link>

        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token">
          <button>Logout</button>
        </router-link>
        <router-link v-bind:to="{ name: 'login' }" v-else>
          <button>Login</button>
        </router-link>
    </nav>
    <section id="score">
      <h3>Score</h3>
      <p id="points">
      <span class="currentScore">{{ $store.state.currentScore }}</span>
      <div id="coinFlipAnime">
        <div id="the-coin"></div>
        <!-- Place coin image for animation in here -->
      </div>
      </p>
    </section>
    <main>
      <router-view />
    </main>
    <footer>&#129702 &copy; 2024. &#129702 All rights reserved. &#129702</footer>
  </div>
</template>

<script>
export default {
  computed: {
    isAdmin() {
      return (
        this.$store.state.user &&
        this.$store.state.user.role &&
        this.$store.state.user.role.includes("ROLE_ADMIN")
      );
    },
  },
};
</script>

<style>
body {
  font-family: "Handjet", "Lucida Sans", "Lucida Sans Regular", sans-serif;
  background-color: rgb(82, 209, 36);
}

body {
  gap: 3px;
  margin: 8px 20px;
}

header {
  grid-area: header;
  display: flex;
  background-color: black;
}

nav {
  grid-area: nav;
  background-color: black;
  display: flex;
  justify-content: space-around;
  flex-direction: row;
  justify-content: space-evenly;
}

#game-app {
  display: grid;
  grid-template-columns: 2fr 2fr 2fr 2fr;
  grid-template-areas:
    "header  header  nav     nav"
    "score   main    main    main"
    "score   choice  choice  choice"
    "footer  footer  footer  footer";
  gap: 5px;
}

main {
  grid-area: main;
  background-color: black;
}

h1 {
  font-family: "Handjet", "Lucida Sans", "Lucida Sans Regular", sans-serif;
  color: rgb(82, 209, 36);
  font-size: 3em;
  font-weight: bold;
  text-align: left;
  margin: 0;
}

h2 {
  font-family: "Handjet", "Lucida Sans", "Lucida Sans Regular", sans-serif;
  color: rgb(82, 209, 36);
  font-size: 1.5em;
  font-weight: 25px;
  font-style: italic;
  text-align: left;
  margin: 0;
  margin-left: 0.75em;
}

h3 {
  color: rgb(82, 209, 36);
  text-align: center;
  text-decoration: underline;
}

header img {
  height: 100px;
  width: auto;
  padding: 5px;
}

header h1 {
  padding-left: 20px;
  padding-top: 10px;
}

main img {
  object-fit: cover;
  margin-right: 10px;
  height: 350px;
  width: 35%;
}
main li {
  list-style: none;
  margin-bottom: 8px;
}

footer {
  color: rgb(82, 209, 36);
  font-size: 25px;
  grid-area: footer;
  text-align: center;
  background-color: black;
}

button {
  font-family: "Handjet", "Lucida Sans", "Lucida Sans Regular", sans-serif;
  display: block;
  margin: 40px auto;
  padding: 8px 12px;
  background-color: rgb(82, 209, 36);
  text-transform: uppercase;
  border: 0px;
  border-radius: 4px;
  font-size: 20px;
}

nav a {
  margin: 0;
  font-weight: bold;
  padding: 20px 0 0 0;
  flex-grow: 1;
  list-style: none;
  text-align: center;
  line-height: 50px;
  text-decoration: none;
}

button:hover {
  color: #ba1616;
  cursor: pointer;
}

@media (max-width: 800px) {
  body#home {
    grid-template-columns: 1fr;
    grid-template-areas:
      "header"
      "nav"
      "main"
      "choice"
      "score"
      "footer";
    margin: 8px;
  }
  body#contact {
    display: grid;
    grid-template-columns: 1fr;
    grid-template-areas:
      "header"
      "nav"
      "main"
      "footer";
    gap: 6px;
    margin: 8px;
  }
  header h1 {
    padding-top: 0;
  }
  nav ul {
    padding-top: 5px;
    padding-bottom: 5px;
  }
  nav ul li {
    line-height: 25px;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  main#homepage {
    display: block;
  }
  main img {
    width: 100%;
  }
  main#contact-us input,
  textarea {
    width: 100%;
  }
  main#contact-us select {
    width: 102%;
  }
}
</style>