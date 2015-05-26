package daw.alex.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author alex
 */
public class VideoGame {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
    
    private double id;
    private String name;
    private ArrayList<String> types;
    private ArrayList<String> platform;
    private Calendar launchdate;
    private String webPage;

    public VideoGame() {
        types = new ArrayList();
        platform = new ArrayList();
        launchdate = new GregorianCalendar();
    }
    
    //Freemarker Getters

    public double getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getTypes() {
        return types.toString();
    }
    
    public String getPlatform() {
        return platform.get(0);
    }
    
     public String getLaunchdate() {
        return dateFormat.format(launchdate.getTime());
    }

    public String getWebPage() {
        return webPage;
    }
    
    
    //Array Internal Getters

    public ArrayList<String> getArrayTypes() {
        return types;
    }
    
    
    public ArrayList<String> getArrayPlatform() {
        return platform;
    }
    
    //Setters

    public void setId(double id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }


    public void setPlatform(ArrayList<String> platform) {
        this.platform = platform;
    }

   

    public void setLaunchdate(String launchdate) {
        try {
            this.launchdate.setTime(dateFormat.parse(launchdate));
        } catch (ParseException ex) {
            System.err.println("Error en el Formato de Fecha" +ex.getMessage());
        }
    }
    

    
    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }
    
    
    
    
}
