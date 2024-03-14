package controller;

import model.Compuesto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Scanner;

/**
 * Controlador para la gestión de la entidad Compuesto.
 * Esta clase extiende de {@link SuperController}, proporcionando funcionalidades
 * específicas para la manipulación de la tabla 'compuestos' en la base de datos.
 */
public class CompuestoController extends SuperController{
    private Scanner scanner;

    /**
     * Crea una nueva instancia de CompuestoController sin inicializar los parámetros.
     */
    public CompuestoController() {
    }

    /**
     * Crea una nueva instancia de CompuestoController con el EntityManagerFactory proporcionado
     * y el nombre de la tabla 'compuestos'.
     * @param entityManagerFactory EntityManagerFactory para la gestión de entidades.
     */
    public CompuestoController(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, "compuestos");
        scanner = new Scanner(System.in);
    }

    /**
     * Método para crear la tabla de compuestos si no existe.
     */
    public void createCompuestos() {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
        if (doesTableExist("compuestos")) {
            System.out.println("La tabla 'compuestos' ya existe");
        } else {
            String nativeQuery = "CREATE TABLE compuestos (\n" +
                    "    idCompuesto INT PRIMARY KEY,\n" +
                    "    nombre VARCHAR(255),\n" +
                    "    formula VARCHAR(255),\n" +
                    "    masa VARCHAR(255),\n" +
                    "    drc VARCHAR(255)\n" +
                    ");";
            em.createNativeQuery(nativeQuery).executeUpdate();
            em.getTransaction().commit();
            System.out.println("Tabla 'compuestos' creada correctamente");
        }
        em.close();
    }


    /**
     * Método para agregar un compuesto a la base de datos.
     * @param data Arreglo de datos del compuesto.
     */
    @Override
    void addInTable(String[] data) {
        EntityManager em = entityManagerFactory.createEntityManager();

        Compuesto compuesto = new Compuesto();
        compuesto.setIdCompuesto(Integer.parseInt(data[0]));
        compuesto.setNombre(data[1]);
        compuesto.setFormula(data[2]);
        compuesto.setMasa(data[3]);
        compuesto.setDRC(data[4]);

        em.getTransaction().begin();
        em.persist(compuesto);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Método para listar todos los compuestos en la base de datos.
     */
    public void listCompuestos() {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            List<Compuesto> result = em.createQuery("FROM Compuesto", Compuesto.class)
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
     * Método para seleccionar y mostrar compuestos según su fórmula química.
     */
    public void selectFormula() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<String> formulas = em.createQuery("SELECT c.formula FROM Compuesto c", String.class).getResultList();
        System.out.println("FÓRMULAS DISPONIBLES: ");

        for(String formula : formulas) {
            System.out.println(formula);
        }

        System.out.println("Ingrese una fórmula química: ");
        String formula = scanner.nextLine();
        System.out.println("Buscando...\n");

        List<Compuesto> result = em.createQuery("SELECT c FROM Compuesto c WHERE c.formula = :formula", Compuesto.class)
                .setParameter("formula", formula)
                .getResultList();

        if (!result.isEmpty()) {
            printData(result);
        } else {
            System.out.println("No se encontraron coincidencias. Intente con otra fórmula");
        }

        em.getTransaction().commit();
        em.close();
    }

    /**
     * Método para eliminar un compuesto de la base de datos según su ID.
     */
    public void deleteCompuestoId() {
        System.out.println("Introduce el id del compuesto que quieres eliminar [1-176]: ");
        int idCompuesto = scanner.nextInt();

        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
        Compuesto compuesto = em.find(Compuesto.class, idCompuesto);

        if (compuesto == null) {
            System.out.println("No es posible eliminar el compuesto. Intente con un id válido");
        } else {
            em.remove(compuesto);
            System.out.println("Compuesto eliminado exitosamente");
        }

        try {
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        em.close();

    }

    /**
     * Método privado para imprimir los datos de los compuestos.
     * @param result Lista de compuestos a imprimir.
     */
    private void printData(List<Compuesto> result) {
        //Imprimir encabezados
        System.out.printf("%-22s %-35s %22s %-22s %-22s%n", "id_compuesto", "nombre", "fórmula", "masa", "DRC" );

        for (Compuesto compuesto : result) {
            System.out.printf("%-22d %-35s %-22s %-22s %-22s%n",
                    compuesto.getIdCompuesto(), compuesto.getNombre(), compuesto.getFormula(), compuesto.getMasa(), compuesto.getDRC());
        }
    }

}
