package co.com.sofka.biblioteca.services.impl;

import co.com.sofka.biblioteca.models.Recurso;
import co.com.sofka.biblioteca.repositories.RecursoRepository;
import co.com.sofka.biblioteca.services.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public String disponibilidadById(String id) {
        var recurso = this.repository.findById(id);

        if (recurso.get().getEstado()) {
            return "Recurso Disponible";
        }
        return "Recurso Prestado " + recurso.get().getFechaPrestamo();
    }

    public String perstarRecursoById(String id) {
        var recurso = this.repository.findById(id);

        if (!recurso.get().getEstado()) {
            return "Recurso Prestado " + recurso.get().getFechaPrestamo();
        }
        recurso.get().setEstado(false);
        recurso.get().setFechaPrestamo(LocalDate.now());
        repository.save(recurso.get());
        return "Recurso Disponible." +
                "\nEl prestamo se realizo con exito. Fecha de Prestamo: " + recurso.get().getFechaPrestamo();
    }

    public List<Recurso> recomendarRecursosByTipo(String tipo) {
        var recursos = this.repository.findAll();
        var filtrasdos = recursos.stream().filter(p ->
                p.getTipoRecurso().equals(tipo)).collect(Collectors.toList());
        return filtrasdos;

    }

    public List<Recurso> recomendarRecursosByCategoria(String categoria) {
        var recursos = this.repository.findAll();
        var filtrasdos = recursos.stream().filter(p ->
                p.getCategoriaRecurso().equals(categoria)).collect(Collectors.toList());
        return filtrasdos;
    }

    public List<Recurso> recomendarRecursosByCategoriaAndTipo(String categoria, String tipo) {

        var recursos = this.repository.findAll();
        var filtrasdos = recursos.stream().filter(p ->
                        p.getCategoriaRecurso().equals(categoria) && p.getTipoRecurso().equals(tipo))
                .collect(Collectors.toList());
        return filtrasdos;
    }

    public String devolverRecursoById(String id) {
        var recurso = this.repository.findById(id);
        if (recurso.get().getEstado()) {
            return "El recurso NO esta PRESTADO!!";
        }
        recurso.get().setEstado(true);
        recurso.get().setFechaPrestamo(LocalDate.now());
        repository.save(recurso.get());
        return "Recurso DEVUELTO." +
                "\n La devolucion se realizo con exito. Fecha de Prestamo: " + recurso.get().getFechaPrestamo();
    }
}
