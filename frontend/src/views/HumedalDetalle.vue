<template>
  <div class="detalle-container" v-if="humedal">
    <button class="btn-volver" @click="volverAHome">
      ← Volver a Humedales
    </button>
    <h1>{{ humedal.nombre }}</h1>

    <nav class="tabs-nav">
      <button @click="activeTab = 'general'" :class="{ active: activeTab === 'general' }">
        General
      </button>
      <button @click="activeTab = 'floraFauna'" :class="{ active: activeTab === 'floraFauna' }">
        Flora y Fauna
      </button>
      <button @click="activeTab = 'testimonios'" :class="{ active: activeTab === 'testimonios' }">
        Testimonios
      </button>
      <button @click="activeTab = 'galeria'" :class="{ active: activeTab === 'galeria' }">
        Galería
      </button>
    </nav>

    <div class="tab-content">
      <!-- dentro de la pestaña General -->
      <div v-show="activeTab === 'general'" class="tab-pane">
        <h3>Descripción</h3>
        <p>{{ humedal.descripcionLarga }}</p>

        <h3>Ubicación</h3>
        <div class="map-container">
          <iframe class="map-frame" width="100%" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"
            title="Mapa del Humedal"
            :src="humedal.mapaUrl || ('https://maps.google.com/maps?q=' + encodeURIComponent(humedal.nombre + ' Temuco') + '&t=&z=14&ie=UTF8&iwloc=&output=embed')"></iframe>
        </div>
      </div>

      <!-- PESTAÑA FLORA Y FAUNA -->
      <div v-show="activeTab === 'floraFauna'" class="tab-pane">
        <!-- Estados de carga / error / vacío -->
        <p v-if="especiesLoading" class="estado-texto">Cargando especies...</p>
        <p v-else-if="especiesError" class="estado-error">{{ especiesError }}</p>
        <p v-else-if="especies.length === 0" class="estado-texto">
          No hay especies registradas para este humedal.
        </p>

        <!-- Lista de especies -->
        <div v-else class="ff-list">
          <div v-for="especie in especies" :key="especie.id" class="ff-item">
            <img :src="especie.imagenUrl" :alt="especie.nombreComun" class="ff-img clickable-image"
              @click="openImageModal(especie.imagenUrl)">
            <div class="ff-info">
              <h4>{{ especie.nombreComun }}</h4>
              <p v-if="especie.nombreCientifico" class="ff-cientifico">
                {{ especie.nombreCientifico }}
              </p>
              <p class="ff-descripcion">
                {{ especie.descripcion }}
              </p>
            </div>
          </div>
        </div>
      </div>

      <!-- Testimonios -->
      <div v-if="activeTab === 'testimonios'" class="tab-pane">
        <div class="testimonios-grid">
          <div v-for="testimonio in humedal.testimonios" :key="testimonio.id" class="testimonio-card">
            <p>"{{ testimonio.contenido }}"</p>
            <span>- {{ testimonio.autor }}</span>
          </div>
        </div>
      </div>

      <!-- Galería -->
      <div v-if="activeTab === 'galeria'" class="tab-pane">
        <div class="galeria-grid">
          <img v-for="(img, index) in humedal.galeria" :key="index" :src="img" alt="Foto de galería"
            @click="openImageModal(img)" class="clickable-image">
        </div>
      </div>
    </div>

  </div>
  <div v-else class="detalle-loading">
    <p>Cargando humedal...</p>
    <p>Si este mensaje persiste, revisa que la URL sea correcta y que el ID exista.</p>
  </div>
  <!-- Modal de imagen ampliada -->
  <div v-if="showImageModal" class="image-modal-overlay" @click="closeImageModal" aria-modal="true" role="dialog">
    <div class="image-modal-content" @click.stop>
      <button class="image-modal-close" @click="closeImageModal" aria-label="Cerrar imagen ampliada">
        ×
      </button>
      <img :src="selectedImageUrl" alt="Vista ampliada" class="image-modal-img">
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import api from '../services/api'

const route = useRoute()
const humedal = ref(null)
const activeTab = ref('general')
const router = useRouter()

// Estado del modal de imagen
const showImageModal = ref(false)
const selectedImageUrl = ref('')

// ESTADO para especies (Flora y Fauna)
const especies = ref([])
const especiesLoading = ref(false)
const especiesError = ref(null)

function volverAHome() {
  router.push('/')
}

function openImageModal(url) {
  selectedImageUrl.value = url
  showImageModal.value = true
}

function closeImageModal() {
  showImageModal.value = false
  selectedImageUrl.value = ''
}
async function cargarHumedal() {
  try {
    const id = route.params.id
    const response = await api.get(`/humedales/${id}`)
    humedal.value = response.data
  } catch (error) {
    console.error('Error cargando humedal:', error)
  }
}

async function cargarEspecies() {
  if (!humedal.value?.id) return
  try {
    especiesLoading.value = true
    especiesError.value = null

    const res = await api.get(`/especies/humedal/${humedal.value.id}`)
    especies.value = res.data
  } catch (err) {
    console.error('Error cargando especies:', err)
    especiesError.value = 'No se pudieron cargar las especies.'
  } finally {
    especiesLoading.value = false
  }
}

onMounted(async () => {
  await cargarHumedal()
  await cargarEspecies()
  // opcional: cargar especies de inmediato
  // await cargarEspecies()
})
</script>

<style scoped>
.map-container {
  border-radius: 12px;
  /* radio del borde */
  overflow: hidden;
  /* recorta el iframe a ese borde */
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
  /* opcional, similar a info */
  margin: 1rem 0;
}

.map-frame {
  width: 100%;
  height: 400px;
  /* o el valor que quieras */
  border: 0;
  display: block;
}

.detalle-loading {
  max-width: 960px;
  margin: 3rem auto;
  padding: 2rem;
  background: var(--color-blanco);
  border-radius: 16px;
  box-shadow: var(--shadow-moderno);
  min-height: 1200px;
  /* asegura un alto razonable */
  display: flex;
  align-items: center;
  justify-content: center;
}

.detalle-container {
  max-width: 960px;
  margin: 3rem auto;
  padding: 1.5rem 2.5rem 2.5rem 2.5rem;
  background: var(--color-blanco);
  border-radius: 16px;
  box-shadow: var(--shadow-moderno);
}

.detalle-container h1 {
  font-size: 2.5rem;
  text-align: center;
  margin-bottom: 2rem;
}

.tabs-nav {
  border-bottom: 2px solid var(--color-tierra-clara);
  margin-bottom: 2rem;
  display: flex;
  justify-content: center;
}

.tabs-nav button {
  background: none;
  border: none;
  padding: 1rem 1.5rem;
  font-size: 1.1rem;
  font-weight: 700;
  cursor: pointer;
  border-bottom: 3px solid transparent;
  margin-bottom: -2px;
  color: var(--color-texto-secundario);
  transition: all 0.3s ease;
}

.tabs-nav button:hover {
  background-color: var(--color-tierra-clara);
}

.tabs-nav button.active {
  border-bottom-color: var(--color-verde-medio);
  color: var(--color-verde-oscuro);
}

.tab-pane {
  padding: 0 1rem;
}

.ubicacion-img {
  width: 100%;
  max-width: 600px;
  border-radius: 12px;
  background-color: #eee;
  margin: 1rem auto;
  display: block;
}

/* Flora y Fauna */
.seccion-ff {
  margin-bottom: 2rem;
}

.seccion-ff h4 {
  font-size: 1.5rem;
  border-bottom: 2px solid var(--color-tierra-clara);
  padding-bottom: 0.5rem;
}

.ff-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(150px, 1fr));
  gap: 1.5rem;
}

.ff-card img {
  width: 100%;
  height: 120px;
  object-fit: cover;
  border-radius: 12px;
  background-color: #eee;
}

/* Testimonios */
.testimonios-grid {
  display: grid;
  grid-template-columns: 1fr;
  gap: 1.5rem;
}

@media (min-width: 768px) {
  .testimonios-grid {
    grid-template-columns: 1fr 1fr;
  }
}

.testimonio-card {
  background: var(--color-tierra-clara);
  padding: 1.5rem;
  border-left: 0;
  border-top: 5px solid var(--color-verde-claro);
  border-radius: 12px;
}

.testimonio-card p {
  font-style: italic;
  margin-top: 0;
}

.testimonio-card span {
  font-weight: bold;
  color: var(--color-verde-oscuro);
}

/* Galeria */
.galeria-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 1rem;
}

.galeria-grid img {
  width: 100%;
  height: 150px;
  object-fit: cover;
  border-radius: 12px;
  background-color: #eee;
  transition: transform 0.3s ease;
}

.galeria-grid img:hover {
  transform: scale(1.05);
}
/* Estados de carga / error */
.estado-texto {
  color: var(--color-texto-secundario);
  margin-top: 1rem;
}

.estado-error {
  color: #e53935;
  margin-top: 1rem;
}

/* Lista de especies */
.ff-list {
  display: grid;
  grid-template-columns: 1fr;
  gap: 1.5rem;
  margin-top: 1.5rem;
}

@media (min-width: 768px) {
  .ff-list {
    grid-template-columns: 1fr 1fr;
  }
}

.ff-item {
  display: flex;
  align-items: flex-start;
  gap: 1rem;
  background: var(--color-tierra-clara);
  padding: 1rem;
  border-radius: 12px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.04);
}

.ff-img {
  width: 96px;
  height: 96px;
  object-fit: cover;
  border-radius: 12px;
  flex-shrink: 0;
  box-shadow: 0 2px 8px rgba(0,0,0,0.12);
}

.ff-info h4 {
  margin: 0;
  font-size: 1.1rem;
  color: var(--color-verde-oscuro);
}

.ff-cientifico {
  margin: 0.1rem 0 0.4rem;
  font-size: 0.9rem;
  color: #666;
  font-style: italic;
}

.ff-descripcion {
  margin: 0;
  font-size: 0.95rem;
  color: var(--color-texto-secundario);
}

/* Imágenes clickeables (galería/especies) */
.clickable-image {
  cursor: pointer;
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.clickable-image:hover {
  transform: scale(1.03);
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.15);
}

/* Overlay del modal */
.image-modal-overlay {
  position: fixed;
  inset: 0;
  background: rgba(0, 0, 0, 0.8);
  z-index: 150;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 1.5rem;
  animation: fadeIn 0.25s ease-out;
}

/* Contenedor blanco con la imagen */
.image-modal-content {
  position: relative;
  max-width: 900px;
  max-height: 90vh;
  background: #000;
  /* fondo neutro detrás de la imagen */
  border-radius: 12px;
  overflow: hidden;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

/* Imagen ampliada */
.image-modal-img {
  max-width: 100%;
  max-height: 90vh;
  object-fit: contain;
  display: block;
}

/* Botón de cerrar */
.image-modal-close {
  position: absolute;
  top: 0.5rem;
  right: 0.75rem;
  background: rgba(0, 0, 0, 0.6);
  color: #fff;
  border: none;
  border-radius: 999px;
  width: 32px;
  height: 32px;
  font-size: 1.2rem;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  line-height: 1;
  transition: background 0.2s ease, transform 0.2s ease;
}

.image-modal-close:hover {
  background: rgba(0, 0, 0, 0.9);
  transform: scale(1.05);
}

.btn-volver {
  margin-bottom: 1rem;
  background-color: #e0e0e0;
  color: #333;
  font-weight: 600;
  padding: 0.5rem 1rem;
  border-radius: 999px;
  border: none;
  cursor: pointer;
  font-size: 0.95rem;
  display: inline-flex;
  align-items: center;
  gap: 0.4rem;
  transition: background-color 0.2s ease, transform 0.1s ease;
}

.btn-volver:hover {
  background-color: #d5d5d5;
  transform: translateY(-1px);
}
/* Animación de aparición */
@keyframes fadeIn {
  from {
    opacity: 0;
  }

  to {
    opacity: 1;
  }
}
</style>