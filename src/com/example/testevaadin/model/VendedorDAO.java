package com.example.testevaadin.model;

import java.util.Date;

import javax.jdo.PersistenceManager;

import com.example.testevaadin.infra.PMF;
import com.example.testevaadin.pojo.Vendedor;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class VendedorDAO {
	
	public Vendedor incluir(String nome, Date dataNascimento) throws Exception {
		
		UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        if (user == null) {
        	throw new Exception("Usuario nao logado");
        }
        
        Vendedor vendedor = new Vendedor(user, nome, dataNascimento);
		
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(vendedor);
        } finally {
            pm.close();
        }
        
        return vendedor;
    }
    
}
