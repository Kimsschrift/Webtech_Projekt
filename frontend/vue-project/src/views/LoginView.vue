<template>
  <div class="login-container">
    <div id="sign-in-widget"></div>
  </div>
</template>

<script>
import { onMounted } from 'vue'
import { oktaSignIn, oktaAuth } from '../router'

export default {
  setup() {
    onMounted(() => {
      oktaSignIn.showSignInToGetTokens({ el: '#sign-in-widget' })
          .then(tokens => oktaAuth.handleLoginRedirect(tokens))
          .catch(err => console.error(err))
    })
  }
}
</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: 3rem auto;
  padding: 2rem;
  background: white;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0,0,0,0.1);
}

</style>