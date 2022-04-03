package co.com.sofka.biblioteca.repositories;

import co.com.sofka.biblioteca.models.Recurso;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecursoRepository extends MongoRepository<Recurso, String> {

}
