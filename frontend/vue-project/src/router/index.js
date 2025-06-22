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
import AdminAreaView from '@/views/AdminAreaView.vue'
import StartPageAfterLogin from '@/views/StartPageAfterLogin.vue'
import ApplicantInfoView from '@/views/ApplicantInfoView.vue'
import RegisterTypeSelectView from "@/views/RegisterTypeSelectView.vue"
import { OktaAuth } from '@okta/okta-auth-js'
import { LoginCallback } from '@okta/okta-vue'

const oktaAuth = new OktaAuth({
    issuer: import.meta.env.VITE_OKTA_ISSUER,
    clientId: import.meta.env.VITE_OKTA_CLIENT_ID,
    redirectUri: window.location.origin + '/login/callback',
    scopes: ['openid', 'profile', 'email'],
    pkce: true
})

const routes = [
    { path: '/', name: 'StartseitePage', component: StartseitePage },
    { path: '/register', name: 'RegisterTypeSelect', component: RegisterTypeSelectView},
    { path: '/jobs', name: 'JobListView', component: JobListView },
    { path: '/jobs/:id', name: 'JobDetailView', component: JobDetailView },
    { path: '/jobs/:id/edit', name: 'JobEditView', component: JobEditView, meta: { requiresCompany: true } },
    { path: '/jobs/new', name: 'JobCreateView', component: JobCreateView, meta: { requiresCompany: true } },
    { path: '/company', name: 'CompanyDashboardView', component: CompanyDashboardView, meta: { requiresCompany: true } },
    { path: '/api/admin/login', name: 'AdminAreaView', component: AdminAreaView },
    { path: '/afterLogin', name: 'StartPageAfterLogin', component: StartPageAfterLogin },
    { path: '/personal-info', name: 'ApplicantInfoView', component: ApplicantInfoView, meta: { requiresApplicant: true } },
    { path: '/register/company', name: 'CompanyRegisterView', component: CompanyRegisterView },
    { path: '/register/applicant', name: 'ApplicantRegisterView', component: ApplicantRegisterView },
    { path: '/login', name: 'LoginView', component: LoginView },
    { path: '/login/callback', component: LoginCallback },

]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
export { oktaAuth }