DROP SCHEMA IF EXISTS oblig3 CASCADE;

CREATE SCHEMA oblig3;
SET search_path TO oblig3;

CREATE TABLE Ansatt (
    ansatt_id SERIAL PRIMARY KEY,
    brukernavn VARCHAR(4) UNIQUE NOT NULL,
    fornavn VARCHAR(50) NOT NULL,
    etternavn VARCHAR(50) NOT NULL,
    dato_ansatt DATE NOT NULL,
    stilling VARCHAR(50) NOT NULL,
    manedslonn DECIMAL(10,2) NOT NULL
);

INSERT INTO Ansatt (brukernavn, fornavn, etternavn, dato_ansatt, stilling, manedslonn)
VALUES 
('lph', 'Per', 'Viskeler', '2020-05-15', 'Utvikler', 55000.00),
('mtr', 'Atle', 'Patle', '2018-09-10', 'Prosjektleder', 75000.00);

