<template>
  <nav class="main-nav-horizontal">
    <div class="nav-content">
      
      <div class="logo-area">
        <router-link to="/" class="logo-link">
          <h1>Humedales Urbanos de Temuco</h1>
          <p>Descubre la naturaleza en nuestra ciudad</p>
        </router-link>
      </div>
      
      <ul class="nav-links">
        <li><router-link to="/">Humedales</router-link></li>
        <li><router-link to="/informacion">Información</router-link></li>
        <li><router-link to="/conservacion">Conservación</router-link></li>
        
        <!-- Si NO está logueado: botón Iniciar Sesión -->
        <li v-if="!isLoggedIn">
          <router-link to="/admin" class="btn-admin-login">
            Iniciar Sesión
          </router-link>
        </li>

        <!-- Si está logueado: botón de menú + dropdown -->
        <li v-else class="admin-menu-wrapper">
          <button
            type="button"
            class="admin-menu-button"
            @click="toggleAdminMenu"
            aria-label="Menú de administración"
          >
            <span class="admin-bar"></span>
            <span class="admin-bar"></span>
            <span class="admin-bar"></span>
          </button>

          <div
            v-if="adminMenuOpen"
            class="admin-dropdown"
          >
            <button
              type="button"
              class="admin-dropdown-item"
              @click="goToAdminPanel"
            >
              Panel de control
            </button>
            <button
              type="button"
              class="admin-dropdown-item admin-logout"
              @click="handleLogout"
            >
              Cerrar sesión
            </button>
          </div>
        </li>
      </ul>

    </div>
  </nav>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/authStore'

const router = useRouter()
const auth = useAuthStore()

// isLoggedIn ahora viene del store reactivo
const isLoggedIn = auth.isLoggedIn

const adminMenuOpen = ref(false)

function toggleAdminMenu() {
  adminMenuOpen.value = !adminMenuOpen.value
}

function goToAdminPanel() {
  adminMenuOpen.value = false
  router.push('/admin/panel')
}

function handleLogout() {
  auth.setToken(null)   // esto limpia token y localStorage
  adminMenuOpen.value = false
  router.push('/')
}
</script>

<style scoped>
.main-nav-horizontal {
  background-color: var(--color-verde-medio); 
  padding: 1rem 2.5rem; 
  position: sticky;
  top: 0;
  z-index: 100;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
}

.nav-content {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  align-items: center;         
}

/* --- Lado Izquierdo --- */
.logo-area {
  text-align: left;
}
.logo-area h1 {
  font-size: 1.5rem; 
  margin: 0;
  color: var(--color-blanco);
  font-weight: 700;
}
.logo-area p {
  font-size: 0.9rem;
  margin: 0;
  color: rgba(255, 255, 255, 0.85);
  font-weight: 300;
}
.logo-link {
  text-decoration: none;
}

/* --- Lado Derecho --- */
.nav-links {
  list-style: none;
  display: flex;
  align-items: center; 
  margin: 0;
  padding: 0;
}

.nav-links li {
  margin-left: 2rem; 
}

.nav-links a {
  color: rgba(255, 255, 255, 0.85);
  text-decoration: none;
  font-weight: 700;
  font-size: 1rem;
  padding-bottom: 8px;
  border-bottom: 3px solid transparent; 
  transition: all 0.3s ease;
}

.nav-links a:hover {
  color: var(--color-blanco);
}

.nav-links a.router-link-exact-active:not(.btn-admin-login) {
  color: var(--color-blanco);
  border-bottom-color: var(--color-acento-arena); 
}

.btn-admin-login {
  background-color: var(--color-acento-arena); 
  color: var(--color-verde-oscuro); 
  padding: 0.6rem 1.2rem;
  border-radius: 50px; 
  font-weight: 700;
  border-bottom: none !important; 
}
.btn-admin-login:hover {
  background-color: var(--color-blanco);
  color: var(--color-verde-oscuro);
}

/* --- Botón menú admin (hamburguesa) --- */
.admin-menu-wrapper {
  position: relative;
  margin-left: 2rem; /* mismo spacing que otros <li> */
}

.admin-menu-button {
  width: 32px;
  height: 32px;
  border: none;
  background: transparent;
  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 4px;
  cursor: pointer;
  padding: 0;
  color: rgba(255, 255, 255, 0.9); /* usa color de la nav */
}

.admin-bar {
  width: 100%;
  height: 2px;
  background-color: currentColor;
  border-radius: 999px;
}

/* Dropdown del menú admin */
.admin-dropdown {
  position: absolute;
  top: 120%;
  right: 0;
  min-width: 180px;
  background: #ffffff;
  border-radius: 10px;
  box-shadow: 0 10px 25px rgba(0,0,0,0.2);
  padding: 0.5rem 0;
  z-index: 150;
}

.admin-dropdown-item {
  width: 100%;
  text-align: left;
  padding: 0.6rem 1rem;
  background: transparent;
  border: none;
  cursor: pointer;
  font-size: 0.95rem;
  color: #333;
}

.admin-dropdown-item:hover {
  background-color: #f0f0f0;
}

.admin-dropdown-item.admin-logout {
  color: #c0392b;
}

/* telefono */
@media (max-width: 768px) {
  .main-nav-horizontal {
    position: relative;
    padding: 1rem;     
  }

  .nav-content {
    flex-direction: column;
    gap: 1rem; 
  }

  .logo-area {
    text-align: center; 
  }
  
  .nav-links {
    margin-top: 0.5rem;
    flex-direction: column;
    gap: 1rem;
    width: 100%;
    text-align: center;
    padding: 0;
  }

  .nav-links li {
    margin: 0;
    width: 100%; 
  }

  .nav-links a {
    display: block; 
    padding: 0.5rem 0;
  }

  /* Ajuste: el wrapper del menú ocupa ancho completo en móvil */
  .admin-menu-wrapper {
    display: flex;
    justify-content: center;
  }

  .admin-dropdown {
    right: 50%;
    transform: translateX(50%);
  }
}
</style>