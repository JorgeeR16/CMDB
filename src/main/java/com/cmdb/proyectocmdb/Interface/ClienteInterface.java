package com.cmdb.proyectocmdb.Interface;

import org.springframework.data.repository.CrudRepository;

import com.cmdb.proyectocmdb.Model.Cliente;

public interface ClienteInterface extends CrudRepository<Cliente, Long> {

}
