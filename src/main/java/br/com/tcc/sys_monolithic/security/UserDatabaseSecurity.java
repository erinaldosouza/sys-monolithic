package br.com.tcc.sys_monolithic.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import br.com.tcc.sys_monolithic.security.model.UserApp;

public class UserDatabaseSecurity implements UserDetails {

	private static final long serialVersionUID = 2359745374924951377L;
	
	private Long id;
	private String username;
	private String password;
	private String name;
	private String lastName;
	private UserApp userApp;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserDatabaseSecurity(UserApp user) {
		super();
		this.id = user.getId();
		this.username = user.getName();
		this.password = user.getPassword();
		this.name = user.getName();
		this.lastName = user.getLastName();
		this.userApp = user;		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(authorities == null) {
			this.buildAuthorities();
		}

		return this.authorities;
	}

	private void buildAuthorities() {
		this.authorities = this.userApp.getRoutes().stream()
				   .map(r -> new SimpleGrantedAuthority(r.getAppName() + r.getLink().replace("$", "[0-9A-Za-z]{0,}/{0,1}") + r.getAuthorization()))
				   .collect(Collectors.toSet());	
	}
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	@Override
	public String getPassword() {
		// The application that generates the passwords stores it in a different format.
		// Stored format: 'bREAL_BCRYPT_ENCODED_PASSWORD'. This method remove the marks e returns just the REAL_BCRYPT_ENCODED_PASSWORD
		if(!StringUtils.isEmpty(password)) {
			this.password = this.password.substring(2, password.length()-1);
		}
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

}
