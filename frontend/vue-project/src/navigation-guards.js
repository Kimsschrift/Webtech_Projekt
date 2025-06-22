import router, { oktaAuth } from './router'

router.beforeEach(async (to, from, next) => {
    const isAuthenticated = await oktaAuth.isAuthenticated()
    if (!isAuthenticated && to.path !== '/' && to.path !== '/login' && !to.path.startsWith('/register') && to.path !== '/api/admin/login') {
        if (to.path.startsWith('/jobs')) {
            return next({ path: '/login', query: { message: 'login_required' } })
        }
        return next('/login')
    }

    next()
})