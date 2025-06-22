import { createApp } from 'vue'
import App from './App.vue'
import router, { oktaAuth } from './router'
import OktaVue from '@okta/okta-vue'
import axios from 'axios';
import './navigation-guards'


// Set Axios base URL from environment or fall back to localhost
axios.defaults.baseURL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080';


createApp(App)
    .use(OktaVue, { oktaAuth })
    .use(router)
    .mount('#app')