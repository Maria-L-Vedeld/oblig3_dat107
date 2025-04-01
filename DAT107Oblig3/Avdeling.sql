
SET search_path TO oblig3;

CREATE TABLE Avdeling (
    avdeling_id SERIAL PRIMARY KEY,
    navn VARCHAR(100) NOT NULL,
    sjef_id INT UNIQUE,
    CONSTRAINT fk_sjef FOREIGN KEY (sjef_id) REFERENCES Ansatt(ansatt_id) ON DELETE SET NULL
);

ALTER TABLE Ansatt ADD COLUMN avdeling_id INT;
ALTER TABLE Ansatt ADD CONSTRAINT fk_avdeling FOREIGN KEY (avdeling_id) REFERENCES Avdeling(avdeling_id);
