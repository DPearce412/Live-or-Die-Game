import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      scenes: [],
      choices: [],
      currentSceneIndex: 0,
      lastScene: false,
      currentScore: 0
    },

    mutations: {
      SET_SCENES(state, scenes) {
        state.scenes = scenes;
        state.lastScene = state.currentSceneIndex >= state.scenes.length - 1;
      },
      SET_CHOICES(state, choices) {
        state.choices = choices;
      },
      SET_CURRENT_SCORE(state, score) {
        state.currentScore = score;
      },
      INCREMENT_CURRENT_SCORE(state) {
        state.currentScore++;
      },
      SET_CURRENT_SCENE_INDEX(state, currentSceneIndex) {
        state.currentSceneIndex = currentSceneIndex;
        state.lastScene = state.currentSceneIndex >= state.scenes.length - 1;
      },
      ADD_SCENE(state, newScene) {

        state.scenes.push(newScene);
      },
      UPDATE_SCENE(state, updatedScene) {
        let sceneIndex = state.scenes.findIndex((foundScene) => {
          foundScene.sceneId === updatedScene.sceneId
        })
        state.scenes[sceneIndex] = updatedScene;
      },
      IS_LAST_SCENE(state) {
        state.lastScene = !state.lastScene; 
      },
      SET_AUTH_TOKEN(state, token) {

        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      }
    },

  })
  return store;
}