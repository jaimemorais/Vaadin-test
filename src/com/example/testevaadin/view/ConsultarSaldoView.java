package com.example.testevaadin.view;

import com.example.testevaadin.controller.ConsultarSaldoController;
import com.example.testevaadin.infra.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ConsultarSaldoView implements View {
        
        // form
        private VerticalLayout form;
        
        // campos
        private TextField campoNumeroConta = new TextField(); 
        private Label labelSaldo = new Label();
        
        // controller
        private ConsultarSaldoController controller;
        
        public ConsultarSaldoView(ConsultarSaldoController controller) {
                this.controller = controller;
                init();
        }
        
        @SuppressWarnings("serial")
        private void init() {
                form = new VerticalLayout();

                form.addComponent(new Label("Número Conta: "));
                form.addComponent(campoNumeroConta);
                
                form.addComponent(new Label("Saldo: "));
                form.addComponent(labelSaldo);
                
                Button btConsultar = new Button("Consultar");
                btConsultar.addListener(new ClickListener() {

                        public void buttonClick(ClickEvent event) {
                                controller.consulteSaldo(_this());
                        }
                });
                form.addComponent(new Label(""));
                form.addComponent(btConsultar);
        }
        
        // 
        
        public String getNumeroConta() {
                return (String)campoNumeroConta.getValue();
        }
        
        public String getSaldo() {
                return (String)labelSaldo.getValue();
        }

        //
        
        public void setNumeroConta(String numeroConta) {
                this.campoNumeroConta.setValue(numeroConta);
        }

        public void setSaldo(String saldo) {
                this.labelSaldo.setValue(saldo);
        }

        //
        
        private ConsultarSaldoView _this() {
                return this;
        }

        // view
        
        public Component getComponent() {
                return form;
        }
        
}