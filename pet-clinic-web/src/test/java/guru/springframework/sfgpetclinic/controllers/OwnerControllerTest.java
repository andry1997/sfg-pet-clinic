package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @InjectMocks
    OwnerController controller;

    @Mock
    OwnerService ownerService;

    Set<Owner> ownerSet;

    // Oggetto utilizzato per i controller per effettuare i test delle interazioni con questi
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        ownerSet = new HashSet<>();
        ownerSet.add(Owner.builder().id(1l).build());
        ownerSet.add(Owner.builder().id(2l).build());

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void ownerList() throws Exception {
        when(ownerService.findAll()).thenReturn(ownerSet);

        // Questo si che quando viene richiesto quel URL lo stato della richiesta
        // è OK, che la view che viene restituita ha quel nome e che la lista di
        // owner contiene in questo caso 2 elementi.
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void ownerListByIndex() throws Exception {
        when(ownerService.findAll()).thenReturn(ownerSet);

        // Questo si che quando viene richiesto quel URL lo stato della richiesta
        // è OK, che la view che viene restituita ha quel nome e che la lista di
        // owner contiene in questo caso 2 elementi.
        mockMvc.perform(get("/owners/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void findOwner() throws Exception{

        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("error/index"));

        // non dovrebbe esserci alcuna richiesta fatta verso un oggeto di qusto tipo
        verifyZeroInteractions(ownerService);
    }

    @Test
    void displayOwner() throws Exception{
        when(ownerService.findById(any())).thenReturn(Owner.builder().id(1l).build());

        mockMvc.perform(get("/owners/123"))
                .andExpect(status().isOk())
                .andExpect((view().name("owners/ownerDetails")))
                .andExpect(model().attribute("owner", hasProperty("id", is(1l))));
    }
}