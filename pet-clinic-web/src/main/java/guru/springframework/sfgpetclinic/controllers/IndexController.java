package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    // lista di possibili URL che vengono associati a questo metodo di questo controller
    @RequestMapping({"", "/", "index.html"})
    public String index(){

        return "index";
    }

}
