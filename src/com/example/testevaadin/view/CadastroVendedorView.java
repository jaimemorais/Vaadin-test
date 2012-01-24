package com.example.testevaadin.view;

import com.example.testevaadin.controller.CadastroVendedorController;
import com.example.testevaadin.infra.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class CadastroVendedorView implements View {
        
        // form
        private VerticalLayout form;
        
        // campos        
        private TextField campoNomeVendedor = new TextField();
        private TextField campoDataNascimentoVendedor = new TextField();
        
        // controller
        private CadastroVendedorController controller;
        
        public CadastroVendedorView(CadastroVendedorController controller) {
                this.controller = controller;
                init();
        }
        
        @SuppressWarnings("serial")
        private void init() {
        	    form = new VerticalLayout();

               
                form.addComponent(new Label("Nome : "));
                form.addComponent(campoNomeVendedor);
                
                form.addComponent(new Label("Data Nascimento: "));
                form.addComponent(campoDataNascimentoVendedor);
                
                Button btInsereVendedor = new Button("Inserir");
                btInsereVendedor.addListener(new ClickListener() {

                        public void buttonClick(ClickEvent event) {
                                try {
									controller.insereVendedor(_this());
	                                controller.getViewManager().showMsg("Vendedor cadastrado com sucesso.");
								} catch (Exception e) {
	                                controller.getViewManager().showMsg("Erro ao cadastrar Vendedor : " + e.getMessage());
								}
                        }
                });
                form.addComponent(new Label(""));
                form.addComponent(btInsereVendedor);
        }
        
        
        // 
        
        public String getNomeVendedor() {
                return (String)campoNomeVendedor.getValue();
        }

        public String getDataNascimentoVendedor() {
                return (String)campoDataNascimentoVendedor.getValue();
        }
        
        //

        public void setNomeVendedor(String campoNomeVendedor) {
                this.campoNomeVendedor.setValue(campoNomeVendedor);
        }

        public void setDataNascimentoVendedor(String campoDataNascimentoVendedor) {
                this.campoDataNascimentoVendedor.setValue(campoDataNascimentoVendedor);
        }
        
        //
        
        private CadastroVendedorView _this() {
                return this;
        }

        // view
        
        public Component getComponent() {
                return form;
        }
        
}