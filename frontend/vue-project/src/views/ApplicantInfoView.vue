<template>
  <div class="personal-info" v-if="applicant">
    <h2>Persönliche Informationen</h2>
    <img v-if="applicant.profileImageUrl" :src="applicant.profileImageUrl" class="profile-image" alt="Profilbild">

    <div v-if="!editMode">
      <p><strong>Name:</strong> {{ applicant.name }}</p>
      <p><strong>Email:</strong> {{ applicant.email }}</p>
      <p><strong>Telefon:</strong> {{ applicant.phoneNumber }}</p>
      <p v-if="applicant.birthDate"><strong>Geburtsdatum:</strong> {{ applicant.birthDate }}</p>
      <p v-if="applicant.cvLink"><strong>CV:</strong> <a :href="applicant.cvLink" target="_blank">Download</a></p>
      <p v-if="applicant.resumeText"><strong>Bewerbungstext:</strong> {{ applicant.resumeText }}</p>
      <button @click="enableEdit">Bearbeiten</button>
    </div>

    <div v-else>
      <input v-model="form.name" placeholder="Name" />
      <input v-model="form.email" type="email" placeholder="Email" />
      <input v-model="form.phoneNumber" placeholder="Telefon" />
      <input v-model="form.birthDate" type="date" />
      <input v-model="form.cvLink" placeholder="CV Link" />
      <textarea v-model="form.resumeText" placeholder="Bewerbungstext" />
      <input v-model="form.profileImageUrl" placeholder="Profilbild URL" />
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
      form: {
        name: '',
        email: '',
        phoneNumber: '',
        birthDate: '',
        cvLink: '',
        resumeText: '',
        profileImageUrl: ''
      }
    }
  },
  async mounted() {
    const user = JSON.parse(localStorage.getItem('user') || '{}')
    if (!user.applicantId) return
    try {
      const res = await axios.get(`/api/applicants/${user.applicantId}`)
      const data = res.data
      if (data.profileImageUrl && !data.profileImageUrl.startsWith('http')) {
        const base = axios.defaults.baseURL.replace(/\/?api$/, '')
        data.profileImageUrl = base + data.profileImageUrl
      }
      this.applicant = data
    } catch (e) {
      console.error('Fehler beim Laden der Bewerberdaten:', e)
    }
  },
  methods: {
    enableEdit() {
      this.editMode = true
      this.form = {
        name: this.applicant.name || '',
        email: this.applicant.email || '',
        phoneNumber: this.applicant.phoneNumber || '',
        birthDate: this.applicant.birthDate || '',
        cvLink: this.applicant.cvLink || '',
        resumeText: this.applicant.resumeText || '',
        profileImageUrl: this.applicant.profileImageUrl || ''
      }
    },
    cancelEdit() {
      this.editMode = false
    },
    async updateInfo() {
      const user = JSON.parse(localStorage.getItem('user') || '{}')
      try {
        const res = await axios.put(`/api/applicants/${user.applicantId}`, this.form)
        const data = res.data
        if (data.profileImageUrl && !data.profileImageUrl.startsWith('http')) {
          const base = axios.defaults.baseURL.replace(/\/?api$/, '')
          data.profileImageUrl = base + data.profileImageUrl
        }
        this.applicant = data
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