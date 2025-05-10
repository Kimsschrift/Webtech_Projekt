<template>
  <div id="app">
    <header class="navbar">
      <div class="logo-box">
        <img :src="logo" alt="Karrierehub Logo" class="logo-banner">
      </div>
    </header>

    <main class="main-section">
      <div class="access-panels">
        <div class="panel">
          <h2>Für Unternehmen</h2>
          <p>Login oder Registrierung für Arbeitgeber</p>
          <div class="button-group">
            <button>Anmelden</button>
            <button class="secondary">Login</button>
          </div>
        </div>
        <div class="panel">
          <h2>Für Bewerber</h2>
          <p>Login oder Registrierung für Jobsuchende</p>
          <div class="button-group">
            <button>Anmelden</button>
            <button class="secondary">Login</button>
          </div>
        </div>
      </div>


      <div class="job-link-container">
        <router-link to="/jobs" class="job-link-button">
          🔍 Offene Stellenanzeigen ansehen
        </router-link>
      </div>
    </main>

    <footer class="footer">
      <p>&copy; 2025 Karrierehub AG. Wir verbinden Talente mit Chancen – einfach, transparent und fair.</p>
    </footer>
  </div>
</template>

<script>
import Logo from '@/assets/KarrierehubLogo.png'

export default {
  name: 'StartseitePage',
  data() {
    return {
      logo: Logo
    }
  }
}
</script>

<style scoped>
#app {
  position: relative;
  z-index: 1;
  background-color: rgba(255, 255, 255, 0.9);
}

#app::before {
  content: '';
  position: fixed;
  top: 0; left: 0; right: 0; bottom: 0;
  background: url('@/assets/Hintergrund.png') no-repeat center center;
  background-size: cover;
  z-index: -1;
}

.navbar {
  background-color: #1a73e8;
  text-align: center;
  padding: 1rem 0;
}

.logo-box {
  background-color: white;
  padding: 1.5rem 0;
  display: flex;
  align-items: center;
  justify-content: center;
}

.logo-banner {
  width: 60%;
  max-width: 210px;
  height: auto;
  display: block;
  margin: 0 auto;
}

.access-panels {
  display: flex;
  justify-content: center;
  gap: 2rem;
  flex-wrap: wrap;
  margin-top: 2rem;
}

.panel {
  background: white;
  border-left: 5px solid #1a73e8;
  box-shadow: 0 4px 20px rgba(0,0,0,0.05);
  padding: 2rem;
  border-radius: 12px;
  min-width: 300px;
}

.main-section {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 3rem 1rem;
}


.panel h2 {
  margin-bottom: 1rem;
  color: #1a73e8;
}

.button-group {
  display: flex;
  justify-content: space-between;
  margin-top: 1rem;
  gap: 1rem;
}

.button-group button {
  flex: 1;
  padding: 0.75rem 1rem;
  font-size: 1rem;
  background-color: #1a73e8;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.button-group .secondary {
  background-color: #4285f4;
}


.job-link-container {
  margin-top: 3rem;
  text-align: center;
}

.job-link-button {
  display: inline-block;
  background-color: #1a73e8;
  color: white;
  padding: 0.75rem 1.5rem;
  font-size: 1rem;
  border-radius: 5px;
  text-decoration: none;
  transition: background-color 0.3s ease;
}

.job-link-button:hover {
  background-color: #0d5cc0;
}

.footer {
  margin-top: 3rem;
  text-align: center;
  padding: 2rem;
  background-color: #1a73e8;
  color: white;
  font-size: 0.95rem;
  line-height: 1.6;
}
</style>
