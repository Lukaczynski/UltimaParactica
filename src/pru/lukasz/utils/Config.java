package pru.lukasz.utils;

import java.io.*;
import java.util.Properties;

public class Config {
    public static Properties settings;

    public static void loadProperties() throws IOException {
        System.out.print("cargando fichero");
        settings = new Properties();
        InputStream is = new FileInputStream("settings.properties");
        settings.load(is);
        System.out.println(" ends");
    }

    public static void saveProperties() throws IOException {
        OutputStream os = new FileOutputStream("settings.properties");
        settings.store(os, "Settings");
    }
}
