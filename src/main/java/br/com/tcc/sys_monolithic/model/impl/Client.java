package br.com.tcc.sys_monolithic.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.tcc.sys_monolithic.model.IBaseModel;

@Entity
@Table(name="tb_client", schema="monolithic")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class Client implements IBaseModel<Long> {
	
	private static final long serialVersionUID = 4650828981000577447L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(name="ds_name", nullable=false)
	private String name;
	
	@NotBlank
	@Column(name="ds_last_name", nullable=false)
	private String lastName;
	
	@NotBlank
	@Column(name="ds_login", nullable=false, unique=true)
	private String login;
	
	@NotBlank
	@Column(name="ds_password", nullable=false)
	private String password;

	@Transient
	private MultipartFile document;
	
	@Transient
	private String base64Photo;
	
	@Column(name="document_info")
	private byte[] documentInfo;

	public Client() {
	}
	
	public Client(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBase64Photo() {
		return base64Photo;
	}

	public void setBase64Photo(String base64Photo) {
		this.base64Photo = base64Photo;
	}

	public MultipartFile getDocument() {
		return document;
	}

	public void setDocument(MultipartFile document) {
		this.document = document;
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

	public byte[] getDocumentInfo() {
		return documentInfo;
	}

	public void setDocumentInfo(byte[] documentInfo) {
		this.documentInfo = documentInfo;
	}
}
