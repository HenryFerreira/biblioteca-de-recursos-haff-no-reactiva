package co.com.sofka.biblioteca.services.impl;

import co.com.sofka.biblioteca.models.Recurso;
import co.com.sofka.biblioteca.repositories.RecursoRepository;
import co.com.sofka.biblioteca.services.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecursoServiceImpl implements RecursoService {
    @Autowired
    private RecursoRepository repository;

    //----------------------------------------------//
    //CRUD
    @Override
    public Recurso save(Recurso recurso) {
        return this.repository.save(recurso);
    }

    @Override
    public void delete(String id) {
        this.repository.deleteById(id);
    }

    @Override
    public Recurso update(String id, Recurso recurso) {
        recurso.setId(id);
        return this.repository.save(recurso);
    }

    @Override
    public List<Recurso> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Recurso> findById(String id) {
        return this.repository.findById(id);
    }
    //----------------------------------------------//

    @Override
    public String disponibilidadById(String id){
        var recurso = this.repository.findById(id);

        if(recurso.get().getEstado()){
            return "Recurso Disponible";
        }

        return "Recurso Prestado " + recurso.get().getFechaPrestamo();
    }

    /*public String perstarRecursoById(String id){
        var recurso = this.repository.findById(id);
        var mensaje = recurso.flatMap(p -> {
            if(!p.getEstado()){
                return Mono.just("Recurso Prestado " + p.getFechaPrestamo());
            }
            p.setEstado(false);
            p.setFechaPrestamo(LocalDate.now());
            return repository.save(p).then(Mono.just("Recurso Disponible." +
                    "\nEl prestamo se realizo con exito. Fecha de Prestamo: " + p.getFechaPrestamo()));
        });
        return mensaje;
    }

    public List<Recurso> recomendarRecursosByTipo(String tipo){
        return this.repository.findAll().filter(p -> p.getTipoRecurso().equals(tipo));
    }
    public List<Recurso> recomendarRecursosByCategoria(String categoria){
        return this.repository.findAll().filter(p -> p.getCategoriaRecurso().equals(categoria));
    }
    public List<Recurso> recomendarRecursosByCategoriaAndTipo(String categoria, String tipo){
        return this.repository.findAll().filter(p ->
                p.getCategoriaRecurso().equals(categoria) && p.getTipoRecurso().equals(tipo));
    }

    public String devolverRecursoById(String id){
        var recurso = this.repository.findById(id);
        var mensaje = recurso.flatMap(p -> {
            if(p.getEstado()){
                return Mono.just("El recurso NO esta PRESTADO!!");
            }
            p.setEstado(true);
            p.setFechaPrestamo(LocalDate.now());
            return repository.save(p).then(Mono.just("Recurso DEVUELTO." +
                    "\n La devolucion se realizo con exito. Fecha de Prestamo: " + p.getFechaPrestamo()));
        });
        return mensaje;
    }*/
}
