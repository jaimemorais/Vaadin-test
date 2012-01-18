package com.example.testevaadin.application;

import com.example.testevaadin.controller.AbrirContaController;
import com.example.testevaadin.controller.AbrirContaUC;
import com.example.testevaadin.controller.ConsultarSaldoController;
import com.example.testevaadin.controller.ConsultarSaldoUC;
import com.example.testevaadin.infra.Controller;
import com.example.testevaadin.infra.View;
import com.example.testevaadin.infra.ViewManager;
import com.example.testevaadin.infra.WindowUtils;
import com.vaadin.Application;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class Main extends Application implements ViewManager {
        
	
	/*
	 * Atencao , para rodar local, alterar no web.xml
	 	<servlet-name>Testevaadin Application</servlet-name>
		<servlet-class>com.vaadin.terminal.gwt.server.GAEApplicationServlet</servlet-class>
		
		para 
		
		<servlet-class>com.vaadin.terminal.gwt.server.ApplicationServlet</servlet-class>
	 */
	
	
        // controllers
        private Controller abrirContaController = new AbrirContaController(new AbrirContaUC());
        private Controller consultarSaldoController = new ConsultarSaldoController(new ConsultarSaldoUC());
        
        // components
        private Window mainWindow = new Window("Vaadin MVC");
        private TabSheet tabs = new TabSheet();
        
        @Override
        public final void init() {
                this.setMainWindow(crieWindow());
        }
        
        public final Window crieWindow() {
                tabs.setSizeFull();
                mainWindow.addComponent(crieAcoes());
                mainWindow.addComponent(tabs);
                ((VerticalLayout) mainWindow.getContent()).setExpandRatio(tabs, 3);
                return mainWindow;
        }

        private Component crieAcoes() {
                HorizontalLayout hl = new HorizontalLayout();
                hl.addComponent(crieAcao("Abrir Conta", abrirContaController));
                hl.addComponent(crieAcao("Consultar Saldo", consultarSaldoController));
                return hl;
        }
        
        private Component crieAcao(final String nome, final Controller controller) {
                // cada acao sera um botao
                Button b = new Button(nome);
                b.addListener(new ClickListener() {

                        public void buttonClick(ClickEvent event) {
                                
                                // inicia o controller, que retorna uma view
                                View view = controller.init(_this());
                                Component c = view.getComponent();

                                // ao clicar ele irá criar uma nova tab
                                Tab t = tabs.addTab(c, nome, null);
                                t.setClosable(true);
                                tabs.setSelectedTab(c);
                        }
                });
                return b;
        }
        
        public Main _this() {
                return this;
        }
        
        // ViewManager
        
        public void close(View view) {
                // remove a view da TabSheet
                tabs.removeComponent(view.getComponent());
        }

        public void showMsg(String msg) {
                WindowUtils.showMsg(mainWindow, msg);
        }
        
        
        public void showError(String error) {
                WindowUtils.showError(mainWindow, error);
        }

        
        public Window getWindow() {
                return mainWindow;
        }
                
}
