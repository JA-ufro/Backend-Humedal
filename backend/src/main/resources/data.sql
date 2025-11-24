-- Insertar Humedales
-- Columnas como 'descripcion_corta' y 'imagen_principal' están en snake_case para coincidir con la conversión de Spring Boot.
INSERT INTO humedal (nombre, subtitulo, descripcion_corta, descripcion_larga, ubicacion, mapa_url, imagen_principal) VALUES
                                                                                                                             ('Humedal urbano Fundo el Carmen', 'Un oasis de biodiversidad en el sector poniente', 'Ubicado en el sector poniente de Temuco, este humedal es un ecosistema vital para la flora y fauna local.', 'El humedal Fundo El Carmen representa un importante relicto de ecosistema palustre...', 'Sector Fundo el Carmen, Temuco, Chile', 'https://www.google.com/maps/embed?pb=...', 'humedal-carmen.jpg'),
                                                                                                                             ('Humedal Vegas de Chivilcán', 'El corazón hídrico del macrosector de Chivilcán', 'Localizado en el macrosector de Chivilcán, es clave para la regulación hídrica...', 'Las Vegas de Chivilcán conforman un sistema de humedales de gran extensión...', 'Macrosector Chivilcán, Temuco, Chile', 'https://www.google.com/maps/embed?pb=...', 'humedal-chivilcan.jpg');

-- Insertar Especies
-- El campo 'tipo' ahora usa los valores de texto 'FAUNA' y 'FLORA' como lo define el EnumType.STRING
INSERT INTO especie (nombre_comun, nombre_cientifico, descripcion, tipo, estado_conservacion, imagen_url, humedal_id) VALUES
                                                                                                         ('Tagua común', 'Fulica armillata', 'Ave acuática de plumaje negro...', 'FAUNA','DD', 'tagua.jpg', 1),
                                                                                                         ('Coipo', 'Myocastor coypus', 'Roedor acuático nativo de Sudamérica...', 'FAUNA','DD', 'coipo.jpg', 1),
                                                                                                         ('Junco', 'Juncus procerus', 'Planta perenne que crece en zonas húmedas...', 'FLORA','DD', 'junco.jpg', 1),
                                                                                                         ('Sauce chileno', 'Salix humboldtiana', 'Árbol nativo que crece en las riberas...', 'FLORA','DD', 'sauce.jpg', 1),
                                                                                                         ('Garza grande', 'Ardea alba', 'Ave zancuda de gran tamaño...', 'FAUNA','DD', 'garza.jpg', 2),
                                                                                                         ('Sapo de cuatro ojos', 'Pleurodema thaul', 'Anfibio endémico de Chile...', 'FAUNA','DD', 'sapo.jpg', 2),
                                                                                                         ('Totora', 'Typha angustifolia', 'Planta acuática robusta que forma...', 'FLORA','DD', 'totora.jpg', 2),
                                                                                                         ('Maqui', 'Aristotelia chilensis', 'Arbusto nativo siempreverde cuyo fruto...', 'FLORA','DD', 'maqui.jpg', 2);

-- Insertar Testimonios
INSERT INTO testimonio (autor, texto, fecha, humedal_id) VALUES
                                                                 ('Ana Pérez', 'Un lugar mágico para conectar...', '2023-10-15', 1),
                                                                 ('Carlos Fuentes', 'Es fundamental que protejamos...', '2023-09-22', 1),
                                                                 ('Javier Rojas', 'Cada vez que camino por aquí...', '2023-11-02', 2),
                                                                 ('Luisa Martínez', 'Me preocupa el avance de las construcciones...', '2023-10-28', 2);

-- Insertar en la tabla de Galería (creada por @ElementCollection)
INSERT INTO humedal_galeria (humedal_id, imagen_url) VALUES
                                                         (1, 'galeria-carmen-1.jpg'),
                                                         (1, 'galeria-carmen-2.jpg'),
                                                         (1, 'galeria-carmen-3.jpg'),
                                                         (1, 'galeria-carmen-4.jpg'),
                                                         (1, 'galeria-carmen-5.jpg'),
                                                         (1, 'galeria-carmen-6.jpg'),
                                                         (2, 'galeria-chivilcan-1.jpg'),
                                                         (2, 'galeria-chivilcan-2.jpg'),
                                                         (2, 'galeria-chivilcan-3.jpg'),
                                                         (2, 'galeria-chivilcan-4.jpg'),
                                                         (2, 'galeria-chivilcan-5.jpg'),
                                                         (2, 'galeria-chivilcan-6.jpg');