<template>
  <body id="contact">
    <main id="contact-us">
      <h2>Contact Us</h2>
      <form action="mailto:thevilereliquary@gmail.com" method="POST">
        <div>
          <label for="name">Name:</label>
          <input type="text" name="name" id="name" />
        </div>
        <div>
          <label for="tell-us">Tell us about your experience:</label>
          <textarea id="text-tell" type="text"></textarea>
        </div>
        <div>
          <label for="didYouLiveOrDie">Did you live or die?</label>
          <select name="didYouLiveOrDie" id="didYouLiveOrDie">
            <option value="ILived">I lived.</option>
            <option value="IDied">I died.</option>
          </select>
        </div>
        <div>
          <input id="submit" type="submit" value="Submit" />
        </div>
      </form>
    </main>
  </body>
</template>

<script>
export default {};
</script>

<style scoped>
div {
  display: flex;
  flex-direction: column;
  width: 300px;
}

form {
  display: flex;
  flex-direction: column;
  align-items: center;
}

label {
  color: rgb(82, 209, 36);
  gap: 3px;
  margin: 8px 20px;
  font-size: 1.3em;
}

#didYouLiveOrDie {
  margin: 5px;
  width: 150px;
}

#submit {
  color: black;
  font-weight: 600;
  text-align: center;
  padding: auto;
  margin: 25px;
  width: 33%;
  margin-left: 130px;
}

#submit:hover {
  color: #ba1616;
  cursor: pointer;
}

</style>