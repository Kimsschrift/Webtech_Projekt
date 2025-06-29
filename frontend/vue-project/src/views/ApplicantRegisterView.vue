<template>
  <div class="register-container">
    <h2>Bewerber Registrierung</h2>
    <form @submit.prevent="submitForm" enctype="multipart/form-data">
      <input v-model="form.email" type="email" placeholder="Email" required />
      <input v-model="form.password" type="password" placeholder="Passwort" required />
      <input v-model="form.applicantName" placeholder="Name" />
      <input v-model="form.applicantPhoneNumber" placeholder="Telefonnummer" />
      <input v-model="form.birthDate" type="date" lang="de" />

      <label class="file-label">Lebenslauf (PDF):</label>
      <input type="file" accept=".pdf" @change="handleFile('cvFile', $event)" />

      <label class="file-label">Anschreiben (txt/pdf):</label>
      <input type="file" accept=".txt,.pdf" @change="handleFile('resumeTextFile', $event)" />

      <label class="file-label">Profilbild (JPG/PNG):</label>
      <input type="file" accept="image/*" @change="handleFile('profileImageFile', $event)" />

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
      form: {
        email: '',
        password: '',
        role: 'APPLICANT',
        applicantName: '',
        applicantPhoneNumber: '',
        birthDate: ''
      },
      files: {
        cvFile: null,
        resumeTextFile: null,
        profileImageFile: null
      }
    }
  },
  methods: {
    handleFile(key, event) {
      this.files[key] = event.target.files[0]
    },
    async submitForm() {
      const formData = new FormData()
      for (const key in this.form) {
        formData.append(key, this.form[key])
      }
      for (const key in this.files) {
        if (this.files[key]) formData.append(key, this.files[key])
      }

      try {
        await axios.post('/api/register', formData, {
          headers: { 'Content-Type': 'multipart/form-data' }
        })
        alert("Registrierung erfolgreich!")
        this.$router.push('/')
      } catch (error) {
        console.error('Fehler bei der Registrierung:', error)
        alert("Fehler: " + (error.response?.data || error.message))
      }
    }
  }
}
</script>

<style scoped>
.register-container {
  max-width: 600px;
  margin: auto;
  padding: 2rem;
}
input {
  display: block;
  margin: 1rem 0;
  width: 100%;
  padding: 0.5rem;
}
.file-label {
  color: black;
  font-weight: bold;
  margin-top: 1.5rem;
  background: white;
}
.buttons {
  display: flex;
  justify-content: space-between;
  margin-top: 2rem;
}
button {
  padding: 0.75rem 1.5rem;
  background: #1a73e8;
  color: white;
  border: none;
  cursor: pointer;
}
.secondary {
  background:#1a73e8;
  color: white;
}
</style>
