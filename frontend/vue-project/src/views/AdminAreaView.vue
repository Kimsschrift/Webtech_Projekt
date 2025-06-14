<template>
  <div>
    <AdminDashboardView v-if="isAdmin" />
    <div v-else class="login-container">
      <h2>Admin Login</h2>
      <form @submit.prevent="login">
        <input v-model="form.code" type="text" placeholder="Admin-Code" required />
        <button type="submit">Einloggen</button>
      </form>

    </div>
  </div>
</template>

<script>
import axios from 'axios'
import AdminDashboardView from './AdminDashboardView.vue'
export default {
  name: 'AdminAreaView',
  components: { AdminDashboardView },
  data() {
    return {
      form: { code: '' },
      isAdmin: false
    }
  },
  created() {
    const user = JSON.parse(localStorage.getItem('user') || '{}')
    if (user.role === 'ADMIN') {
      this.isAdmin = true
    }
  },
  methods: {
    async login() {
      try {
        const res = await axios.post('/api/admin/login', this.form)
        if (res.data.role === 'ADMIN') {
          localStorage.setItem('user', JSON.stringify(res.data))
          this.isAdmin = true
          this.$router.push('/afterLogin')
        } else {
          alert('Keine Admin-Rechte')
        }
      } catch (e) {
        const msg = e.response?.data || 'Login fehlgeschlagen'
        alert(msg)
      }
    }
  }
}
</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: 3rem auto;
  padding: 2rem;
  background: white;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0,0,0,0.1);
}
input {
  display: block;
  width: 100%;
  padding: 0.6rem;
  margin-bottom: 1rem;
}
button {
  padding: 0.6rem 1.2rem;
  background: #1a73e8;
  border: none;
  color: white;
  cursor: pointer;
  width: 100%;
}
</style>