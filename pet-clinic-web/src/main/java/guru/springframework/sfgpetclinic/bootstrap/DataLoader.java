package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Caricamento dei dati
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Michele");
        owner1.setLastName("Bezze");

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Thomas");
        owner2.setLastName("Rampon");

        System.out.println("Loaded owners.....");

        ownerService.save(owner1);
        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Davide");
        vet1.setLastName("Molon");

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Rachele");
        vet2.setLastName("Ceron");

        System.out.println("Loaded vets.....");

        vetService.save(vet1);
        vetService.save(vet2);
    }
}
