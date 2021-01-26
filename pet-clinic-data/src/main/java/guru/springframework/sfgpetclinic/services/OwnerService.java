package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long>{

    // defianimo solo metodi specifici
    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);

}
