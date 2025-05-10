<template>
  <div id="app">
    <header class="navbar">
      <div class = "logo-box">
        <img :src="logo" alt="Karrierehub Logo" class = "logo-banner">
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
  padding: 0;
  height: 210px;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: auto;
}

.logo-banner {
  width: 100%;
  height: 100%;
  object-fit: cover;

}

.main-section {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 3rem 1rem;
}

.access-panels {
  display: flex;
  flex-wrap: wrap;
  gap: 2rem;
  justify-content: center;
  width: 100%;
  max-width: 1000px;
}

.panel {
  flex: 1 1 300px;
  background-color: #eef2f7;
  padding: 2rem;
  border-radius: 8px;
  text-align: center;
  box-shadow: 0 0 10px rgba(0,0,0,0.05);
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
