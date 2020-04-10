CREATE TABLE regione (
    regione_id            SERIAL NOT NULL,
    regione_codice        CHAR(3) NOT NULL,
    regione_descrizione   TEXT NOT NULL,
    CONSTRAINT reg_pk PRIMARY KEY (regione_id)
);

INSERT INTO regione (regione_id, regione_codice, regione_descrizione) VALUES (1, '010', 'PIEMONTE');
INSERT INTO regione (regione_id, regione_codice, regione_descrizione) VALUES (2, '020', 'VALLE DAOSTA');
INSERT INTO regione (regione_id, regione_codice, regione_descrizione) VALUES (3, '030', 'LOMBARDIA');
INSERT INTO regione (regione_id, regione_codice, regione_descrizione) VALUES (4, '040', 'TRENTINO - ALTO ADIGE');
INSERT INTO regione (regione_id, regione_codice, regione_descrizione) VALUES (5, '050', 'VENETO');
INSERT INTO regione (regione_id, regione_codice, regione_descrizione) VALUES (6, '060', 'FRIULI - VENEZIA GIULIA');
INSERT INTO regione (regione_id, regione_codice, regione_descrizione) VALUES (7, '070', 'LIGURIA');
INSERT INTO regione (regione_id, regione_codice, regione_descrizione) VALUES (8, '080', 'EMILIA - ROMAGNA');
INSERT INTO regione (regione_id, regione_codice, regione_descrizione) VALUES (9, '090', 'TOSCANA');
INSERT INTO regione (regione_id, regione_codice, regione_descrizione) VALUES (10, '100', 'UMBRIA');
INSERT INTO regione (regione_id, regione_codice, regione_descrizione) VALUES (11, '110', 'MARCHE');
INSERT INTO regione (regione_id, regione_codice, regione_descrizione) VALUES (12, '120', 'LAZIO');
INSERT INTO regione (regione_id, regione_codice, regione_descrizione) VALUES (13, '130', 'ABRUZZO');
INSERT INTO regione (regione_id, regione_codice, regione_descrizione) VALUES (14, '140', 'MOLISE');
INSERT INTO regione (regione_id, regione_codice, regione_descrizione) VALUES (15, '150', 'CAMPANIA');
INSERT INTO regione (regione_id, regione_codice, regione_descrizione) VALUES (16, '160', 'PUGLIA');
INSERT INTO regione (regione_id, regione_codice, regione_descrizione) VALUES (17, '170', 'BASILICATA');
INSERT INTO regione (regione_id, regione_codice, regione_descrizione) VALUES (18, '180', 'CALABRIA');
INSERT INTO regione (regione_id, regione_codice, regione_descrizione) VALUES (19, '190', 'SICILIA');
INSERT INTO regione (regione_id, regione_codice, regione_descrizione) VALUES (20, '200', 'SARDEGNA');


CREATE TABLE protciv_dati_regione(
  id SERIAL NOT NULL,
  data DATE NOT NULL,
  stato	CHAR(3) NOT NULL,
  codice_regione INT NOT NULL,
  denominazione_regione	TEXT NOT NULL,
  lat	FLOAT NOT NULL,
  long FLOAT NOT NULL,
  ricoverati_con_sintomi	INT NOT NULL DEFAULT 0,
  terapia_intensiva	INT NOT NULL DEFAULT 0,
  totale_ospedalizzati	INT NOT NULL DEFAULT 0,
  isolamento_domiciliare	INT NOT NULL DEFAULT 0,
  totale_attualmente_positivi	INT NOT NULL DEFAULT 0,
  nuovi_attualmente_positivi	INT NOT NULL DEFAULT 0,
  dimessi_guariti	INT NOT NULL DEFAULT 0,
  deceduti	INT NOT NULL DEFAULT 0,
  totale_casi	INT NOT NULL DEFAULT 0,
  tamponi INT NOT NULL DEFAULT 0,
  CONSTRAINT protciv_dati_regioni_pk PRIMARY KEY (id),
  CONSTRAINT codice_regione_fk FOREIGN KEY (codice_regione)
        REFERENCES regione (regione_id) MATCH FULL
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE protciv_dati_provincia(
  id SERIAL NOT NULL,
  data DATE NOT NULL,
  stato	CHAR(3) NOT NULL,
  codice_regione INT NOT NULL,
  denominazione_regione	TEXT NOT NULL,
  codice_provincia INT NOT NULL,
  denominazione_provincia	TEXT NOT NULL,
  sigla_provincia	CHAR(2) NOT NULL,
  lat	FLOAT NOT NULL,
  long FLOAT NOT NULL,
  totale_casi	INT NOT NULL DEFAULT 0,
  note_it	TEXT,
  note_en TEXT,
  CONSTRAINT protciv_dati_provincia_pk PRIMARY KEY (id),
  CONSTRAINT prov_codice_regione_fk FOREIGN KEY (codice_regione)
        REFERENCES regione (regione_id) MATCH FULL
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
