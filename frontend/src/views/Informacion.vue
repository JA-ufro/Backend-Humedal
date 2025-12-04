<template>
  <div class="info-container">
    <div class="header-info">
      <h2>Información del Proyecto</h2>
      <p>Conoce los detalles de nuestra iniciativa y los humedales que protegemos.</p>
    </div>

    <section class="info-section">
      <div class="img-wrapper main-img">
        <img src="/img/info_humedales.png" alt="Información General Humedales">
      </div>
    </section>

    <hr class="divider">

    <section class="info-section">
      <h3></h3>
      <div class="img-wrapper">
        <img src="/img/info_fundo_el_carmen.png" alt="Info Fundo El Carmen">
      </div>
    </section>

    <hr class="divider">

    <section class="info-section">
      <h3></h3>
      <div class="img-wrapper">
        <img src="/img/info_vegas_de_chivilcan.png" alt="Info Vegas de Chivilcán">
      </div>
    </section>

  </div>
</template>

<style scoped>
.info-container {
  max-width: 1000px;
  margin: 3rem auto;
  padding: 2.5rem;
  background: white;
  border-radius: 16px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.05);
}

.header-info {
  text-align: center;
  margin-bottom: 2rem;
}

h2 {
  color: var(--color-verde-oscuro);
  font-size: 2rem;
  margin-bottom: 0.5rem;
}

h3 {
  color: var(--color-verde-medio);
  font-size: 1.5rem;
  margin-bottom: 1.5rem;
  border-left: 5px solid var(--color-acento-arena);
  padding-left: 1rem;
}

p {
  color: var(--color-texto-secundario);
}

.divider {
  border: 0;
  height: 1px;
  background: #eee;
  margin: 3rem 0;
}

.img-wrapper {
  border-radius: 12px;
  overflow: hidden;
  box-shadow: 0 4px 15px rgba(0,0,0,0.08);
  background-color: #f9f9f9;
  transition: transform 0.3s ease;
  display: flex;
  justify-content: center;
}

.img-wrapper:hover {
  transform: translateY(-5px);
}

.img-wrapper img {
  width: 100%;
  height: auto;
  display: block;
  max-width: 100%;
}
</style>