<template>
  <div class="dashboard">
    <h2>Unternehmensbereich</h2>
    <router-link to="/jobs/new" class="btn">Neue Stellenausschreibung</router-link>
    <button class="delete-account" @click="deleteAccount">Account löschen</button>

    <ul class="job-list">
      <li v-for="job in jobs" :key="job.id" class="job-item">
        <span>{{ job.title }}</span>
        <div class="actions">
          <router-link :to="`/jobs/${job.id}/edit`" class="edit-btn">Bearbeiten</router-link>
          <button class="delete-btn" @click="deleteJob(job.id)">Löschen</button>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'CompanyDashboardView',
  data() {
    return { jobs: [] }
  },
  mounted() {
    const user = JSON.parse(localStorage.getItem('user') || '{}')
    if (!user.companyId) return
    axios.get('/api/jobpostings')
        .then(res => {
          this.jobs = res.data.filter(j => j.company.id === user.companyId)
        })
  },
  methods: {
    async deleteJob(id) {
      const user = JSON.parse(localStorage.getItem('user') || '{}')
      try {
        await axios.delete(`/api/jobpostings/${id}`, { headers: { 'X-Company-Id': user.companyId } })
        this.jobs = this.jobs.filter(j => j.id !== id)
      } catch (e) {
        alert('Löschen fehlgeschlagen')
      }
    },
    async deleteAccount() {
      const user = JSON.parse(localStorage.getItem('user') || '{}')
      try {
        await axios.delete(`/api/appUsers/${user.userId}`, { headers: { 'X-User-Id': user.userId } })
        localStorage.removeItem('user')
        this.$router.push('/')
      } catch (e) {
        alert('Account konnte nicht gelöscht werden')
      }
    }
  }
}
</script>

<style scoped>
.dashboard {
  max-width: 600px;
  margin: 2rem auto;
  text-align: center;
}
.btn {
  display: inline-block;
  padding: 0.75rem 1.5rem;
  background: #1a73e8;
  color: white;
  text-decoration: none;
  border-radius: 5px;
}
.delete-account {
  display: inline-block;
  margin-left: 1rem;
  padding: 0.75rem 1.5rem;
  background: #e53935;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
.job-list {
  list-style: none;
  padding: 0;
  margin-top: 2rem;
}
.job-item {
  background: white;
  padding: 1rem 1.5rem;
  margin-bottom: 1rem;
  border-left: 4px solid #1a73e8;
  border-radius: 6px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.actions {
  display: flex;
  gap: 0.5rem;
}
.edit-btn {
  background: #4285f4;
  color: white;
  padding: 0.3rem 0.6rem;
  border-radius: 4px;
  text-decoration: none;
}
.delete-btn {
  background: #e53935;
  color: white;
  border: none;
  padding: 0.3rem 0.6rem;
  border-radius: 4px;
  cursor: pointer;
}
</style>