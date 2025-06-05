<template>
  <div class="dashboard">
    <h2>Unternehmensbereich</h2>
    <router-link to="/jobs/new" class="btn">Neue Stellenausschreibung</router-link>
  </div>
</template>

<script>
export default {
  name: 'CompanyDashboardView'
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
</style>