package daw.alex.webCrud;

import daw.alex.DAO.VideoGameDAO;
import daw.alex.entity.VideoGame;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import static spark.Spark.get;
import static spark.Spark.post;
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
                data.put("games",VideoGameDAO.pagina("games",Integer.parseInt(rqst.params(":page"))));
                return modelAndView(data,"index.ftl");
            }
        });
        
        
        get(new FreeMarkerRoute("/add") {

            @Override
            public ModelAndView handle(Request rqst, Response rspns) {
                data.put("id",VideoGameDAO.nextID("games"));
                data.put("games",VideoGameDAO.pagina("games", (int) (VideoGameDAO.nextID("games")/20)));
                return modelAndView(data,"add.ftl");
            }
            
        });
        
        post(new FreeMarkerRoute("/add/:newid") {

            @Override
            public Object handle(Request rqst, Response rspns) {
                VideoGame game = new VideoGame();
                game.setId(Double.parseDouble(rqst.params(":newid")));
                game.setName(rqst.queryParams("name"));
                game.setLaunchdate(rqst.queryParams("launchdate"));
                game.setWebPage(rqst.queryParams("webPage"));
                for (String s:rqst.queryParams("types").split(",")) game.addTypes(s);
                for (String s:rqst.queryParams("platform").split(",")) game.addPlatform(s);

                VideoGameDAO.añadir("games",game);
                
                data.put("games",VideoGameDAO.pagina("games",Integer.parseInt(rqst.params(":newid"))/20));
                rspns.redirect("/add");
                return null;
            }
            
        });
        
    
    }
}
