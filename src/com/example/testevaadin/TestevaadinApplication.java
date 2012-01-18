package com.example.testevaadin;

import com.vaadin.Application;
import com.vaadin.ui.*;

public class TestevaadinApplication extends Application {
	@Override
	public void init() {
		Window mainWindow = new Window("Testevaadin Application");
		Label label = new Label("Hello Vaadin user");
		mainWindow.addComponent(label);
		setMainWindow(mainWindow);
	}

}
