package br.com.pagination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.pagination.model.Product;
import br.com.pagination.service.ProductService;

@Controller(value = "/")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping
    public String searchProduct(
        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
        @RequestParam(value = "size", required = false, defaultValue = "10") int size, 
        @ModelAttribute("product") Product product, Model model) {

            model.addAttribute("produtos", productService.getProducts(product, page, size));
            model.addAttribute("currentPage", page);
            return "products";
        }

    
}
