package controller;

import model.Compuesto;
import model.CompuestoElemento;
import model.Elemento;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Controlador para la gestión de la entidad CompuestoElemento que representa la tabla intermedia.
 * Esta clase extiende de {@link SuperController}, proporcionando funcionalidades
 * específicas para la manipulación de la tabla 'compuestoelemetno' en la base de datos.
 */
public class CompuestoElementoController extends SuperController{
    private Scanner scanner;

    /**
     * Crea una nueva instancia de CompuestoElementoController sin inicializar los parámetros.
     */
    public CompuestoElementoController() {
    }

    /**
     * Crea una nueva instancia de CompuestoElementoController con el EntityManagerFactory proporcionado
     * y el nombre de la tabla 'compuestoelemento'.
     * @param entityManagerFactory EntityManagerFactory para la gestión de entidades.
     */
    public CompuestoElementoController(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, "CompuestoElemento");
        scanner = new Scanner(System.in);
    }

    /**
     * Método para crear la tabla de compuestoelemento si no existe.
     */
    public void createCompuestoElemento() {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
        if (doesTableExist("CompuestoElemento")) {
            System.out.println("La tabla 'CompuestoElemento' ya existe");
        } else {
            String nativeQuery = "CREATE TABLE CompuestoElemento (\n" +
                    "    id SERIAL PRIMARY KEY,\n" +
                    "    compuesto_id INT,\n" +
                    "    nombreCompuesto VARCHAR(255),\n" +
                    "    elemento_id INT,\n" +
                    "    simboloElemento VARCHAR(255),\n" +
                    "    subindice INT,\n" +
                    "    FOREIGN KEY (compuesto_id) REFERENCES compuestos(idCompuesto),\n" +
                    "    FOREIGN KEY (elemento_id) REFERENCES elementos(idElemento)\n" +
                    ");";
            em.createNativeQuery(nativeQuery).executeUpdate();
            em.getTransaction().commit();
            System.out.println("Tabla 'CompuestoElemento' creada correctamente");
        }
        em.close();
    }

    /**
     * Método para agregar datos a la tabla CompuestoElemento.
     * Se realiza la búsqueda de los id asociados, en caso de no encontrarse se notifica al usuario
     * y no se realiza la inserción.
     *
     * @param data Arreglo de datos a ser agregados a la tabla.
     */
    @Override
    void addInTable(String[] data) {
        EntityManager em = entityManagerFactory.createEntityManager();

        int idCompuesto = Integer.parseInt(data[0]);
        int idElemento = Integer.parseInt(data[2]);
        Compuesto compuesto = em.find(Compuesto.class, idCompuesto);
        Elemento elemento = em.find(Elemento.class, idElemento);

        if (compuesto != null && elemento != null) {
            CompuestoElemento compuestoElemento = new CompuestoElemento();

            compuestoElemento.setIdCompuesto(compuesto);
            compuestoElemento.setNombreC(data[1]);
            compuestoElemento.setIdElemento(elemento);
            compuestoElemento.setSimbolo(data[3]);
            compuestoElemento.setSubindice(parseInteger(data[4]));

            em.getTransaction().begin();
            em.persist(compuestoElemento);
            em.getTransaction().commit();
            em.close();

        } else {
            System.out.println("No es posible agregar "+ Arrays.toString(data) + " , verifique que la realción exista");
        }
    }

    /**
     * Método para listar los datos de la tabla CompuestoElemento.
     */
    public void listCompuestoElemento() {
        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            List<CompuestoElemento> result = em.createQuery("FROM CompuestoElemento", CompuestoElemento.class)
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
     * Método para seleccionar datos de la tabla CompuestoElemento por elemento.
     */
    public void selectPorElemento() {
        System.out.println("Ingresa el id del elemento [1-118]");
        int idElemento = scanner.nextInt();
        System.out.println("Buscando...\n");

        EntityManager em = entityManagerFactory.createEntityManager();

        Elemento elemento = em.find(Elemento.class, idElemento);

        if (elemento != null) {
            em.getTransaction().begin();

            List<CompuestoElemento> result =  em.createQuery("SELECT ce FROM CompuestoElemento ce WHERE ce.idElemento = :idElemento", CompuestoElemento.class)
                    .setParameter("idElemento", elemento)
                    .getResultList();

                printData(result);
        } else {
            System.out.println("No se encontraron coincidencias. Intente con otro id");
        }
        em.close();
    }

    /**
     * Método privado para imprimir los datos de la tabla CompuestoElemento.
     *
     * @param result Lista de objetos CompuestoElemento a imprimir.
     */
    private void printData(List<CompuestoElemento> result) {
        System.out.printf("%-22s %-22s %-35s %-22s %-22s %-22s%n", "id", "id_compuesto", "nombre compuesto", "id_elemento", "símbolo elemento", "subíndice");

        for (CompuestoElemento compuestoElemento : result) {
            System.out.printf("%-22d %-22d %-35s %-22d %-22s %-22d%n",
                    compuestoElemento.getId(), compuestoElemento.getIdCompuesto().getIdCompuesto(), compuestoElemento.getNombreC(), compuestoElemento.getIdElemento().getIdElemento(), compuestoElemento.getSimbolo(), compuestoElemento.getSubindice());
        }
    }

}
