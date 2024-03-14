
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
