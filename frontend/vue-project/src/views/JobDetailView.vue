<template>
  <div>
    <DetailJobPosting :job="job" v-if="job"/>
    <button v-if="showDelete" class="delete-btn" @click="deleteJob">Löschen</button>
    <p v-if="!job">Lade Daten...</p>
  </div>
</template>

<script>
import axios from 'axios'
import DetailJobPosting from "@/components/DetailJobPosting.vue";

export default {
  name: 'JobDetailView',
  components: {DetailJobPosting},
  data() {
    return {
      job: null,
      showDelete: false
    }
  },
  mounted() {
    const id = this.$route.params.id;
    axios.get(`/api/jobpostings/${id}`)
        .then(res => {
          this.job = res.data
          const user = JSON.parse(localStorage.getItem('user') || '{}')
          this.showDelete = user.role === 'COMPANY' && user.companyId === this.job.company.id
        })
        .catch(err => console.error('Fehler beim Laden:', err))
  },
  methods: {
    async deleteJob() {
      const user = JSON.parse(localStorage.getItem('user') || '{}')
      try {
        await axios.delete(`/api/jobpostings/${this.job.id}`, {headers: {'X-Company-Id': user.companyId}})
        this.$router.push('/jobs')
      } catch (e) {
        alert('Löschen fehlgeschlagen')
      }
    }
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

.delete-btn {
  background: #e53935;
  border: none;
  color: white;
  padding: 0.5rem 1rem;
  margin: 1rem auto;
  display: block;
  border-radius: 5px;
  cursor: pointer;
}
</style>
