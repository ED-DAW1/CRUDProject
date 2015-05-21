package daw.alex.webCrud;

import spark.Spark;

/**
 * Proyecto Para Entornos de Desarollo y Bases de Datos
 * CRUD de Videojuegos.
 *
 */
public class App {
    public static void main( String[] args ) {
        Spark.staticFileLocation("/public");
    }
}
