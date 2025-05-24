package se.yrgo.client;

import org.springframework.context.annotation.*;

public class MainApp {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AppConfig.class);
        Client client = context.getBean(Client.class);
        client.run(); // ✅ Your app logic starts here
    }
}
