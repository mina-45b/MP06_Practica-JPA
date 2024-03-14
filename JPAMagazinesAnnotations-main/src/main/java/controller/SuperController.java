package controller;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.List;

/**
 * La clase abstracta `SuperController` proporciona funcionalidades comunes para controladores específicos.
 * Los controladores que extiendan esta clase pueden utilizar métodos para agregar datos desde un archivo CSV a una tabla,
 * eliminar una tabla, verificar si una tabla existe y realizar conversiones de tipos de datos.
 */
public abstract class SuperController {

    /**
     * EntityManagerFactory para interactuar con la base de datos.
     */
    EntityManagerFactory entityManagerFactory;

    /**
     * Nombre de la tabla en la base de datos.
     */
    String tableName;

    /**
     * Constructor por defecto de la clase `SuperController`.
     */
    public SuperController() {
    }

    /**
     * Constructor de la clase `SuperController`.
     *
     * @param entityManagerFactory Fábrica de entity manager para interactuar con la base de datos.
     * @param tableName            Nombre de la tabla en la base de datos.
     */
    public SuperController(EntityManagerFactory entityManagerFactory, String tableName) {
        this.entityManagerFactory = entityManagerFactory;
        this.tableName = tableName;

    }

    /**
     * Método abstracto que debe ser implementado por las subclases para agregar datos a una tabla.
     *
     * @param data Datos a agregar en la tabla.
     */
    abstract void addInTable(String[] data);

    /**
     * Lee un archivo CSV y agrega los datos a la tabla.
     *
     * @param csvPath Ruta del archivo CSV.
     */
    public void readCsv(String csvPath) {
        InputStream is = getClass().getResourceAsStream(csvPath);

        try {
            CSVReader reader = new CSVReader(new InputStreamReader(is));
            List<String[]> records = reader.readAll();

            for (String[] record : records) {
                addInTable(record);
            }
            System.out.println("Tabla '" + tableName + "' completada");
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Elimina la tabla de la base de datos.
     */
    public void deleteTable() {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
        try {
            String nativeQuery = "DROP TABLE " + tableName +";";
            em.createNativeQuery(nativeQuery).executeUpdate();
            em.getTransaction().commit();

            System.out.println("Tabla " + tableName + " eliminada correctamente");
         }catch (Exception e) {
            System.out.println("No se pudo eliminar la tabla " + tableName +", verifica que exista o su relación con otras tablas");
        }
        em.close();
    }

    /**
     * Verifica si una tabla existe en la base de datos.
     *
     * @param tableName Nombre de la tabla a verificar.
     * @return `true` si la tabla existe, `false` si no.
     */
    public boolean doesTableExist(String tableName)  {
        EntityManager em = entityManagerFactory.createEntityManager();
        SessionFactoryImplementor sfi = em.getEntityManagerFactory().unwrap(SessionFactoryImplementor.class);

        try {
            Connection connection = sfi.getServiceRegistry().getService(ConnectionProvider.class).getConnection();
            DatabaseMetaData metaData = connection.getMetaData();

            try (ResultSet tables = metaData.getTables(null, null, tableName, null)) {
                return tables.next(); // Devuelve true si la tabla existe
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    /**
     * Convierte una cadena de texto en un valor entero.
     *
     * @param value Cadena de texto a convertir.
     * @return El valor entero resultante. Si la conversión falla, devuelve 0.
     */
    public Integer parseInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            // Manejo de la excepción (puedes devolver un valor predeterminado)
            return 0;
        }
    }

    /**
     * Convierte una cadena de texto en un valor decimal.
     *
     * @param value Cadena de texto a convertir.
     * @return El valor decimal resultante. Si la cadena es nula o vacía, devuelve null.
     */
    public BigDecimal parseBigDecimal(String value) {
        if (value != null && !value.isEmpty()) {
            return new BigDecimal(value).setScale(3, RoundingMode.HALF_UP);
        }
        return null;
    }
}
