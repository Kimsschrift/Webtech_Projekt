<template>
  <div class="register-type-select">
    <h2>Bitte wählen Sie den Registrierungstyp</h2>
    <div class="button-group">
      <router-link to="/register/applicant" class="register-btn">Als Bewerber registrieren</router-link>
      <router-link to="/register/company" class="register-btn">Als Unternehmen registrieren</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: 'RegisterTypeSelectView'
}
</script>

<style>
.register-type-select {
  max-width: 380px;
  margin: 100px auto 0 auto;
  text-align: center;
}
.button-group {
  margin-top: 1.5rem;
  display: flex;
  flex-direction: column;
  gap: 1.2rem;
}
.register-btn {
  padding: 0.8rem 0;
  border-radius: 8px;
  background: #377dff;
  color: #fff;
  text-decoration: none;
  font-size: 1.09rem;
  font-weight: bold;
  transition: background 0.18s;
}
.register-btn:hover {
  background: #185abc;
}
</style>
