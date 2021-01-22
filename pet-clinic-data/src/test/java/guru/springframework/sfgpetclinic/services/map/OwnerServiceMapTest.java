package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;
    final Long ownerId = 1l;
    final String lastName = "Bezze";

    @BeforeEach
    void setUp() {
        // Immitando ciò che spring fa per noi
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
        // Inseriamo un oggetto
        ownerServiceMap.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
         Set<Owner> owners = ownerServiceMap.findAll();

         assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        Owner owner1 = ownerServiceMap.findById(ownerId);
        assertEquals(ownerId, owner1.getId());
    }

    @Test
    void saveExistinId() {
        Long id = 2l;
        Owner owner = Owner.builder().id(id).build();
        Owner savedOwner = ownerServiceMap.save(owner);

        assertEquals(id, savedOwner.getId());
    }

    @Test
    public void saveNoId(){

        // inserire un owner che non ha alcun id assegnato da mnoi
        Owner savedOwner = ownerServiceMap.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());


    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerId));
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerId);
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerServiceMap.findByLastName(lastName);

        assertNotNull(owner);
        assertEquals(ownerId,owner.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner owner = ownerServiceMap.findByLastName("foo");

        // mi aspetto che sia null
        assertNull(owner);
    }
}