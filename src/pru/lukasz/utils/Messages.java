package pru.lukasz.utils;

import javax.swing.*;

public class Messages {
    public static void showError(String message){
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
