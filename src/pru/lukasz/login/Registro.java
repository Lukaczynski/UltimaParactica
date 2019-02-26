package pru.lukasz.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Registro implements ActionListener {

    private ArrayList<User> usuarios;

    private static Registro  registro = null;
    private LoginDialog logindialog;
    private boolean auth=false;

    public static Registro getInstance(){
        if(registro==null){
            registro = new Registro();
        }
        return registro;
    }

    private Registro() {
        try {
            readFile();
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo...");
            usuarios = new ArrayList<>();
            usuarios.add(new User("",""));
        } catch (ClassNotFoundException e) {
            System.out.println("Error leyendo el archivo...");
            usuarios = new ArrayList<>();
            usuarios.add(new User("",""));
        }

    }

    public static boolean authUser(){
        registro.auth();
        return registro.auth;
    }

    private void auth(){
        logindialog = new LoginDialog(null);

        logindialog.setActionListener(this);
        logindialog.setVisible(true);
    }

    private void readFile() throws IOException, ClassNotFoundException {
        System.out.println("leyendo fichero");
        File data = new File("data.bin");
        if(data.exists()){
            FileInputStream fis = new FileInputStream(data.getAbsolutePath());
            ObjectInputStream ois = new ObjectInputStream(fis);
            usuarios = (ArrayList<User>) ois.readObject();
            ois.close();
        }else{
            usuarios = new ArrayList<>();
            usuarios.add(new User("",""));
            writeFile();
        }
    }

    private void  writeFile() throws IOException {
        System.out.println("Escribiendo fichero");
        if(usuarios!=null && !usuarios.isEmpty()){
            File data = new File("data.bin");
            FileOutputStream fis = new FileOutputStream(data.getAbsolutePath());
            ObjectOutputStream ois = new ObjectOutputStream(fis);
            ois.writeObject(usuarios);
            ois.close();
        }else{
            System.err.println("Usuarios inexistentes no se crea el archivo");
        }
    }
    public void addUser(String nombre, String passw){
        usuarios.add(new User(nombre,passw));
        try {
            writeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeUser(User u){
        usuarios.remove(u);
        try {
            writeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> getUserlist(){
        return usuarios;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if("Login".equalsIgnoreCase(e.getActionCommand())){
            for(User u : usuarios){
                if(u.getPassword().equalsIgnoreCase(logindialog.getPassword()) && u.getUsername().equalsIgnoreCase(logindialog.getUsername())){
                    auth=true;
                    logindialog.dispose();
                    return;
                }
            }
        }
        if("Cancel".equalsIgnoreCase(e.getActionCommand())){
            auth=false;
        }
    }
}
