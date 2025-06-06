import { createRouter, createWebHistory } from 'vue-router'
import StartseitePage from '@/components/StartseitePage.vue'
import JobListView from '@/views/JobListView.vue'
import JobDetailView from '@/views/JobDetailView.vue'
import CompanyRegisterView from '@/views/CompanyRegisterView.vue'
import ApplicantRegisterView from '@/views/ApplicantRegisterView.vue'
import LoginView from '@/views/LoginView.vue'
import JobCreateView from '@/views/JobCreateView.vue'
import JobEditView from '@/views/JobEditView.vue'
import CompanyDashboardView from '@/views/CompanyDashboardView.vue'

const routes = [
    { path: '/', name: 'StartseitePage', component: StartseitePage },
    { path: '/jobs', name: 'JobListView', component: JobListView },
    { path: '/jobs/:id', name: 'JobDetailView', component: JobDetailView },
    { path: '/jobs/:id/edit', name: 'JobEditView', component: JobEditView },
    { path: '/jobs/new', name: 'JobCreateView', component: JobCreateView },
    { path: '/company', name: 'CompanyDashboardView', component: CompanyDashboardView },
    { path: '/register/company', name: 'CompanyRegisterView', component: CompanyRegisterView },
    { path: '/register/applicant', name: 'ApplicantRegisterView', component: ApplicantRegisterView },
    { path: '/login', name: 'LoginView', component: LoginView },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
