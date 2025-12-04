<template>
  <div class="edit-detalle-wrapper" v-if="humedalLocal">
    <!-- Encabezado -->
    <div class="edit-header">
      <div>
        <button class="btn-volver" @click="volverAtras">
          ← Volver al panel
        </button>
        <h2>Editar humedal</h2>
        <p class="edit-subtitle">{{ humedalLocal.nombre }}</p>
      </div>
    </div>

    <!-- Tabs -->
    <nav class="edit-tabs-nav">
      <button class="edit-tab-btn" :class="{ active: activeTab === 'general' }" @click="activeTab = 'general'">
        General
      </button>
      <button class="edit-tab-btn" :class="{ active: activeTab === 'especies' }" @click="activeTab = 'especies'">
        Especies
      </button>
      <button class="edit-tab-btn" :class="{ active: activeTab === 'testimonios' }" @click="activeTab = 'testimonios'">
        Testimonios
      </button>
      <button class="edit-tab-btn" :class="{ active: activeTab === 'galeria' }" @click="activeTab = 'galeria'">
        Galería
      </button>
    </nav>

    <!-- Contenido de pestañas -->
    <div class="edit-tab-content">
      <!-- GENERAL -->
      <section v-show="activeTab === 'general'" class="edit-tab-pane">
        <h3 class="pane-title">Información general</h3>

        <div class="form-grid-2">
          <div class="form-group">
            <label for="nombre">Nombre del humedal</label>
            <input id="nombre" type="text" v-model="humedalLocal.nombre" />
          </div>

          <div class="form-group">
            <label for="subtitulo">Subtítulo</label>
            <input id="subtitulo" type="text" v-model="humedalLocal.subtitulo" />
          </div>
        </div>

        <div class="form-group">
          <label for="descCorta">Descripción corta</label>
          <textarea id="descCorta" rows="2" v-model="humedalLocal.descripcionCorta"></textarea>
        </div>

        <div class="form-group">
          <label for="descLarga">Descripción larga</label>
          <textarea id="descLarga" rows="6" v-model="humedalLocal.descripcionLarga"></textarea>
        </div>

        <div class="form-group">
          <label for="ubicacion">Ubicación (texto)</label>
          <input id="ubicacion" type="text" v-model="humedalLocal.ubicacion" />
        </div>

        <div class="form-grid-2">
          <div class="form-group">
            <label for="mapaUrl">URL de Google Maps (embed)</label>
            <textarea id="mapaUrl" rows="3" v-model="humedalLocal.mapaUrl"></textarea>
          </div>

          <div class="preview-card">
            <p class="preview-title">Vista previa del mapa</p>
            <div class="map-preview">
              <iframe v-if="humedalLocal.mapaUrl" :src="humedalLocal.mapaUrl" width="100%" height="100%"
                style="border:0;" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
              <p v-else class="preview-placeholder">
                Ingresa una URL de mapa para ver la vista previa.
              </p>
            </div>
          </div>
        </div>

        <div class="pane-actions">
          <button class="btn-primary" @click="guardarGeneral" :disabled="savingGeneral">
            {{ savingGeneral ? 'Guardando...' : 'Guardar cambios de General' }}
          </button>
        </div>
      </section>

      <!-- ESPECIES -->
      <section v-show="activeTab === 'especies'" class="edit-tab-pane">
        <div class="pane-header-row">
          <h3 class="pane-title">Especies asociadas</h3>
          <button class="btn-add" @click="agregarEspecie">
            + Añadir especie
          </button>
        </div>

        <p class="nota-explicacion">
          Administra las especies de este humedal. Puedes editar sus datos, agregar nuevas o eliminar las que ya no
          correspondan.
        </p>

        <div v-if="loadingEspecies">
          <p class="estado-texto">Cargando especies...</p>
        </div>

        <div v-else-if="!especiesLocal.length">
          <p class="estado-texto">No hay especies registradas para este humedal.</p>
        </div>

        <div v-else class="especies-list">
          <div v-for="(e, index) in especiesLocal" :key="e.id || 'nueva-' + index" class="especie-card">
            <div class="especie-img-col">
              <div class="especie-img-preview">
                <img v-if="e.imagenUrl" :src="e.imagenUrl" :alt="e.nombreComun || 'Especie'" />
                <span v-else class="especie-img-placeholder">
                  Sin imagen
                </span>
              </div>
              <div class="form-group small">
                <label>Imagen (URL)</label>
                <input type="text" v-model="e.imagenUrl" placeholder="https://..." />
              </div>
            </div>

            <div class="especie-main-col">
              <div class="form-group">
                <label>Nombre común</label>
                <input type="text" v-model="e.nombreComun" placeholder="Ej: Coipo" />
              </div>

              <div class="form-group">
                <label>Nombre científico</label>
                <input type="text" v-model="e.nombreCientifico" placeholder="Ej: Myocastor coypus" />
              </div>

              <div class="form-group">
                <label>Descripción</label>
                <textarea rows="3" v-model="e.descripcion" placeholder="Descripción breve de la especie"></textarea>
              </div>

              <div class="form-grid-2 compact">
                <div class="form-group">
                  <label>Tipo</label>
                  <select v-model="e.tipo">
                    <option :value="null" disabled>Selecciona tipo</option>
                    <option value="FLORA">Flora</option>
                    <option value="FAUNA">Fauna</option>
                  </select>
                </div>

                <div class="form-group">
                  <label>Estado de conservación</label>
                  <select v-model="e.estadoConservacion">
                    <option :value="null" disabled>Selecciona estado</option>
                    <option value="LC">Preocupación menor (LC)</option>
                    <option value="NT">Casi amenazada (NT)</option>
                    <option value="VU">Vulnerable (VU)</option>
                    <option value="EN">En peligro (EN)</option>
                    <option value="CR">En peligro crítico (CR)</option>
                    <option value="EW">Extinta en estado silvestre (EW)</option>
                    <option value="EX">Extinta (EX)</option>
                    <option value="DD">Datos insuficientes (DD)</option>
                  </select>
                </div>
              </div>

              <div class="especie-actions-row">
                <button class="btn-danger btn-small" @click="eliminarEspecie(index)">
                  Eliminar
                </button>
              </div>
            </div>
          </div>
        </div>

        <div class="pane-actions">
          <button class="btn-primary" @click="guardarEspecies" :disabled="savingEspecies">
            {{ savingEspecies ? 'Guardando...' : 'Guardar cambios de Especies' }}
          </button>
        </div>
      </section>

      <!-- TESTIMONIOS -->
      <section v-show="activeTab === 'testimonios'" class="edit-tab-pane">
        <div class="pane-header-row">
          <h3 class="pane-title">Testimonios</h3>
          <button class="btn-add" @click="agregarTestimonio">
            + Añadir testimonio
          </button>
        </div>

        <p class="nota-explicacion">
          Edita los testimonios asociados a este humedal. La fecha se gestiona automáticamente y no se muestra aquí.
        </p>

        <div v-if="loadingTestimonios">
          <p class="estado-texto">Cargando testimonios...</p>
        </div>

        <div v-else-if="!testimoniosLocal.length">
          <p class="estado-texto">
            No hay testimonios registrados para este humedal.
          </p>
        </div>

        <ul v-else class="testimonios-list">
          <li v-for="(t, index) in testimoniosLocal" :key="t.id || 'nuevo-' + index" class="testimonio-item">
            <div class="testimonio-edit-card">
              <div class="form-group-inline">
                <label>Autor</label>
                <input type="text" v-model="t.autor" placeholder="Nombre de la persona" />
              </div>
              <div class="form-group">
                <label>Contenido del testimonio</label>
                <textarea rows="3" v-model="t.contenido" placeholder="Texto del testimonio"></textarea>
              </div>
              <div class="testimonio-actions-row">
                <button class="btn-danger btn-small" @click="eliminarTestimonio(index)">
                  Eliminar
                </button>
              </div>
            </div>
          </li>
        </ul>

        <div class="pane-actions">
          <button class="btn-primary" @click="guardarTestimonios" :disabled="savingTestimonios">
            {{ savingTestimonios ? 'Guardando...' : 'Guardar cambios de Testimonios' }}
          </button>
        </div>
      </section>

      <!-- GALERÍA -->
      <section v-show="activeTab === 'galeria'" class="edit-tab-pane">
        <div class="pane-header-row">
          <h3 class="pane-title">Galería de imágenes</h3>
          <button class="btn-add" @click="agregarImagenGaleria">
            + Añadir URL de imagen
          </button>
        </div>

        <div v-if="!humedalLocal.galeria || humedalLocal.galeria.length === 0">
          <p class="estado-texto">No hay imágenes en la galería.</p>
        </div>

        <div v-else class="galeria-grid-admin">
          <div v-for="(img, index) in humedalLocal.galeria" :key="index" class="img-preview">
            <img v-if="img" :src="img" class="thumb-img" />
            <span v-else>Imagen {{ index + 1 }}</span>
            <button class="btn-delete-item btn-delete-abs" @click="eliminarImagenGaleria(index)">
              ×
            </button>
          </div>
        </div>

        <div class="pane-actions">
          <button class="btn-primary" @click="guardarGaleria" :disabled="savingGaleria">
            {{ savingGaleria ? 'Guardando...' : 'Guardar cambios de Galería' }}
          </button>
        </div>
      </section>
    </div>
  </div>

  <div v-else class="edit-loading">
    <p>Cargando datos del humedal...</p>
    <button class="btn-secondary" @click="volverAtras">Volver</button>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import api from '@/services/api'

const route = useRoute()
const router = useRouter()

const humedalId = route.params.id
const humedalLocal = ref(null)
const activeTab = ref('general')

// estados de guardado/carga por pestaña
const savingGeneral = ref(false)
const savingGaleria = ref(false)

// testimonios
const testimoniosLocal = ref([])
const testimoniosEliminados = ref([]) // ids a borrar
const loadingTestimonios = ref(false)
const savingTestimonios = ref(false)

// ESPECIES
const especiesLocal = ref([])
const especiesEliminadas = ref([]) // ids a borrar
const loadingEspecies = ref(false)
const savingEspecies = ref(false)

async function cargarDatos() {
  try {
    const response = await api.get(`/humedales/${humedalId}`)
    humedalLocal.value = response.data
    await Promise.all([
      cargarEspecies(),
      cargarTestimonios(),
    ])
  } catch (error) {
    console.error('Error cargando datos del humedal:', error)
    if (error.response) {
      alert(`Error ${error.response.status} al cargar el humedal.`)
    } else {
      alert('No se pudo conectar con el servidor.')
    }
  }
}

onMounted(cargarDatos)

// ---- Guardado por pestaña ----
async function guardarGeneral() {
  if (!humedalLocal.value) return
  try {
    savingGeneral.value = true
    const dto = buildUpdateDTO()
    await api.put(`/humedales/${humedalId}`, dto)
    alert('Cambios de la pestaña General guardados.')
  } catch (error) {
    console.error(error)
    alert('Error al guardar los cambios de General.')
  } finally {
    savingGeneral.value = false
  }
}

async function guardarGaleria() {
  if (!humedalLocal.value) return
  try {
    savingGaleria.value = true
    const dto = buildUpdateDTO()
    await api.put(`/humedales/${humedalId}`, dto)
    alert('Cambios de la galería guardados.')
  } catch (error) {
    console.error(error)
    alert('Error al guardar la galería.')
  } finally {
    savingGaleria.value = false
  }
}

// Construye el DTO de actualización con los campos que maneja el admin
function buildUpdateDTO() {
  return {
    nombre: humedalLocal.value.nombre,
    subtitulo: humedalLocal.value.subtitulo,
    descripcionCorta: humedalLocal.value.descripcionCorta,
    descripcionLarga: humedalLocal.value.descripcionLarga,
    ubicacion: humedalLocal.value.ubicacion,
    mapaUrl: humedalLocal.value.mapaUrl,
    imagenPrincipal: humedalLocal.value.imagenPrincipal,
    galeria: humedalLocal.value.galeria || [],
  }
}

// ---- Especies y testimonios ----
async function refrescarEspecies() {
  if (!humedalLocal.value) return
  try {
    loadingEspecies.value = true
    // Opción 1: recargar todo el humedal
    const response = await api.get(`/humedales/${humedalId}`)
    humedalLocal.value.especies = response.data.especies
  } catch (error) {
    console.error('Error actualizando especies:', error)
    alert('Error al refrescar especies.')
  } finally {
    loadingEspecies.value = false
  }
}

async function cargarTestimonios() {
  try {
    loadingTestimonios.value = true
    const res = await api.get(`/testimonios/humedal/${humedalId}`)
    testimoniosLocal.value = res.data
    testimoniosEliminados.value = []
  } catch (error) {
    console.error('Error cargando testimonios:', error)
    alert('No se pudieron cargar los testimonios.')
  } finally {
    loadingTestimonios.value = false
  }
}

async function cargarEspecies() {
  try {
    loadingEspecies.value = true
    const res = await api.get(`/especies/humedal/${humedalId}`)
    especiesLocal.value = res.data
    especiesEliminadas.value = []
  } catch (error) {
    console.error('Error cargando especies:', error)
    alert('No se pudieron cargar las especies.')
  } finally {
    loadingEspecies.value = false
  }
}

// ---- CRUD especies en frontend ----

function agregarEspecie() {
  especiesLocal.value.unshift({
    id: null,
    nombreComun: '',
    nombreCientifico: '',
    descripcion: '',
    tipo: null,
    estadoConservacion: null,
    imagenUrl: '',
    humedalId: Number(humedalId),
  })
}

function eliminarEspecie(index) {
  const e = especiesLocal.value[index]
  if (e.id) {
    especiesEliminadas.value.push(e.id)
  }
  especiesLocal.value.splice(index, 1)
}

async function guardarEspecies() {
  try {
    savingEspecies.value = true

    // 1) eliminar las que se marcaron
    for (const id of especiesEliminadas.value) {
      try {
        await api.delete(`/especies/${id}`)
      } catch (e) {
        console.error('Error eliminando especie', id, e)
      }
    }

    // 2) crear / actualizar las actuales
    for (const e of especiesLocal.value) {
      // filtra totalmente vacías (sin nombre común ni científico)
      if (!e.nombreComun?.trim() && !e.nombreCientifico?.trim()) continue

      if (e.id) {
        // actualizar
        await api.put(`/especies/${e.id}`, {
          nombreComun: e.nombreComun,
          nombreCientifico: e.nombreCientifico,
          descripcion: e.descripcion,
          tipo: e.tipo,
          estadoConservacion: e.estadoConservacion,
          imagenUrl: e.imagenUrl,
        })
      } else {
        // crear
        await api.post('/especies', {
          nombreComun: e.nombreComun,
          nombreCientifico: e.nombreCientifico,
          descripcion: e.descripcion,
          tipo: e.tipo,
          estadoConservacion: e.estadoConservacion,
          imagenUrl: e.imagenUrl,
          humedalId: Number(humedalId),
        })
      }
    }

    await cargarEspecies()
    alert('Cambios de especies guardados.')
  } catch (error) {
    console.error('Error guardando especies:', error)
    alert('Hubo un error al guardar las especies.')
  } finally {
    savingEspecies.value = false
    especiesEliminadas.value = []
  }
}

// ---- CRUD testimonios en frontend ----
function agregarTestimonio() {
  testimoniosLocal.value.unshift({
    id: null,
    autor: '',
    contenido: '',
    humedalId: Number(humedalId),
  })
}

function eliminarTestimonio(index) {
  const t = testimoniosLocal.value[index]
  if (t.id) {
    testimoniosEliminados.value.push(t.id)
  }
  testimoniosLocal.value.splice(index, 1)
}

async function guardarTestimonios() {
  try {
    savingTestimonios.value = true

    // 1) eliminar testimonios marcados
    for (const id of testimoniosEliminados.value) {
      try {
        await api.delete(`/testimonios/${id}`)
      } catch (e) {
        console.error('Error eliminando testimonio', id, e)
      }
    }

    // 2) guardar/actualizar los actuales
    for (const t of testimoniosLocal.value) {
      // si está totalmente vacío, lo ignoramos
      if (!t.autor?.trim() && !t.contenido?.trim()) continue

      if (t.id) {
        // actualizar
        await api.put(`/testimonios/${t.id}`, {
          autor: t.autor,
          contenido: t.contenido,
        })
      } else {
        // crear
        await api.post('/testimonios', {
          autor: t.autor,
          contenido: t.contenido,
          humedalId: Number(humedalId),
          // fecha: opcional, backend ya acepta `dto.getFecha()`, puedes enviarla o dejarla null
        })
      }
    }

    // 3) recargar lista para tener ids/fechas correctos
    await cargarTestimonios()
    alert('Cambios de testimonios guardados.')
  } catch (error) {
    console.error('Error guardando testimonios:', error)
    alert('Hubo un error al guardar los testimonios.')
  } finally {
    savingTestimonios.value = false
    testimoniosEliminados.value = []
  }
}

async function refrescarTestimonios() {
  if (!humedalLocal.value) return
  try {
    loadingTestimonios.value = true
    const response = await api.get(`/humedales/${humedalId}`)
    humedalLocal.value.testimonios = response.data.testimonios
  } catch (error) {
    console.error('Error actualizando testimonios:', error)
    alert('Error al refrescar testimonios.')
  } finally {
    loadingTestimonios.value = false
  }
}

// ---- Galería: edición local ----
function agregarImagenGaleria() {
  if (!humedalLocal.value) return
  if (!humedalLocal.value.galeria) {
    humedalLocal.value.galeria = []
  }
  const url = prompt('Ingresa la URL de la imagen a agregar a la galería:')
  if (url) {
    humedalLocal.value.galeria.push(url)
  }
}

function eliminarImagenGaleria(index) {
  if (!humedalLocal.value || !humedalLocal.value.galeria) return
  if (!confirm('¿Seguro que deseas eliminar esta imagen de la galería?')) return
  humedalLocal.value.galeria.splice(index, 1)
}

// ---- Utilidades ----
function volverAtras() {
  router.push('/admin/panel')
}

function formatearFecha(fechaStr) {
  if (!fechaStr) return ''
  try {
    const d = new Date(fechaStr)
    return d.toLocaleDateString('es-CL')
  } catch {
    return fechaStr
  }
}
</script>

<style scoped>
.edit-detalle-wrapper {
  max-width: 1000px;
  margin: 2.5rem auto;
  padding: 2rem;
  background: var(--color-blanco, #fff);
  border-radius: 16px;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
}

/* Encabezado */
.edit-header {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  gap: 1rem;
  border-bottom: 2px solid var(--color-tierra-clara, #e0e0e0);
  padding-bottom: 1rem;
  margin-bottom: 1.5rem;
}

.edit-header h2 {
  margin: 0;
  font-size: 1.6rem;
  color: var(--color-verde-oscuro, #234);
}

.edit-subtitle {
  margin: 0.2rem 0 0;
  color: var(--color-texto-secundario, #666);
}

.edit-header-actions {
  display: flex;
  gap: 0.75rem;
}

/* Tabs */
.edit-tabs-nav {
  display: flex;
  gap: 0.5rem;
  border-bottom: 1px solid #ddd;
  margin-bottom: 1.5rem;
  flex-wrap: wrap;
}

.edit-tab-btn {
  border: none;
  background: transparent;
  padding: 0.6rem 1rem;
  cursor: pointer;
  font-weight: 600;
  color: #666;
  border-bottom: 3px solid transparent;
  font-size: 0.95rem;
}

.edit-tab-btn.active {
  color: var(--color-verde-oscuro, #234);
  border-bottom-color: var(--color-acento-arena, #f0c27b);
}

/* Contenido */
.edit-tab-content {
  margin-top: 1rem;
}

.edit-tab-pane {
  animation: fadeIn 0.15s ease-out;
}

.pane-title {
  margin-top: 0;
  margin-bottom: 1rem;
  font-size: 1.2rem;
  color: var(--color-verde-oscuro, #234);
}

.pane-header-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 1rem;
}

/* Form layout */
.form-group {
  margin-bottom: 1.25rem;
}

.form-group label {
  display: block;
  font-weight: 600;
  margin-bottom: 0.35rem;
}

.form-group input[type="text"],
.form-group textarea {
  width: 100%;
  padding: 0.7rem 0.8rem;
  border-radius: 8px;
  border: 1px solid #ccc;
  font-family: inherit;
  font-size: 0.95rem;
}

.form-grid-2 {
  display: grid;
  grid-template-columns: minmax(0, 1.4fr) minmax(0, 1fr);
  gap: 1.25rem;
  margin-bottom: 1.25rem;
}

/* Previews */
.preview-card {
  background-color: var(--color-tierra-clara, #f6f3ee);
  border-radius: 10px;
  padding: 0.8rem;
}

.preview-title {
  margin: 0 0 0.5rem;
  font-size: 0.9rem;
  color: #555;
}

.map-preview {
  width: 100%;
  height: 100%;
  border-radius: 8px;
}

.image-preview {
  width: 100%;
  height: 220px;
  border-radius: 8px;
  overflow: hidden;
  background: #ddd;
  display: flex;
  align-items: center;
  justify-content: center;
}

.image-preview img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.preview-placeholder {
  font-size: 0.9rem;
  color: #666;
  padding: 0.5rem;
  text-align: center;
}

/* Especies */
.ff-list {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 1rem;
  margin-top: 1rem;
}

.ff-item {
  display: flex;
  gap: 0.75rem;
  background: var(--color-tierra-clara, #f6f3ee);
  padding: 0.75rem;
  border-radius: 10px;
}

.ff-img {
  width: 72px;
  height: 72px;
  border-radius: 10px;
  object-fit: cover;
  flex-shrink: 0;
}

.ff-info h4 {
  margin: 0;
  font-size: 1rem;
  color: var(--color-verde-oscuro, #234);
}

.ff-cientifico {
  margin: 0.1rem 0 0;
  font-size: 0.85rem;
  color: #666;
  font-style: italic;
}

/* Testimonios */
.testimonios-list {
  list-style: none;
  padding: 0;
  margin: 1rem 0 0;
}

.testimonio-item {
  margin-bottom: 1rem;
}

.testimonio-edit-card {
  background: #f5f7f8;
  border-radius: 10px;
  padding: 0.9rem 1rem;
  border-left: 4px solid var(--color-verde-medio, #4a6c4a);
}

.form-group-inline {
  display: flex;
  flex-direction: column;
  margin-bottom: 0.6rem;
}

.form-group-inline label {
  font-weight: 600;
  margin-bottom: 0.3rem;
}

.form-group-inline input[type="text"] {
  width: 100%;
  padding: 0.6rem 0.8rem;
  border-radius: 8px;
  border: 1px solid #ccc;
  font-family: inherit;
  font-size: 0.9rem;
}

.testimonio-edit-card .form-group textarea {
  min-height: 80px;
}

.testimonio-actions-row {
  margin-top: 0.4rem;
  display: flex;
  justify-content: flex-end;
  gap: 0.5rem;
}

.btn-small {
  padding: 0.35rem 0.9rem;
  font-size: 0.85rem;
  border-radius: 999px;
}

.btn-danger {
  background-color: #e57373;
  color: #fff;
  border: none;
  cursor: pointer;
}

.btn-danger:hover {
  background-color: #d32f2f;
}

/* Galería */
.galeria-grid-admin {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(130px, 1fr));
  gap: 1rem;
  margin-top: 1rem;
}

.img-preview {
  height: 120px;
  background: #eee;
  border-radius: 8px;
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
}

.thumb-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 8px;
}

.btn-delete-item {
  background: #e57373;
  color: white;
  border: none;
  border-radius: 50%;
  width: 22px;
  height: 22px;
  cursor: pointer;
  font-weight: bold;
  font-size: 0.9rem;
}

.btn-add {
  background-color: var(--color-verde-medio, #4a6c4a);
  color: #fff;
  border: none;
  padding: 0.5rem 1.2rem;
  border-radius: 999px;
  font-weight: 600;
  font-size: 0.9rem;
  cursor: pointer;
  display: inline-flex;
  align-items: center;
  gap: 0.3rem;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.12);
}

.btn-add:hover {
  background-color: #3b593b;
}

.btn-delete-abs {
  position: absolute;
  top: -6px;
  right: -6px;
}

.btn-volver {
  margin-bottom: 0;
  /* aquí puede ser 0 porque está dentro del header */
  background-color: #e0e0e0;
  color: #333;
  font-weight: 600;
  padding: 0.5rem 1rem;
  border-radius: 999px;
  border: none;
  cursor: pointer;
  font-size: 0.9rem;
  display: inline-flex;
  align-items: center;
  gap: 0.4rem;
  transition: background-color 0.2s ease, transform 0.1s ease;
}

.btn-volver:hover {
  background-color: #d5d5d5;
  transform: translateY(-1px);
}

/* Botones */
.pane-actions {
  margin-top: 1.5rem;
  display: flex;
  justify-content: flex-end;
}

.btn-primary {
  background-color: var(--color-verde-medio, #4a6c4a);
  color: #fff;
  border: none;
  padding: 0.7rem 1.4rem;
  border-radius: 999px;
  font-weight: 700;
  cursor: pointer;
  font-size: 0.95rem;
}

.btn-primary[disabled] {
  opacity: 0.7;
  cursor: default;
}

.btn-secondary {
  background: transparent;
  border: 1px solid #ccc;
  padding: 0.6rem 1.2rem;
  border-radius: 999px;
  cursor: pointer;
  font-size: 0.9rem;
}

/* Estados */
.nota-explicacion {
  font-size: 0.9rem;
  color: #666;
}

.estado-texto {
  color: #777;
  font-size: 0.95rem;
}

.edit-loading {
  max-width: 600px;
  margin: 3rem auto;
  text-align: center;
}

.testimonios-list {
  list-style: none;
  padding: 0;
  margin: 1rem 0 0;
}

.testimonio-item {
  margin-bottom: 1rem;
}

.testimonio-edit-card {
  background: #f5f7f8;
  border-radius: 10px;
  padding: 0.9rem 1rem;
  border-left: 4px solid var(--color-verde-medio, #4a6c4a);
}

.form-group-inline {
  display: flex;
  flex-direction: column;
  margin-bottom: 0.6rem;
}

.form-group-inline label {
  font-weight: 600;
  margin-bottom: 0.3rem;
}

.form-group-inline input[type="text"] {
  width: 100%;
  padding: 0.6rem 0.8rem;
  border-radius: 8px;
  border: 1px solid #ccc;
  font-family: inherit;
  font-size: 0.9rem;
}

.testimonio-edit-card .form-group textarea {
  min-height: 80px;
}

.testimonio-actions-row {
  margin-top: 0.4rem;
  display: flex;
  justify-content: flex-end;
}

.btn-small {
  padding: 0.4rem 0.9rem;
  font-size: 0.85rem;
}

/* Animación simple */
@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(4px);
  }

  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.especies-list {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  margin-top: 1rem;
}

.especie-card {
  display: grid;
  grid-template-columns: minmax(0, 0.9fr) minmax(0, 2fr);
  gap: 1rem;
  padding: 0.9rem 1rem;
  border-radius: 12px;
  background: #f5f7f8;
  border-left: 4px solid var(--color-verde-medio, #4a6c4a);
}

.especie-img-col {
  display: flex;
  flex-direction: column;
  gap: 0.6rem;
}

.especie-img-preview {
  width: 100%;
  height: 120px;
  border-radius: 10px;
  background: #ddd;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
}

.especie-img-preview img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.especie-img-placeholder {
  font-size: 0.85rem;
  color: #666;
}

.especie-main-col {
  display: flex;
  flex-direction: column;
  gap: 0.6rem;
}

.form-group.small {
  margin-bottom: 0;
}

.form-group select {
  width: 100%;
  padding: 0.6rem 0.8rem;
  border-radius: 8px;
  border: 1px solid #ccc;
  font-family: inherit;
  font-size: 0.9rem;
  background-color: #fff;
}

.form-grid-2.compact {
  gap: 0.8rem;
}

.especie-actions-row {
  display: flex;
  justify-content: flex-end;
  margin-top: 0.3rem;
}

@media (max-width: 768px) {
  .edit-detalle-wrapper {
    margin: 1rem;
    padding: 1.25rem;
  }

  .especie-card {
    grid-template-columns: 1fr;
  }

  .form-grid-2 {
    grid-template-columns: 1fr;
  }

  .ff-list {
    grid-template-columns: 1fr;
  }

  .pane-actions {
    justify-content: center;
  }
}
</style>