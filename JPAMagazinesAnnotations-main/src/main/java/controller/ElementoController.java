package controller;

import model.Elemento;
import model.Estado;
import model.Serie;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Scanner;

/**
 * Controlador para la gestión de la entidad Elemento.
 * Esta clase extiende de {@link SuperController}, proporcionando funcionalidades
 * específicas para la manipulación de la tabla 'elementos' en la base de datos.
 */
public class ElementoController extends SuperController {
    private Scanner scanner;

    /**
     * Crea una nueva instancia de ElementoController sin inicializar los parámetros.
     */
    public ElementoController() {
    }

    /**
     * Crea una nueva instancia de ElementoController con el EntityManagerFactory proporcionado
     * y el nombre de la tabla 'elementos'.
     * @param entityManagerFactory EntityManagerFactory para la gestión de entidades.
     */
    public ElementoController(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, "elementos");
        scanner = new Scanner(System.in);
    }

    /**
     * Método para crear la tabla de elementos si no existe.
     */
    public void createElementos() {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        if (doesTableExist("elementos")) {
            System.out.println("La tabla 'elementos' ya existe");
        } else {
            String nativeQuey = "CREATE TABLE elementos (\n" +
                    "    idElemento INT PRIMARY KEY,\n" +
                    "    nombre VARCHAR(255),\n" +
                    "    simbolo VARCHAR(255),\n" +
                    "    peso DECIMAL(12,6),\n" +
                    "    idSerie INT,\n" +
                    "    idEstado INT,\n" +
                    "    energia VARCHAR(255),\n" +
                    "    en DECIMAL(12,6),\n" +
                    "    fusion DECIMAL(12,6),\n" +
                    "    ebullicion DECIMAL(12,6),\n" +
                    "    ea DECIMAL(12,6),\n" +
                    "    ionizacion DECIMAL(12,6),\n" +
                    "    radio INT NULL,\n" +
                    "    dureza DECIMAL(12,6),\n" +
                    "    modulo DECIMAL(12,6),\n" +
                    "    densidad DECIMAL(12,6),\n" +
                    "    cond DECIMAL(12,6),\n" +
                    "    calor DECIMAL(12,6),\n" +
                    "    abundancia DECIMAL(12,6),\n" +
                    "    dto INT NULL,\n" +
                    "    FOREIGN KEY (idSerie) REFERENCES series(idSerie),\n" +
                    "    FOREIGN KEY (idEstado) REFERENCES estados(idEstado)\n" +
                    ");";
            em.createNativeQuery(nativeQuey).executeUpdate();
            em.getTransaction().commit();
            System.out.println("Tabla 'elementos' creada correctamente");
        }
        em.close();
    }

    /**
     * Método para agregar un nuevo elemento a la tabla de elementos en la base de datos.
     * Los datos del elemento se pasan como un arreglo de Strings.
     * Se realiza una búsqueda de la serie y el estado del elemento en la base de datos.
     * Si no se encuentran, se asignan valores predeterminados.
     *
     * @param data Arreglo de Strings que contiene los datos del elemento.
     */
    @Override
    void addInTable(String[] data) {
        EntityManager em = entityManagerFactory.createEntityManager();

        Elemento elemento = new Elemento();

        elemento.setIdElemento(parseInteger(data[0]));
        elemento.setNombre(data[1]);
        elemento.setSimbolo(data[2]);
        elemento.setPeso(parseBigDecimal(data[3]));

        int idSerie = parseInteger(data[4]);
        Serie serie = em.find(Serie.class, idSerie);
        if (serie == null) {
            serie = em.find(Serie.class, 10);
        }
        elemento.setIdSerie(serie);

        int idEstado = parseInteger(data[5]);
        Estado estado = em.find(Estado.class, idEstado);
        if (estado == null) {
            estado = em.find(Estado.class, 5);
        }
        elemento.setIdEstado(estado);

        elemento.setEnergia(data[6]);
        elemento.setEN(parseBigDecimal(data[7]));
        elemento.setFusion(parseBigDecimal(data[8]));
        elemento.setEbullicion(parseBigDecimal(data[9]));
        elemento.setEA(parseBigDecimal(data[10]));
        elemento.setIonizacion(parseBigDecimal(data[11]));
        elemento.setRadio(parseInteger(data[12]));
        elemento.setDureza(parseBigDecimal(data[13]));
        elemento.setModulo(parseBigDecimal(data[14]));
        elemento.setDensidad(parseBigDecimal(data[15]));
        elemento.setCond(parseBigDecimal(data[16]));
        elemento.setCalor(parseBigDecimal(data[17]));
        elemento.setAbundancia(parseBigDecimal(data[18]));
        elemento.setDto(parseInteger(data[19]));

        em.getTransaction().begin();
        em.persist(elemento);
        em.getTransaction().commit();
        em.close();

    }

    /**
     * Método para listar los datos de la tabla elementos.
     */
    public void listElementos() {
        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            List<Elemento> result = em.createQuery("FROM Elemento", Elemento.class)
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
     * Método para buscar elementos en la base de datos por su nombre.
     * Se solicita al usuario que ingrese el texto a buscar.
     * Se realiza una consulta utilizando LIKE en la base de datos y se imprime el resultado.
     */
    public void selectText() {
        System.out.println("Ingresa el texto: ");
        String texto = scanner.nextLine();
        System.out.println("Buscando...\n");

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        try {
            List<Elemento> result = em.createQuery("SELECT e FROM Elemento e WHERE e.nombre LIKE :texto", Elemento.class)
                    .setParameter("texto", "%" + texto + "%")
                    .getResultList();

            if (!result.isEmpty()) {
                printData(result);
            } else {
                System.out.println("No se encontraron coincidencias. Intente con otro texto");
            }
        }  finally {
            em.getTransaction().commit();
            em.close();
        }

    }

    /**
     * Método para actualizar el ID de la serie de los elementos en la base de datos.
     * Se solicita al usuario que ingrese el año a partir del cual se hará la actualización.
     * Se solicita el nuevo ID de la serie y se actualiza el ID de la serie para los elementos a partir del año especificado.
     */
    public void updateIdSerie() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<Integer> years = em.createQuery("SELECT e.dto FROM Elemento e ORDER BY e.dto ASC", Integer.class).getResultList();
        System.out.println("AÑOS DISPONIBLES (menor a mayor): ");

        for(Integer year : years) {
            System.out.println(year);
        }

        System.out.println("Indica a partir de que año en adelante en adelante se hará la modificación: ");
        int year = scanner.nextInt();

        System.out.println("Introduce el nuevo idSerie [1-10]: ");
        int nuevoIdSerie = scanner.nextInt();

        if (nuevoIdSerie > 10 || nuevoIdSerie < 1) {
            System.out.println("No es posible realizar la modificación. Intente id válido");
        } else {
            System.out.println("Modificando...\n");

            Serie serie = em.find(Serie.class, nuevoIdSerie);

            List<Elemento> elementos = em.createQuery("SELECT e FROM Elemento e WHERE e.dto = :year", Elemento.class)
                    .setParameter("year", year)
                    .getResultList();

            for (Elemento elemento : elementos) {
                elemento.setIdSerie(serie);
                em.merge(elemento);
            }

            List<Elemento> resultList = em.createQuery("SELECT e FROM Elemento e WHERE e.dto = :year", Elemento.class)
                    .setParameter("year", year)
                    .getResultList();

            printData(resultList);
            em.getTransaction().commit();

        }
        em.close();
    }

    /**
     * Método para eliminar un elemento de la base de datos según su ID de estado.
     */
    public void deleteElementoIdEstado() {
        System.out.println("Introduce el idEstado de los elementos a eliminar [1-5]:");
        int idEstado = scanner.nextInt();

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Estado estado = em.find(Estado.class, idEstado);

        if (estado == null) {
            System.out.println("No es posible realizar la eliminación. Intente un id válido");
        } else {

            List<Elemento> result = em.createQuery("FROM Elemento WHERE idEstado = :idEstado", Elemento.class)
                    .setParameter("idEstado", estado)
                    .getResultList();

            for (Elemento elemento : result) {
                em.remove(elemento);
            }

            try {
                em.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            listElementos();
            em.close();
        }
    }

    /**
     * Método privado para imprimir los datos de la tabla elementos.
     *
     * @param result Lista de objetos CompuestoElemento a imprimir.
     */
    private void printData (List<Elemento> result) {
        // Imprimir encabezado de la tabla
        System.out.printf("%-22s %-22s %-22s %-22s %-22s %-22s %-22s %-22s %-22s %-22s %-22s %-22s %-22s %-22s %-22s %-22s %-22s %-22s %-22s %-22s%n",
                "id_elemento", "Nombre", "Símbolo", "Peso", "id_Serie", "id_Estado", "Energía", "EN", "Fusión", "Ebullición", "EA", "Ionización", "Radio", "Dureza", "Módulo", "Densidad", "Cond", "Calor", "Abundancia", "Dto");

        for (Elemento elemento : result) {
            // Imprimir cada fila de la tabla
            System.out.printf("%-22d %-22s %-22s %-22.4f %-22d %-22d %-22s %-22.4f %-22.4f %-22.4f %-22.4f %-22.4f %-22d %-22.4f %-22.4f %-22.4f %-22.4f %-22.4f %-22.4f %-22d%n",
                    elemento.getIdElemento(), elemento.getNombre(), elemento.getSimbolo(), elemento.getPeso(), elemento.getIdSerie().getIdSerie(), elemento.getIdEstado().getIdEstado(), elemento.getEnergia(), elemento.getEN(), elemento.getFusion(),
                    elemento.getEbullicion(), elemento.getEA(), elemento.getIonizacion(), elemento.getRadio(), elemento.getDureza(), elemento.getDureza(), elemento.getModulo(), elemento.getCond(), elemento.getCalor(), elemento.getAbundancia(), elemento.getDto());
        }
    }

}
