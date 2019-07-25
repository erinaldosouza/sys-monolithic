package br.com.tcc.sys_monolithic.security.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="users_app_userapp", schema="monolitic")
@NamedQueries(
		@NamedQuery(name="findByLogin",
		            query="Select u FROM UserApp u FETCH ALL PROPERTIES  WHERE u.login = :login AND u.flActive IS TRUE")
)

public class UserApp implements Serializable {

	private static final long serialVersionUID = -5385791749911409190L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(name="last_name")	
	private String lastName;
	
	private String login;
	
	private String password;
	
	@Column(name="fl_active")
	private Boolean flActive;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.JOIN)
	@JoinTable(name = "users_app_userapp_group",
			   schema = "monolitic",
	           joinColumns = {@JoinColumn(name="userapp_id")},
	           inverseJoinColumns = {@JoinColumn(name="groupapp_id")}
	)
	private Set<GroupApp> groups;
	
	public UserApp() {
	}
	
	public UserApp(String login) {
		this.login = login;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		System.out.println(Thread.currentThread().getName());
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getFlActive() {
		return flActive;
	}

	public void setFlActive(Boolean flActive) {
		this.flActive = flActive;
	}

	public Set<GroupApp> getGroups() {
		return groups;
	}

	public void setGroups(Set<GroupApp> groups) {
		this.groups = groups;
	}
	
	public Set<RouteApp> getRoutes() {
		Set<RouteApp> routes = new HashSet<RouteApp>();
		if(groups != null) {			
			this.groups.forEach(g -> 
				routes.addAll(g.getRoutes())
			);

		}
		
		return routes;
	}
}
