package pl.jarrobots.totalcommander;3

import java.nio.file.attribute.FileTime
import java.util.Date;

public class RowItem {
    private final boolean dir;
    private final String name;
    private String url;
    private final Date date;

    public RowItem(String url, String name, boolean type, FileTime date){
        this.name = name;
        this.url = "/"+url;
        this.dir = type;
        if(date != null) {
            this.date = new Date(date.toMillis());
        }
        else{
            this.date = new Date(0);
        }
    }
    public boolean getDir(){
        return dir;
    }
    public String getURL(){
        return url;
    }
    public void setURL(String url){
        this.url = url;
    }
    public String getName(){
        return name;
    }
    public Date getDate(){
        return date;
    }
}
