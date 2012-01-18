package com.example.testevaadin.infra;

public abstract class Controller {
    
    private ViewManager viewManager;

    public ViewManager getViewManager() {
            return viewManager;
    }

    public void setViewManager(ViewManager viewManager) {
            this.viewManager = viewManager;
    }
    
    abstract public View init(ViewManager viewManager);

}