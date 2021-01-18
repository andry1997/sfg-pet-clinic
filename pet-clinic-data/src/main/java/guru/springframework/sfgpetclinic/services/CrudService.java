package guru.springframework.sfgpetclinic.services;

import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Implementiamo un'interfaccia comune con i metodi crud comuni per tutti i servizi
 * @param <T> fa riferimento al tipo di oggetto
 * @param <ID> fa riferimento il tipo di dato associato all'ID
 */

@Service
public interface CrudService<T, ID> {

    Set<T> findAll();

    T findById(ID id);

    T save(T object);

    void delete(T object);

    void deleteById(ID id);


}
