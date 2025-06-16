<template>
  <div class="job-list-page">
    <JobFilterSidebar @apply-filters="applyFilters" />
    <div class="job-list">
      <div class="top-actions">

        <button v-if="isCompany" class="create-btn" @click="$router.push('/jobs/new')">Neue Stelle</button>

      </div>

      <div v-for="job in filteredJobs" :key="job.id" @click="goToDetail(job.id)" class="job-card">
        <h3>{{ job.title }}</h3>
        <p>{{ job.location }} | {{ job.company?.name }} | {{ job.workTime }}</p>
        <p>Sprache: {{ job.languages.join(', ') }}</p>
        <p>Gehalt: €{{ job.expectedSalary }}</p>
        <button v-if="canDelete(job)" class="delete-btn" @click.stop="deleteJob(job.id)">Löschen</button>
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
      activeFilters: {}
    }
  },
  computed: {
    isCompany() {
      const user = JSON.parse(localStorage.getItem('user') || '{}')
      return ['COMPANY', 'ADMIN'].includes(user.role)
    },
    filteredJobs() {
      return this.jobs.filter(job => {
        const f = this.activeFilters;
        const matchesRemote = !f.remoteAllowed || job.remoteAllowed;
        const matchesSalary = job.expectedSalary >= (f.minSalary || 0);
        const matchesLang = !f.language?.length || f.language.some(lang =>
            job.languages.map(l => l.toLowerCase()).includes(lang.toLowerCase())
        );
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
  },
  methods: {
    applyFilters(filters) {
      this.activeFilters = filters;
    },
    goToDetail(id) {
      this.$router.push(`/jobs/${id}`);
    },
    canDelete(job) {
      const user = JSON.parse(localStorage.getItem('user') || '{}')
      if (user.role === 'ADMIN') return true
      return user.role === 'COMPANY' && user.companyId === job.company.id
    },
    async deleteJob(id) {
      const user = JSON.parse(localStorage.getItem('user') || '{}')
      try {
        await axios.delete(`/api/jobpostings/${id}`, { headers: { 'X-Company-Id': user.companyId, 'X-User-Role': user.role } })
        this.jobs = this.jobs.filter(j => j.id !== id)
      } catch (e) {
        alert('Löschen fehlgeschlagen')
      }

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
  position: relative;
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

.top-actions {
  display: flex;
  gap: 1rem;
  margin-bottom: 1rem;
}

.delete-btn {
  position: absolute;
  top: 10px;
  right: 10px;
  background: #e53935;
  border: none;
  color: white;
  padding: 0.3rem 0.6rem;
  cursor: pointer;
  border-radius: 4px;
}

</style>
