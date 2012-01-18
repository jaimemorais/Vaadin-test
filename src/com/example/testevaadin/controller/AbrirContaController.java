package com.example.testevaadin.controller;

import com.example.testevaadin.infra.Controller;
import com.example.testevaadin.infra.ViewManager;
import com.example.testevaadin.view.AbrirContaView;

public class AbrirContaController extends Controller {
    
    // use case
    private AbrirContaUC uc;
    
    public AbrirContaController(AbrirContaUC uc) {
            this.uc = uc;
    }
    
    // init 
    public AbrirContaView init(ViewManager viewManager) {
            setViewManager(viewManager);
            return new AbrirContaView(this);
    }

    // uma acao do usuario
    public void abraConta(AbrirContaView view) {
            //TODO: regras de telas
            uc.abraConta(new Long(view.getIdAgencia()), view.getNomeCliente(), view.getRG());
    }

}