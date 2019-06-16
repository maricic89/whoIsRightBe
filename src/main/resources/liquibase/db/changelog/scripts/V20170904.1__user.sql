CREATE TABLE wir_user (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(150) NOT NULL,
  image_url VARCHAR(150),
  email_verified BOOLEAN,
  password VARCHAR(100),
  provider VARCHAR(50) NOT NULL,
  provider_id VARCHAR(20)
);

ALTER TABLE wir_user ADD UNIQUE (email);

