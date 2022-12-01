package com.cmdb.proyectocmdb.Service;

import java.util.ArrayList;
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
    private int conteo;
    private ArrayList<Infraestructura> ipNoRepetidas = new ArrayList<Infraestructura>();
    private ArrayList<String> equiposCliente = new ArrayList<String>();
    private ArrayList<String> clientesByEquipo = new ArrayList<String>();

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

    public List<String> getIpListCliente(String ip) {
        clientesByEquipo.clear();
        if (!ip.isEmpty()) {
            List<Infraestructura> infr = infraestructuraService.findIp(ip);
            if (!infr.isEmpty()) {
                clientesByEquipo.add("------------Equipo-------------");
                clientesByEquipo.add("Hostname: " + infr.get(0).getHostname());
                clientesByEquipo.add("IP: " + infr.get(0).getIp());
                clientesByEquipo.add("OS: " + infr.get(0).getOs());
                clientesByEquipo.add("Plataforma: " + infr.get(0).getPlataforma());
                clientesByEquipo.add("Rol / servicio: " + infr.get(0).getRolServicio());
                clientesByEquipo.add("Responsable: " + infr.get(0).getResponsable());
                clientesByEquipo.add("Tipo de equipo: " + infr.get(0).getTipoEquipo());

                clientesByEquipo.add("-------------------------------");
                for (Cliente inf : clienteService.findIp(ip)) {
                    clientesByEquipo.add("Cliente: " + inf.getCliente());
                }
            }
            clientesByEquipo.add("-------------------------------");
            clientesByEquipo.add("Total clientes: " + clienteService.findIp(ip).size());
        }
        return clientesByEquipo;
    }

    public List<String> clienteInfra(String cli) {
        equiposCliente.clear();
        if (cli != null) {
            Optional<Cliente> pro = clienteService
                    .getId(clienteService.getByCliente(cli).get(0).getIdCliente());
            if (!pro.isEmpty()) {
                for (Infraestructura infr : pro.get().getInfraestructura()) {
                    equiposCliente.add("Servidor: " + infr.getHostname() + " IP:" + infr.getIp() + " Ambiente: "
                            + infr.getResponsable());
                }
                equiposCliente.add("-------------------------------");
                equiposCliente.add("Total equipos: " + pro.get().getInfraestructura().size());
                return equiposCliente;
            }
        }
        return equiposCliente;
    }

    public Cliente save(Cliente prod) {// Validar por nombre si ya existe
        if (!clienteService.existCliente(prod.getCliente())) {

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
                        // validar si existe el equipo
                        if (infraestructuraService.findIp(ip.getIp()).size() != 0) {
                            number = infraestructuraService.findIp(ip.getIp()).get(0).getIdInfraestructura();
                            prod.getInfraestructura().get(i).setIdInfraestructura(number);
                            i++;
                        }
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

    public boolean deleteCi(Cliente cli) {
        if (clienteService.existCliente(cli.getCliente()) != false) {
            if (infraestructuraService.findIp(cli.getInfraestructura().get(0).getIp()).size() != 0) {
                Optional<Cliente> pro = clienteService
                        .getId(clienteService.getByCliente(cli.getCliente()).get(0).getIdCliente());
                for (int i = 0; i <= pro.get().getInfraestructura().size(); i++) {
                    if (pro.get().getInfraestructura().get(i).getIp().equals(cli.getInfraestructura().get(0).getIp())) {
                        pro.get().getInfraestructura().remove(i);
                        clienteService.save(pro.get());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // borrar con el nombre

    public Cliente addInfra(Cliente prod) {
        ipNoRepetidas.clear();
        if (prod.getCliente() != null) {
            Optional<Cliente> pro = clienteService
                    .getId(clienteService.getByCliente(prod.getCliente()).get(0).getIdCliente());
            if (prod.getCliente() != null) {
                if (!pro.isEmpty()) {// desde aqui
                    if (prod.getInfraestructura() != null) {
                        for (int i = 0; i < prod.getInfraestructura().size(); i++) {
                            for (int j = 0; j < pro.get().getInfraestructura().size(); j++) {
                                if (prod.getInfraestructura().get(i).getIp()
                                        .equals(pro.get().getInfraestructura().get(j).getIp())) {
                                    conteo++;
                                    System.out.println();
                                }
                            }
                            if (conteo == 0) {
                                if (infraestructuraService.findIp(prod.getInfraestructura().get(i).getIp())
                                        .size() != 0) {
                                    ipNoRepetidas.add(prod.getInfraestructura().get(i));
                                }

                            }
                            conteo = 0;
                        }
                        if (!ipNoRepetidas.isEmpty()) {
                            prod.setInfraestructura(ipNoRepetidas);
                            prod.getInfraestructura().addAll(pro.get().getInfraestructura());
                            i = 0;

                            for (Infraestructura ip : prod.getInfraestructura()) {
                                if (infraestructuraService.findIp(ip.getIp()).size() != 0) {
                                    number = infraestructuraService.findIp(ip.getIp()).get(0).getIdInfraestructura();
                                    prod.getInfraestructura().get(i).setIdInfraestructura(number);
                                    i++;
                                }
                            }
                            pro.get().setInfraestructura(prod.getInfraestructura());
                            return clienteService.save(pro.get());
                        }
                    }
                }
            }
        }
        return prod;
    }
}
