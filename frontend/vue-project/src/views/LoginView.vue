<template>
  <div class="widget-wrapper">
    <div id="okta-signin"></div>
  </div>
</template>

<script setup>
import { onMounted, onBeforeUnmount } from 'vue'
import OktaSignIn from '@okta/okta-signin-widget'
import { oktaAuth } from '../router'

let widget

onMounted(() => {
  widget = new OktaSignIn({
    baseUrl: import.meta.env.VITE_OKTA_BASE_URL,
    clientId: import.meta.env.VITE_OKTA_CLIENT_ID,
    redirectUri: window.location.origin + '/login/callback',
    authParams: { issuer: import.meta.env.VITE_OKTA_ISSUER, pkce: true }
  })

  widget.renderEl({ el: '#okta-signin' }, res => {
    if (res.status === 'SUCCESS') {
      oktaAuth.token.getWithRedirect({sessionToken: res.session.token})
    }
  })
})

onBeforeUnmount(() => {
  widget?.remove()
})
</script>

<style scoped>
.widget-wrapper {
  max-width: 400px;
  margin: 3rem auto;
}
</style>