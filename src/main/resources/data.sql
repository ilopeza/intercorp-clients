-- PUBLIC.CLIENTS definition

-- Drop table
drop table clients;
create TABLE clients (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  death_date DATE DEFAULT NOT NULL,
  birthdate DATE DEFAULT NOT NULL
);

/*
INSERT INTO clients (first_name, last_name, birthdate) VALUES
('Aliko', 'Dangote', CURRENT_TIMESTAMP()),
('Bill', 'Gates', CURRENT_TIMESTAMP()),
('Folrunsho', 'Alakija', CURRENT_TIMESTAMP());*/
