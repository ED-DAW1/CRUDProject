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
            Document d = cursor.next();
            VideoGame tmp = new VideoGame();
            tmp.setId((d.getDouble("_id")));
            tmp.setName(d.getString("name"));
            tmp.setTypes((List<String>) d.get("types"));
            tmp.setPlatform((List<String>) d.get("platform"));
            tmp.setLaunchdate(d.getString("launchdate"));
            tmp.setWebPage(d.getString("webpage"));
            games.add(tmp);
        }
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

        db.collection.insertOne(
                new Document("_id",game.getId())
                        .append("name",game.getName())
                        .append("types",game.getTypes())
                        .append("platform",game.getPlatform())
                        .append("launchdate",game.getLaunchdate())
                        .append("webpage",game.getWebPage())
        );

    }
    
}
