<template>
  <div class="job-list">
    <h2>Offene Stellenanzeigen</h2>
    <ul v-if="jobs.length > 0">
      <li v-for="job in jobs" :key="job.id" class="job-item">
        <h3>{{ job.title }}</h3>
        <p><strong>Firma:</strong> {{ job.company?.name }}</p>
        <p><strong>Ort:</strong> {{ job.location }}</p>
        <p><strong>Gehalt:</strong> €{{ job.expectedSalary }}</p>
        <p><strong>Erforderlicher Abschluss:</strong> {{ job.minDegree }}</p>
        <p><strong>Sprachen:</strong> {{ job.languages.join(', ') }}</p>
        <p><strong>Skills:</strong> {{ job.skills.join(', ') }}</p>
        <p><strong>Remote möglich:</strong> {{ job.remoteAllowed ? 'Ja' : 'Nein' }}</p>
        <p><strong>Berufserfahrung erforderlich:</strong> {{ job.requiresExperience ? 'Ja' : 'Nein' }}</p>
        <p><strong>Beschäftigungsart:</strong> {{ job.employmentType }}</p>
        <p><strong>Arbeitszeitmodell:</strong> {{ job.workTime }}</p>
        <p><strong>Branche:</strong> {{ job.industry }}</p>
      </li>
    </ul>
    <p v-else>Zurzeit gibt es keine offenen Stellen.</p>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'JobPostingList',
  data() {
    return {
      jobs: []
    }
  },
  mounted() {
    axios.get('/api/jobpostings')
        .then(response => {
          this.jobs = response.data
        })
        .catch(error => {
          console.error("Fehler beim Laden der Jobs:", error)
        })
  }
}
</script>

<style scoped>
.job-list {
  max-width: 800px;
  margin: 3rem auto;
  background: #f9f9f9;
  padding: 2rem;
  border-radius: 10px;
}
.job-item {
  border-bottom: 1px solid #ccc;
  padding: 1rem 0;
}
</style>
