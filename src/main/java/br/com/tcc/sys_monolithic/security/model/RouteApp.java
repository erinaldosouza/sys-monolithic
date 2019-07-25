package br.com.tcc.sys_monolithic.security.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="routes_app_routeapp", schema="monolitic")
public class RouteApp implements Serializable {

	private static final long serialVersionUID = -5308947134807660588L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="app_name")
	private String appName;
	
	private String link;
	
	private String description;
	
	private String authorization;
	
	@Column(name="fl_active")
	private Boolean flActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	public Boolean getFlActive() {
		return flActive;
	}

	public void setFlActive(Boolean flActive) {
		this.flActive = flActive;
	}

}
