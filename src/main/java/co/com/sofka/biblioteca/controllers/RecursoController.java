package co.com.sofka.biblioteca.controllers;

import co.com.sofka.biblioteca.models.Recurso;
import co.com.sofka.biblioteca.services.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class RecursoController {

    @Autowired
    private RecursoService service;


    //----------------------------------------------//
    //CRUD
    @PostMapping("/recurso")
    @ResponseStatus(HttpStatus.CREATED)
    private Recurso save(@RequestBody Recurso recurso) {
        return this.service.save(recurso);
    }

    @DeleteMapping("/recurso/{id}")
    private void delete(@PathVariable("id") String id) {
        this.service.delete(id);
    }

    @PutMapping("/recurso/{id}")
    private Recurso update(@PathVariable("id") String id, @RequestBody Recurso recurso) {
        return this.service.update(id, recurso);

    }

    @GetMapping(value = "/recurso")
    private List<Recurso> findAll() {
        return this.service.findAll();
    }

    @GetMapping("/recurso/{id}/findById")
    private Optional<Recurso> findByid(@PathVariable("id") String id) {
        return this.service.findById(id);
    }
    //----------------------------------------------//

    @GetMapping("/recurso/{id}/disponibilidad")
    private String disponibilidadById(@PathVariable("id") String id) {
        return this.service.disponibilidadById(id);
    }

    @PutMapping("/recurso/{id}/prestamo")
    private String perstarRecursoById(@PathVariable("id") String id) {
        return this.service.perstarRecursoById(id);
    }

    @GetMapping("/recurso/{tipoRecurso}/recomendacion/tipo")
    private List<Recurso> recomendarRecursosByTipo(@PathVariable("tipoRecurso") String tipo) {
        return this.service.recomendarRecursosByTipo(tipo);
    }

    @GetMapping("/recurso/{categoriaRecurso}/recomendacion/categoria")
    private List<Recurso> recomendarRecursosByCategoria(@PathVariable("categoriaRecurso") String categoria) {
        return this.service.recomendarRecursosByCategoria(categoria);
    }

    @GetMapping("/recurso/{categoriaRecurso}/{tipoRecurso}/recomendacion/categoriaAndTipo")
    private List<Recurso> recomendarRecursosByCategoriaAndTipo
            (@PathVariable("categoriaRecurso") String categoria, @PathVariable("tipoRecurso") String tipo) {
        return this.service.recomendarRecursosByCategoriaAndTipo(categoria, tipo);
    }

    @PutMapping("/recurso/{id}/devolucion")
    private String devolverRecursoById(@PathVariable("id") String id) {
        return this.service.devolverRecursoById(id);
    }

}
