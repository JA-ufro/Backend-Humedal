-- Insertar Humedales
-- Columnas como 'descripcion_corta' y 'imagen_principal' están en snake_case para coincidir con la conversión de Spring Boot.
INSERT INTO humedal (nombre, subtitulo, descripcion_corta, descripcion_larga, ubicacion, mapa_url, imagen_principal) VALUES
                                                                                                                             ('Humedal urbano Fundo el Carmen', 'Un oasis de biodiversidad en el sector poniente', 'Ubicado en el sector poniente de Temuco, este humedal es un ecosistema vital para la flora y fauna local.', 'El humedal Fundo El Carmen representa un importante relicto de ecosistema palustre...', 'Sector Fundo el Carmen, Temuco, Chile', 'https://www.google.com/maps/embed?pb=...', 'fundo_el_carmen.jpg'),
                                                                                                                             ('Humedal Vegas de Chivilcán', 'El corazón hídrico del macrosector de Chivilcán', 'Localizado en el macrosector de Chivilcán, es clave para la regulación hídrica...', 'Las Vegas de Chivilcán conforman un sistema de humedales de gran extensión...', 'Macrosector Chivilcán, Temuco, Chile', 'https://www.google.com/maps/embed?pb=...', 'chivilcan.jpg');

-- Insertar Especies
-- El campo 'tipo' ahora usa los valores de texto 'FAUNA' y 'FLORA' como lo define el EnumType.STRING
INSERT INTO especie (nombre_comun, nombre_cientifico, descripcion, tipo, estado_conservacion, imagen_url, humedal_id) VALUES
                                                                                                                            ('Sapito de cuatro ojos', 'Pleurodema thaul',
                                                                                                                             'Pequeño anfibio nativo de Chile, reconocido por las dos manchas claras en su espalda que simulan ojos adicionales como mecanismo defensivo.',
                                                                                                                             'FAUNA', 'LC', 'https://static.inaturalist.org/photos/61403596/large.jpg', 1),
                                                                                                                            ('Lagartija lemniscata', 'Liolaemus lemniscatus',
                                                                                                                             'Reptil ágil y de hábitos diurnos que habita zonas abiertas y semiáridas, caracterizada por sus patrones longitudinales oscuros.',
                                                                                                                             'FAUNA', 'DD', 'https://static.inaturalist.org/photos/44069382/large.jpeg', 1),
                                                                                                                            ('Tagua', 'Fulica armillata',
                                                                                                                             'Ave acuática común en humedales, de plumaje oscuro y escudo frontal blanco, excelente nadadora y frecuentemente observada en cuerpos de agua tranquilos.',
                                                                                                                             'FAUNA', 'DD', 'https://www.avesdechile.cl/0jpgn/126gde.jpg', 1),
                                                                                                                            ('Vinagrillo', 'Rumex acetosella',
                                                                                                                             'Planta herbácea ampliamente distribuida, apreciada por su sabor ácido característico y su capacidad de crecer en suelos perturbados.',
                                                                                                                             'FLORA', 'DD', 'https://inaturalist-open-data.s3.amazonaws.com/photos/177768765/original.jpg', 1),
                                                                                                                            ('Maqui', 'Aristotelia chilensis',
                                                                                                                             'Arbusto nativo de hojas siempreverdes y frutos morados comestibles, valorado por sus propiedades antioxidantes y su rol ecológico.',
                                                                                                                             'FLORA', 'DD', 'https://inaturalist-open-data.s3.amazonaws.com/photos/28746610/large.jpg', 1),
                                                                                                                            ('Rana chilena', 'Calyptocephalella gayi',
                                                                                                                             'Anfibio de gran tamaño endémico de Chile, asociado a cuerpos de agua permanentes y considerado vulnerable debido a la pérdida de hábitat.',
                                                                                                                             'FAUNA', 'VU', 'https://inaturalist-open-data.s3.amazonaws.com/photos/69691825/original.jpg', 2),
                                                                                                                            ('Bandurria', 'Theristicus melanopis',
                                                                                                                             'Ave de tamaño mediano con plumaje ocre y pico largo curvado, común en zonas húmedas y praderas abiertas.',
                                                                                                                             'FAUNA', 'LC', 'https://inaturalist-open-data.s3.amazonaws.com/photos/351319399/original.jpg', 2),
                                                                                                                            ('Torcaza', 'Patagioenas araucana',
                                                                                                                             'Paloma nativa de gran tamaño, reconocida por su plumaje grisáceo y su característico canto profundo.',
                                                                                                                             'FAUNA', 'LC', 'https://cdn.download.ams.birds.cornell.edu/api/v1/asset/205589271/640', 2),
                                                                                                                            ('Cisne de cuello negro', 'Cygnus melancoryphus',
                                                                                                                             'Ave acuática emblemática de los humedales del sur de Chile, distinguida por su cuello negro y cuerpo blanco.',
                                                                                                                             'FAUNA', 'LC', 'https://static.inaturalist.org/photos/213754116/large.jpg', 2),
                                                                                                                            ('Choroy', 'Enicognathus leptorhynchus',
                                                                                                                             'Loro nativo de Chile, conocido por su pico largo y su comportamiento gregario en bandadas ruidosas.',
                                                                                                                             'FAUNA', 'LC', 'https://static.inaturalist.org/photos/79063596/large.jpg', 2);
-- Insertar Testimonios
INSERT INTO testimonio (autor, texto, fecha, humedal_id) VALUES
                                                                ('H',
                                                                 'Nos parece súper interesante que se empiece a divulgar sobre la importancia que tienen los humedales, sobre todo para una zona como esta donde cada vez el agua se hace un recurso más valioso y más escaso. Es lamentable que haya sido intervenida de la manera que ha sido por los humanos.',
                                                                 '2025-12-03', 1),
                                                                ('K',
                                                                 'Este humedal se vio como un reservorio de agua dulce, especialmente durante el terremoto del 2011, cuando muchos vecinos no tenían agua. Queremos recalcar ese servicio que da el humedal. También alberga mucha diversidad; hoy pudimos observar varias especies de aves que, a pesar de que el humedal se encuentre degradado, encuentran refugio aquí para sobrevivir y reproducirse.',
                                                                 '2025-12-03', 1),
                                                                ('Anónimo',
                                                                 'No hay una noción de que ese espacio es un espacio sagrado, un lugar donde podemos convivir con otras especies. La falta de conciencia de que allí existe un ngen, de no creer en eso, provoca esta relación tan dañada que tenemos con nuestros propios espacios naturales.',
                                                                 '2025-12-03', 2),
                                                                ('R',
                                                                 'Hemos estado ahí con los chicos, con las chicas observando, sintiendo, estando vinculados con el territorio desde que llegamos ese lugar y ahora con esta experiencia de una manera mucho más integrada y ojalá de aquí para adelante también mucho más conectada con nuestro vecino el humedal, de manera de no solo conocerlo, sino que también sentir su valor, sus cambios estacionales y que eso sea parte también de la vivencia de los chicos y las chicas de la escuela.',
                                                                 '2025-12-03', 2);

-- Insertar en la tabla de Galería (creada por @ElementCollection)
INSERT INTO humedal_galeria (humedal_id, imagen_url) VALUES
                                                            (1, 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764741979/carmen1_g8j694.png'),
                                                            (1, 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764741979/carmen2_mhvapy.png'),
                                                            (1, 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764741978/carmen3_b2g76i.png'),
                                                            (1, 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764741978/carmen4_caibba.png'),
                                                            (2, 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764741982/chivilcan1_zvmqi7.png'),
                                                            (2, 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764741981/chivilcan2_h7ln5h.png'),
                                                            (2, 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764741980/chivilcan3_mxrmqd.png'),
                                                            (2, 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764741979/chivilcan4_nwrzux.png');
