package portfolio.rg.springsecuritybook.controllers;

import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/api/v1")
public class HelloController {
    @GetMapping("/hello")
    public String hello(Authentication a) {
        return "Hello, " + a.getName() + "!";
    }

    @GetMapping("/hola")
    public String hola() throws Exception {

        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };

        //Now instead of wrapping the task itself,
        //We use DelegatingSecurityContextExecutorService
        //To manage the task, and provide security context to it

        ExecutorService e = Executors.newCachedThreadPool();
        e = new DelegatingSecurityContextExecutorService(e);
        try {
            return "Hola, " + e.submit(task).get() + "!";
        } finally {
            e.shutdown();
        }
    }
}


