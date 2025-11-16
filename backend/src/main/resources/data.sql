-- Insertar Humedales
-- Columnas como 'descripcion_corta' y 'imagen_principal' están en snake_case para coincidir con la conversión de Spring Boot.
INSERT INTO humedal (id, nombre, subtitulo, descripcion_corta, descripcion_larga, ubicacion, mapa_url, imagen_principal) VALUES
                                                                                                                             (1, 'Humedal urbano Fundo el Carmen', 'Un oasis de biodiversidad en el sector poniente', 'Ubicado en el sector poniente de Temuco, este humedal es un ecosistema vital para la flora y fauna local.', 'El humedal Fundo El Carmen representa un importante relicto de ecosistema palustre...', 'Sector Fundo el Carmen, Temuco, Chile', 'https://www.google.com/maps/embed?pb=...', 'humedal-carmen.jpg'),
                                                                                                                             (2, 'Humedal Vegas de Chivilcán', 'El corazón hídrico del macrosector de Chivilcán', 'Localizado en el macrosector de Chivilcán, es clave para la regulación hídrica...', 'Las Vegas de Chivilcán conforman un sistema de humedales de gran extensión...', 'Macrosector Chivilcán, Temuco, Chile', 'https://www.google.com/maps/embed?pb=...', 'humedal-chivilcan.jpg');

-- Insertar Especies
-- El campo 'tipo' ahora usa los valores de texto 'FAUNA' y 'FLORA' como lo define el EnumType.STRING
INSERT INTO especie (id, nombre_comun, nombre_cientifico, descripcion, tipo, imagen_url, humedal_id) VALUES
                                                                                                         (1, 'Tagua común', 'Fulica armillata', 'Ave acuática de plumaje negro...', 'FAUNA', 'tagua.jpg', 1),
                                                                                                         (2, 'Coipo', 'Myocastor coypus', 'Roedor acuático nativo de Sudamérica...', 'FAUNA', 'coipo.jpg', 1),
                                                                                                         (3, 'Junco', 'Juncus procerus', 'Planta perenne que crece en zonas húmedas...', 'FLORA', 'junco.jpg', 1),
                                                                                                         (4, 'Sauce chileno', 'Salix humboldtiana', 'Árbol nativo que crece en las riberas...', 'FLORA', 'sauce.jpg', 1),
                                                                                                         (5, 'Garza grande', 'Ardea alba', 'Ave zancuda de gran tamaño...', 'FAUNA', 'garza.jpg', 2),
                                                                                                         (6, 'Sapo de cuatro ojos', 'Pleurodema thaul', 'Anfibio endémico de Chile...', 'FAUNA', 'sapo.jpg', 2),
                                                                                                         (7, 'Totora', 'Typha angustifolia', 'Planta acuática robusta que forma...', 'FLORA', 'totora.jpg', 2),
                                                                                                         (8, 'Maqui', 'Aristotelia chilensis', 'Arbusto nativo siempreverde cuyo fruto...', 'FLORA', 'maqui.jpg', 2);

-- Insertar Testimonios
INSERT INTO testimonio (id, autor, texto, fecha, humedal_id) VALUES
                                                                 (1, 'Ana Pérez', 'Un lugar mágico para conectar...', '2023-10-15', 1),
                                                                 (2, 'Carlos Fuentes', 'Es fundamental que protejamos...', '2023-09-22', 1),
                                                                 (3, 'Javier Rojas', 'Cada vez que camino por aquí...', '2023-11-02', 2),
                                                                 (4, 'Luisa Martínez', 'Me preocupa el avance de las construcciones...', '2023-10-28', 2);

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