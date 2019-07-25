package br.com.tcc.sys_monolithic.security.model;

import java.io.Serializable;
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
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="groups_app_groupapp", schema="monolitic")
public class GroupApp implements Serializable {

	private static final long serialVersionUID = 3473546934625097980L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
		
	private String description;
	
	@Column(name="fl_active")
	private Boolean flActive;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.JOIN)
	@JoinTable(name = "groups_app_groupapp_route",
		        schema = "monolitic",
	           joinColumns = {@JoinColumn(name="groupapp_id")},
	           inverseJoinColumns = {@JoinColumn(name="routeapp_id")}
	)
	private Set<RouteApp> routes;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getFlActive() {
		return flActive;
	}

	public void setFlActive(Boolean flActive) {
		this.flActive = flActive;
	}

	public Set<RouteApp> getRoutes() {
		return routes;
	}

	public void setRoutes(Set<RouteApp> routes) {
		this.routes = routes;
	}
}
