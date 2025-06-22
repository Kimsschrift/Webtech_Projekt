<template>
  <div class="login-container">
    <button class="login-btn" @click="login">Mit Okta anmelden</button>

    <div class="no-account">
      Sie haben noch kein Konto?
      <router-link to="/register" class="signup-link">Jetzt registrieren</router-link>
    </div>
  </div>

  <footer class="footer">
    <p>&copy; 2025 Karrierehub AG. Wir verbinden Talente mit Chancen – einfach, transparent und fair.</p>
  </footer>
</template>


<script>
import { oktaAuth } from '../router'

export default {
  name: "StartseitePage",

  methods: {
    login() {
      oktaAuth.signInWithRedirect()
    }
  }
}

</script>

<style>

.login-container {
  max-width: 380px;
  margin: 80px auto 0 auto;
  background: #fff;
  padding: 2.5rem 2rem;
  border-radius: 14px;
  box-shadow: 0 6px 32px rgba(40,60,120,0.10);
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 18px;
}




.login-btn {
  margin-top: 10px;
  padding: 0.7rem 0;
  border: none;
  border-radius: 9px;
  background: #377dff;
  color: #fff;
  font-size: 1.08rem;
  font-weight: bold;
  cursor: pointer;
  transition: background 0.2s;
}

.login-btn:hover {
  background: #185abc;
}

.no-account {
  margin-top: 1rem;
  font-size: 1rem;
}

.signup-link {
  color: #377dff;
  text-decoration: none;
  font-weight: 600;
  margin-left: 7px;
}

.footer {
  margin-top: 2.5rem;
  padding: 1.2rem 0;
  background: #e5e7eb;
  text-align: center;
  color: #6c6c6c;
  font-size: 1.01rem;
  border-radius: 0 0 14px 14px;
}

</style>