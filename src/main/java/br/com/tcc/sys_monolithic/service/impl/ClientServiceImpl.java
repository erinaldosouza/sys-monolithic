package br.com.tcc.sys_monolithic.service.impl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.tcc.sys_monolithic.model.impl.Client;
import br.com.tcc.sys_monolithic.repository.ClientRepository;
import br.com.tcc.sys_monolithic.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {
		
	private final ClientRepository repository;
	
	@Autowired
	public ClientServiceImpl(ClientRepository repository) {
		this.repository = repository;
	}

	@Override
	public void save(Client user) {
		
		try {

			MultipartFile document = user.getDocument();
			user.setDocumentInfo(document.getBytes());
			this.repository.save(user);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Client findById(Long id) {
		return this.repository.findById(id).orElse(null);
	}

	@Override
	public boolean existsById(Long id) {
		return this.repository.existsById(id);
	}

	@Override
	public Iterable<Client> findAll() {
		return this.repository.findAll();
	}

	@Override
	public long count() {
		return this.repository.count();
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}


	@Override
	public Client update(Client user) {
		
		Client userBD = null;
		Optional<Client> opt = repository.findById(user.getId());
		
		try {
			
			if((userBD = opt.orElse(null)) != null) {
				userBD.setLogin(user.getLogin());
				userBD.setPassword(user.getPassword());
					
				repository.save(userBD);
				
				if(user.getDocument() != null) {
					user.setDocumentInfo(userBD.getDocument().getBytes());
				}
			}	

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return user;
	}
}
