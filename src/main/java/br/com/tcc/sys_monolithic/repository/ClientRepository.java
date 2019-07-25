package br.com.tcc.sys_monolithic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.tcc.sys_monolithic.model.impl.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

}
