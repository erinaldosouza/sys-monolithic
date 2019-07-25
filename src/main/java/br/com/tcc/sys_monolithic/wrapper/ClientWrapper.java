package br.com.tcc.sys_monolithic.wrapper;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.tcc.sys_monolithic.model.impl.Client;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)	
public class ClientWrapper implements Serializable {

	private static final long serialVersionUID = -4887772344155987297L;
	
	private Client user;
	private Long userId;
	private Iterable<Client> users;
	
	private String error;
	private String message;

	public ClientWrapper(Long userId) {
		this. userId = userId;
	}
	
	public ClientWrapper(Client user) {
		this.user = user;
	}
	
	public ClientWrapper(String message) {
		this.message = message;
	}
	
	public ClientWrapper() {
	}

	public ClientWrapper(Iterable<Client> users) {
		this.users = users;
	}
	
	public Client getUser() {
		return user;
	}
	
	public void setUser(Client user) {
		this.user = user;
	}
	
	public Iterable<Client> getUsers() {
		return users;
	}
	
	public void setUsers(Iterable<Client> users) {
		this.users = users;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getError() {
		return error;
	}
	
	public void setError(String errorType) {
		this.error = errorType;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
