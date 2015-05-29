package daw.alex.webCrud;

import daw.alex.DAO.VideoGameDAO;
import daw.alex.entity.VideoGame;
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
    //Localizar el servidor en el que ejecutarse
    //Direccion IP
    private static final String IP_ADDRESS = 
            System.getenv("OPENSHIFT_DIY_IP") != null ? 
            System.getenv("OPENSHIFT_DIY_IP") : "localhost";
    //Puerto
    private static final int PORT = 
            System.getenv("OPENSHIFT_DIY_PORT") != null ? 
            Integer.parseInt(System.getenv("OPENSHIFT_DIY_PORT")) : 4567;
    
    public static void main( String[] args ) {
        Spark.setIpAddress(IP_ADDRESS);
        Spark.setPort(PORT);
        Spark.staticFileLocation("/public");
        final Map<String,Object> data = new HashMap<>();
        
        //Arrancar la base de datos en la coleccion correspondiente a videogame
        VideoGameDAO.start();
        
        //Consultar
        
        get(new FreeMarkerRoute("/") {
            
            @Override
            public Object handle(Request rqst, Response rspns) {
                data.put("page",0);
                rspns.redirect("/show/0");
                return null;
            }
        });
        
        get(new FreeMarkerRoute("/show/:page") {

            @Override
            public ModelAndView handle(Request rqst, Response rspns) {
                data.put("games",VideoGameDAO.pagina(Integer.parseInt(rqst.params(":page"))));
                data.put("error","none");
                data.put("error2","none");
                return modelAndView(data,"index.ftl");
            }
        });
        
        //Añadir 
        
        
        get(new FreeMarkerRoute("/add") {

            @Override
            public ModelAndView handle(Request rqst, Response rspns) {
                data.put("error2","none");
                data.put("id",VideoGameDAO.nextID());
                data.put("games",VideoGameDAO.pagina((int) (VideoGameDAO.contar()/10)));
                return modelAndView(data,"add.ftl");
            }
            
        });
        
        post(new FreeMarkerRoute("/add/:newid") {

            @Override
            public Object handle(Request rqst, Response rspns) {
            try {
                queryCheck(rqst);
                data.put("error","none");
                VideoGameDAO.añadir(queryToClass(rqst,rqst.params(":newid")));
            } catch (IllegalArgumentException ex) {
                data.put("error","block");
            }
                rspns.redirect("/add");
                return null;
            }
            
        });
        
        //Borrar
        
        get(new FreeMarkerRoute("/delete/:page") {
            
            @Override
            public Object handle(Request rqst, Response rspns) {
                data.put("error","none");
                data.put("error2","none");
                data.put("games",VideoGameDAO.pagina(Integer.parseInt(rqst.params(":page"))));
                return modelAndView(data,"del.ftl");
            }
        });
        
        get(new FreeMarkerRoute("/deleteall") {
            
            @Override
            public Object handle(Request rqst, Response rspns) {
                VideoGameDAO.truncar();
                rspns.redirect("/");
                return null;
            }
        });
        
        get(new FreeMarkerRoute("/delete/id/:id") {

            @Override
            public Object handle(Request rqst, Response rspns) {
                VideoGameDAO.borrar(Double.parseDouble(rqst.params(":id")));
                rspns.redirect("/delete/0");
                return null;
            }
        }); 
        
        //Editar
        
        get(new FreeMarkerRoute("/edit/:page") {
            
            @Override
            public ModelAndView handle(Request rqst, Response rspns) {
                data.put("error","none");
                data.put("games",VideoGameDAO.pagina(Integer.parseInt(rqst.params(":page"))));
                return modelAndView(data,"edit.ftl");            
            }
        });
        
        post(new FreeMarkerRoute("/edit/id/:id") {
            
            @Override
            public Object handle(Request rqst, Response rspns) {
            try {
                queryCheck(rqst);
                data.put("error2","none");
                VideoGameDAO.actualizar(queryToClass(rqst,rqst.params(":id")));
            }catch (IllegalArgumentException ex) {
                data.put("error2","block");
            }
                rspns.redirect("/edit/0");
                return null;
            }
        });
        
        //Paginadores
        
        get(new FreeMarkerRoute("/*/next/:page") {

            @Override
            public Object handle(Request rqst, Response rspns) {
                int p = Integer.parseInt(rqst.params(":page"));
                if ((double) VideoGameDAO.contar()/10 >p+1) {
                    data.put("page",p+1);
                    rspns.redirect("/" +rqst.splat()[0] +"/" +(p+1));
                } else {
                    rspns.redirect("/" +rqst.splat()[0] +"/" +p);
                }
                return null;
            }
        });
        
        get(new FreeMarkerRoute("/*/prev/:page") {

            @Override
            public Object handle(Request rqst, Response rspns) {
                int p = Integer.parseInt(rqst.params(":page"));
                if (p>0) {
                    data.put("page",p-1);
                    rspns.redirect("/" +rqst.splat()[0] +"/" +(p-1));
                } else {
                    rspns.redirect("/" +rqst.splat()[0] +"/" +p);
                }
                return null;
            }
        });

    }
            
    private static void queryCheck(Request rqst) throws IllegalArgumentException{
        for (String s : rqst.queryParams()) {
            if (rqst.queryParams(s).equals("")) {
                throw new IllegalArgumentException("Consulta con campos vacios");
            }
        };
    }
    
    private static VideoGame queryToClass(Request rqst,String id) {
        VideoGame game = new VideoGame();
        
        game.setId(Double.parseDouble(id));
        game.setName(rqst.queryParams("name"));
        game.setLaunchdate(rqst.queryParams("launchdate"));
        game.setWebPage(rqst.queryParams("webPage"));
        for (String s:rqst.queryParams("types").split(",")) game.addTypes(s);
        for (String s:rqst.queryParams("platform").split(",")) game.addPlatform(s);
        
        return game;
    }
}


