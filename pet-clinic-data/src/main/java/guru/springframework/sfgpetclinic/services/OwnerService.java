package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{

    // defianimo solo metodi specifici


    Owner findByLastName(String lastName);

}
