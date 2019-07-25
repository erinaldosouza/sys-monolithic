package br.com.tcc.sys_monolithic.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.tcc.sys_monolithic.security.model.UserApp;

@Repository
public interface UserRepository extends JpaRepository<UserApp, Long> {
	
	@Query(name="findByLogin")
	UserApp findByLogin(String login);

}
