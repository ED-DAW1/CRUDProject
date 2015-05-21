/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private MongoCollection<Document> collection;
    

    public Connection(String db) {
        this.db = db; 
    }
    
    public void setCollection(String collection) {
        this.collection = database.getCollection(collection);
    }

    public MongoCollection<Document> collection(String collection) {
        this.collection = database.getCollection(collection);
        return this.collection;
    }

    public void open() {
        client = new MongoClient();
        database = client.getDatabase(db);
    }
    
    public void close() {
        if (client!=null) client.close();
    }
    
    
}
