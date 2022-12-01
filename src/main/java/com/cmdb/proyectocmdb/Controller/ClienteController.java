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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.http.HttpStatus;

@RestController
@Api(value = "clientecontroller", description = "API para el manejo de los clientes")
@RequestMapping("/jers/api/cliente")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class ClienteController {

    @Autowired
    private ClienteService clienteController;

    @ApiOperation(value = "Se valida si el servicio esta arriba")
    @GetMapping("/holamundo")
    public String saludar() {
        return "html";
    }

    @ApiOperation(value = "Se consultan todos los clientes almacenados en la DB", response = Cliente[].class)
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> getAll() {
        return clienteController.getAll();
    }

    @ApiOperation(value = "Se consulta el cliente con el ID que le asigno el servicio", response = Cliente[].class)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Cliente> getId(@PathVariable("id") Long id) {
        return clienteController.getId(id);
    }

    @ApiOperation(value = "Se consulta por el cliente para validar que CI estan asociados a este", response = Cliente[].class)
    @GetMapping("/campana/{cliente}")
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> getByCliente(@PathVariable("cliente") String cli) {
        return clienteController.getByCliente(cli);
    }

    @ApiOperation(value = "Se consulta por el cliente para validar que CI estan asociados a este", response = Cliente[].class)
    @GetMapping("/cliente/{cliente}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> clienteInfra(@PathVariable("cliente") String cli) {
        return clienteController.clienteInfra(cli);
    }

    @GetMapping("/ip/{ip}")
    @ApiOperation(value = "Se consulta por la IP del CI y nos indica que clientes estan asociados a este equipo", response = Cliente[].class)
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> getIp(@PathVariable("ip") String ip) {
        return clienteController.getIp(ip);
    }

    @GetMapping("/cliente/ip/{ip}")
    @ApiOperation(value = "Se consulta por la IP del CI y nos indica el listado de clientes que estan asociados a este equipo", response = Cliente[].class)
    @ResponseStatus(HttpStatus.OK)
    public List<String> getIpListCliente(@PathVariable("ip") String ip) {
        return clienteController.getIpListCliente(ip);
    }

    @ApiOperation(value = "Se añade una persona, se puede con o sin indicar su infraestructura", response = Cliente.class)
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody Cliente pro) {
        return clienteController.save(pro);
    }

    @ApiOperation(value = "Se actualiza una persona, se debe aclarar que si solo se desea cambiar un equipo o algo en el nombre se debe enviar toda su infraestructura", response = Cliente.class)
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente update(@RequestBody Cliente pro) {
        return clienteController.update(pro);
    }

    @ApiOperation(value = "Se actualiza una persona, añadiendo un nuevo equipo sin eliminar la infraestructura anterior", response = Cliente.class)
    @PutMapping("/update/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente add(@RequestBody Cliente pro) {
        return clienteController.addInfra(pro);
    }

    @ApiOperation(value = "Se elimina persona", response = Cliente.class)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        clienteController.delete(id);
    }

    @ApiOperation(value = "Se elimina equipo de Campaña", response = Cliente.class)
    @DeleteMapping("/delete/ci")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCi(@RequestBody Cliente pro) {
        clienteController.deleteCi(pro);
    }

}
