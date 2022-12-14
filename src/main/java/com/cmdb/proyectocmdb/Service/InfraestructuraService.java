package com.cmdb.proyectocmdb.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmdb.proyectocmdb.Model.Infraestructura;
import com.cmdb.proyectocmdb.Repository.InfraestructuraRepository;

@Service
public class InfraestructuraService {

    @Autowired
    private InfraestructuraRepository infraestructuraService;

    public List<Infraestructura> getAll() {
        return infraestructuraService.getAll();
    }

    public List<Infraestructura> getByIp(String ip) {
        return infraestructuraService.findIp(ip);
    }

    public Optional<Infraestructura> getId(Long id) {
        return infraestructuraService.getId(id);
    }

    public Infraestructura save(Infraestructura cat) {
        if (!infraestructuraService.existInfra(cat.getIp())) {
            if (cat.getIdInfraestructura() == null) {
                return infraestructuraService.save(cat);

            } else {
                Optional<Infraestructura> pro = infraestructuraService.getId(cat.getIdInfraestructura());
                if (pro.isEmpty()) {
                    return infraestructuraService.save(cat);
                }
            }
        }
        return cat;
    }

    public Infraestructura update(Infraestructura cat) {

        // con ID
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
                return infraestructuraService.save(pca.get());
            }
        }

        // con IP
        if (cat.getIp() != null) {

            Optional<Infraestructura> pca = infraestructuraService
                    .getId(infraestructuraService.findIp(cat.getIp()).get(0).getIdInfraestructura());
            if (!pca.isEmpty()) {
                if (pca.get().getHostname() != null) {
                    pca.get().setHostname(cat.getHostname());
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
    // falta borrar por ip
}
