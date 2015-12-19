package app06a.formatter;

import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;

public class MyFormatterRigistrar implements FormatterRegistrar
{
    private String datePattern;
    
    public MyFormatterRigistrar(String datePattern)
    {
        this.datePattern = datePattern;
    }

    @Override
    public void registerFormatters(FormatterRegistry registry)
    {
        
        registry.addFormatter(new DateFormatter(datePattern));
        
        // TODO Auto-generated method stub
        
    }

}
