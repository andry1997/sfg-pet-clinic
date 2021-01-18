package guru.springframework.sfgpetclinic.services.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T, ID> {

    // Associa ad ogni elemento una chiave
    // tale chiave è l'id definito come chiave primaria all'interno delle entity
    protected Map<ID, T> map = new HashMap<>();

    /**
     * Aggiunge alla Mappa un nuovo elemento
     * @return l'elemento appena aggiunto
     */
    Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    T findById(ID id){
        return map.get(id);
    }

    T save(ID id ,T object){
        map.put(id, object);

        return object;
    }

    void deleteById(ID id){
        map.remove(id);
    }

    void delete(T object){

        //entrySet restituisce una set view del contenuto della mappa
        //removeIf elimina l'oggetto indicato se questo e uguale a quello passato

        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

}
