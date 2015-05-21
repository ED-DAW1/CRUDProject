/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.alex.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author alex
 */
public class VideoGame {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
    
    private String name;
    private List<String> types;
    private List<String> platform;
    private Calendar launchdate;
    private String webPage;

    public VideoGame() {
        types = new ArrayList();
        platform = new ArrayList();
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
