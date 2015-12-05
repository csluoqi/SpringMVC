package app04.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import app04.domain.Product;
import app04.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService
{
    private Map<Long,Product> products = new HashMap<Long, Product>(1);
    private AtomicLong generator = new AtomicLong();
    private static Log log = LogFactory.getLog(ProductServiceImpl.class);
    

    public ProductServiceImpl()
    {
       log.info("contruct"); 
       Product product = new Product();
       long newId = generator.incrementAndGet();
       product.setId(newId);
       product.setName("JX1");
       product.setDescription("power 123!");
       product.setPrice(123.0F);
       add(product);
    }

    @Override
    public Product add(Product product)
    {
        long newId = generator.incrementAndGet();
        product.setId(newId);
        products.put(newId, product);
        
        return product;
    }

    @Override
    public Product get(long id)
    {
        Product product =  products.get(id);
        return product;
    }
    
}
