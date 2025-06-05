<template>
  <div class="login-container">
    <h2>Login</h2>
    <form @submit.prevent="submit">
      <input v-model="form.email" type="email" placeholder="Email" required />
      <input v-model="form.password" type="password" placeholder="Passwort" required />
      <button type="submit">Einloggen</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      form: { email: '', password: '' }
    }
  },
  methods: {
    async submit() {
      try {
        const res = await axios.post('/api/login', this.form)
        localStorage.setItem('user', JSON.stringify(res.data))
        if (res.data.role === 'COMPANY') {
          this.$router.push('/company')
        } else {
          this.$router.push('/jobs')
        }
      } catch (e) {
        alert('Login fehlgeschlagen')
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