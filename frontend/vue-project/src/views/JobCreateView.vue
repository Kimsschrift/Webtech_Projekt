<template>
  <div class="create-job">
    <h2>Neue Stellenausschreibung</h2>
    <form @submit.prevent="submit">
      <input v-model="form.title" placeholder="Titel" required />
      <input v-model="skills" placeholder="Skills (comma separated)" />
      <input v-model="languages" placeholder="Sprachen (comma separated)" />
      <input v-model.number="form.expectedSalary" type="number" placeholder="Gehalt in €" />
      <input v-model="form.location" placeholder="Ort" />
      <select v-model="form.minDegree">
        <option disabled value="">Abschluss</option>
        <option value="BACHELOR">Bachelor</option>
        <option value="MASTER">Master</option>
        <option value="DIPLOMA">Diploma</option>
        <option value="PHD">PhD</option>
        <option value="BELOW_UNIVERSITY">Ohne Abschluss</option>
      </select>
      <select v-model="form.employmentType">
        <option disabled value="">Anstellungsart</option>
        <option value="INTERN">Intern</option>
        <option value="APPRENTICESHIP">Apprenticeship</option>
        <option value="WORK_STUDENT">Work Student</option>
        <option value="FULL_TIME">Full Time</option>
        <option value="OTHER">Other</option>
      </select>
      <select v-model="form.workTime">
        <option disabled value="">Arbeitszeit</option>
        <option value="FULL_TIME">Full Time</option>
        <option value="PART_TIME">Part Time</option>
        <option value="MINI_JOB">Mini Job</option>
        <option value="OTHER">Other</option>
      </select>
      <select v-model="form.industry">
        <option disabled value="">Industrie</option>
        <option value="IT">IT</option>
        <option value="LOGISTICS">Logistics</option>
        <option value="RETAIL">Retail</option>
        <option value="MEDIA">Media</option>
        <option value="AUTOMOTIVE">Automotive</option>
        <option value="FINANCE">Finance</option>
        <option value="MEDICAL">Medical</option>
        <option value="ENERGY">Energy</option>
        <option value="AEROSPACE">Aerospace</option>
        <option value="OTHER">Other</option>
      </select>
      <div class="checkbox-group">
        <label class="checkbox-item">
          <input type="checkbox" v-model="form.remoteAllowed" />
          <span>Homeoffice</span>
        </label>
        <label class="checkbox-item">
          <input type="checkbox" v-model="form.requiresExperience" />
          <span>Erfahrung erforderlich</span>
        </label>
      </div>
      <textarea v-model="form.description" placeholder="Beschreibung"></textarea>
      <textarea v-model="form.taskArea" placeholder="Aufgaben"></textarea>
      <textarea v-model="form.userProfile" placeholder="Profil"></textarea>
      <textarea v-model="form.benefits" placeholder="Benefits"></textarea>
      <button type="submit">Speichern</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      form: {
        title: '',
        minDegree: '',
        expectedSalary: 0,
        location: '',
        remoteAllowed: false,
        requiresExperience: false,
        employmentType: '',
        workTime: '',
        industry: '',
        description: '',
        taskArea: '',
        userProfile: '',
        benefits: ''
      },
      skills: '',
      languages: ''
    }
  },
  methods: {
    async submit() {
      const user = JSON.parse(localStorage.getItem('user') || '{}')
      const payload = {
        ...this.form,
        skills: this.skills.split(',').map(s => s.trim()).filter(Boolean),
        languages: this.languages.split(',').map(l => l.trim()).filter(Boolean),
        company: { id: user.companyId }
      }
      try {
        await axios.post('/api/jobpostings', payload)
        this.$router.push('/jobs')
      } catch (e) {
        alert('Speichern fehlgeschlagen')
      }
    }
  }
}
</script>

<style scoped>
.create-job {
  max-width: 700px;
  margin: 2rem auto;
  background: white;
  padding: 2rem;
  border-radius: 8px;
}
input, select, textarea {
  display: block;
  width: 100%;
  margin-bottom: 1rem;
  padding: 0.5rem;
}
button {
  padding: 0.7rem 1.5rem;
  background: #1a73e8;
  border: none;
  color: white;
  cursor: pointer;
}


.checkbox-group {
  display: flex;
  gap: 1rem;
  margin-bottom: 1rem;
}

.checkbox-item {
  display: flex;
  align-items: center;
  gap: 0.5rem;
}
</style>