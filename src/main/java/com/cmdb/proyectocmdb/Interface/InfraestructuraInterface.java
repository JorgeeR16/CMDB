package com.cmdb.proyectocmdb.Interface;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cmdb.proyectocmdb.Model.Infraestructura;

public interface InfraestructuraInterface extends CrudRepository<Infraestructura, Long> {

    List<Infraestructura> findByIpLike(String ip);

    boolean existsByIp(String infra);
}
