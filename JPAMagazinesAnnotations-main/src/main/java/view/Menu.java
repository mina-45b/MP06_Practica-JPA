package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase Menu representa un menú interactivo para gestionar tablas relacionadas con elementos químicos.
 * Proporciona opciones para crear, borrar, completar, ver, seleccionar, modificar y eliminar tablas.
 */
public class Menu {
    //Almacena la opción seleccionada por el usuario
    private int option;

    /**
     * Constructor por defecto de la clase Menu.
     */
    public Menu() {
        super();
    }

    /**
     * Método que muestra el menú principal y permite al usuario seleccionar una opción.
     * @return La opción seleccionada por el usuario.
     */
    public int mainMenu() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        do {
            //Menú principal con diferentes opciones
            System.out.println("\n====== MENU PRINCIPAL ======\n");

            System.out.println("1.  Crear todas las tablas");
            System.out.println("2.  Borrar todas las tablas\n");

            System.out.println("Completar tablas:");
            System.out.println("3.  Completar tabla estados");
            System.out.println("4.  Completar tabla series");
            System.out.println("5.  Completar tabla elementos");
            System.out.println("6.  Completar tabla compuestos");
            System.out.println("7.  Completar tabla intermedia: CompuestoElemento\n");

            System.out.println("Ver tablas: ");
            System.out.println("8.  Muestra elementos químicos");
            System.out.println("9.  Muestra series");
            System.out.println("10. Muestra estados");
            System.out.println("11. Muestra compuestos");
            System.out.println("12. Muestra tabla intermedia: CompuestoElemento\n");

            System.out.println("Select tablas:");
            System.out.println("13. Seleccionar nombre elemento por texto");
            System.out.println("14. Seleccionar compuesto por id_elemento");
            System.out.println("15. Seleccionar compuesto por fórmula\n");

            System.out.println("Modificar tablas:");
            System.out.println("16. Modificar nombre de los estados");
            System.out.println("17. Modificar idSerie de los elementos a partir del descubrimiento\n");

            System.out.println("Eliminar tablas:");
            System.out.println("18. Eliminar compuestos por ID");
            System.out.println("19. Eliminar elementos por idEstado\n");


            System.out.println("20. Salir. \n");

            System.out.println("Elija una opción: ");
            try {
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("Valor no válido");
                e.printStackTrace();
            }
        } while (option < 1 || option > 20);

        return option;
    }






}