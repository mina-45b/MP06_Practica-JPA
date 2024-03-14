package model;

import javax.persistence.*;

/**
 * La clase Serie representa una entidad de serie en la base de datos.
 * Cada serie tiene un identificador único (idSerie) y un nombre asociado.
 * */
@Entity
@Access(AccessType.FIELD)
@Table(name = "series")
public class Serie {
    /**
     * Indicador único de la serie.
     */
    @Id
    @Column(name = "idSerie")
    int idSerie;

    /**
     * Nombre asociado a la serie.
     */
    @Column (name = "nombre")
    String nombre;

    /**
     * Constructor que permite inicializar una Serie con un ID y un nombre;
     * @param idSerie Identificador único de la serie.
     * @param nombre Nombre asociado a la serie.
     */
    public Serie(int idSerie, String nombre) {
        this.idSerie = idSerie;
        this.nombre = nombre;
    }

    /**
     * Constructor por defecto.
     */
    public Serie() {
        super();
    }

    /**
     * Obtiene el identificador único de la serie.
     * @return El identificador único de la serie.
     */
    public long getIdSerie() {
        return idSerie;
    }

    /**
     * Establece el identificador único de la serie.
     * @param idSerie El nuevo identificador único de la serie.
     */
    public void setIdSerie(int idSerie) {
        this.idSerie = idSerie;
    }

    /**
     * Establece el nombre asociado a la serie.
     * @return nombre El nuevo nombre asociado a la serie.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre asociado a la serie.
     * @param nombre El nuevo nombre asociado a la serie.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve una representación en cadena de la serie.
     * @return Una cadena que representa la serie con sus atributos.
     */
    @Override
    public String toString() {
        return "Serie [ idSerie: " +idSerie+ ", nombre: " +nombre +" ]";
    }
}
