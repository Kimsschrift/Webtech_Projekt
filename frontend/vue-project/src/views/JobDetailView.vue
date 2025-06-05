<template>
  <DetailJobPosting :job="job" v-if="job"/>
  <p v-else>Lade Daten...</p>
</template>

<script>
import axios from 'axios'
import DetailJobPosting from "@/components/DetailJobPosting.vue";

export default {
  name: 'JobDetailView',
  components: {DetailJobPosting},
  data() {
    return {
      job: null
    }
  },
  mounted() {
    const id = this.$route.params.id;
    axios.get(`/api/jobpostings/${id}`)
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
