package daw.alex.webCrud;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import static spark.Spark.get;
import spark.template.freemarker.FreeMarkerRoute;

/**
 * Proyecto Para Entornos de Desarollo y Bases de Datos
 * CRUD de Videojuegos.
 */
public class App {
    public static void main( String[] args ) {
        Spark.staticFileLocation("/public");
        
        get(new FreeMarkerRoute("/") {
            
            @Override
            public ModelAndView handle(Request rqst, Response rspns) {
                return modelAndView(null,"index.ftl");
            }
        });
    
    }
}