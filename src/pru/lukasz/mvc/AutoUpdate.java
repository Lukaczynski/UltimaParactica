package pru.lukasz.mvc;

import java.awt.*;

public class AutoUpdate extends Thread {

    public int delay=2000;
    public boolean keepUpdating = false;
    private Controller controler;



    public synchronized void setDelay(int delay) {
        this.delay = delay;
    }

    public synchronized void setKeepUpdating(boolean keepUpdating) {
        this.keepUpdating = keepUpdating;
    }

    public AutoUpdate(Controller controler) {
        this.controler = controler;
    }

    @Override
    public void run() {
        while(keepUpdating){
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("actualizando...");
            controler.gui.log.setText("Actualizacion en curso...");
            try {
                controler.updateLists();

            }catch (Exception e){
                controler.gui.log.setForeground(Color.RED);
                controler.gui.log.setText("Error al actualizar. Comprueba la conexion a la base de datos");

                keepUpdating=false;
            }
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(keepUpdating) {
                controler.gui.log.setForeground(Color.GRAY);
                controler.gui.log.setText("...");
            }
        }
        controler.gui.log.setForeground(Color.GRAY);
        controler.gui.log.setText("Autoupdate finished");

    }
}
