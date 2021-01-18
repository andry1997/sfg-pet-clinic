package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// Essendo che /owners verrò utilizzato all'interno degli url associti a questo controller posso porlo come generale
@RequestMapping("/owners")
@Controller
public class OwnerController {


    @RequestMapping({"", "/index", "/index.html"})
    public String ownerList(){

        return "owners/index";
    }
}
