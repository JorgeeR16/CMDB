package com.cmdb.proyectocmdb.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cmdb.proyectocmdb.Model.Infraestructura;
import com.cmdb.proyectocmdb.Service.InfraestructuraService;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/infraestructura")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })

public class InfraestructuraController {

    @Autowired
    private InfraestructuraService infraestructuraController;

    @GetMapping("/holamundo")
    public String saludar() {
        return "html";
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Infraestructura> getAll() {
        return infraestructuraController.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Infraestructura> getId(@PathVariable("id") Long id) {
        return infraestructuraController.getId(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Infraestructura save(@RequestBody Infraestructura cat) {
        return infraestructuraController.save(cat);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Infraestructura update(@RequestBody Infraestructura cat) {
        return infraestructuraController.update(cat);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") Long id) {
        return infraestructuraController.delete(id);
    }

}
