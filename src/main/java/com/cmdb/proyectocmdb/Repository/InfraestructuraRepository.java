package com.cmdb.proyectocmdb.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cmdb.proyectocmdb.Interface.InfraestructuraInterface;
import com.cmdb.proyectocmdb.Model.Infraestructura;

@Repository
public class InfraestructuraRepository {

    @Autowired
    private InfraestructuraInterface infraestructuraCRUD;

    public List<Infraestructura> getAll() {
        return (List<Infraestructura>) infraestructuraCRUD.findAll();
    }

    public Optional<Infraestructura> getId(Long id) {
        return infraestructuraCRUD.findById(id);
    }

    public Infraestructura save(Infraestructura cat) {
        return infraestructuraCRUD.save(cat);
    }

    public void delete(Infraestructura cat) {
        infraestructuraCRUD.delete(cat);
    }

    public List<Infraestructura> findIp(String ip) {
        return infraestructuraCRUD.findByIpLike(ip);
    }

    public boolean existInfra(String infr) {
        return infraestructuraCRUD.existsByIp(infr);
    }

}
