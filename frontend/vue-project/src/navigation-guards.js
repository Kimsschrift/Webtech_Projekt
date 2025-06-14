import router from './router'

router.beforeEach((to, from, next) => {
    const user = JSON.parse(localStorage.getItem('user') || '{}')
    const isLoggedIn = !!user.role
    if (!isLoggedIn && to.path !== '/' && to.path !== '/login' && !to.path.startsWith('/register') && to.path !== '/api/admin/login') {
        if (to.path.startsWith('/jobs')) {
            return next({ path: '/login', query: { message: 'login_required' } })
        }
        return next('/login')
    }
    if (to.meta.requiresAdmin && user.role !== 'ADMIN') {
        return next('/jobs')
    }
    if (to.meta.requiresCompany && !['COMPANY', 'ADMIN'].includes(user.role)) {
        return next('/jobs')
    }
    next()
})