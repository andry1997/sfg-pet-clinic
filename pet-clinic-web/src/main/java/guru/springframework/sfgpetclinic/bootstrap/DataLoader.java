package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

// Caricamento dei dati
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;
    private final PetService petService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService, PetService petService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
        this.petService = petService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Utilizza petTypeService ma andava bene qualsiasi altra cosa definita
        int count = petTypeService.findAll().size();

        // Evitare di rieseguire ogni volta il metodo quando andiamo a effettuare il re-build
        if(count == 0){
            System.out.println("Data loader");
            loadData();
        }

    }

    /**
     * Metodo privato che va a creare e caricare i dati
     */
    private void loadData() {

        Owner owner1 = new Owner();
        owner1.setFirstName("Michele");
        owner1.setLastName("Bezze");
        owner1.setAddress("Via Repoise");
        owner1.setCity("Fossona");
        owner1.setTelephone("123456789");

        Owner owner2 = new Owner();
        owner2.setFirstName("Thomas");
        owner2.setLastName("Rampon");
        owner2.setAddress("Via Repoise");
        owner2.setCity("Fossona");
        owner2.setTelephone("123456789");

        try{
            ownerService.save(owner1);
            ownerService.save(owner2);
        }catch (Exception e){
            System.out.println(e);
        }

        System.out.println("Loaded owners.....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Davide");
        vet1.setLastName("Molon");

        Vet vet2 = new Vet();
        vet2.setFirstName("Rachele");
        vet2.setLastName("Ceron");

        System.out.println("Loaded vets.....");

        try{
            vetService.save(vet1);
            vetService.save(vet2);
        }catch (Exception e){
            System.out.println(e);
        }

        // Creazione di due oggetti PetType e salvataggio di questi

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType saveCatPetType = petTypeService.save(cat);


                                              // Definire una "relazione" simil-persistente tra i proprietari e i loro animali
                                              // Associazione degli animali creati ai rispettivi owner

        Pet michelePet = new Pet();
        michelePet.setPetType(saveDogPetType);
        michelePet.setOwner(owner1);
        michelePet.setName("Schizzo");
        michelePet.setBirthday(LocalDate.now());
        Pet savedPet1 = petService.save(michelePet);
        owner1.getPets().add(savedPet1);


        Pet thomasPet = new Pet();
        thomasPet.setPetType(saveCatPetType);
        thomasPet.setName("Micia");
        thomasPet.setBirthday(LocalDate.now());
        thomasPet.setOwner(owner2);
        Pet savedPet2 = petService.save(thomasPet);
        owner2.getPets().add(savedPet2);

                                            // Definizione delle specilties e associazione di queste ai vari vet

        Specialty rediology = new Specialty();
        rediology.setDescription("Radiolozia");
        Specialty savedRadiology = specialtyService.save(rediology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Chirurgia");
        Specialty savedSurgery = specialtyService.save(surgery);

        vet1.getSpecialties().add(savedRadiology);
        vet1.getSpecialties().add(savedSurgery);
        vet2.getSpecialties().add(savedRadiology);

                                            // Definizione delle Visite

        Visit visit1 = new Visit();
        visit1.setPet(michelePet);
        visit1.setDescription("Ha visitato il suo cane per vedere come stava dopo l'operazione");
        visit1.setDate(LocalDate.now());

        visitService.save(visit1);
    }
}
