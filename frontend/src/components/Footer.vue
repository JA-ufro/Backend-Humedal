<template>
  <footer class="main-footer">
    <div class="footer-content">
      
      <div class="footer-section section-team">
        <h4>Buena Vista</h4>
        <p>Proyecto para el ramo de Comprensión del Contexto Social.</p>
      </div>

      <div class="footer-section section-sitemap">
        <h4>Mapa del Sitio</h4>
        <ul>
          <li><router-link to="/">Humedales</router-link></li>
          <li><router-link to="/informacion">Información</router-link></li>
          <li><router-link to="/conservacion">Conservación</router-link></li>
        </ul>
      </div>

      <div class="footer-section section-contact">
        <h4>Contacto</h4>
        <p>
          <a href="mailto:ejemplo@ejemplo.com">ejemplo@ejemplo.com</a>
        </p>
      </div>

    </div>
    
    <div class="footer-bottom">
      <p>&copy; 2025 BuenaVista. Todos los derechos reservados.</p>
    </div>
  </footer>
</template>

<style scoped>
.main-footer {
  background-color: var(--color-verde-medio); 
  color: rgba(255, 255, 255, 0.85);
  padding: 3rem 2rem 0 2rem;
}

.footer-content {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
  gap: 2.5rem;
  padding-bottom: 2.5rem;
}

.footer-section {
  flex: 1;
  min-width: 220px;
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}

.footer-section h4 {
  color: var(--color-blanco);
  font-size: 1.25rem;
  margin-bottom: 1.5rem;
  border-bottom: 2px solid var(--color-acento-arena);
  padding-bottom: 0.5rem;
  display: inline-block;
}

.footer-section p {
  color: rgba(255, 255, 255, 0.85);
  line-height: 1.6;
}

.footer-section ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.footer-section ul li {
  margin-bottom: 0.75rem;
}

.footer-section a {
  color: rgba(255, 255, 255, 0.85);
  text-decoration: none;
  transition: color 0.3s ease;
}

.footer-section a:hover {
  color: var(--color-blanco);
  text-decoration: underline;
}

.footer-bottom {
  border-top: 1px solid rgba(255, 255, 255, 0.2);
  padding: 1.5rem 0;
  text-align: center;
}

.footer-bottom p {
  margin: 0;
  font-size: 0.9rem;
  color: rgba(255, 255, 255, 0.7);
}

@media (max-width: 768px) {
  .footer-content {
    flex-direction: column;
    align-items: center;
    text-align: center;
  }
  
  .footer-section h4 {
    display: block; 
  }
}

</style>