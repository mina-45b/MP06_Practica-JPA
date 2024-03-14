-- crear tablas

CREATE TABLE series (
    idSerie INT PRIMARY KEY,
    nombre VARCHAR(255)
);


CREATE TABLE estados (
    idEstado INT PRIMARY KEY,
    nombre VARCHAR(255)
);

CREATE TABLE compuestos (
    idCompuesto INT PRIMARY KEY,
    nombre VARCHAR(255),
    formula VARCHAR(255),
    masa VARCHAR(255),
    drc VARCHAR(255)
);

CREATE TABLE CompuestoElemento (
    id SERIAL PRIMARY KEY,
    compuesto_id INT,
    nombreCompuesto VARCHAR(255),
    elemento_id INT,
    simboloElemento VARCHAR(255),
    subindice INT,
    FOREIGN KEY (compuesto_id) REFERENCES compuestos(idCompuesto),
    FOREIGN KEY (elemento_id) REFERENCES elementos(idElemento)
);

CREATE TABLE elementos (
    idElemento INT PRIMARY KEY,
    nombre VARCHAR(255),
    simbolo VARCHAR(255),
    peso DECIMAL(12,6),
    idSerie INT,
    idEstado INT,
    energia VARCHAR(255),
    en DECIMAL(12,6),
    fusion DECIMAL(12,6),
    ebullicion DECIMAL(12,6),
    ea DECIMAL(12,6),
    ionizacion DECIMAL(12,6),
    radio INT NULL,
    dureza DECIMAL(12,6),
    modulo DECIMAL(12,6),
    densidad DECIMAL(12,6),
    cond DECIMAL(12,6),
    calor DECIMAL(12,6),
    abundancia DECIMAL(12,6),
    dto INT NULL,
    FOREIGN KEY (idSerie) REFERENCES series(idSerie),
    FOREIGN KEY (idEstado) REFERENCES estados(idEstado)
);



