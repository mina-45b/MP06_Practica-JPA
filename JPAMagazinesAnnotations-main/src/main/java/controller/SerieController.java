package controller;

import model.Serie;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Controlador para la gestión de la entidad Serie.
 * Esta clase extiende de {@link SuperController}, proporcionando funcionalidades
 * específicas para la manipulación de la tabla 'series' en la base de datos.
 */
public class SerieController extends SuperController {

    /**
     * Crea una nueva instancia de SerieController sin inicializar los parámetros.
     */
    public SerieController() {
    }

    /**
     * Crea una nueva instancia de SerieController con el EntityManagerFactory proporcionado
     * y el nombre de la tabla 'series'.
     *
     * @param entityManagerFactory EntityManagerFactory para la gestión de entidades.
     */
    public SerieController(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, "series");
    }

    /**
     * Crea la tabla 'series' en la base de datos si no existe.
     */
    public void createSeries () {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
        if(doesTableExist("series")) {
            System.out.println("La tabla 'series' ya existe");
        } else {
            String nativeQuery = "CREATE TABLE series (\n" +
                    "    idSerie INT PRIMARY KEY,\n" +
                    "    nombre VARCHAR(255)\n" +
                    ");";
            em.createNativeQuery(nativeQuery).executeUpdate();
            em.getTransaction().commit();
            System.out.println("Tabla 'series' creada correctamente");
        }
        em.close();
    }

    /**
     * Método para agregar una Serie a la tabla 'series'.
     *
     * @param data Arreglo de String con los datos de la Serie a agregar.
     */
    @Override
    void addInTable(String[] data) {
        EntityManager em = entityManagerFactory.createEntityManager();

        Serie serie = new Serie();
        serie.setIdSerie(Integer.parseInt(data[0]));
        serie.setNombre(data[1]);

        em.getTransaction().begin();
        em.persist(serie);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Lista todas las series almacenadas en la base de datos.
     */
    public void listSeries() {
        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            List<Serie> result = em.createQuery("FROM Serie", Serie.class)
                    .getResultList();

            System.out.printf("%-22s %-22s%n", "id_serie", "nombre");

            for (Serie serie : result) {
                System.out.printf("%-22d %-22s%n", serie.getIdSerie(), serie.getNombre());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

}
