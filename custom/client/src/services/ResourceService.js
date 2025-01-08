import axios from 'axios'; 


export default { 

  getScenes() {
    return axios.get(`/scenes`)
  },

  getChoices() {
    return axios.get(`/scenes/choices`);
  },

  createScene(scene) {
    return axios.post('/scenes', scene);
  }, 

  createChoice(choice) {
    return axios.post('/scenes/choices', choice)
  }

 };

