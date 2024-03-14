package model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 * La clase Elemento representa un elemento químico y sus propiedades.
 * Cada elemento tiene un identificador único (idElemento) y un nombre asociado.
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "elementos")
public class Elemento implements Serializable {

    /**
     * Identificador únido del elemento.
     */
    @Id
    @Column(name = "idElemento")
    int idElemento;

    /**
     * Nombre del elemento.
     */
    @Column (name = "nombre", length = 255)
    String nombre;

    /**
     * Símbolo del elemento.
     */
    @Column(name = "simbolo", length = 255)
    String simbolo;

    /**
     * Peso atómico del elemento.
     */
    @Column(name = "peso", precision = 12, scale = 6)
    BigDecimal peso;

    /**
     * Serie a la que pertenece el elemento.
     * Esta relación define que muchos elementos pueden pertenecer a la misma serie,
     * pero un elemento solo puede pertenecer a una única serie.
     */
    @ManyToOne
    @JoinColumn(name = "idSerie", referencedColumnName = "idSerie")
    public Serie idSerie;

    /**
     * Estado del elemento.
     * Esta relación establece que muchos elementos pueden tener el mismo estado,
     * pero un estado solo puede pertenecer a un único elemento.
     */
    @ManyToOne
    @JoinColumn(name = "idEstado", referencedColumnName = "idEstado")
    public Estado idEstado;

    /**
     * Energía del elemento.
     */
    @Column (name = "energia", length = 255)
    String energia;

    /**
     * Electronegatividad del elemento.
     */
    @Column (name = "en", precision = 12, scale = 6)
    BigDecimal EN;

    /**
     * Punto de fusión del elemento.
     */
    @Column (name = "fusion", precision = 12, scale = 6)
    BigDecimal fusion;

    /**
     * Punto de ebullición del elemento.
     */
    @Column (name = "ebullicion", precision = 12, scale = 6)
    BigDecimal ebullicion;

    /**
     * Afinidad electrónica del elemento.
     */
    @Column (name = "ea", precision = 12, scale = 6)
    BigDecimal EA;

    /**
     * Energía de ionización del elemento.
     */
    @Column (name = "ionizacion", precision = 12, scale = 6)
    BigDecimal ionizacion;

    /**
     * Radio atómico del elemento.
     */
    @Column (name = "radio")
    int radio;

    /**
     * Dureza del elemento.
     */
    @Column (name = "dureza", precision = 12, scale = 6)
    BigDecimal dureza;

    /**
     * Módulo del elemento.
     */
    @Column (name = "modulo", precision = 12, scale = 6)
    BigDecimal modulo;

    /**
     * Densidad del elemento.
     */
    @Column (name = "densidad", precision = 12, scale = 6)
    BigDecimal densidad;

    /**
     * Conductividad del elemento.
     */
    @Column (name = "cond", precision = 12, scale = 6)
    BigDecimal Cond;

    /**
     * Calor específico del elemento.
     */
    @Column (name = "calor", precision = 12, scale = 6)
    BigDecimal calor;

    /**
     * Abundancia del elemento.
     */
    @Column (name = "abundancia", precision = 12, scale = 6)
    BigDecimal abundancia;

    /**
     * Descubrimiento del elemento.
     */
    @Column (name = "dto")
    int dto;

    /**
     * Conjunto de compuestos que contienen el elemento.
     * Esta relación representa que un elemento puede estar presente en varios compuestos
     * y un compuesto puede contener varios elementos.
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "CompuestoElemento",
            joinColumns = @JoinColumn(name = "elemento_id"),
            inverseJoinColumns = @JoinColumn(name = "compuesto_id")
    )
    private Set<Compuesto> compuestos;

    /**
     * Constructor de la clase Elemeto.
     * @param elementoId    El identificador del elemento.
     * @param nombre        El nombre del elemento.
     * @param simbolo       El símbolo del elemento.
     * @param peso          El peso atómico del elemento.
     * @param idSerie       La serie a la que pertenece el elemento.
     * @param idEstado      El estado del elemento.
     * @param energia       La energía del elemento.
     * @param EN            La electronegatividad del elemento.
     * @param fusion        El punto de fusión del elemento.
     * @param ebullicion    El punto de ebullición del elemento.
     * @param EA            Afinidad electrónica del elemento.
     * @param ionizacion    La energía de ionización del elemento.
     * @param radio         El radio atómico del elemento.
     * @param dureza        La dureza del elemento.
     * @param modulo        El módulo del elemento.
     * @param densidad      La densidad del elemento.
     * @param cond          La conductividad del elemento.
     * @param calor         El calor específico del elemento.
     * @param abundancia    La abundancia del elemento.
     * @param dto           El descubrimiento del elemento.
     */
    public Elemento(int elementoId, String nombre, String simbolo, BigDecimal peso, Serie idSerie, Estado idEstado, String energia, BigDecimal EN, BigDecimal fusion, BigDecimal ebullicion, BigDecimal EA, BigDecimal ionizacion, int radio, BigDecimal dureza, BigDecimal modulo, BigDecimal densidad, BigDecimal cond, BigDecimal calor, BigDecimal abundancia, int dto) {
        this.idElemento = elementoId;
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.peso = peso;
        this.idSerie = idSerie;
        this.idEstado = idEstado;
        this.energia = energia;
        this.EN = EN;
        this.fusion = fusion;
        this.ebullicion = ebullicion;
        this.EA = EA;
        this.ionizacion = ionizacion;
        this.radio = radio;
        this.dureza = dureza;
        this.modulo = modulo;
        this.densidad = densidad;
        this.Cond = cond;
        this.calor = calor;
        this.abundancia = abundancia;
        this.dto = dto;
    }

    /**
     * Constructor por defecto de la clase Elemento.
     */
    public Elemento() {
        super();
    }

    /**
     * Obtiene el identificador del elemento.
     *
     * @return El identificador del elemento.
     */
    public long getIdElemento() {
        return idElemento;
    }

    /**
     * Establece el identificador del elemento.
     *
     * @param idElemento El nuevo identificador del elemento.
     */
    public void setIdElemento(int idElemento) {
        this.idElemento = idElemento;
    }

    /**
     * Obtiene el nombre del elemento.
     *
     * @return El nombre del elemento.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del elemento.
     *
     * @param nombre El nuevo nombre del elemento.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el símbolo del elemento.
     *
     * @return El símbolo del elemento.
     */
    public String getSimbolo() {
        return simbolo;
    }

    /**
     * Establece el símbolo del elemento.
     *
     * @param simbolo El nuevo símbolo del elemento.
     */
    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    /**
     * Obtiene el peso del elemento.
     *
     * @return El peso del elemento.
     */
    public BigDecimal getPeso() {
        return peso;
    }

    /**
     * Establece el peso del elemento.
     *
     * @param peso El nuevo peso del elemento.
     */
    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    /**
     * Obtiene la serie a la que pertenece el elemento.
     *
     * @return La serie a la que pertenece el elemento.
     */
    public Serie getIdSerie() {
        return idSerie;
    }

    /**
     * Establece la serie a la que pertenece el elemento.
     *
     * @param idSerie La nueva serie a la que pertenece el elemento.
     */
    public void setIdSerie(Serie idSerie) {
        this.idSerie = idSerie;
    }

    /**
     * Obtiene el estado del elemento.
     *
     * @return El estado del elemento.
     */
    public Estado getIdEstado() {
        return idEstado;
    }

    /**
     * Establece el estado del elemento.
     *
     * @param idEstado El nuevo estado del elemento.
     */
    public void setIdEstado(Estado idEstado) {
        this.idEstado = idEstado;
    }

    /**
     * Obtiene la energía del elemento.
     * @return La energía del elemento.
     */
    public String getEnergia() {
        return energia;
    }

    /**
     * Establece la energía del elemento.
     * @param energia La nueva energía del elemento.
     */
    public void setEnergia(String energia) {
        this.energia = energia;
    }

    /**
     * Obtienen la electronegatividad del elemento.
     * @return La electronegatividad del elemento.
     */
    public BigDecimal getEN() {
        return EN;
    }

    /**
     * Establece la electronegatividad del elemento.
     * @param EN La nueva electronegatividad del elemento.
     */
    public void setEN(BigDecimal EN) {
        this.EN = EN;
    }

    /**
     * Obtienen la fusión del elemento.
     * @return La fusión del elemento.
     */
    public BigDecimal getFusion() {
        return fusion;
    }

    /**
     * Establece la fusión del elemento.
     * @param fusion La nueva fusión del elemento.
     */
    public void setFusion(BigDecimal fusion) {
        this.fusion = fusion;
    }

    /**
     * Obtienen la ebullición del elemento.
     * @return La ebullición del elemento.
     */
    public BigDecimal getEbullicion() {
        return ebullicion;
    }

    /**
     * Establece la ebullición del elemento.
     * @param ebullicion La nueva ebullición del elemento.
     */
    public void setEbullicion(BigDecimal ebullicion) {
        this.ebullicion = ebullicion;
    }

    /**
     * Obtienen la afinidad electrónica del elemento.
     * @return La afinidad electrónica del elemento.
     */
    public BigDecimal getEA() {
        return EA;
    }

    /**
     * Establece la afinidad electrónica del elemento.
     * @param EA La nueva afinidad electrónica del elemento.
     */
    public void setEA(BigDecimal EA) {
        this.EA = EA;
    }

    /**
     * Obtienen la ionización del elemento.
     * @return La ionización del elemento.
     */
    public BigDecimal getIonizacion() {
        return ionizacion;
    }

    /**
     * Establece la ionización del elemento.
     * @param ionizacion La nueva ionización del elemento.
     */
    public void setIonizacion(BigDecimal ionizacion) {
        this.ionizacion = ionizacion;
    }

    /**
     * Obtienen el radio del elemento.
     * @return El radio del elemento.
     */
    public int getRadio() {
        return radio;
    }

    /**
     * Establece el radio del elemento.
     * @param radio El nuevo radio del elemento.
     */
    public void setRadio(int radio) {
        this.radio = radio;
    }

    /**
     * Obtienen la dureza del elemento.
     * @return La dureza del elemento.
     */
    public BigDecimal getDureza() {
        return dureza;
    }

    /**
     * Establece la dureza del elemento.
     * @param dureza La nueva dureza del elemento.
     */
    public void setDureza(BigDecimal dureza) {
        this.dureza = dureza;
    }

    /**
     * Obtienen el módulo del elemento.
     * @return El módulo del elemento.
     */
    public BigDecimal getModulo() {
        return modulo;
    }

    /**
     * Establece el módulo del elemento.
     * @param modulo El nuevo módulo del elemento.
     */
    public void setModulo(BigDecimal modulo) {
        this.modulo = modulo;
    }

    /**
     * Obtienen la densidad del elemento.
     * @return La densidad del elemento.
     */
    public BigDecimal getDensidad() {
        return densidad;
    }

    /**
     * Establece la densidad del elemento.
     * @param densidad La nueva densidad del elemento.
     */
    public void setDensidad(BigDecimal densidad) {
        this.densidad = densidad;
    }

    /**
     * Obtienen la conductividad del elemento.
     * @return La conductividad del elemento.
     */
    public BigDecimal getCond() {
        return Cond;
    }

    /**
     * Establece la conductividad del elemento.
     * @param cond La nueva conductividad del elemento.
     */
    public void setCond(BigDecimal cond) {
        Cond = cond;
    }

    /**
     * Obtienen el calor del elemento.
     * @return El calor del elemento.
     */
    public BigDecimal getCalor() {
        return calor;
    }

    /**
     * Establece el calor del elemento.
     * @param calor El nuevo calor del elemento.
     */
    public void setCalor(BigDecimal calor) {
        this.calor = calor;
    }

    /**
     * Obtienen la abundancia del elemento.
     * @return La abundancia del elemento.
     */
    public BigDecimal getAbundancia() {
        return abundancia;
    }

    /**
     * Establece la abundancia del elemento.
     * @param abundancia La nueva abundancia del elemento.
     */
    public void setAbundancia(BigDecimal abundancia) {
        this.abundancia = abundancia;
    }

    /**
     * Obtienen el descubrimiento del elemento.
     * @return El descubrimiento del elemento.
     */
    public int getDto() {
        return dto;
    }

    /**
     * Establece el descubrimiento del elemento.
     * @param dto El nuevo descubrimiento del elemento.
     */
    public void setDto(int dto) {
        this.dto = dto;
    }

    /**
     * Método para obtener una representación en forma de cadena del objeto Elemento.
     * @return Una cadena que representa el objeto Elemento.
     */
    @Override
    public String toString() {
        return  "Elemento [ idElemento: " +idElemento+ " , nombre: " +nombre+ ", simbolo: " +simbolo+ ", peso: " +peso+
                " , idSerie: " +idSerie+ ", idEstado: " +idEstado+ ", energía: " +energia+ ", EN: " +EN+ ", fusión: " +fusion+
                ", ebullición: " +ebullicion+ ", EA: " +EA+ ", ionización: " +ionizacion+ ", radi: " +radio+ ", dureza: " +dureza+
                ", modúlo: " +modulo+ ", densidad: " +densidad+ ", cond: " +Cond+ ", calor: " +calor+ ", abundancia: " +abundancia+
                ", descubrimiento: " +dureza+ " ]";
    }
}
