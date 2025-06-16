<template>
  <div class="detail" v-if="job">
    <h2>{{ job.title }}</h2>
    <p><strong>Firma:</strong> {{ job.company?.name }}</p>
    <p v-if="job.company?.contactEmail"><strong>Kontaktemail:</strong> {{ job.company.contactEmail }}</p>
    <p><strong>Ort:</strong> {{ job.location }}</p>
    <p><strong>Skills:</strong> {{ job.skills.join(', ') }}</p>
    <p><strong>Sprachen:</strong> {{ job.languages.join(', ') }}</p>
    <p><strong>Gehalt:</strong> €{{ job.expectedSalary }}</p>
    <p><strong>Arbeitszeit:</strong> {{ job.workTime }}</p>
    <p><strong>Erfahrung:</strong> {{ job.requiresExperience ? 'Ja' : 'Nein' }}</p>
    <p><strong>Beschreibung:</strong> {{ job.description }}</p>
    <p><strong>Aufgaben:</strong> {{ job.taskArea }}</p>
    <p><strong>Profil:</strong> {{ job.userProfile }}</p>
    <p><strong>Benefits:</strong> {{ job.benefits }}</p>
  </div>
</template>

<script>
export default {
  name: 'DetailJobPosting',
  props: {
    job: Object
  }
}
</script>

<style scoped>
.detail {
  background: white;
  padding: 2rem;
  border-left: 6px solid #1a73e8;
  border-radius: 12px;
  box-shadow: 0 4px 16px rgba(0,0,0,0.1);
  max-width: 700px;
  margin: 3rem auto;
}
</style>