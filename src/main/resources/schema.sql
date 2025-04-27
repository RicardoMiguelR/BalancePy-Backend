-- Crea el usuario "ricardomr" en la base de datos si no existe
CREATE USER IF NOT EXISTS ricardomr PASSWORD 'Lander2313';

-- Otorga todos los privilegios al usuario "ricardomr" sobre el esquema "public"
GRANT ALL ON SCHEMA PUBLIC TO ricardomr;
