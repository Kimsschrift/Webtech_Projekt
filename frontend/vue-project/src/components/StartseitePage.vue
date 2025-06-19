<template>
  <div class="login-container">
    <p style="font-weight: bold">Bitte geben Sie Ihre Daten ein</p>

    <form class="login-form" @submit.prevent="submit">
      <label for="email">E-mail</label>
      <input id="email" v-model="email" type="email" placeholder="E-Mail" required/>

      <label for="password">Passwort</label>
      <input id="password" v-model="password" type="password" placeholder="Passwort" required />

      <!-- not implemented -->
      <div class="remember-forgot">
        <label class="remember-label">
          <input type="checkbox" /> Für 30 Tage merken
        </label>

        <!-- not implemented -->
        <a href="#" class="forgot-link">Passwort vergessen?</a>
      </div>

      <button class="login-btn" type="submit">Einloggen</button>
      <div v-if="errorMsg" class="login-error">{{ errorMsg }}</div>

      <!-- not implemented -->
      <div class="google-login">
        <img src="@/assets/GoogleLogo.png" alt="Google Logo" class="google-logo"/>
        <span>Mit Google anmelden</span>
      </div>
    </form>

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
import axios from 'axios'

export default {
  name: "StartseitePage",

  data() {
    return {
      form: { email: '', password: '' },
      errorMsg: ""
    }
  },
  methods: {
    async submit() {
      try {
        const res = await axios.post('/api/login', this.form)
        localStorage.setItem('user', JSON.stringify(res.data))
        this.$router.push('/afterLogin')
      } catch (e) {
        alert('Login fehlgeschlagen')
      }
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


.login-form {
  display: flex;
  flex-direction: column;
  gap: 0.9rem;
  width: 100%;
}

.login-form label {
  font-weight: 500;
}

.login-form input[type="email"],
.login-form input[type="password"] {
  padding: 0.7rem 0.8rem;
  border-radius: 8px;
  border: 1px solid #e5e7eb;
  font-size: 1rem;
}

.remember-forgot {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 0.3rem;
}

.remember-label {
  font-size: 0.98rem;
  display: flex;
  align-items: center;
  gap: 5px;
}

.forgot-link {
  font-size: 0.95rem;
  color: #377dff;
  text-decoration: none;
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

.google-login {
  margin: 1.2rem 0 0.6rem 0;
  display: flex;
  align-items: center;
  gap: 9px;
  justify-content: center;
  padding: 0.6rem;
  background: #f8f9fa;
  border-radius: 8px;
  cursor: pointer;
  font-weight: 500;
  border: 1px solid #e5e7eb;
  transition: background 0.15s;
}

.google-login:hover {
  background: #f1f3f4;
}

.google-logo {
  width: 23px;
  height: 23px;
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

.login-error {
  color: red;
  margin-top: 0.6rem;
  text-align: center;
  font-weight: bold;
}
</style>