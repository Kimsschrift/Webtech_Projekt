import { createRouter, createWebHistory } from 'vue-router'
import StartseitePage from "@/components/StartseitePage.vue";
import JobListView from "@/views/JobListView.vue";
import JobDetailView from "@/views/JobDetailView.vue";

const routes = [
    { path: '/', component: StartseitePage },
    { path: '/jobs', component: JobListView },
    { path: '/jobs/:id', component: JobDetailView }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;
