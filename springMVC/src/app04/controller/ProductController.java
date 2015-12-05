package app04.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app04.domain.Product;
import app04.form.ProductForm;
import app04.service.ProductService;

@Controller
public class ProductController
{
    @Autowired
    private ProductService productService;
    
    
    private static final Log log = LogFactory
            .getLog(ProductController.class);
    
    @RequestMapping(value = "/inputProduct.do")
    public String inputProduct()
    {
        log.info("inputProduct call");
        return "ProductForm";
    }
    
    @RequestMapping(value = "/saveProduct.do")
    public String saveProduct(ProductForm productForm ,RedirectAttributes redirectAttributes)
    {
        log.info("saveProduct call");
        Product product = new Product();
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        product.setPrice(Float.parseFloat(productForm.getPrice()));
        
        Product saveProduct = productService.add(product);
        
        redirectAttributes.addFlashAttribute("message", "the product was successfully added");
        
        return "redirect:/product_view/"+saveProduct.getId();
    }
    
    @RequestMapping(value = "/product_view/{id}")
    public String productView(@PathVariable long id,Model model)
    {
        Product product = productService.get(id);
        model.addAttribute("product", product);
        log.info("view call");
        return "ProductDetails";
    }
    
/*
    @RequestMapping(value = "/product_input")
    public String inputProduct()
    {
        logger.info("inputProduct called");
        return "ProductForm";
    }

    @RequestMapping(value = "/product_save")
    public String saveProduct(ProductForm productForm, Model model)
    {
        logger.info("saveProduct called");
        // no need to create and instantiate a ProductForm
        // create Product
        Product product = new Product();
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());
        try
        {
            product.setPrice(Float.parseFloat(productForm.getPrice()));
        } catch (NumberFormatException e)
        {
        }

        // add product

        model.addAttribute("product", product);
        return "ProductDetails";
    }
*/
}
