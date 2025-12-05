-- ============================================
-- V2__data.sql
-- Inserción de datos iniciales con IDs fijas
-- Adaptado para Flyway (sin duplicación)
-- ============================================

-- ======================
-- HUMEDALES
-- ======================
INSERT INTO humedal (id, nombre, subtitulo, descripcion_corta, descripcion_larga, ubicacion, mapa_url, imagen_principal)
VALUES
    (1, 'Humedal urbano Fundo el Carmen', 'Un oasis de biodiversidad en el sector poniente',
     'Ubicado en el sector poniente de Temuco, este humedal es un ecosistema vital para la flora y fauna local.',
     'El humedal Fundo El Carmen representa un importante relicto de ecosistema palustre...',
     'Sector Fundo el Carmen, Temuco, Chile',
     'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d12450.485459381504!2d-72.67380184496317!3d-38.71714401838647!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x9614d52bb7261aa7%3A0xd697ab6f32d48065!2sHumedal%20urbano%20Fundo%20el%20Carmen!5e0!3m2!1ses!2scl!4v1720810842771!5m2!1ses!2scl',
     '/img/humedal_fundo_el_carmen.jpg')
    ON CONFLICT (id) DO NOTHING;

INSERT INTO humedal (id, nombre, subtitulo, descripcion_corta, descripcion_larga, ubicacion, mapa_url, imagen_principal)
VALUES
    (2, 'Humedal Vegas de Chivilcán', 'El corazón hídrico del macrosector de Chivilcán',
     'Localizado en el macrosector de Chivilcán, es clave para la regulación hídrica y la vida silvestre de la zona.',
     'Las Vegas de Chivilcán conforman un sistema de humedales de gran extensión...',
     'Macrosector Chivilcán, Temuco, Chile',
     'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d6228.087289568972!2d-72.61111626012478!3d-38.71352639206757!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x9614d3848a5a5aaf%3A0x51c2b2d9aad29a5a!2sHumedal%20Vegas%20de%20Chivilc%C3%A1n!5e0!3m2!1ses!2scl!4v1720810890250!5m2!1ses!2scl',
     '/img/Parque-Humedal-Chivilcan.jpg')
    ON CONFLICT (id) DO NOTHING;


-- ======================
-- ESPECIES
-- ======================
INSERT INTO especie (id, nombre_comun, nombre_cientifico, descripcion, tipo, estado_conservacion, imagen_url, humedal_id)
VALUES
    (1, 'Sapito de cuatro ojos', 'Pleurodema thaul',
     'Pequeño anfibio nativo de Chile, reconocido por las dos manchas claras en su espalda que simulan ojos adicionales como mecanismo defensivo.',
     'FAUNA', 'LC', 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764744576/pleurodema_thaul_vakoll.jpg', 1),

    (2, 'Lagartija lemniscata', 'Liolaemus lemniscatus',
     'Reptil ágil y de hábitos diurnos que habita zonas abiertas y semiáridas, caracterizada por sus patrones longitudinales oscuros.',
     'FAUNA', 'DD', 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764744574/liolaemus_lemniscatus_ubwjte.jpg', 1),

    (3, 'Tagua', 'Fulica armillata',
     'Ave acuática común en humedales, de plumaje oscuro y escudo frontal blanco, excelente nadadora y frecuentemente observada en cuerpos de agua tranquilos.',
     'FAUNA', 'DD', 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764744574/fulica_armillata_dy8ibe.jpg', 1),

    (4, 'Vinagrillo', 'Rumex acetosella',
     'Planta herbácea ampliamente distribuida, apreciada por su sabor ácido característico y su capacidad de crecer en suelos perturbados.',
     'FLORA', 'DD', 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764744590/rumex_acetosella_r6ulpy.jpg', 1),

    (5, 'Maqui', 'Aristotelia chilensis',
     'Arbusto nativo de hojas siempreverdes y frutos morados comestibles, valorado por sus propiedades antioxidantes y su rol ecológico.',
     'FLORA', 'DD', 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764744589/aristotelia_chilensis_qqxmcp.jpg', 1),

    (6, 'Zarzamora', 'Rubus ulmifolius',
     'Arbusto espinoso de rápido crecimiento, conocido por formar matorrales densos y producir frutos comestibles de color negro.',
     'FLORA', 'DD', 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764747291/rubus_ulmifolius_zw11d5.jpg', 1),

    (7, 'Yerba fina', 'Agrostis capillaris',
     'Planta herbácea perenne conocida como yerba fina, presente en praderas y áreas abiertas con suelos bien drenados.',
     'FLORA', 'DD', 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764745636/agrostis_capillaris_a5s9hn.jpg', 2),

    (8, 'Rana chilena', 'Calyptocephalella gayi',
     'Anfibio de gran tamaño endémico de Chile, asociado a cuerpos de agua permanentes y considerado vulnerable debido a la pérdida de hábitat.',
     'FAUNA', 'VU', 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764745986/calyptocephalella_gayi_sww6mj.jpg', 2),

    (9, 'Bandurria', 'Theristicus melanopis',
     'Ave de tamaño mediano con plumaje ocre y pico largo curvado, común en zonas húmedas y praderas abiertas.',
     'FAUNA', 'LC', 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764744575/theristicus_melanopis_qe9i2f.jpg', 2),

    (10, 'Torcaza', 'Patagioenas araucana',
     'Paloma nativa de gran tamaño, reconocida por su plumaje grisáceo y su característico canto profundo.',
     'FAUNA', 'LC', 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764744575/patagioenas_araucana_c8w2ai.jpg', 2),

    (11, 'Cisne de cuello negro', 'Cygnus melancoryphus',
     'Ave acuática emblemática de los humedales del sur de Chile, distinguida por su cuello negro y cuerpo blanco.',
     'FAUNA', 'LC', 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764744575/cygnus_melancoryphus_gmbn0l.jpg', 2),

    (12, 'Junquillo', 'Juncus procerus',
     'Planta herbácea propia de zonas húmedas, reconocida por sus tallos delgados y erectos que forman densas matas en los bordes de cursos de agua.',
     'FLORA', 'DD', 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764746815/juncus_procerus_ktt8db.jpg', 2),

    (13, 'Choroy', 'Enicognathus leptorhynchus',
     'Loro nativo de Chile, conocido por su pico largo y su comportamiento gregario en bandadas ruidosas.',
     'FAUNA', 'LC', 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764746240/enicognathus_leptorhynchus_owf2ux.jpg', 2)
    ON CONFLICT (id) DO NOTHING;


-- ======================
-- TESTIMONIOS
-- ======================
INSERT INTO testimonio (id, autor, texto, fecha, humedal_id)
VALUES
    (1, 'H',
     'Nos parece súper interesante que se empiece a divulgar sobre la importancia que tienen los humedales, sobre todo para una zona como esta donde cada vez el agua se hace un recurso más valioso y más escaso. Es lamentable que haya sido intervenida de la manera que ha sido por los humanos.',
     '2025-12-03', 1),

    (2, 'K',
     'Este humedal se vio como un reservorio de agua dulce, especialmente durante el terremoto del 2011, cuando muchos vecinos no tenían agua. Queremos recalcar ese servicio que da el humedal. También alberga mucha diversidad; hoy pudimos observar varias especies de aves que, a pesar de que el humedal se encuentre degradado, encuentran refugio aquí para sobrevivir y reproducirse.',
     '2025-12-03', 1),

    (3, 'Anónimo',
     'No hay una noción de que ese espacio es un espacio sagrado, un lugar donde podemos convivir con otras especies. La falta de conciencia de que allí existe un ngen, de no creer en eso, provoca esta relación tan dañada que tenemos con nuestros propios espacios naturales.',
     '2025-12-03', 2),

    (4, 'R',
     'Hemos estado ahí con los chicos, con las chicas observando, sintiendo, estando vinculados con el territorio desde que llegamos ese lugar y ahora con esta experiencia de una manera mucho más integrada y ojalá de aquí para adelante también mucho más conectada con nuestro vecino el humedal, de manera de no solo conocerlo, sino que también sentir su valor, sus cambios estacionales y que eso sea parte también de la vivencia de los chicos y las chicas de la escuela.',
     '2025-12-03', 2)
    ON CONFLICT (id) DO NOTHING;


-- ======================
-- GALERÍA
-- ======================
INSERT INTO humedal_galeria (humedal_id, imagen_url) VALUES
                                                         (1, 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764741979/carmen1_g8j694.png'),
                                                         (1, 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764741979/carmen2_mhvapy.png'),
                                                         (1, 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764741978/carmen3_b2g76i.png'),
                                                         (1, 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764741978/carmen4_caibba.png'),
                                                         (2, 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764741982/chivilcan1_zvmqi7.png'),
                                                         (2, 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764741981/chivilcan2_h7ln5h.png'),
                                                         (2, 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764741980/chivilcan3_mxrmqd.png'),
                                                         (2, 'https://res.cloudinary.com/dquv36sgf/image/upload/v1764741979/chivilcan4_nwrzux.png')
    ON CONFLICT DO NOTHING;
