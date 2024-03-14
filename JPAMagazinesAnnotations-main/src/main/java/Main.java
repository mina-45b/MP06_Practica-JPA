import controller.*;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import view.Menu;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase Main contiene el método principal (main) que inicia la aplicación y gestiona el flujo del programa.
 * Inicializa las Session Factory de Hibernate y gestiona el bucle del menú principal.
 * El menú principal permite a los usuarios interactuar con varios controladores para realizar operaciones sobre las entidades.
 * La clase también contiene métodos de utilidad para crear EntityManagerFactory.
 */

public class Main {
    static SessionFactory sessionFactoryObj;

    /**
     * Método que construye y devuelve una Session Factory de Hibernate.
     * @return La Session Factory inicializada.
     * @throws HibernateException Sí se produce un error durante la creación de la Session Factory.
     */
    private static SessionFactory buildSessionFactory() {
        try {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();
        } catch (HibernateException he) {
            System.out.println("Session Factory creation failure");
            throw he;
        }
    }

    /**
     * Método que crea y devuelve una EntityManagerFactory para JPA.
     * @return La EntityManagerFactory inicializada
     * @throws ExceptionInInitializerError Sí ocurre un error durante la creación de la EntityManagerFactory.
     */
    public static EntityManagerFactory createEntityManagerFactory() {
        EntityManagerFactory emf;
        try {
            emf = Persistence.createEntityManagerFactory("JPAElementos");
        } catch (Throwable ex) {
            System.err.println("Failed to create EntityManagerFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        return emf;
    }

    /**
     * Método principal que inicia la aplicación y gestiona las operaciones en las tablas de la base de datos.
     * @param args Argumentos de línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args)  {
        //Instancia de EntityManagerFactory
        EntityManagerFactory entityManagerFactory = createEntityManagerFactory();

        //Instancia de los controladores
        SerieController serieController = new SerieController(entityManagerFactory);
        EstadoController estadoController = new EstadoController(entityManagerFactory);
        CompuestoController compuestoController = new CompuestoController(entityManagerFactory);
        ElementoController elementoController = new ElementoController(entityManagerFactory);
        CompuestoElementoController compuestoElementoController = new CompuestoElementoController(entityManagerFactory);

        Menu menu = new Menu();
        int option;
        option = menu.mainMenu();

        // Se ejecuta un bucle mientas la opción seleccionada esté dentro del rango válido
        while (option > 0 && option < 20) {
            // Se utiliza una estructura de control switch para realizar la operación correspondiente según la opción seleccionada
            switch (option) {
                case 1:
                    serieController.createSeries();
                    estadoController.createEstado();
                    compuestoController.createCompuestos();
                    elementoController.createElementos();
                    compuestoElementoController.createCompuestoElemento();
                    break;
                case 2:
                    compuestoElementoController.deleteTable();
                    compuestoController.deleteTable();
                    elementoController.deleteTable();
                    serieController.deleteTable();
                    estadoController.deleteTable();
                    break;
                case 3:
                    estadoController.readCsv("/estadosDeElementos.csv");
                    break;
                case 4:
                    serieController.readCsv("/seriesDeElementos.csv");
                    break;
                case 5:
                    elementoController.readCsv("/elementosQuimicos.csv");
                    break;
                case 6:
                    compuestoController.readCsv("/compuestosQuimicos.csv");
                    break;
                case 7:
                    compuestoElementoController.readCsv("/compuestosPorElementos.csv");
                    break;
                case 8:
                    System.out.println("TABLA ELEMENTOS");
                    elementoController.listElementos();
                    break;
                case 9:
                    System.out.println("TABLA SERIES");
                    serieController.listSeries();
                    break;
                case 10:
                    System.out.println("TABLA ESTADOS");
                    estadoController.listEstados();
                    break;
                case 11:
                    System.out.println("TABLA COMPUESTOS");
                    compuestoController.listCompuestos();
                    break;
                case 12:
                    System.out.println("TABLA COMPUESTO-ELEMENTO");
                    compuestoElementoController.listCompuestoElemento();
                    break;
                case 13:
                    elementoController.selectText();
                    break;
                case 14:
                    compuestoElementoController.selectPorElemento();
                    break;
                case 15:
                    compuestoController.selectFormula();
                    break;
                case 16:
                    estadoController.updateNameEstado();
                    break;
                case 17:
                    elementoController.updateIdSerie();
                    break;
                case 18:
                    compuestoController.deleteCompuestoId();
                    break;
                case 19:
                    elementoController.deleteElementoIdEstado();
                    break;

                case 20:
                    System.exit(0);

                default:
                    System.out.println("Introduce una de las opciones anteriores");
                    break;
            }
            // Se muestra nuevamente el menú principal y se actualiza la opción seleccionada.
            option = menu.mainMenu();
        }
    }
}