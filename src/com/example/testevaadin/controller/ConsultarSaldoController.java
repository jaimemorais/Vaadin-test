package com.example.testevaadin.controller;

import com.example.testevaadin.infra.Controller;
import com.example.testevaadin.infra.ViewManager;
import com.example.testevaadin.view.ConsultarSaldoView;

public class ConsultarSaldoController extends Controller {
    
    private ConsultarSaldoUC uc;
    
    public ConsultarSaldoController(ConsultarSaldoUC uc) {
            this.uc = uc;
    }
    
    public ConsultarSaldoView init(ViewManager viewManager) {
            this.setViewManager(viewManager);
            return new ConsultarSaldoView(this);
    }

    public void consulteSaldo(ConsultarSaldoView view) {
            float saldo = uc.consulteSaldo(new Long(view.getNumeroConta()));
            view.setSaldo(String.valueOf(saldo));
            
    }

}
