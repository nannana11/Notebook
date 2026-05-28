package Notebook;

import java.io.Serializable;

public class Note implements Serializable {

    public String time;
    public String title;
    public String content;

    public Note(String time,String title,String content)
    {
        this.time=time;
        this.title=title;
        this.content=content;
    }

    public String toString()
    {
        return title+" "+"["+time+"]";
    }
}
