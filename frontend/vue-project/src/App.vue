<template>
  <div class="logo" style="cursor:pointer;">
    <!-- Logo (left side) -->
    <img src="@/assets/KarrierehubLogo.png" alt="Karrierehub Logo" @click="goHome"/>
  </div>
  <router-view/>
</template>

<script>

export default {
  name: 'App',
  methods: {
    goHome() {
      const user = JSON.parse(localStorage.getItem('user') || '{}')
      if (user && user.role) {

        this.$router.push('/afterLogin')
      } else {

        this.$router.push('/')
      }
    }
  }
}


</script>


<style>
/* In this section, we establish a unified frontend design */
:root {
  --primary: #377dff;
  --primary-dark: #185abc;
  --background: #e5e7eb;
  --border: #e5e7eb;
  --radius: 10px;
  --basic-font: 'Inter', 'Roboto', 'Montserrat', sans-serif;
  --shadow-card: 0 4px 24px rgba(40,60,120,0.08);
  --input-padding: 0.9rem 1rem;
}

/* Logo */
.logo{
  width: 100%;
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}

.logo img {
  height: 130px;
}

/* . */
body {
  font-family: var(--basic-font);
  background: var(--background);
  color: black;
  margin: 0;
  padding: 0;
  min-height: 100vh;
  letter-spacing: -0.01em;
}


</style>
