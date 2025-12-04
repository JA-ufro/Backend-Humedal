<template>
  <div class="info-container">
    <h2>Amenazas a nuestros humedales</h2>
     <img src="/img/conservacion.jpg" alt=" Conservacion" class="conservacion-img"></img>
    <p>
      A pesar de su inmenso valor, los humedales urbanos de Temuco enfrentan constantes
      amenazas que ponen en riesgo su existencia. La principal es la presión del crecimiento
      urbano: la expansión inmobiliaria a menudo ve estos terrenos como espacios rellenables
      para nuevas construcciones, destruyendo el ecosistema de forma irreversible.
      La contaminación es otro enemigo silencioso. La basura, los escombros y la descarga de aguas
      servidas sin tratar alteran la química del agua, afectando a todas las especies que dependen
      del humedal. La introducción de flora y fauna exótica también puede desplazar a las especies nativas,
      rompiendo el delicado equilibrio ecológico.
    </p>

    <hr class="divider">

      <h2>¿Cómo Podemos Ayudar?</h2>
      
      <ul class="help-list">
        <li>
          <strong>Educa e Informa:</strong> Comparte la importancia de los humedales con tu familia, amigos y en 
          redes sociales. El conocimiento es el primer paso para la acción.
        </li>
        <li>
          <strong>Participa en Limpiezas:</strong> Únete a jornadas de limpieza comunitaria. Cada residuo que se 
          retira es una victoria para el ecosistema.
        </li>
        <li>
          <strong>Denuncia Atentados Ambientales:</strong> Si observas rellenos ilegales, vertido de basura o 
          cualquier daño a un humedal, informa a las autoridades municipales o a la 
          Superintendencia del Medio Ambiente.
        </li>
        <li>
          <strong>Apoya el Comercio Local Sostenible:</strong> Elige productos y servicios de empresas 
          comprometidas con el medio ambiente.
        </li>
        <li>
          <strong>Reduce tu Huella:</strong> Practica un consumo responsable, gestiona adecuadamente tus 
          residuos y evita el uso de químicos dañinos en tu hogar.
        </li>
      </ul>

      <div class="highlight-box">
        <p>¡Cada acción cuenta! La conservación de los humedales de Temuco es una tarea de todos.</p>
      </div>

    </div>
</template>

<style scoped>
.info-container {
  max-width: 800px;
  margin: 2rem auto;
  padding: 2rem;
  background: white;
  border-radius: 8px;
  line-height: 1.6;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.05);
}

.info-container h2:last-child{
  margin-bottom: 0;
}
.conservacion-img {
  width: 100%;
  max-height: 500px;              /* opcional, por si quieres limitar altura */
  object-fit: cover;              /* recorta sin deformar */
  border-radius: 12px;            /* mismo radio que en Informacion.vue */
  box-shadow: 0 4px 15px rgba(0,0,0,0.08); /* para que combine con info */
  display: block;
  margin: 1.5rem 0;
}
</style>