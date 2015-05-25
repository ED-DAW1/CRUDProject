package daw.alex.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author alex
 */
public class VideoGame {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
    
    private double id;
    private String name;
    private List<String> types;
    private List<String> platform;
    private Calendar launchdate;
    private String webPage;

    public VideoGame() {
        types = new ArrayList();
        platform = new ArrayList();
        launchdate = new GregorianCalendar();
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getPlatform() {
        return platform;
    }

    public void setPlatform(List<String> platform) {
        this.platform = platform;
    }

    public String getLaunchdate() {
        return dateFormat.format(launchdate.getTime());
    }

    public void setLaunchdate(String launchdate) {
        try {
            this.launchdate.setTime(dateFormat.parse(launchdate));
        } catch (ParseException ex) {
            System.err.println("Error en el Formato de Fecha" +ex.getMessage());
        }
    }
    
    public String getWebPage() {
        return webPage;
    }
    
    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }
    
    
    
    
}
