package model;

import javax.persistence.*;
import java.util.Set;

/**
 * La clase Compuesto representa un compuesto químico y sus propiedades.
 * Cada compuesto tiene un identificador único (idCompuesto) y un nombre asociado.
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "compuestos")
public class Compuesto {

    /**
     * Identificador único del compuesto.
     */
    @Id
    @Column(name = "idCompuesto")
    int idCompuesto;

    /**
     * Nombre del compuesto.
     */
    @Column (name = "nombre", length = 255)
    String nombre;

    /**
     * Fórmula química del compuesto.
     */
    @Column (name = "formula", length = 255)
    String formula;

    /**
     * Masa molar del compuesto.
     */
    @Column (name = "masa", length = 255)
    String masa;

    /**
     * Densidad rango de concentración del compuesto.
     */
    @Column (name = "drc", length = 255)
    String DRC;

    /**
     * Conjunto de elementos que componen el compuesto.
     * Esta relación representa los elementos que forman parte del compuesto e
     * indica que un compuesto puede estar formado por varios elementos y un elemento puede
     * estar presente en varios compuestos.
     */
    @ManyToMany
    @JoinTable(
            name = "CompuestoElemento",
            joinColumns = @JoinColumn(name = "compuesto_id"),
            inverseJoinColumns = @JoinColumn(name = "elemento_id")
    )
    private Set<Elemento> elementos;

    /**
     * Constructor de la clase Compuesto
     * @param idCompuesto El identificador del compuesto.
     * @param nombre      El nombre del compuesto.
     * @param formula     La fórmula química del compuesto.
     * @param masa        La masa del compuesto.
     * @param DRC         La densidad de rango de concentración del compuesto.
     */
    public Compuesto(int idCompuesto, String nombre, String formula, String masa, String DRC) {
        this.idCompuesto = idCompuesto;
        this.nombre = nombre;
        this.formula = formula;
        this.masa = masa;
        this.DRC = DRC;
    }

    /**
     * Constructor por defecto de la clase Compuesto.
     */
    public Compuesto() {
        super();
    }

    /**
     * Obtiene el identificador del compuesto.
     *
     * @return El identificador del compuesto.
     */
    public long getIdCompuesto() {
        return idCompuesto;
    }

    /**
     * Establece el identificador del compuesto.
     *
     * @param idCompuesto El nuevo identificador del compuesto.
     */
    public void setIdCompuesto(int idCompuesto) {
        this.idCompuesto = idCompuesto;
    }

    /**
     * Obtiene el nombre del compuesto.
     *
     * @return El nombre del compuesto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del compuesto.
     *
     * @param nombre El nuevo nombre del compuesto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la fórmula química del compuesto.
     *
     * @return La fórmula química del compuesto.
     */
    public String getFormula() {
        return formula;
    }

    /**
     * Establece la fórmula química del compuesto.
     *
     * @param formula La nueva fórmula química del compuesto.
     */
    public void setFormula(String formula) {
        this.formula = formula;
    }


    /**
     * Obtiene la masa del compuesto.
     *
     * @return La masa del compuesto.
     */
    public String getMasa() {
        return masa;
    }

    /**
     * Establece la masa del compuesto.
     *
     * @param masa La nueva masa del compuesto.
     */
    public void setMasa(String masa) {
        this.masa = masa;
    }

    /**
     * Obtiene la densidad rango de concentración del compuesto.
     *
     * @return La densidad rango de concentración del compuesto.
     */
    public String getDRC() {
        return DRC;
    }

    /**
     * Establece la densidad rango de concentración del compuesto.
     *
     * @param DRC La nueva densidad rango de concentración del compuesto.
     */
    public void setDRC(String DRC) {
        this.DRC = DRC;
    }

    /**
     * Método para obtener una representación en forma de cadena del objeto Compuesto.
     * @return Una cadena que representa el objeto Compuesto.
     */
    @Override
    public String toString() {
        return "Compuesto [ idCompuesto: " +idCompuesto+ ", nombre:  " +nombre+ ", fórmula: " +formula+ ", masa: " +masa+
                ", DRC: " +DRC+ " ]";
    }
}
