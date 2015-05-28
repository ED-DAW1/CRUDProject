package daw.alex.DAO;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.eq;
import daw.alex.entity.VideoGame;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author alex
 */
public class VideoGameDAO {
    static Connection db = new Connection("videogames");
    static String coleccion = "games";
    
    public static ArrayList<VideoGame> pagina(int page) {
        db.open();
        db.setCollection(coleccion);
        ArrayList<VideoGame> games = new ArrayList();
        
        MongoCursor<Document> cursor = db.collection.find().sort(eq("name",1)).limit(10).skip(page*10).iterator();
        while (cursor.hasNext()) {
            games.add(docToClass(cursor.next()));
        }
        
        db.close();
        return games;
    }
    
    public static double nextID() {
        db.open();
        db.setCollection(coleccion);
        
        double id;
        try {
        id=1 + db.collection.find().sort(eq("_id",-1)).first().getDouble("_id");
        } catch (NullPointerException ex) {
            id=1;
        }
        return id;
        
    }
    
    public static void añadir(VideoGame game) {
        db.open();
        db.setCollection(coleccion);
        
        db.collection.insertOne(classToDoc(game));
        db.close();
    }
    
    public static void borrar(Double id) {
        db.open();
        db.setCollection(coleccion);
        
        db.collection.deleteOne(eq("_id",id));
        db.close();
    }
    
    public static void truncar() {
        db.open();
        db.setCollection(coleccion);
        
        db.collection.drop();
        db.close();
    }
    
    public static void actualizar(VideoGame game) {
        db.open();
        db.setCollection(coleccion);
        
        db.collection.replaceOne(eq("_id",game.getId()),classToDoc(game));
    }
    
    public static long contar() {
        db.open();
        db.setCollection(coleccion);
        
        long c = db.collection.count();
        db.close();
        return c;
    }
    
    private static VideoGame docToClass(Document doc) {
        VideoGame tmp = new VideoGame();
            tmp.setId((doc.getDouble("_id")));
            tmp.setName(doc.getString("name"));
            tmp.setTypes((ArrayList<String>) doc.get("types"));
            tmp.setPlatform((ArrayList<String>) doc.get("platform"));
            tmp.setLaunchdate(doc.getString("launchdate"));
            tmp.setWebPage(doc.getString("webpage"));
        return tmp;
    }
    
    private static Document classToDoc(VideoGame game) {
        return new Document("_id", game.getId())
                .append("name", game.getName())
                .append("types", game.getArrayTypes())
                .append("platform", game.getArrayPlatform())
                .append("launchdate", game.getLaunchdate())
                .append("webpage", game.getWebPage());
    }
    
}
