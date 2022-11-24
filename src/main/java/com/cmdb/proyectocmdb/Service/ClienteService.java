package com.cmdb.proyectocmdb.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmdb.proyectocmdb.Model.Cliente;
import com.cmdb.proyectocmdb.Repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteService;

    public List<Cliente> getAll() {
        return clienteService.getAll();
    }

    public Optional<Cliente> getId(Long id) {
        return clienteService.getId(id);
    }

    public Cliente save(Cliente prod) {
        if (prod.getIdCliente() == null) {
            return clienteService.save(prod);

        } else {
            Optional<Cliente> pro = clienteService.getId(prod.getIdCliente());
            if (pro.isEmpty()) {
                return clienteService.save(prod);
            }
        }
        return prod;
    }

    public Cliente update(Cliente prod) {
        if (prod.getIdCliente() != null) {
            Optional<Cliente> pro = clienteService.getId(prod.getIdCliente());
            if (!pro.isEmpty()) {
                if (prod.getCliente() != null) {
                    pro.get().setCliente(prod.getCliente());
                }
                return clienteService.save(pro.get());
            }
        }
        return prod;
    }

    public boolean delete(Long id) {
        Optional<Cliente> pro = getId(id);
        if (!pro.isEmpty()) {
            clienteService.delete(pro.get());
            return true;
        }
        return false;
    }

}
