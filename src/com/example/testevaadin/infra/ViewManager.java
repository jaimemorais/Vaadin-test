package com.example.testevaadin.infra;

import com.vaadin.ui.Window;

public interface ViewManager {
        void close(View view);
        void showMsg(String msg);
        void showError(String error);
        Window getWindow();
}