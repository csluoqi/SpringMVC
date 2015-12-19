package app06a.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;






import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//不要导错包了
import org.springframework.core.convert.converter.Converter;
public class StringToDateConverter implements Converter<String, Date>
{
    private static final Log log = LogFactory.getLog(StringToDateConverter.class);
    
    private String datePattern;
    
    public StringToDateConverter(String datePattern)
    {
        super();
        this.datePattern = datePattern;
        log.info("instanting ... converter with pattern :* " + datePattern);
    }

    @Override
    public Date convert(String s)
    {
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
            /**
             * Specify whether or not date/time parsing is to be lenient. 
             * With lenient parsing, the parser may use heuristics to interpret
             *  inputs that do not precisely match this object's format. 
             *  With strict parsing, inputs must match this object's format.
             */
            dateFormat.setLenient(false);
            return dateFormat.parse(s);
        } catch (ParseException e)
        {
            //the error message will be displayed when using 
            //<form:errors>
            log.info(e.getMessage());
            throw new IllegalArgumentException("invalid date form. please user this pattern\""+datePattern+"\"");
        }
    }

}
