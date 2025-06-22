<template>
  <div class="after-login">
    <h2>Willkommen</h2>
    <button @click="$router.push('/personal-info')">Persönliche Daten</button>
    <button @click="$router.push('/jobs')">JobPostingAnsehen</button>
    <button @click="logout">Logout</button>
  </div>
</template>

<script>
export default {
  name: 'StartPageAfterLogin',

  methods: {
    async logout() {
      await this.$auth.logout()
    }
  }
}
</script>

<style scoped>
.after-login {
  text-align: center;
  margin-top: 3rem;
}
button {
  margin: 0.5rem;
  padding: 0.6rem 1.2rem;
  background: #1a73e8;
  border: none;
  color: white;
  cursor: pointer;
  border-radius: 5px;
}
</style>