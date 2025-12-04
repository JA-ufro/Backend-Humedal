<template>
  <div class="home-container">
    <div class="humedal-card" v-for="humedal in humedales" :key="humedal.id">
      <img
        :src="humedal.imagenPrincipal || humedal.mapaUrl"
        :alt="'Imagen de ' + humedal.nombre"
        class="card-map"
      >
      <div class="card-content">
        <h2>{{ humedal.nombre }}</h2>
        <p>{{ humedal.descripcionCorta }}</p>
        <router-link :to="'/humedal/' + humedal.id" class="btn-explorar">
          Explorar más
        </router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import api from '../services/api';

const humedales = ref([]);

onMounted(async () => {
  try {
    const response = await api.get('/humedales');
    humedales.value = response.data;
  } catch (error) {
    console.error("Error cargando humedales:", error);
  }
});
</script>

<style scoped>
.home-container {
  display: flex;
  justify-content: center;
  gap: 2.5rem;
  padding: 3rem 2rem;
  flex-wrap: wrap;
}
.humedal-card {
  width: 400px;
  background-color: var(--color-blanco);
  border-radius: 16px;
  box-shadow: var(--shadow-sutil);
  overflow: hidden;
  transition: all 0.3s ease;
}

.humedal-card:hover {
  transform: translateY(-8px);
  box-shadow: 0 15px 35px -10px rgba(45, 84, 59, 0.3);
}
.card-map {
  width: 100%;
  height: 220px;
  object-fit: cover;
  background-color: var(--color-tierra-clara); 
}
.card-content {
  padding: 1.5rem 2rem 2rem 2rem;
}
.card-content h2 {
  margin-top: 0;
  margin-bottom: 0.5rem;
  font-size: 1.75rem;
}

.card-content p {
  font-size: 1rem;
  margin-bottom: 1.5rem;
}
.btn-explorar {
  display: inline-block;
  background-color: var(--color-verde-medio); 
  color: white;
  padding: 0.8rem 1.8rem;
  border-radius: 50px;
  text-decoration: none;
  font-weight: 700;
  transition: all 0.3s ease;
}
.btn-explorar:hover {
  background-color: var(--color-verde-oscuro);
  transform: scale(1.05);
}

/*-telefono-*/

@media (max-width: 768px){
  .hero-section h1 {
    font-size: 2rem;
  }

  .card-container {
    grid-template-columns: 1fr;
    padding: 0 1rem;
  }

  .humedal-card {
    width: 100%;
    margin-bottom: 2rem;
  }
}
</style>