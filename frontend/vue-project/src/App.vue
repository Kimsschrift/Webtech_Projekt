<template>
  <router-link :to="backLink" class="back-link">Zurück zur Startseite</router-link>
  <router-view />
</template>

<script>
export default {
  name: 'App',
  computed: {
    backLink() {
      const user = JSON.parse(localStorage.getItem('user') || '{}')
      return user.role ? '/afterLogin' : '/'
    }
  }
}
</script>

<style>
body {
  background: url('@/assets/Hintergrund.png') no-repeat center center fixed;
  background-size: cover;
  font-family: 'Segoe UI', sans-serif;
  margin: 0;
  padding: 0;
  color: #222;
}

.back-link {
  position: fixed;
  top: 10px;
  left: 10px;
  z-index: 1000;
  display: inline-block;
  background: #1a73e8;
  color: white;
  padding: 0.4rem 0.8rem;
  border-radius: 5px;
  text-decoration: none;
}
</style>
