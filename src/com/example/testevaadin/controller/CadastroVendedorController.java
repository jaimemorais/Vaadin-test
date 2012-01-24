package com.example.testevaadin.controller;


import java.util.Date;

import com.example.testevaadin.infra.Controller;
import com.example.testevaadin.infra.ViewManager;
import com.example.testevaadin.model.VendedorDAO;
import com.example.testevaadin.utils.DateUtils;
import com.example.testevaadin.view.CadastroVendedorView;

public class CadastroVendedorController extends Controller {
    
    private VendedorDAO dao;
    
    public CadastroVendedorController(VendedorDAO dao) {
            this.dao = dao;
    }
    
    // init 
    public CadastroVendedorView init(ViewManager viewManager) {
            setViewManager(viewManager);
            return new CadastroVendedorView(this);
    }

    // uma acao do usuario
    public void insereVendedor(CadastroVendedorView view) throws Exception {
        
    	Date dtNasc = 
    			DateUtils.stringToDate(view.getDataNascimentoVendedor(), DateUtils.PATTERN_DATE_DIA_MES_ANO);
    	
        dao.incluir(view.getNomeVendedor(), dtNasc);
    }

}