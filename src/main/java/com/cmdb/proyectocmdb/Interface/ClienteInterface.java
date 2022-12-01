package com.cmdb.proyectocmdb.Interface;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cmdb.proyectocmdb.Model.Cliente;

public interface ClienteInterface extends CrudRepository<Cliente, Long> {
    List<Cliente> findByClienteContaining(String campaña);

    List<Cliente> findByClienteLike(String campaña);

    @Query(value = "SELECT clientes.id_cliente, clientes.cliente,infraestructuras.id_infraestructura, infraestructuras.hostname, infraestructuras.ip FROM clientes INNER JOIN cliente_infraestructura ON clientes.id_cliente = cliente_infraestructura.cliente_id INNER JOIN infraestructuras ON cliente_infraestructura.infraestructura_id = infraestructuras.id_infraestructura    WHERE infraestructuras.ip like :filtro", nativeQuery = true)
    List<Cliente> findIp(@Param("filtro") String ip);

    boolean existsByCliente(String cliente);

}
