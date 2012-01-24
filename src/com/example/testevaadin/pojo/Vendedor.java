package com.example.testevaadin.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

@PersistenceCapable
public class Vendedor implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key id;

    @Persistent
    private User usuarioAcao;

    @Persistent
    private String nome;

    @Persistent
    private Date dataAniversario;

    public Vendedor(User usuarioAcao, String nome, Date dataAniversario) {
        this.nome = nome;
        this.usuarioAcao = usuarioAcao;
        this.dataAniversario = dataAniversario;
    }

	public Key getId() {
		return id;
	}

	public void setId(Key id) {
		this.id = id;
	}

	public User getUsuarioAcao() {
		return usuarioAcao;
	}

	public void setUsuarioAcao(User usuarioAcao) {
		this.usuarioAcao = usuarioAcao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataAniversario() {
		return dataAniversario;
	}

	public void setDataAniversario(Date dataAniversario) {
		this.dataAniversario = dataAniversario;
	}



}
