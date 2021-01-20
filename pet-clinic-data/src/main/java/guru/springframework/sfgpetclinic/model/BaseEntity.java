package guru.springframework.sfgpetclinic.model;

import java.io.Serializable;

// Va a definire una sola classe che verrà definita come Entity che poi verrà estesa dale altre
public class BaseEntity implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
