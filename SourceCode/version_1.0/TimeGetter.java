package Notebook;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeGetter {

    public String getTime()
    {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yy.MM.dd HH:mm");
        return dateFormat.format(new Date());
    }
}
