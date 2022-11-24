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
public class InfraestructuraService {

    @Autowired
    private InfraestructuraRepository infraestructuraService;
    @Autowired
    private ClienteRepository clienteService;

    public List<Infraestructura> getAll() {
        return infraestructuraService.getAll();
    }

    public Optional<Infraestructura> getId(Long id) {
        return infraestructuraService.getId(id);
    }

    public Infraestructura save(Infraestructura cat) {
        if (cat.getIdInfraestructura() == null) {
            if (cat.getClientes() != null) {
                if (cat.getClientes().get(0) != null) {
                    Optional<Cliente> pva = clienteService.getId(cat.getClientes().get(0).getIdCliente());
                    if (!pva.isEmpty()) {
                        return infraestructuraService.save(cat);
                    }
                }
            }
        } else {
            Optional<Infraestructura> pca = infraestructuraService.getId(cat.getIdInfraestructura());
            if (pca.isEmpty()) {
                if (cat.getClientes().get(0) != null) {
                    Optional<Cliente> pva = clienteService.getId(cat.getClientes().get(0).getIdCliente());
                    if (!pva.isEmpty()) {
                        return infraestructuraService.save(cat);
                    }
                }
            }
        }
        return cat;
    }

    public Infraestructura update(Infraestructura cat) {

        if (cat.getIdInfraestructura() != null) {

            Optional<Infraestructura> pca = infraestructuraService.getId(cat.getIdInfraestructura());
            if (!pca.isEmpty()) {
                if (pca.get().getHostname() != null) {
                    pca.get().setHostname(cat.getHostname());
                }
                if (pca.get().getIp() != null) {
                    pca.get().setIp(cat.getIp());
                }
                if (pca.get().getTipoEquipo() != null) {
                    pca.get().setTipoEquipo(cat.getTipoEquipo());
                }
                if (pca.get().getRolServicio() != null) {
                    pca.get().setRolServicio(cat.getRolServicio());
                }
                if (pca.get().getPlataforma() != null) {
                    pca.get().setPlataforma(cat.getPlataforma());
                }
                if (pca.get().getResponsable() != null) {
                    pca.get().setResponsable(cat.getResponsable());
                }
                if (pca.get().getOs() != null) {
                    pca.get().setOs(cat.getOs());
                }
                if (pca.get().getClientes() != null) {
                    pca.get().setClientes(cat.getClientes());
                }
                return infraestructuraService.save(pca.get());
            }
        }
        return cat;
    }

    public boolean delete(Long id) {
        Optional<Infraestructura> pax = getId(id);
        if (!pax.isEmpty()) {
            infraestructuraService.delete(pax.get());
            return true;
        }
        return false;
    }

}
