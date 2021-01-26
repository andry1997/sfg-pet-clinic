package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// Essendo che /owners verrò utilizzato all'interno degli url associti a questo controller posso porlo come generale
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }


    @RequestMapping({"/owners", "/owners/index", "/owners/index.html"})
    public String ownerList(Model model){

        model.addAttribute("owners", ownerService.findAll());

        return "owners/index";
    }

    @RequestMapping("/owners/find")
    public String findOwner(){
        return "error/index";
    }

    @RequestMapping("/owners/{ownerid}")
    public ModelAndView showOwner(@PathVariable ("ownerid") Long id ){

        ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
        modelAndView.addObject(ownerService.findById(id));
        return  modelAndView;
    }
}
