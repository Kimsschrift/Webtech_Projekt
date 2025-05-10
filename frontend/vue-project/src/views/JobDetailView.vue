<template>
  <div class="job-detail" v-if="job">
    <h2>{{ job.title }}</h2>
    <p><strong>Ort:</strong> {{ job.location }}</p>
    <p><strong>Firma:</strong> {{ job.company?.name }}</p>
    <p><strong>Skills:</strong> {{ job.skills.join(', ') }}</p>
    <p><strong>Sprachen:</strong> {{ job.languages.join(', ') }}</p>
    <p><strong>Gehalt:</strong> €{{ job.expectedSalary }}</p>
    <p><strong>Arbeitszeit:</strong> {{ job.workTime }}</p>
    <p><strong>Erfahrung:</strong> {{ job.requiresExperience ? 'Ja' : 'Nein' }}</p>
  </div>
  <p v-else>Lade Daten...</p>
</template>

<script>
import axios from 'axios'

export default {
  name: 'JobDetailView',
  data() {
    return {
      job: null
    }
  },
  mounted() {
    const id = this.$route.params.id;
    axios.get(`http://localhost:8080/domain/jobpostings/${id}`)
        .then(res => this.job = res.data)
        .catch(err => console.error('Fehler beim Laden:', err))
  }
}
</script>

<style scoped>
.job-detail {
  background: white;
  padding: 2rem;
  border-left: 6px solid #1a73e8;
  border-radius: 12px;
  box-shadow: 0 4px 16px rgba(0,0,0,0.1);
  max-width: 700px;
  margin: 3rem auto;
}
</style>
