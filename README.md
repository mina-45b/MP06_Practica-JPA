
## Diagrama

```mermaid
erDiagram
    ChemicalElement {
        int idElement
        string name
        string chemicalSymbol
        int atomicNumber
        int idState
        int idSerie
        string massNumber
        string energyLevel
        string electroNegativity
        string electronicAffinity
        string meltingPoint
        string ionisationEnergy
        string radius
        string density
        string hardness
        string boilingPoint
        string module
        string discovery
        string abundance
    }
    State {
        int idState
        string name
    }
    Serie {
        int idSerie
        string name
    }
    ChemicalCompounds {
        int idCompounds
        string compoundName
        string molecularFormula
        string molecularMass
        string densityConcentrationRange
    }
    CompoundElement {
        string serialId
        int idCompounds
        int idElement
        string name
        string symbol
        int subIndex
    }
    ChemicalElement ||--|{ CompoundElement : "part of"
    ChemicalElement ||--o| State : "belongs to"
    ChemicalElement ||--o| Serie : "belongs to"
    ChemicalCompounds ||--|{ CompoundElement : "contains"

```

## Tablas
## Tabla de Estructura de la Tabla "estados"

| Column   |          Type          | Collation | Nullable | Default |
|----------|------------------------|-----------|----------|---------|
| idestado | integer                |           | not null |         |
| nombre   | character varying(255) |           |          |         |

Indexes:
    "estados_pkey" PRIMARY KEY, btree (idestado)

Referenced by:
    TABLE "elementos" CONSTRAINT "elementos_idestado_fkey" FOREIGN KEY (idestado) REFERENCES estados(idestado)


## Tabla de Estructura de la Tabla "series"

| Column  |          Type          | Collation | Nullable | Default |
|---------|------------------------|-----------|----------|---------|
| idserie | integer                |           | not null |         |
| nombre  | character varying(255) |           |          |         |

Indexes:
    "series_pkey" PRIMARY KEY, btree (idserie)

Referenced by:
    TABLE "elementos" CONSTRAINT "elementos_idserie_fkey" FOREIGN KEY (idserie) REFERENCES series(idserie)

## Tabla de Estructura de la Tabla "compuestos"

| Column      |          Type          | Collation | Nullable | Default |
|-------------|------------------------|-----------|----------|---------|
| idcompuesto | integer                |           | not null |         |
| nombre      | character varying(255) |           |          |         |
| formula     | character varying(255) |           |          |         |
| masa        | character varying(255) |           |          |         |
| drc         | character varying(255) |           |          |         |

Indexes:
    "compuestos_pkey" PRIMARY KEY, btree (idcompuesto)

Referenced by:
    TABLE "compuestoelemento" CONSTRAINT "compuestoelemento_compuesto_id_fkey" FOREIGN KEY (compuesto_id) REFERENCES compuestos(idcompuesto)

## Tabla de Estructura de la Tabla "compuestoelemento"

| Column          |          Type          | Collation | Nullable |                                     Default                                      |
|-----------------|------------------------|-----------|----------|---------------------------------------------------------------------------------|
| id              | integer                |           | not null | nextval('compuestoelemento_id_seq'::regclass)                                   |
| compuesto_id    | integer                |           |          |                                                                                 |
| nombrecompuesto | character varying(255) |           |          |                                                                                 |
| elemento_id     | integer                |           |          |                                                                                 |
| simboloelemento | character varying(255) |           |          |                                                                                 |
| subindice       | integer                |           |          |                                                                                 |

Indexes:
    "compuestoelemento_pkey" PRIMARY KEY, btree (id)

Foreign-key constraints:
    "compuestoelemento_compuesto_id_fkey" FOREIGN KEY (compuesto_id) REFERENCES compuestos(idcompuesto)
    "compuestoelemento_elemento_id_fkey" FOREIGN KEY (elemento_id) REFERENCES elementos(idelemento)


## Tabla de Estructura de la Tabla "elementos"

| Column      |          Type          | Collation | Nullable | Default |
|-------------|------------------------|-----------|----------|---------|
| idelemento  | integer                |           | not null |         |
| nombre      | character varying(255) |           |          |         |
| simbolo     | character varying(255) |           |          |         |
| peso        | numeric(12,6)          |           |          |         |
| idserie     | integer                |           |          |         |
| idestado    | integer                |           |          |         |
| energia     | character varying(255) |           |          |         |
| en          | numeric(12,6)          |           |          |         |
| fusion      | numeric(12,6)          |           |          |         |
| ebullicion  | numeric(12,6)          |           |          |         |
| ea          | numeric(12,6)          |           |          |         |
| ionizacion  | numeric(12,6)          |           |          |         |
| radio       | integer                |           |          |         |
| dureza      | numeric(12,6)          |           |          |         |
| modulo      | numeric(12,6)          |           |          |         |
| densidad    | numeric(12,6)          |           |          |         |
| cond        | numeric(12,6)          |           |          |         |
| calor       | numeric(12,6)          |           |          |         |
| abundancia  | numeric(12,6)          |           |          |         |
| dto         | integer                |           |          |         |

Indexes:
    "elementos_pkey" PRIMARY KEY, btree (idelemento)

Foreign-key constraints:
    "elementos_idestado_fkey" FOREIGN KEY (idestado) REFERENCES estados(idestado)
    "elementos_idserie_fkey" FOREIGN KEY (idserie) REFERENCES series(idserie)

Referenced by:
    TABLE "compuestoelemento" CONSTRAINT "compuestoelemento_elemento_id_fkey" FOREIGN KEY (elemento_id) REFERENCES elementos(idelemento)



