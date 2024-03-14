package model;

import javax.persistence.*;

/**
 * La clase Estado representa una entidad de estado en la base de datos.
 * Cada estado tiene un identificador único (idEstado) y un nombre asociado.
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "estados")
public class Estado {

    /**
     * Identificador único del estado.
     */
    @Id
    @Column(name = "idEstado")
    int idEstado;

    /**
     * Nombre asociado al estado.
     */
    @Column (name = "nombre", length = 255)
    String nombre;

    /**
     * Constructor que permite inicializar un Estado con un ID y un nombre.
     * @param idEstado Identificador único del estado.
     * @param nombre Nombre asociado al estado.
     */
    public Estado(int idEstado, String nombre) {
        this.idEstado = idEstado;
        this.nombre = nombre;
    }

    /**
     * Constructor por defecto.
     */
    public Estado() {
        super();
    }

    /**
     * Obtiene el identificador único del estado.
     * @return El identificador único del estado.
     */
    public long getIdEstado() {
        return idEstado;
    }

    /**
     * Establece el identificador único del estado.
     * @param idEstado El nuevo identificador único del estado.
     */
    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    /**
     * Obtiene el nombre asociado al estado.
     * @return El nombre asociado al estado.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre asociado al estado.
     * @param nombre El nuevo nombre asociado al estado.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve una representación en cadena del estado.
     * @return Una cadena que representa el estado con sus atributos.
     */
    @Override
    public String toString() {
         return "Estado [ idEstado: " +idEstado+ ", nombre: " +nombre+ " ]";
    }
}
