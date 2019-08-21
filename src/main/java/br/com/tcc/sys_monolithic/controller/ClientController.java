package br.com.tcc.sys_monolithic.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.tcc.sys_monolithic.model.impl.Client;
import br.com.tcc.sys_monolithic.service.ClientService;
import br.com.tcc.sys_monolithic.wrapper.ClientWrapper;

@RestController
@RequestMapping(value="api-user/v1")
public class ClientController {

	private final ClientService service;	
	
	@Autowired
	public ClientController(ClientService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<ClientWrapper> save(@Valid Client user) {
		 this.service.save(user);	 
		 return ResponseEntity.status(HttpStatus.CREATED).build();				
	}
	
	@GetMapping(value="{id}")
	public ResponseEntity<ClientWrapper> find(@PathVariable(name="id", required=true) Long id) {
		ResponseEntity<ClientWrapper> response = null;
		Client user = this.service.findById(id);
		
		if(user != null) {
			response = ResponseEntity.ok(new ClientWrapper(user));
		} else {
			response =  ResponseEntity.noContent().build();
		}
		
		return response;
    }
	
	@GetMapping
	public ResponseEntity<ClientWrapper> findAll() {
		ResponseEntity<ClientWrapper> response = null;
		Iterable<Client> users = this.service.findAll();
		
		if(users.iterator().hasNext()) {
			response = ResponseEntity.ok(new ClientWrapper(users));
		} else {
			response =  ResponseEntity.noContent().build();
		}
		
		return response;
	}
	
	@PutMapping(value="{id}")
	public ResponseEntity<ClientWrapper> update(@PathVariable(name="id", required=true) Long id, @RequestPart(name="document", required=false) MultipartFile document, @Valid Client user) {
		user.setId(id);
		user.setDocument(document);
		this.service.update(user);
		return ResponseEntity.ok().build();
	} 
	
	@DeleteMapping(value="{id}")
	public ResponseEntity<ClientWrapper> delete(@PathVariable(name="id", required=true) Long id) {
		this.service.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
