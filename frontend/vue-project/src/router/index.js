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
import OktaSignIn from '@okta/okta-signin-widget'
import { OktaAuth } from '@okta/okta-auth-js'

const oktaSignIn = new OktaSignIn({
    baseUrl: 'https://trial-5957820.okta.com',
    clientId: '0oaske4gq52p9f0gZ697',
    redirectUri: process.env.VUE_APP_FRONTEND_BASE_URL + '/login/callback',
    authParams: {
        pkce: true,
        issuer: 'https://trial-5957820.okta.com/oauth2/default',
        display: 'page',
        scopes: ['openid', 'profile', 'email']
    },
    features: { registration: true }
})

const oktaAuth = new OktaAuth({
    issuer: 'https://trial-5957820.okta.com/oauth2/default',
    clientId: '0oaske4gq52p9f0gZ697',
    redirectUri: window.location.origin + '/login/callback',
    scopes: ['openid', 'profile', 'email']
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

]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
export { oktaAuth, oktaSignIn }
