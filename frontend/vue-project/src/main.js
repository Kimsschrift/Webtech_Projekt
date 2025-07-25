import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios';
import './navigation-guards'


// Set Axios base URL from environment or fall back to localhost
axios.defaults.baseURL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080';
// axios.defaults.baseURL = import.meta.env.VITE_API_BASE_URL || 'https://webtech-projekt-g9qw.onrender.com';


createApp(App).use(router).mount('#app')
