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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@RestController
@Api(value = "infraestructuracontroller", description = "API para el manejo de los CI")
@RequestMapping("/api/infraestructura")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })

public class InfraestructuraController {

    @Autowired
    private InfraestructuraService infraestructuraController;

    @ApiOperation(value = "Se valida si el servicio esta arriba")
    @GetMapping("/holamundo")
    public String saludar() {
        return "html";
    }

    @ApiOperation(value = "Se consultan todos los CI almacenados en la DB", response = Infraestructura[].class)
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Infraestructura> getAll() {
        return infraestructuraController.getAll();
    }

    @ApiOperation(value = "Se consulta el equipo con el ID que le asigno el servicio", response = Infraestructura[].class)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Infraestructura> getId(@PathVariable("id") Long id) {
        return infraestructuraController.getId(id);
    }

    @ApiOperation(value = "Se consulta el equipo con la IP del servidor", response = Infraestructura[].class)
    @GetMapping("/ip/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Infraestructura> getById(@PathVariable("id") String id) {
        return infraestructuraController.getByIp(id);
    }

    @ApiOperation(value = "Se añade una CI, el servicio automaticamente le genera el id para almacenarlo en la DB", response = Infraestructura.class)
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Infraestructura save(@RequestBody Infraestructura cat) {
        return infraestructuraController.save(cat);
    }

    @ApiOperation(value = "Se actualiza un equipo, se puede con el id del CI o su IP", response = Infraestructura.class)
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Infraestructura update(@RequestBody Infraestructura cat) {
        return infraestructuraController.update(cat);
    }

    @ApiOperation(value = "Se elimina equipo, sin embargo, si se desea eliminar se debe validar si el equipo esta en muchos clientes ya que no se podra eliminar hasta que la realcion con los cliente sea eliminada ", response = Infraestructura.class)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") Long id) {
        return infraestructuraController.delete(id);
    }

}
