package com.cmdb.proyectocmdb.Model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "infraestructuras")
public class Infraestructura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInfraestructura;
    private String hostname;
    private String ip;
    private String tipoEquipo;
    private String RolServicio;
    private String plataforma;
    private String responsable;
    private String os;

    public Long getIdInfraestructura() {
        return idInfraestructura;
    }

    public void setIdInfraestructura(Long idInfraestructura) {
        this.idInfraestructura = idInfraestructura;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(String tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    public String getRolServicio() {
        return RolServicio;
    }

    public void setRolServicio(String rolServicio) {
        RolServicio = rolServicio;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

}
