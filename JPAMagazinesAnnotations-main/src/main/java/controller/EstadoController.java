package controller;

import model.Estado;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Scanner;

/**
 * Controlador para la gestión de la entidad Estado.
 * Esta clase extiende de {@link SuperController}, proporcionando funcionalidades
 * específicas para la manipulación de la tabla 'estados' en la base de datos.
 */
public class EstadoController extends SuperController {
    private Scanner scanner;

    /**
     * Crea una nueva instancia de EstadoController sin inicializar los parámetros.
     */
    public EstadoController() {}

    /**
     * Crea una nueva instancia de EstadoController con el EntityManagerFactory proporcionado
     * y el nombre de la tabla 'estados'.
     *
     * @param entityManagerFactory EntityManagerFactory para la gestión de entidades.
     */
    public EstadoController(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, "estados");
        scanner = new Scanner(System.in);
    }

    /**
     * Crea la tabla 'estados' en la base de datos si no existe.
     */
    public void createEstado() {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
        if (doesTableExist("estados")) {
            System.out.println("La tabla 'estados' ya existe");
        } else {
            String nativeQuery = "CREATE TABLE estados (\n" +
                    "    idEstado INT PRIMARY KEY,\n" +
                    "    nombre VARCHAR(255)\n" +
                    ");";
            em.createNativeQuery(nativeQuery).executeUpdate();
            em.getTransaction().commit();
            System.out.println("Tabla 'estados' creada correctamente");
        }
        em.close();
    }

    /**
     * Método para agregar un Estado a la tabla 'estados'.
     *
     * @param data Arreglo de String con los datos del Estado a agregar.
     */
    @Override
    void addInTable(String[] data) {
        EntityManager em = entityManagerFactory.createEntityManager();

        Estado estado = new Estado();
        estado.setIdEstado(Integer.parseInt(data[0]));
        estado.setNombre(data[1]);

        em.getTransaction().begin();
        em.persist(estado);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Lista todos los estados almacenados en la base de datos.
     */
    public void listEstados() {
        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            List<Estado> result = em.createQuery("FROM Estado", Estado.class)
                    .getResultList();

            printData(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Actualiza el nombre de un estado en la base de datos.
     * Solicita al usuario el ID del estado a modificar y el nuevo nombre.
     */
    public void updateNameEstado() {
        listEstados();

        System.out.println("Indica el id del estado que quieres modificar: ");
        int idEstado = scanner.nextInt();

        if (idEstado > 5 || idEstado < 1) {
            System.out.println("No se encontraron coincidencias. Intente con otro id");
        } else {
            scanner.nextLine();
            System.out.println("Introduce el nuevo nombre del estado: ");
            String nuevoNombre = scanner.nextLine();

            EntityManager em = entityManagerFactory.createEntityManager();

            Estado estado = em.find(Estado.class, idEstado);
            estado.setNombre(nuevoNombre);

            em.getTransaction().begin();
            em.merge(estado);
            em.getTransaction().commit();

            em.getTransaction().begin();
            estado = em.find(Estado.class, idEstado);
            System.out.println(estado.toString());
            em.getTransaction().commit();

            em.close();
        }


    }

    /**
     * Imprime los datos de los estados.
     *
     * @param result Lista de objetos Estado a imprimir.
     */
    private void printData(List<Estado> result) {
        System.out.printf("%-22s %-22s%n", "id_estado", "nombre");

        for (Estado estado : result) {
            System.out.printf("%-22d %-22s%n", estado.getIdEstado(), estado.getNombre());
        }
    }

}
