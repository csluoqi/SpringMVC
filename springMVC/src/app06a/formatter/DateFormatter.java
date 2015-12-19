package app06a.formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class DateFormatter implements Formatter<Date>
{
    private String datePattern;
    private SimpleDateFormat dateFormat;
    
    public DateFormatter(String datePattern) 
    {
        this.datePattern = datePattern;
        dateFormat = new SimpleDateFormat(datePattern);
        dateFormat.setLenient(false);
        
    }

    @Override
    public String print(Date date, Locale arg1)
    {
        
        return dateFormat.format(date);
    }

    @Override
    public Date parse(String s, Locale local) throws ParseException
    {
        
        try
        {
            return dateFormat.parse(s);
        } catch (Exception e)
        {
            throw new IllegalArgumentException("invalid date format . please use this pattern\""+datePattern+"\"");
        }
    }

}
