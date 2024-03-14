package model;

import javax.persistence.*;

/**
 * La clase CompuestoElemento representa una tabla intermedia que almacena la relación entre los compuestos y los elementos.
 * Esta tabla intermedia se utiliza para representar la asociación muchos a muchos entre las clases Elemento y Compuesto.
 */
@Entity
public class CompuestoElemento {

    /**
     * Identificador único de la relación compuesto-elemento.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * Compuesto asociado a la relación.
     * Esta relación representa el compuesto al que pertenece el elemento.
     * Indica que varios registros de la tabla `CompuestoElemento` pueden estar asociados a un solo compuesto,
     * lo que implica que un compuesto puede contener varios elementos.
     */
    @ManyToOne
    @JoinColumn(name = "compuesto_id")
    Compuesto idCompuesto;

    /**
     * Nombre del compuesto asociado.
     */
    @Column(name = "nombreCompuesto", length = 255)
    String nombreC;

    /**
     * Elemento asociado a la relación.
     * Esta relación representa el elemento que forma parte del compuesto.
     * Indica que varios registros de la tabla `CompuestoElemento` pueden estar asociados a un solo elemento,
     * lo que implica que un elemento puede estar presente en varios compuestos.
     */
    @ManyToOne
    @JoinColumn(name = "elemento_id")
    Elemento idElemento;

    /**
     * Símbolo del elemento asociado.
     */
    @Column(name = "simboloElemento", length = 255)
    String simbolo;


    /**
     * Subíndice del elemento asociado en el compuesto.
     */
    @Column(name = "subindice", length = 255)
    int subindice;

    /**
     * Crea una nueva instancia de la clase `CompuestoElemento` con los valores proporcionados.
     *
     * @param id          El identificador único de la relación compuesto-elemento.
     * @param idCompuesto El compuesto asociado a la relación.
     * @param nombreC     El nombre del compuesto asociado.
     * @param idElemento  El elemento asociado a la relación.
     * @param simbolo     El símbolo del elemento asociado.
     * @param subindice   El subíndice del elemento asociado en el compuesto.
     */
    public CompuestoElemento(Long id, Compuesto idCompuesto, String nombreC, Elemento idElemento, String simbolo, int subindice) {
        this.id = id;
        this.idCompuesto = idCompuesto;
        this.nombreC = nombreC;
        this.idElemento = idElemento;
        this.simbolo = simbolo;
        this.subindice = subindice;
    }

    /**
     * Crea una nueva instancia de la clase `CompuestoElemento`.
     */
    public CompuestoElemento() {
        super();
    }

    /**
     * Obtiene el identificador único de la relación compuesto-elemento.
     *
     * @return El identificador único de la relación.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único de la relación compuesto-elemento.
     *
     * @param id El nuevo identificador único de la relación.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el compuesto asociado a la relación.
     *
     * @return El compuesto asociado a la relación.
     */
    public Compuesto getIdCompuesto() {
        return idCompuesto;
    }

    /**
     * Establece el compuesto asociado a la relación.
     *
     * @param idCompuesto El nuevo compuesto asociado a la relación.
     */
    public void setIdCompuesto(Compuesto idCompuesto) {
        this.idCompuesto = idCompuesto;
    }

    /**
     * Obtiene el nombre del compuesto asociado a la relación.
     *
     * @return El nombre del compuesto asociado.
     */
    public String getNombreC() {
        return nombreC;
    }

    /**
     * Establece el nombre del compuesto asociado a la relación.
     *
     * @param nombreC El nuevo nombre del compuesto asociado.
     */
    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

    /**
     * Obtiene el elemento asociado a la relación.
     *
     * @return El elemento asociado a la relación.
     */
    public Elemento getIdElemento() {
        return idElemento;
    }

    /**
     * Establece el elemento asociado a la relación.
     *
     * @param idElemento El nuevo elemento asociado a la relación.
     */
    public void setIdElemento(Elemento idElemento) {
        this.idElemento = idElemento;
    }

    /**
     * Obtiene el símbolo del elemento asociado a la relación.
     *
     * @return El símbolo del elemento asociado.
     */
    public String getSimbolo() {
        return simbolo;
    }

    /**
     * Establece el símbolo del elemento asociado a la relación.
     *
     * @param simbolo El nuevo símbolo del elemento asociado.
     */
    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    /**
     * Obtiene el subíndice del elemento asociado en el compuesto.
     *
     * @return El subíndice del elemento asociado en el compuesto.
     */
    public int getSubindice() {
        return subindice;
    }

    /**
     * Establece el subíndice del elemento asociado en el compuesto.
     *
     * @param subindice El nuevo subíndice del elemento asociado en el compuesto.
     */
    public void setSubindice(int subindice) {
        this.subindice = subindice;
    }

    /**
     * Devuelve una representación en forma de cadena de caracteres de la relación compuesto-elemento.
     *
     * @return Una cadena que representa la relación compuesto-elemento.
     */
    @Override
    public String toString() {
        return "CompuestoElemento [ id: " +id+ ", idCompuesto: " +idCompuesto+ ", nombre: " +nombreC+ ", idElemento: " +idElemento+
                ", símbolo: " +simbolo+ ", subíndice: " +subindice+ " ]";
    }
}
