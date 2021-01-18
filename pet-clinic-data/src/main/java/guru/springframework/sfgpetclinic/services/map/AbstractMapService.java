package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.BaseEntity;

import java.util.*;

// All'interno di tale classe implementiamo i metodi CRUD più comuni
// ma definiamo anche una variabile di classe di tipo mappa che ci permetterà di
// memorizzare al suo interno oggetti delle classi definite all'interno del package model del modulo data


// Vogliamo far si che la chiave ID usata venga generata automaticamente cosa che Hashmap non riesce a fare da solo

// Per gestire al meglio i nostri elementi anche se la classe viene definita  in modo generico
// posiamo imporre che tali elementi estendano specifici elementi cosi da imporre che abbiamo acune caratteristiche e che possano usare alcuni metodi
public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

    // Associa ad ogni elemento una chiave
    // tale chiave è l'id definito come chiave primaria all'interno delle entity
    protected Map<Long, T> map = new HashMap<>();

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

    T save(T object){
        // Verifica se l'oggetto ha un id associato
        // in caso contrario gliene fornisce uno
        if(object != null){
            if (object.getId() == null){
                object.setId(getNextId());
            }
            map.put(object.getId(), object);
        }else{
            throw new RuntimeException("Object cannot be null");
        }
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

    private Long getNextId(){
        /**
         * Vogliamo che l'ID venga gestito automaticamente
         * tale metodo restituisce un ID che possiamo utilizzare
         */

        // il metodo keyset restiruce l'insieme di chiavi presenti nella tabella
        Long nextId = null;

        try {
            // in caso non vi sia alcuna chiave all'interno della mappa viene lanciata un'eccezione
            nextId = Collections.max(map.keySet()) + 1L;
        }catch (NoSuchElementException e){
            // in caso non vi fosse ancora alcun elemento
            nextId = 1L;
        }

        return nextId;
    }

}
