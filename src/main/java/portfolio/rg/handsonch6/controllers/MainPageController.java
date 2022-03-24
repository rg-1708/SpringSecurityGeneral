package portfolio.rg.handsonch6.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import portfolio.rg.handsonch6.services.ProductService;

@Controller
public class MainPageController {

    private final ProductService productService;
    @Autowired
    public MainPageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/main")
    public String main(Authentication a, Model model) {
        model.addAttribute("username", a.getName());
        model.addAttribute("products", productService.findAll());
        return "main";
    }
}