package com.cmdb.proyectocmdb.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmdb.proyectocmdb.Model.Cliente;
import com.cmdb.proyectocmdb.Model.Infraestructura;
import com.cmdb.proyectocmdb.Repository.ClienteRepository;
import com.cmdb.proyectocmdb.Repository.InfraestructuraRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteService;
    @Autowired
    private InfraestructuraRepository infraestructuraService;

    public List<Cliente> getAll() {
        return clienteService.getAll();
    }

    public Optional<Cliente> getId(Long id) {
        return clienteService.getId(id);
    }

    public List<Cliente> getByCliente(String cli) {
        return clienteService.getByCliente(cli);
    }

    public Cliente save(Cliente prod) {
        if (prod.getIdCliente() == null) {
            if (prod.getInfraestructura() != null) {
                Optional<Infraestructura> infr = infraestructuraService
                        .getId(prod.getInfraestructura().get(0).getIdInfraestructura());
                if (!infr.isEmpty()) {
                    return clienteService.save(prod);
                }
            } else {
                return clienteService.save(prod);
            }

        } else {
            Optional<Cliente> pro = clienteService.getId(prod.getIdCliente());
            if (pro.isEmpty()) {
                if (prod.getInfraestructura() != null) {
                    Optional<Infraestructura> infr = infraestructuraService
                            .getId(prod.getInfraestructura().get(0).getIdInfraestructura());
                    if (!infr.isEmpty()) {
                        return clienteService.save(prod);
                    }
                } else {
                    return clienteService.save(prod);
                }
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
                if (prod.getInfraestructura() != null) {
                    pro.get().setInfraestructura(prod.getInfraestructura());
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
