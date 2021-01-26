package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

// Essendo che /owners verrò utilizzato all'interno degli url associti a questo controller posso porlo come generale
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

  // @RequestMapping({"/owners", "/owners/index", "/owners/index.html"})
  // public String ownerList(Model model){

  //     model.addAttribute("owners", ownerService.findAll());

  //     return "owners/index";
  // }

    @RequestMapping("/owners/find")
    public String findOwner(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping("/owners")
    public String processFindForm(Owner owner, BindingResult result, Model model){
        // allow parameterless GET request for /owners to return all records
        if (owner.getLastName() == null) {
            owner.setLastName(""); // empty string signifies broadest possible search
        }

        // find owners by last name (cerca anche elementi simili)
        List<Owner> results = ownerService.findAllByLastNameLike("%"+ owner.getLastName() + "%");

        if (results.isEmpty()) {
            // no owners found
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (results.size() == 1) {
            // 1 owner found
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            // multiple owners found
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }

    @RequestMapping("/owners/{ownerid}")
    public ModelAndView showOwner(@PathVariable ("ownerid") Long id ){

        ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
        modelAndView.addObject(ownerService.findById(id));
        return  modelAndView;
    }



    // Con questo metodo andiamo a restituire all'utente la view che si occupa della creazione di un nuovo utente
    // Oltre a questo vado a creare una model associata ad un oggetto di tipo owner che verrà utilizzata poi dal web form
    @GetMapping("/owners/new")
    public String initCreationForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

  @PostMapping("/owners/new")
  public String processCreationForm(@Valid Owner owner, BindingResult result) {
      if (result.hasErrors()) {
          return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
      } else {
          Owner savedOwner =  ownerService.save(owner);
          return "redirect:/owners/" + savedOwner.getId();
      }
  }



   @GetMapping("/{ownerId}/edit")
   public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
       model.addAttribute(ownerService.findById(ownerId));
       return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
   }

   @PostMapping("/{ownerId}/edit")
   public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable Long ownerId) {
       if (result.hasErrors()) {
           return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
       } else {
           owner.setId(ownerId);
           Owner savedOwner = ownerService.save(owner);
           return "redirect:/owners/" + savedOwner.getId();
       }
   }
}
