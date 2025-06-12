<template>
  <div class="admin-wrapper">
    <div class="columns">
      <div class="jobs-column">
        <input v-model="jobQuery" @input="filterJobSuggestions" placeholder="Job suchen" />
        <ul v-if="jobSuggestions.length" class="suggestions">
          <li v-for="s in jobSuggestions" :key="s.id" @click="scrollToJob(s.id)">{{ s.title }}</li>
        </ul>
        <ul class="list">
          <li v-for="job in jobs" :key="job.id" :id="'job-' + job.id">
            {{ job.title }} - {{ job.company?.name }}
            <button class="delete-btn" @click="deleteJob(job.id)">Löschen</button>
          </li>
        </ul>
      </div>
      <div class="companies-column">
        <input v-model="companyQuery" @input="filterCompanySuggestions" placeholder="Unternehmen suchen" />
        <ul v-if="companySuggestions.length" class="suggestions">
          <li v-for="c in companySuggestions" :key="c.id" @click="scrollToCompany(c.id)">{{ c.name }}</li>
        </ul>
        <ul class="list">
          <li v-for="company in companies" :key="company.id" :id="'company-' + company.id">
            {{ company.name }}
            <button class="delete-btn" @click="deleteCompany(company.id)">Löschen</button>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'AdminDashboardView',
  data() {
    return {
      jobs: [],
      companies: [],
      jobQuery: '',
      companyQuery: '',
      jobSuggestions: [],
      companySuggestions: []
    }
  },
  mounted() {
    axios.get('/api/jobpostings').then(res => this.jobs = res.data)
    axios.get('/api/companies').then(res => this.companies = res.data)
  },
  methods: {
    filterJobSuggestions() {
      const q = this.jobQuery.toLowerCase()
      this.jobSuggestions = q ? this.jobs.filter(j => j.title.toLowerCase().startsWith(q)) : []
    },
    filterCompanySuggestions() {
      const q = this.companyQuery.toLowerCase()
      this.companySuggestions = q ? this.companies.filter(c => c.name && c.name.toLowerCase().startsWith(q)) : []
    },
    scrollToJob(id) {
      document.getElementById('job-' + id)?.scrollIntoView({ behavior: 'smooth' })
      this.jobSuggestions = []
    },
    scrollToCompany(id) {
      document.getElementById('company-' + id)?.scrollIntoView({ behavior: 'smooth' })
      this.companySuggestions = []
    },
    async deleteJob(id) {
      const user = JSON.parse(localStorage.getItem('user') || '{}')
      await axios.delete(`/api/jobpostings/${id}`, { headers: { 'X-User-Role': user.role } })
      this.jobs = this.jobs.filter(j => j.id !== id)
    },
    async deleteCompany(id) {
      await axios.delete(`/api/companies/${id}`)
      this.companies = this.companies.filter(c => c.id !== id)
    }
  }
}
</script>

<style scoped>
.admin-wrapper {
  padding: 2rem;
}
.columns {
  display: flex;
  gap: 2rem;
}
.list {
  max-height: 60vh;
  overflow-y: auto;
  margin-top: 1rem;
  padding-left: 0;
}
.list li {
  list-style: none;
  padding: 0.5rem;
  border-bottom: 1px solid #ccc;
}
.delete-btn {
  margin-left: 1rem;
  background: #e53935;
  color: #fff;
  border: none;
  padding: 0.2rem 0.6rem;
  cursor: pointer;
}
.suggestions {
  background: white;
  border: 1px solid #ccc;
  list-style: none;
  padding-left: 0;
  margin: 0;
}
.suggestions li {
  padding: 0.3rem 0.5rem;
  cursor: pointer;
}
.suggestions li:hover {
  background: #eee;
}
</style>