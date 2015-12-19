package app07a.controller;

import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import app07a.domain.Product;
import app07a.validator.ProductValidator;
/**
 * Spring中的框架可以替代js的判断功能,使业务逻辑尽量集中在java中
 * 如果使用js去判断虽然做起来容易但是因该有缺陷吧
 * @author rocky
 *
 */
@Controller
public class ProductController
{

    private static final Log logger = LogFactory
            .getLog(ProductController.class);

    @RequestMapping(value = "/product_input07")
    public String inputProduct(Model model) {
        model.addAttribute("product", new Product());
        return "ProductFormForApp07a";
    }

    @RequestMapping(value = "/product_save07")
    public String saveProduct(@ModelAttribute Product product,
            BindingResult bindingResult, Model model) {
        logger.info("product_saveForApp07a");
        System.out.println("prod save");
        ProductValidator productValidator = new ProductValidator();
        productValidator.validate(product, bindingResult);

        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            logger.info("Code:" + fieldError.getCode() + ", field:"
                    + fieldError.getField());

            return "ProductFormForApp07a";
        }

        // save product here

        model.addAttribute("product", product);
        return "ProductDetailsForApp07a";
    }
}
