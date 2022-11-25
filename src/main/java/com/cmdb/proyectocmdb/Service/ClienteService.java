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
    private int i = 0;
    private Long number;

    public List<Cliente> getAll() {
        return clienteService.getAll();
    }

    public Optional<Cliente> getId(Long id) {
        return clienteService.getId(id);
    }

    public List<Cliente> getByCliente(String cli) {
        return clienteService.getByCliente(cli);
    }

    public List<Cliente> getIp(String ip) {
        return clienteService.findIp(ip);
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

        // SE CONSULTA POR ID, para actualizar un nombre si toca con el id
        if (prod.getIdCliente() != null) {
            Optional<Cliente> pro = clienteService.getId(prod.getIdCliente());
            if (!pro.isEmpty()) {
                if (prod.getCliente() != null) {
                    pro.get().setCliente(prod.getCliente());
                }
                if (prod.getInfraestructura() != null) {
                    i = 0;
                    for (Infraestructura ip : prod.getInfraestructura()) {
                        number = infraestructuraService.findIp(ip.getIp()).get(0).getIdInfraestructura();
                        prod.getInfraestructura().get(i).setIdInfraestructura(number);
                        i++;
                    }
                    pro.get().setInfraestructura(prod.getInfraestructura());
                }
                return clienteService.save(pro.get());
            }
        }
        // SE CONSULTA POR CLIENTE
        if (prod.getCliente() != null) {
            Optional<Cliente> pro = clienteService
                    .getId(clienteService.getByCliente(prod.getCliente()).get(0).getIdCliente());
            if (!pro.isEmpty()) {// desde aqui
                if (prod.getInfraestructura() != null) {
                    i = 0;
                    for (Infraestructura ip : prod.getInfraestructura()) {
                        number = infraestructuraService.findIp(ip.getIp()).get(0).getIdInfraestructura();
                        prod.getInfraestructura().get(i).setIdInfraestructura(number);
                        i++;
                    }
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
