package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetController {

   //private final CrudService crudService;

   //public VetController(CrudService crudService) {
   //    this.crudService = crudService;
   //}

    @RequestMapping({"/vets","/vets/index","/vets/index.html"})
    public String listVets(Model model){

        return "vets/index";
    }
}
