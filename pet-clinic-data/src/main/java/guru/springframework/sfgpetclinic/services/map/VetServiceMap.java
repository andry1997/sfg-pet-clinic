package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Specialty;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.SpecialtiesService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialtiesService specialtiesService;

    public VetServiceMap(SpecialtiesService specialtiesService) {
        this.specialtiesService = specialtiesService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public Vet save(Vet object) {
        if(object == null){
            return null;
        }else{
            // Anche qui stiamo andando a definire una "relazione" persistente tra vet e specialty
            if(object.getSpecialties().size() > 0){
                object.getSpecialties().forEach(specialty -> {
                    if(specialty.getId() == null){
                        Specialty savedSpecialty = specialtiesService.save(specialty);
                        specialty.setId(savedSpecialty.getId());
                    }
                });
            }
        }
        return super.save(object);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}
