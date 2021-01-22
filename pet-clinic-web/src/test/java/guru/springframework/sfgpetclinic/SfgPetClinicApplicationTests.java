package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest

//JUnit5 anotation
@ExtendWith(SpringExtension.class)
class SfgPetClinicApplicationTests {

    @Test
    // verifica che lo Spring Context venga caricato
    void contextLoads() {
    }

}
