<template>
  <div class="job-list-page">
    <JobFilterSidebar @apply-filters="applyFilters" />
    <div class="job-list">
      <button v-if="isCompany" class="create-btn" @click="$router.push('/jobs/new')">Neue Stelle</button>
      <div v-for="job in filteredJobs" :key="job.id" @click="goToDetail(job.id)" class="job-card">
        <h3>{{ job.title }}</h3>
        <p>{{ job.location }} | {{ job.company?.name }} | {{ job.workTime }}</p>
        <p>Sprache: {{ job.languages.join(', ') }}</p>
        <p>Gehalt: €{{ job.expectedSalary }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import JobFilterSidebar from '../components/JobFilterSidebar.vue'

export default {
  name: 'JobListView',
  components: { JobFilterSidebar },
  data() {
    return {
      jobs: [],
      activeFilters: {},
      isCompany: false
    }
  },
  computed: {
    filteredJobs() {
      return this.jobs.filter(job => {
        const f = this.activeFilters;
        const matchesRemote = !f.remoteAllowed || job.remoteAllowed;
        const matchesSalary = job.expectedSalary >= (f.minSalary || 0);
        const matchesLang = !f.language?.length || f.language.some(lang => job.languages.includes(lang));
        const matchesExp = !f.requiresExperience || job.requiresExperience;
        const matchesIndustry = !f.industry || job.industry === f.industry;
        const matchesWorkTime = !f.workTime || job.workTime === f.workTime;
        const matchesEmpType = !f.employmentType || job.employmentType === f.employmentType;
        return matchesRemote && matchesSalary && matchesLang && matchesExp && matchesIndustry && matchesWorkTime && matchesEmpType;
      });
    }
  },
  mounted() {
    axios.get('/api/jobpostings')
        .then(res => this.jobs = res.data);
    const user = JSON.parse(localStorage.getItem('user') || '{}')
    this.isCompany = user.role === 'COMPANY'
  },
  methods: {
    applyFilters(filters) {
      this.activeFilters = filters;
    },
    goToDetail(id) {
      this.$router.push(`/jobs/${id}`);
    }
  }
}
</script>

<style scoped>
.job-list-page {
  display: flex;
  gap: 2rem;
  padding: 3rem;
}
.job-list {
  flex-grow: 1;
}
.job-card {
  background: white;
  border-left: 4px solid #1a73e8;
  padding: 1.5rem;
  border-radius: 10px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  transition: transform 0.2s ease;
}
.job-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0,0,0,0.1);
}

.create-btn {
  background: #1a73e8;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  margin-bottom: 1rem;
  cursor: pointer;
  border-radius: 5px;
}

</style>
