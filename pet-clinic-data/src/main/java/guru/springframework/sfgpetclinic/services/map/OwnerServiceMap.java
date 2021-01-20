package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        if(object == null) {
            return null;
        }else{
            /**
             * Vogliamo far si che la property ID associato agli owner, pet e petType, definiti tramite una HashMap
             * sia sincronizzato in qualche modo
             */

            if (object.getPets() != null){
                object.getPets().forEach(pet -> {
                    // Se abbiamo assegnato al nostro owner un animale il cui
                    // tipo non è ancora stato aggiunto alla lista questo viene aggiunto
                    if(pet.getPetType() != null){
                        // L'id viene esteso dalla classe BaseEntity
                        if (pet.getPetType().getId() == null){
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    }else{
                        // Altrimenti avremmo owner senza un animale che non sarebbe proprio corretto logicamente parlando
                        throw  new RuntimeException("Pet Type is required");
                    }
                    // abbiamo un animale di quel tipo nel nostro elenco ma non abbiamo uno specifico ID per quello specifico animale
                    if(pet.getId() == null){
                        Pet savePet = petService.save(pet);
                        pet.setId(savePet.getId());
                    }
                });
            }
            return super.save(object);
        }
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
