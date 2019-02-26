package pru.lukasz;

import pru.lukasz.login.Registro;
import pru.lukasz.mvc.Controller;
import pru.lukasz.mvc.Gui;
import pru.lukasz.mvc.Model;
import pru.lukasz.utils.Config;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {


        if(Registro.getInstance().authUser()) {
            try {
                Config.loadProperties();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("iniciando modelo...");
            Model model = new Model();
            System.out.println("iniciando gui...");
            Gui gui = new Gui();
            System.out.println("iniciando controlador...");
            new Controller(model, gui);
        }
    }
}
