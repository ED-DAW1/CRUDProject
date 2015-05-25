package daw.alex.DAO;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import daw.alex.entity.VideoGame;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author alex
 */
public class VideoGameDAO {
    static Connection db = new Connection("videogames");
    
    public static List<VideoGame> pagina(String coleccion,int page) {
        db.open();
        db.setCollection(coleccion);
        List<VideoGame> games = new ArrayList();
        
        MongoCursor<Document> cursor = db.collection.find().sort(eq("name",1)).limit(20).skip(page).iterator();
        while (cursor.hasNext()) {
            games.add(docToClass(cursor.next()));
        }
        db.close();
        return games;
    }
    
    public static double nextID(String coleccion) {
        db.open();
        db.setCollection(coleccion);
       
        return 1 + db.collection.find().sort(eq("_id",-1)).first().getDouble("_id");
    }
    
    public static void añadir(String coleccion,VideoGame game) {
        db.open();
        db.setCollection(coleccion);

        db.collection.insertOne(classToDoc(game));
        db.close();
    }
    
    public static void borrar(String coleccion,Double id) {
        db.open();
        db.setCollection(coleccion);
        
        db.collection.deleteOne(eq("_id",id));
        db.close();
    }
    
    public static void actualizar(String coleccion,VideoGame game) {
        db.open();
        db.setCollection(coleccion);
        
        db.collection.replaceOne(eq(":id",game.getId()),classToDoc(game));
    }
    
    private static VideoGame docToClass(Document doc) {
        VideoGame tmp = new VideoGame();
            tmp.setId((doc.getDouble("_id")));
            tmp.setName(doc.getString("name"));
            tmp.setTypes((List<String>) doc.get("types"));
            tmp.setPlatform((List<String>) doc.get("platform"));
            tmp.setLaunchdate(doc.getString("launchdate"));
            tmp.setWebPage(doc.getString("webpage"));
        return tmp;
    }
    
    private static Document classToDoc(VideoGame game) {
        return new Document("_id", game.getId())
                .append("name", game.getName())
                .append("types", game.getTypes())
                .append("platform", game.getPlatform())
                .append("launchdate", game.getLaunchdate())
                .append("webpage", game.getWebPage());
    }
     
}
