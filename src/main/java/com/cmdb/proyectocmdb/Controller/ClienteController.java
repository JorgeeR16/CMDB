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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cmdb.proyectocmdb.Model.Cliente;
import com.cmdb.proyectocmdb.Service.ClienteService;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class ClienteController {

    @Autowired
    private ClienteService clienteController;

    @GetMapping("/holamundo")
    public String saludar() {
        return "html";
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> getAll() {
        return clienteController.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Cliente> getId(@PathVariable("id") Long id) {
        return clienteController.getId(id);
    }

    @GetMapping("/campana/{cliente}")
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> getByCliente(@PathVariable("cliente") String cli) {
        return clienteController.getByCliente(cli);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody Cliente pro) {
        return clienteController.save(pro);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente update(@RequestBody Cliente pro) {
        return clienteController.update(pro);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        clienteController.delete(id);
    }

}
