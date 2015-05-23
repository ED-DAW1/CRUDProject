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
    
    static List<VideoGame> pagina(String coleccion,int page) {
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
    
}
