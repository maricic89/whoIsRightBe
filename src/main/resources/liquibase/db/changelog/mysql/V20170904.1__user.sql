CREATE TABLE if not exists user (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(150) NOT NULL,
  image_url VARCHAR(150),
  email_verified SMALLINT,
  password VARCHAR(100),
  provider VARCHAR(50) NOT NULL,
  provider_id VARCHAR(20),
  PRIMARY KEY(id));

ALTER TABLE user ADD UNIQUE (email);

