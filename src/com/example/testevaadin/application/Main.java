package com.example.testevaadin.application;

import java.security.Principal;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.example.testevaadin.controller.AbrirContaController;
import com.example.testevaadin.controller.CadastroVendedorController;
import com.example.testevaadin.controller.ConsultarSaldoController;
import com.example.testevaadin.infra.Controller;
import com.example.testevaadin.infra.View;
import com.example.testevaadin.infra.ViewManager;
import com.example.testevaadin.infra.WindowUtils;
import com.example.testevaadin.model.AbrirContaDAO;
import com.example.testevaadin.model.ConsultarSaldoDAO;
import com.example.testevaadin.model.VendedorDAO;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.vaadin.Application;
import com.vaadin.service.ApplicationContext;
import com.vaadin.service.ApplicationContext.TransactionListener;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class Main extends Application implements ViewManager {
        
	/*
	 * http://rafaelnaskar.blogspot.com/2010/11/usando-vaadin-com-mvc.html
	 */
	
	/*
	 * Atencao , para rodar local, alterar no web.xml
	 	<servlet-name>Testevaadin Application</servlet-name>
		<servlet-class>com.vaadin.terminal.gwt.server.GAEApplicationServlet</servlet-class>
		
		para 
		
		<servlet-class>com.vaadin.terminal.gwt.server.ApplicationServlet</servlet-class>
		
	 */
	
	
        // controllers
        private Controller abrirContaController = new AbrirContaController(new AbrirContaDAO());
        private Controller consultarSaldoController = new ConsultarSaldoController(new ConsultarSaldoDAO());
        private Controller cadastroVendedorController = new CadastroVendedorController(new VendedorDAO());
        
        private User usuarioLogado;
        
        // components
        private Window mainWindow = new Window("Teste Vaadin MVC");
        private TabSheet tabs = new TabSheet();
        
		HorizontalLayout authInfo;

        
    	private static HashMap<ApplicationContext, TransactionListener> requestListeners = new HashMap<ApplicationContext, TransactionListener>();

    	private boolean isAppInitialized = false;
    	
    	private static final long serialVersionUID = 1108466519494665064L;

        
        @Override
        public void init() {
        	
        	// Only add one listener per context as Vaadin calls every listener in
    		// the context for every request.
    		if (getContext() != null && requestListeners.get(getContext()) == null) {
    			TransactionListener listener = new TransactionListener() {
    				private static final long serialVersionUID = -8490216576436646032L;

    				//@Override
    				public void transactionStart(Application app, Object req) {
    					if (!isAppInitialized) {
    						criaMainWindow();
    						isAppInitialized = true;
    					}
    					
    					checkLoginLogout((HttpServletRequest) req);
    		    		
    				}

    				//@Override
    				public void transactionEnd(Application app, Object req) {
    					// NOP
    				}
    			};

    			getContext().addTransactionListener(listener);

    			requestListeners.put(getContext(), listener);
    		}
        	
        }
        
        
        private synchronized void criaMainWindow() {   
        	
        	mainWindow.addComponent(authInfo = new HorizontalLayout());
			authInfo.setSpacing(true);			
			
			//ele nao ta exibindo os dados da crieAplicacaoWindow() abaixo
			
			this.setMainWindow(crieAplicacaoWindow());
        }     
        
        
        private void checkLoginLogout(HttpServletRequest request) {
    		Principal user = request.getUserPrincipal();

    		mainWindow.removeAllComponents();
    		
    		mainWindow.addComponent(authInfo);
    		
    		if (authInfo.getComponentIterator().hasNext()
    				&& ((user != null && getUser() != null) || (user == null && getUser() == null))) {
    			return; // Layout initialized, and no login/logout detected
    		}

    		UserService userService = UserServiceFactory.getUserService();

    		Link link;
    		Label email;
    		if (request.getUserPrincipal() != null) {
    			// Login detected, store user email to application
    			setUser(request.getUserPrincipal().getName());

    			
    			usuarioLogado = userService.getCurrentUser();  
    			// TODO : esse usuarioLogado pode ser setado no setUser mais acima.
    			// O vaadin tem esse setuser que vc seta o usuario geral da aplicacao.
    			
    			
    			if (userService.isUserAdmin()) {
    				mainWindow.showNotification("You have admin privileges");
    			}

    			//mainWindow.showNotification("Hello " + request.getUserPrincipal().getName());

    			showMsg("Bem vindo " + request.getUserPrincipal().getName());
    			
    			email = new Label(request.getUserPrincipal().getName());
    			link = new Link("Logout", new ExternalResource(userService
    					.createLogoutURL("/")));
    		} else {
    			// Logout detected, or application initialized without login info
    			if (getUser() != null) {
    				//mainWindow.showNotification("Bye " + getUser().toString());
    				showMsg("Saiu " + getUser().toString());
    			}

    			setUser(null);

    			email = new Label("Usuario nao logado - ");
    			link = new Link(" Clique aqui para fazer o login", 
    					new ExternalResource(userService.createLoginURL("/")));
    		}

    		authInfo.removeAllComponents();

    		email.setStyleName("email");
    		authInfo.addComponent(email);

    		authInfo.addComponent(link);    		
    	}
        
        
        public final Window crieAplicacaoWindow() {
                tabs.setSizeFull();
                mainWindow.addComponent(crieAcoes());
                mainWindow.addComponent(tabs);
                ((VerticalLayout) mainWindow.getContent()).setExpandRatio(tabs, 3);


                if (usuarioLogado != null) {
                	Label usu = new Label("Usuario : " + usuarioLogado.getEmail());
                	mainWindow.addComponent(usu);
                }
    			Link linkLogout = new Link("Logout", new ExternalResource(UserServiceFactory.getUserService().createLogoutURL("/")));
    			mainWindow.addComponent(linkLogout);

    			
                return mainWindow;
        }

        
        
        private Component crieAcoes() {
                HorizontalLayout hl = new HorizontalLayout();
                hl.addComponent(crieAcao("Abrir Conta - teste", abrirContaController));
                hl.addComponent(crieAcao("Consultar Saldo", consultarSaldoController));
                hl.addComponent(crieAcao("Incluir Vendedor", cadastroVendedorController));
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
