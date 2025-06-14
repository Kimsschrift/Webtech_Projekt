<template>
  <div class="register-container">
    <h2>Admin Registrierung</h2>
    <form @submit.prevent="submitForm">
      <input v-model="form.email" type="email" placeholder="Email" required />
      <input v-model="form.password" type="password" placeholder="Passwort" required />
      <div class="buttons">
        <button type="submit">Registrieren</button>
        <button type="button" class="secondary" @click="$router.push('/')">Zurück</button>
      </div>
    </form>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      form: { email: '', password: '', role: 'ADMIN' }
    }
  },
  methods: {
    async submitForm() {
      try {
        const formData = new FormData()
        for (const key in this.form) {
          formData.append(key, this.form[key])
        }
        await axios.post('/api/register', formData, {
          headers: { 'Content-Type': 'multipart/form-data' }
        })
        alert('Admin erfolgreich registriert!')
        this.$router.push('/Admin')
      } catch (e) {
        alert('Registrierung fehlgeschlagen')
      }
    }
  }
}
</script>

<style scoped>
.register-container {
  max-width: 400px;
  margin: 3rem auto;
  padding: 2rem;
  background: white;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0,0,0,0.1);
}
.buttons {
  display: flex;
  justify-content: space-between;
  margin-top: 1rem;
}
button {
  padding: 0.6rem 1.2rem;
  background: #1a73e8;
  border: none;
  color: white;
  cursor: pointer;
}
.secondary {
  background: #1a73e8;
}
</style>