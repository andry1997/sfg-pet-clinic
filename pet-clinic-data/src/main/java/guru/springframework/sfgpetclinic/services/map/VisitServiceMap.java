package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.VisitService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VisitServiceMap extends AbstractMapService<Visit, Long> implements VisitService {


    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Visit object) {
        super.delete(object);
    }

    @Override
    public Visit save(Visit object) {
        // verificare ce quell'animale sia presente oppure se questo ha un animale
        if(object.getPet() == null || object.getPet().getOwner() == null || object.getId() == null){
            throw new RuntimeException("Invalid visit");
        }else{
            return super.save(object);
        }
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }
}
