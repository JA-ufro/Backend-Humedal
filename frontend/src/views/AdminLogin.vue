<template>
  <div class="login-container">
    <div class="login-box">
      <h2>Panel de Administración</h2>
      <p>Inicia sesión para editar el contenido.</p>

      <form @submit.prevent="handleLogin">
        <div class="form-group">
          <label for="username">Usuario</label>
          <input type="text" id="username" v-model="username" placeholder="Ingresar usuario.">
        </div>
        <div class="form-group">
          <label for="password">Contraseña</label>
          <div class="password-wrapper">
            <input :type="showPassword ? 'text' : 'password'" id="password" v-model="password" placeholder="Ingresar contraseña.">
            <button type="button" class="toggle-password" @click="showPassword = !showPassword"
              :aria-label="showPassword ? 'Ocultar contraseña' : 'Mostrar contraseña'">
              <!-- Ícono simple de ojo usando SVG -->
              <svg v-if="!showPassword" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" class="eye-icon"
                aria-hidden="true">
                <path fill="currentColor" d="M12 4.5C7 4.5 2.73 7.61 1 12c1.73 4.39 6 
             7.5 11 7.5s9.27-3.11 11-7.5C21.27 7.61 17 
             4.5 12 4.5zm0 12a4.5 4.5 0 1 1 0-9 
             4.5 4.5 0 0 1 0 9zm0-7.5A3 3 0 1 0 15 
             12a3 3 0 0 0-3-3z" />
              </svg>
              <!-- Ícono de ojo tachado cuando está visible -->
              <svg v-else xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" class="eye-icon" aria-hidden="true">
                <path fill="currentColor" d="M2.1 3.51L3.51 2.1l18.39 18.39-1.41 1.41-2.15-2.15C16.6 
             20.39 14.39 21 12 21 7 21 2.73 17.89 1 13.5c.73-1.85 
             1.95-3.47 3.5-4.71L2.1 3.51zM12 7.5c1.09 0 2.09.35 
             2.91.94L13.35 9.99A3 3 0 0 0 9.99 13.35l-1.64 
             1.64A4.5 4.5 0 0 1 12 7.5zm10 6c-.68 1.74-1.81 3.26-3.24 
             4.45L16.23 16.4A7.46 7.46 0 0 0 19.5 12c-1.2-2.71-3.87-4.5-7.5-4.5-.66 
             0-1.29.07-1.9.21L8.46 5.07C9.61 4.79 10.78 4.5 12 
             4.5c5 0 9.27 3.11 11 7.5z" />
              </svg>
            </button>
          </div>
        </div>
        <button type="submit" class="btn-login">Entrar</button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import api from '@/services/api'
import { useAuthStore } from '@/stores/authStore'

const router = useRouter()
const username = ref('')
const password = ref('')
const errorMsg = ref('')

// Estado para mostrar/ocultar contraseña
const showPassword = ref(false)
const auth = useAuthStore()

async function handleLogin() {
  try {
    // 1. Enviamos usuario y contraseña al backend
    // Coincide con LoginRequest.java
    const response = await api.post('/auth/login', {
      username: username.value,
      password: password.value
    });

    const token = response.data.token;
    auth.setToken(token);
    alert("¡Login exitoso!");
    router.push('/admin/panel');

  } catch (error) {
    console.error("Error de login:", error);
    alert("Usuario o contraseña incorrectos");
  }
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 4rem 2rem;
}

.login-box {
  background: var(--color-blanco);
  padding: 2.5rem;
  border-radius: 16px;
  box-shadow: var(--shadow-moderno);
  width: 100%;
  max-width: 400px;
  text-align: center;
}

.login-box h2 {
  margin-top: 0;
}

.form-group {
  margin-bottom: 1.5rem;
  text-align: left;
}

.form-group label {
  display: block;
  margin-bottom: 0.5rem;
  font-weight: 700;
}

.form-group input {
  width: 100%;
  padding: 0.75rem;
  border-radius: 8px;
  border: 1px solid #ccc;
  font-size: 1rem;
}

.btn-login {
  display: inline-block;
  background-color: var(--color-verde-medio);
  color: white;
  padding: 0.8rem 1.8rem;
  border-radius: 50px;
  text-decoration: none;
  font-weight: 700;
  transition: all 0.3s ease;
  cursor: pointer;
  border: none;
  font-size: 1rem;
  width: 100%;
}

.btn-login:hover {
  background-color: var(--color-verde-oscuro);
}
.password-wrapper {
  position: relative;
  display: flex;
  align-items: center;
}

.password-wrapper input {
  width: 100%;
  padding-right: 2.5rem; /* espacio para el botón ojo */
}

.toggle-password {
  position: absolute;
  right: 0.6rem;
  background: transparent;
  border: none;
  cursor: pointer;
  color: #888;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0;
}

.toggle-password:hover {
  color: #555;
}

.eye-icon {
  width: 20px;
  height: 20px;
}

/*-telefono-*/

@media (max-width: 768px) {
  .login-container {
    padding: 2rem 1rem;
    align-items: flex-start;
    margin-top: 2rem;
  }

  input {
    font-size: 16px;
  }
}
</style>