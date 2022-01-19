package br.com.pagination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.pagination.model.Product;
import br.com.pagination.service.ProductService;

@Controller
@RequestMapping("/produtos")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public Page<Product> searchProduct(
        @RequestParam(value = "name", required = false) String name, 
        @RequestParam(value = "price", required = false) Double price,
        @RequestParam(value = "page", required = false, defaultValue = "0") int page,
        @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

            return productService.getProductsPaginated(name, price, size, size);
        }
}
