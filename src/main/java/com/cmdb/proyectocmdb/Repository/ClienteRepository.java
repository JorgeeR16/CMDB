package com.cmdb.proyectocmdb.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cmdb.proyectocmdb.Interface.ClienteInterface;
import com.cmdb.proyectocmdb.Model.Cliente;

@Repository
public class ClienteRepository {

    @Autowired
    private ClienteInterface clienteCRUD;

    public List<Cliente> getAll() {
        return (List<Cliente>) clienteCRUD.findAll();
    }

    public Optional<Cliente> getId(Long id) {
        return clienteCRUD.findById(id);
    }

    public Cliente save(Cliente prod) {
        return clienteCRUD.save(prod);
    }

    public void delete(Cliente id) {
        clienteCRUD.delete(id);
    }

    public List<Cliente> getByCliente(String client) {
        return clienteCRUD.findByClienteContaining(client);
    }

    public List<Cliente> findIp(String ip) {
        return clienteCRUD.findIp(ip);
    }

    public boolean existCliente(String client) {
        return clienteCRUD.existsByCliente(client);
    }
}
