package daw.alex.DAO;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author alex
 */
public class Connection {
    private String db;
    private MongoClient client;
    private MongoDatabase database;
    protected MongoCollection<Document> collection;
    

    Connection(String db) {
        this.db = db;
    }
    
    protected void setCollection(String collection) {
        this.collection = database.getCollection(collection);
    }

    protected MongoCollection<Document> collection(String collection) {
        this.collection = database.getCollection(collection);
        return this.collection;
    }
    

    protected void open() {
        client = new MongoClient();
        database = client.getDatabase(db);
    }
    
    protected void close() {
        if (client!=null) client.close();
    }
    
    
}
