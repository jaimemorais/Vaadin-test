package com.example.testevaadin.view;

import com.example.testevaadin.controller.AbrirContaController;
import com.example.testevaadin.infra.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class AbrirContaView implements View {
        
        // form
        private VerticalLayout form;
        
        // campos
        private TextField campoIdAgencia = new TextField();
        private TextField campoNomeCliente = new TextField();
        private TextField campoRG = new TextField();
        
        // controller
        private AbrirContaController controller;
        
        public AbrirContaView(AbrirContaController controller) {
                this.controller = controller;
                init();
        }
        
        @SuppressWarnings("serial")
        private void init() {
                form = new VerticalLayout();

                form.addComponent(new Label("Agência: "));
                form.addComponent(campoIdAgencia);
                
                form.addComponent(new Label("Nome Cliente: "));
                form.addComponent(campoNomeCliente);
                
                form.addComponent(new Label("RG: "));
                form.addComponent(campoRG);
                
                Button btCriarConta = new Button("Efetuar Abertura de Conta");
                btCriarConta.addListener(new ClickListener() {

                        public void buttonClick(ClickEvent event) {
                                controller.abraConta(_this());
                                controller.getViewManager().showMsg("Conta aberta com sucesso.");
                        }
                });
                form.addComponent(new Label(""));
                form.addComponent(btCriarConta);
        }
        
        // 
        
        public String getIdAgencia() {
                return (String)campoIdAgencia.getValue();
        }
        
        public String getNomeCliente() {
                return (String)campoNomeCliente.getValue();
        }

        public String getRG() {
                return (String)campoRG.getValue();
        }
        
        //
        
        public void setIdAgencia(String idAgencia) {
                this.campoIdAgencia.setValue(idAgencia);
        }

        public void setNomeCliente(String nomeCliente) {
                this.campoNomeCliente.setValue(nomeCliente);
        }

        public void setRG(String campoRG) {
                this.campoRG.setValue(campoRG);
        }
        
        //
        
        private AbrirContaView _this() {
                return this;
        }

        // view
        
        public Component getComponent() {
                return form;
        }
        
}