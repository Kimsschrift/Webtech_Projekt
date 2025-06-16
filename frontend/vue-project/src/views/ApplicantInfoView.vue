<template>
  <div class="personal-info" v-if="applicant">
    <h2>Persönliche Informationen</h2>
    <img v-if="applicant.profileImageUrl" :src="applicant.profileImageUrl" class="profile-image" alt="Profilbild">

    <div v-if="!editMode">
      <p><strong>Name:</strong> {{ applicant.name }}</p>
      <p><strong>Email:</strong> {{ applicant.email }}</p>
      <p><strong>Telefon:</strong> {{ applicant.phoneNumber }}</p>
      <p v-if="applicant.birthDate"><strong>Geburtsdatum:</strong> {{ applicant.birthDate }}</p>
      <button @click="enableEdit">Bearbeiten</button>
    </div>

    <div v-else>
      <input v-model="form.name" placeholder="Name" />
      <input v-model="form.email" type="email" placeholder="Email" />
      <input v-model="form.phoneNumber" placeholder="Telefon" />
      <button @click="updateInfo">Speichern</button>
      <button @click="cancelEdit">Abbrechen</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'ApplicantInfoView',
  data() {
    return {
      applicant: null,
      editMode: false,
      form: { name: '', email: '', phoneNumber: '' }
    }
  },
  async mounted() {
    const user = JSON.parse(localStorage.getItem('user') || '{}')
    if (!user.applicantId) return
    const res = await axios.get(`/api/applicants/${user.applicantId}`)
    const data = res.data
    if (data.profileImageUrl) {
      data.profileImageUrl = new URL(data.profileImageUrl, axios.defaults.baseURL).href
    }
    this.applicant = data
  },
  methods: {
    enableEdit() {
      this.editMode = true
      this.form = {
        name: this.applicant.name || '',
        email: this.applicant.email || '',
        phoneNumber: this.applicant.phoneNumber || ''
      }
    },
    cancelEdit() {
      this.editMode = false
    },
    async updateInfo() {
      const user = JSON.parse(localStorage.getItem('user') || '{}')
      try {
        const res = await axios.put(`/api/applicants/${user.applicantId}`, this.form)
        this.applicant = res.data
        this.editMode = false
      } catch (e) {
        alert('Aktualisierung fehlgeschlagen')
      }
    }
  }
}
</script>

<style scoped>
.personal-info {
  max-width: 600px;
  margin: 2rem auto;
  background: white;
  padding: 2rem;
  border-radius: 8px;
}
.profile-image {
  max-width: 150px;
  border-radius: 50%;
  margin-bottom: 1rem;
}
input {
  display: block;
  width: 100%;
  margin-bottom: 1rem;
  padding: 0.5rem;
}
button {
  margin-right: 0.5rem;
  padding: 0.6rem 1.2rem;
  background: #1a73e8;
  color: white;
  border: none;
  cursor: pointer;
  border-radius: 4px;
}
</style>