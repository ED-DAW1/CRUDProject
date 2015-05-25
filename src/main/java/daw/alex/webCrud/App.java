package daw.alex.webCrud;

import daw.alex.DAO.VideoGameDAO;
import java.util.HashMap;
import java.util.Map;
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
        final Map<String,Object> data = new HashMap<>();
        
        get(new FreeMarkerRoute("/") {
            
            @Override
            public Object handle(Request rqst, Response rspns) {
                rspns.redirect("/show/0");
                return null;
            }
        });
        
        get(new FreeMarkerRoute("/show/:page") {

            @Override
            public ModelAndView handle(Request rqst, Response rspns) {
                data.put("games",VideoGameDAO.pagina("game",Integer.parseInt(rqst.params(":page"))));
                return modelAndView(data,"index.ftl");
            }
        });
        
    
    }
}
