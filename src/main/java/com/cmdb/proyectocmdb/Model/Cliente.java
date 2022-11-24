package com.cmdb.proyectocmdb.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    private String cliente;

    @ManyToMany
    @JoinTable(name = "cliente_infraestructura", joinColumns = @JoinColumn(name = "cliente_id"), inverseJoinColumns = @JoinColumn(name = "infraestructura_id"))
    private List<Infraestructura> infraestructura;// =new HashSet<>();

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<Infraestructura> getInfraestructura() {
        return infraestructura;
    }

    public void setInfraestructura(List<Infraestructura> infraestructura) {
        this.infraestructura = infraestructura;
    }

}
