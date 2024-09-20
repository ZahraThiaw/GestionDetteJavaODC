package org.architecture;

import org.architecture.View.MainMenu;
import org.architecture.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;



public class App {
    public static void main(String[] args) {
        // Initialisation du contexte Spring
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Récupération du bean MainMenu et affichage du menu
        MainMenu menu = context.getBean(MainMenu.class);
        menu.show();
    }
}
