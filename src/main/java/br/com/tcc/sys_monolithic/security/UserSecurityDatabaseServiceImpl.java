package br.com.tcc.sys_monolithic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.tcc.sys_monolithic.security.model.UserApp;
import br.com.tcc.sys_monolithic.security.repository.UserRepository;

@Service("userSecurityDatabaseService")
public class UserSecurityDatabaseServiceImpl implements UserDetailsService {
	
	private UserRepository repository;
	
	@Autowired
	public UserSecurityDatabaseServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		UserApp user = repository.findByLogin(login);
		
		if(user == null) {
			throw new UsernameNotFoundException("Incorrect user or password");
		}

		return new UserDatabaseSecurity(user);
	}

}
