<template>
  <div class="dashboard-container">
    <div class="dashboard-header">
      <h2>Panel de Contenidos</h2>
    </div>
    <p>Selecciona un humedal para editar o eliminar su contenido.</p>

    <div class="content-list">
      <div v-for="humedal in humedales" :key="humedal.id" class="content-item">
        <span>{{ humedal.nombre }}</span>
        <div class="actions">
          <router-link :to="'/admin/edit/' + humedal.id" class="btn-edit">
            Editar
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import api from '@/services/api'

const humedales = ref([])

onMounted(async () => {
  try {
    const res = await api.get('/humedales')
    humedales.value = res.data
  } catch (error) {
    console.error('Error cargando humedales para admin:', error)
    alert('No se pudieron cargar los humedales.')
  }
})
</script>

<style scoped>
.dashboard-container {
  max-width: 960px;
  margin: 3rem auto;
  padding: 2.5rem;
  background: var(--color-blanco);
  border-radius: 16px;
  box-shadow: var(--shadow-moderno);
}
.dashboard-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 2px solid var(--color-tierra-clara);
  padding-bottom: 1rem;
  margin-bottom: 1rem;
}
.dashboard-header h2 {
  margin: 0;
  color: var(--color-verde-oscuro);
}
.dashboard-container p {
  margin-top: 1rem;
  color: var(--color-texto-secundario);
}

.content-list {
  margin-top: 2rem;
}
.content-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1.5rem;
  border: 1px solid var(--color-tierra-clara);
  border-radius: 8px;
  margin-bottom: 1rem;
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.content-item:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0,0,0,0.05);
  border-left: 5px solid var(--color-verde-medio);
}

.content-item span {
  font-weight: 700;
  font-size: 1.1rem;
}
.actions {
  display: flex;
  gap: 1rem;
}

.btn-edit {
  background-color: var(--color-acento-arena);
  color: var(--color-verde-oscuro);
  padding: 0.6rem 1.5rem; 
  border: none;
  border-radius: 50px;
  font-weight: 700;
  cursor: pointer;
  transition: all 0.3s ease;
  text-decoration: none;
  font-size: 0.9rem;
}

.btn-edit:hover {
  background-color: var(--color-verde-oscuro);
  color: white; 
}
</style>