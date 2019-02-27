package pru.lukasz.mvc;

import pru.lukasz.base.*;
import pru.lukasz.login.Registro;
import pru.lukasz.login.User;
import pru.lukasz.utils.Config;
import pru.lukasz.utils.Messages;
import pru.lukasz.utils.Numeric;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.time.LocalDate;

public class Controller implements ListSelectionListener, WindowListener, ActionListener {
    private Model model;
    Gui gui;
    private boolean keepUpdated = false;
    private AutoUpdate update;
    public Controller(Model model, Gui gui) {
        this.model = model;
        this.gui = gui;
        gui.log.setForeground(Color.BLUE);
        gui.log.setText("Conectando con el servidor de la base de datos");
        System.out.println("conectando...");
        //System.out.println("test-> "+model.testConnection(true));
        model.connect();

        System.out.println("wait started");

        addButtonListeners();
        addListListeners();

        update = new AutoUpdate(this);
        update.setDelay(Integer.parseInt(Config.settings.getProperty("dbUpdateInterval","6000")));
        update.setKeepUpdating(Boolean.valueOf(Config.settings.getProperty("dbUpdate","true")));
        update.start();
        System.out.println("ya paso por el hilo");
        try {
            updateLists();
        }catch (Exception e ){
            gui.log.setForeground(Color.RED);
            gui.log.setText("Comprueba la conexion con la base de datos");
        }


        updateInfoSettings();
        gui.modelUsers.removeAllElements();
        for(User u: Registro.getInstance().getUserlist()){
            System.out.println(u.getUsername());
            gui.modelUsers.addElement(u);
        }
    }


    private void addButtonListeners() {
        gui.carreraDelete.addActionListener(this);
        gui.carreraClear.addActionListener(this);
        gui.carreraEdit.addActionListener(this);
        gui.carreraAdd.addActionListener(this);
        gui.carreraShowDetail.addActionListener(this);

        gui.circuitoDelete.addActionListener(this);
        gui.circuitoClear.addActionListener(this);
        gui.circuitoEdit.addActionListener(this);
        gui.circuitoAdd.addActionListener(this);

        gui.cocheDelete.addActionListener(this);
        gui.cocheClear.addActionListener(this);
        gui.cocheEdit.addActionListener(this);
        gui.cocheAdd.addActionListener(this);
        gui.cocheShowEscuderia.addActionListener(this);

        gui.escuderiaDelete.addActionListener(this);
        gui.escuderiaClear.addActionListener(this);
        gui.escuderiaEdit.addActionListener(this);
        gui.escuderiaAdd.addActionListener(this);

        gui.patrocinadorDelete.addActionListener(this);
        gui.patrocinadorClear.addActionListener(this);
        gui.patrocinadorEdit.addActionListener(this);
        gui.patrocinadorAdd.addActionListener(this);
        gui.patrocinadorShowDetail.addActionListener(this);

        gui.pilotoDelete.addActionListener(this);
        gui.pilotoClear.addActionListener(this);
        gui.pilotoEdit.addActionListener(this);
        gui.pilotoAdd.addActionListener(this);
        gui.pilotoShowDetail.addActionListener(this);

        gui.relationDeleteCarrera.addActionListener(this);
        gui.realationAddCarrera.addActionListener(this);

        gui.settingDisconnect.addActionListener(this);
        gui.settingsConnect.addActionListener(this);
        gui.settingsSave.addActionListener(this);

        gui.userAdd.addActionListener(this);
        gui.userDelete.addActionListener(this);
    }

    private void addListListeners() {
        gui.listPatrocinador.addListSelectionListener(this);
        gui.listEscuderia.addListSelectionListener(this);
        gui.listEscuderiaCoche.addListSelectionListener(this);
        gui.listPiloto.addListSelectionListener(this);
        gui.listCoches.addListSelectionListener(this);
        gui.listCochePatrocinador.addListSelectionListener(this);
        gui.listCochePiloto.addListSelectionListener(this);

        gui.listCircuito.addListSelectionListener(this);
        gui.listCarrera.addListSelectionListener(this);
        gui.listCarreraPiloto.addListSelectionListener(this);
        gui.listCircuitoCarreras.addListSelectionListener(this);
        gui.listPilotoCarrera.addListSelectionListener(this);

        gui.listRelationPiloto.addListSelectionListener(this);
        gui.listRelationCarrera.addListSelectionListener(this);

    }

    void updateLists() {
        updateListCircuito();
        updateListCarrera();
        updateListCoche();
        updateListEscuderia();
        updateListPatrocinador();
        updateListPiloto();

    }




    private void updateListCircuito() {
        gui.modelCircuito.clear();
        for (Circuito circuito : model.getCircuitos()) {
            gui.modelCircuito.addElement(circuito);
        }
    }

    private void updateListCarrera() {
        gui.modelCarrera.clear();
        for (Carrera carrera : model.getCarrera()) {
            gui.modelCarrera.addElement(carrera);
        }
    }

    private void updateListCoche() {
        gui.modelCoche.clear();
        for (Coche coche : model.getCoche()) {
            gui.modelCoche.addElement(coche);
        }
    }

    private void updateListEscuderia() {
        gui.modelEscuderia.clear();
        for (Escuderia escuderia : model.getEscuderia()) {
            gui.modelEscuderia.addElement(escuderia);
        }
    }

    private void updateListPatrocinador() {
        gui.modelPatrocinador.clear();
        for (Patrocinador patrocinador : model.getPatrocinador()) {
            gui.modelPatrocinador.addElement(patrocinador);
        }
    }

    private void updateListPiloto() {
        gui.modelPiloto.clear();
        for (Piloto piloto : model.getPiloto()) {
            gui.modelPiloto.addElement(piloto);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {

            if (e.getSource() == gui.listPiloto) {
                showInfoPiloto(gui.modelPiloto.getElementAt(gui.listPiloto.getSelectedIndex()));
            } else if (e.getSource() == gui.listCircuito) {
                showInfoCircuito(gui.modelCircuito.getElementAt(gui.listCircuito.getSelectedIndex()));
            } else if (e.getSource() == gui.listCarrera) {
                showInfoCarrera(gui.modelCarrera.getElementAt(gui.listCarrera.getSelectedIndex()));
            } else if (e.getSource() == gui.listCoches) {
                showInfoCoche(gui.modelCoche.getElementAt(gui.listCoches.getSelectedIndex()));
            } else if (e.getSource() == gui.listEscuderia) {
                showInfoEscuderia(gui.modelEscuderia.getElementAt(gui.listEscuderia.getSelectedIndex()));
            } else if (e.getSource() == gui.listPatrocinador) {
                showInfoPatrocinador(gui.modelPatrocinador.getElementAt(gui.listPatrocinador.getSelectedIndex()));
            } else if (e.getSource() == gui.listCircuitoCarreras) {
                gui.tabbedPane.setSelectedIndex(1);
                gui.listCarrera.setSelectedValue(gui.listCircuitoCarreras.getSelectedValue(), true);
                showInfoCarrera((Carrera) gui.listCircuitoCarreras.getSelectedValue());

            } else if (e.getSource() == gui.listCarreraPiloto) {
                gui.tabbedPane.setSelectedIndex(2);
                gui.listPiloto.setSelectedValue(gui.listCarreraPiloto.getSelectedValue(), true);
                showInfoPiloto((Piloto) gui.listCarreraPiloto.getSelectedValue());

            } else if (e.getSource() == gui.listPilotoCarrera) {
                gui.tabbedPane.setSelectedIndex(1);
                gui.listCarrera.setSelectedValue(gui.listPilotoCarrera.getSelectedValue(), true);
                showInfoCarrera((Carrera) gui.listPilotoCarrera.getSelectedValue());

            } else if (e.getSource() == gui.listCochePiloto) {
                gui.tabbedPane.setSelectedIndex(2);
                gui.listPiloto.setSelectedValue(gui.listCochePiloto.getSelectedValue(), true);
                showInfoPiloto((Piloto) gui.listCochePiloto.getSelectedValue());

            } else if (e.getSource() == gui.listCochePatrocinador) {
                gui.tabbedPane.setSelectedIndex(5);
                gui.listPatrocinador.setSelectedValue(gui.listCochePatrocinador.getSelectedValue(), true);
                showInfoPatrocinador((Patrocinador) gui.listCochePatrocinador.getSelectedValue());

            }else if (e.getSource() == gui.listEscuderiaCoche) {
                gui.tabbedPane.setSelectedIndex(3);
                gui.listCoches.setSelectedValue(gui.listEscuderiaCoche.getSelectedValue(), true);
                showInfoCoche((Coche) gui.listEscuderiaCoche.getSelectedValue());
            }
        }
    }


    private void showInfoCircuito(Circuito value) {
        gui.circuitoId.setText(String.valueOf(value.getId()));
        gui.circuitoFecha.setDate(value.getFechaEstreno());
        gui.circuitoLongitud.setText(String.valueOf(value.getLongitud()));
        gui.circuitoNombre.setText(value.getNombre());

    }

    private void showInfoCarrera(Carrera value) {
        gui.carreraId.setText(String.valueOf(value.getId()));
        gui.carreraFecha.setDate(value.getFecha());
        gui.carreraPremio.setText(String.valueOf(value.getPremio()));
        gui.carreraVueltas.setText(String.valueOf(value.getVueltas()));
    }

    private void showInfoPiloto(Piloto value) {
        gui.pilotoId.setText(String.valueOf(value.getId()));
        gui.pilotoNombre.setText(value.getNombre());
        gui.pilotoFecha.setDate(value.getFechaNacimiento());
        gui.pilotoSalario.setText(String.valueOf(value.getSalario()));


    }

    private void showInfoCoche(Coche value) {
        gui.cocheId.setText(String.valueOf(value.getId()));
        gui.cocheNumero.setText(String.valueOf(value.getId()));
        gui.cocheColor.setText(value.getColor());
        gui.cocheEstado.setSelected(value.getEstrellado() != 0);

    }

    private void showInfoEscuderia(Escuderia value) {
        gui.escuderiaId.setText(String.valueOf(value.getId()));
        gui.escuderiaNombre.setText(value.getNombre());
        gui.escuderiaPresdupuesto.setText(String.valueOf(value.getPresupuesto()));
        gui.escuderiaFecha.setDate(value.getFechaApertura());

    }

    private void showInfoPatrocinador(Patrocinador value) {
        gui.patrocinadorId.setText(String.valueOf(value.getId()));
        gui.patrocinadorNombre.setText(value.getNombre());
        gui.patrocinadorPresupuesto.setText(String.valueOf(value.getPresupuesto()));
        gui.patrocinadorFecha.setDate(value.getFechaContrato());
    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        model.disconnect();
        System.out.println("Hibernate connection terminate");
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "carrera_clear":
                clearCarrera();
                break;
            case "carrera_update":
                updateCarrera();
                break;
            case "carrera_remove":
                if (gui.listCarrera.getSelectedIndex() != -1 && !gui.carreraId.getText().isEmpty()) {
                    removeCarrera();
                } else {
                    Messages.showError("Selecione una carrera");
                }
                break;
            case "carrera_add":
                if (checkCarrera()) return;
                addCarrera();
                break;

            case "circuito_clear":
                clearCircuito();
                break;
            case "circuito_update":
                if (gui.listCircuito.getSelectedIndex() != -1 && !gui.circuitoId.getText().isEmpty()) {
                    updateCircuito();
                } else {
                    Messages.showError("Selecione un circuito");
                }
                break;
            case "circuito_remove":
                if (gui.listCircuito.getSelectedIndex() != -1 && !gui.circuitoId.getText().isEmpty()) {
                    removeCircuito();
                } else {
                    Messages.showError("Selecione un circuito");
                }

                break;
            case "circuito_add": {
                addCircuito();
                break;
            }

            case "coche_clear": {
                cocheClear();
                break;
            }

            case "coche_update":
                if (gui.listCoches.getSelectedIndex() != -1 && !gui.cocheId.getText().isEmpty()) {
                    if (!Numeric.isInt(gui.cocheNumero.getText())) {
                        Messages.showError("El numero tiene que ser un numero");
                        return;
                    }

                    if (gui.cocheColor.getText().isEmpty()) {
                        Messages.showError("El color es obligatorio");
                        return;
                    }
                    Coche coche = (Coche) gui.listCoches.getSelectedValue();
                    coche.setColor(gui.cocheColor.getText());
                    coche.setNumero(Numeric.getInt(gui.cocheNumero.getText()));
                    coche.setEstrellado(gui.cocheEstado.isSelected() ? (byte) 1 : (byte) 0);
                    model.addOrUpdateCoche(coche);
                    updateLists();

                } else {
                    Messages.showError("Selecione un circuito");
                }
                break;
            case "coche_remove":
                if (gui.listCoches.getSelectedIndex() != -1 && !gui.cocheId.getText().isEmpty()) {
                    Coche coche = (Coche) gui.listCoches.getSelectedValue();
                    model.deleteCoche(coche);
                    updateLists();
                } else {
                    Messages.showError("Selecione un coche");
                }
                break;
            case "coche_add":

                if (!Numeric.isInt(gui.cocheNumero.getText())) {
                    Messages.showError("El numero tiene que ser un numero");
                    return;
                }

                if (gui.cocheColor.getText().isEmpty()) {
                    Messages.showError("El color es obligatorio");
                    return;
                }
                Coche coche = new Coche();
                coche.setColor(gui.cocheColor.getText());
                coche.setNumero(Numeric.getInt(gui.cocheNumero.getText()));
                coche.setEstrellado(gui.cocheEstado.isSelected() ? (byte) 1 : (byte) 0);
                model.addOrUpdateCoche(coche);
                updateLists();

                break;

            case "escuderia_clear":
                gui.escuderiaId.setText("");
                gui.escuderiaNombre.setText("");
                gui.escuderiaPresdupuesto.setText("");
                gui.escuderiaFecha.setDate(LocalDate.now());
                break;
            case "escuderia_update":
                if (gui.listEscuderia.getSelectedIndex() != -1 && !gui.escuderiaId.getText().isEmpty()) {
                    if (gui.escuderiaNombre.getText().isEmpty()) {
                        Messages.showError("En nombre es obligatorio");
                        return;
                    }

                    if (Numeric.isDouble(gui.escuderiaPresdupuesto.getText())) {
                        Messages.showError("Presupuesto tiene que ser un numero");
                        return;
                    }
                    if (!gui.circuitoFecha.isTextFieldValid()) {
                        Messages.showError("La fecha tiene que ser valida");
                        return;
                    }
                    Escuderia escuderia = (Escuderia) gui.listEscuderia.getSelectedValue();
                    escuderia.setNombre(gui.escuderiaNombre.getText());
                    escuderia.setPresupuesto(Numeric.getDouble(gui.escuderiaPresdupuesto.getText()));
                    //escuderia.setCoche((Coche) gui.escuderiaCoche.getSelectedItem());
                    model.addOrUpdateEscuderia(escuderia);
                    updateLists();

                } else {
                    Messages.showError("Selecione una Escuderia");
                }
                break;
            case "escuderia_remove":
                if (gui.listEscuderia.getSelectedIndex() != -1 && !gui.escuderiaId.getText().isEmpty()) {
                    Escuderia escuderia = (Escuderia) gui.listEscuderia.getSelectedValue();
                    model.deleteEscuderia(escuderia);
                    updateLists();

                } else {
                    Messages.showError("Selecione una Escuderia");
                }
                break;
            case "escuderia_add":

                addEscuderia();

                break;

            case "patrocinador_clear":
                gui.patrocinadorId.setText("");
                gui.patrocinadorNombre.setText("");
                gui.patrocinadorPresupuesto.setText("");
                gui.patrocinadorFecha.setDate(LocalDate.now());
                break;
            case "patrocinador_update":
                if (gui.listPatrocinador.getSelectedIndex() != -1 && !gui.patrocinadorId.getText().isEmpty()) {
                    updatePatrocinador();
                } else {
                    Messages.showError("Selecione una Escuderia");
                }
                break;
            case "patrocinador_remove":

                if (gui.listPatrocinador.getSelectedIndex() != -1 && !gui.patrocinadorId.getText().isEmpty()) {
                    remocePatrocinador();
                } else {
                    Messages.showError("Selecione un Patrocinador");
                }
                break;
            case "patrocinador_add":
                addPatrocinador();

                break;

            case "piloto_clear":
                clearPiloto();
                break;
            case "piloto_update":
                if (gui.listPiloto.getSelectedIndex() != -1 && !gui.pilotoId.getText().isEmpty()) {
                    updatePiloto();
                } else {
                    Messages.showError("Selecione una Escuderia");
                }
                break;
            case "piloto_remove":
                if (gui.listPiloto.getSelectedIndex() != -1 && !gui.pilotoId.getText().isEmpty()) {
                    deletePiloto();
                } else {
                    Messages.showError("Selecione un Piloto");
                }
                break;
            case "piloto_add":
                if (gui.pilotoNombre.getText().isEmpty()) {
                    Messages.showError("En nombre es obligatorio");
                    return;
                }

                if (!Numeric.isDouble(gui.pilotoSalario.getText())) {
                    Messages.showError("salario tiene que ser un numero");
                    return;
                }
                if (!gui.pilotoFecha.isTextFieldValid() && gui.pilotoFecha.getText().isEmpty()) {
                    Messages.showError("La fecha tiene que ser valida");
                    return;
                }
                Piloto piloto = new Piloto();
                piloto.setNombre(gui.pilotoNombre.getText());
                piloto.setSalario(Numeric.getDouble(gui.pilotoSalario.getText()));
                piloto.setFechaNacimiento(gui.pilotoFecha.getDate());
                model.addOrUpdatePiloto(piloto);
                updateLists();

                break;


            case "settings_disconnect":
                model.disconnect();
                break;
            case "settings_connect":
                model.connect();
                updateLists();
                break;
            case "setting_save":
                System.out.println("guardando configuracion...");
                Config.settings.setProperty("dbIp",gui.dbIp.getText());
                Config.settings.setProperty("dbPort",gui.dbPort.getText());
                Config.settings.setProperty("dbUsername",gui.dbUser.getText());
                Config.settings.setProperty("dbPassw",gui.dbPassw.getText());
                Config.settings.setProperty("isMysql",gui.dbType.getSelectedIndex()==0?"true":"false");
                Config.settings.setProperty("dbUpdate",String.valueOf(gui.dbUpdate.isSelected()));
                Config.settings.setProperty("dbUpdateInterval",gui.dbupdateInterval.getText());
                try {
                update.setDelay(Integer.parseInt(gui.dbupdateInterval.getText()));
            }catch (Exception ed){
                    gui.log.setForeground(Color.RED);
                    gui.log.setText("El intervalo tiene que ser un entero "+ed.getMessage());
                }
                if(update.keepUpdating) {
                    update.setKeepUpdating(gui.dbUpdate.isSelected());
                }else{
                    if(gui.dbUpdate.isSelected()){
                        System.out.println("Ejecutando tarea programada...");
                        update = new AutoUpdate(this);
                        update.setDelay(Integer.parseInt(gui.dbupdateInterval.getText()));
                        update.setKeepUpdating(true);
                        update.start();
                    }
                }

                try {
                    Config.saveProperties();
                } catch (IOException e1) {
                    System.out.println("Error creando el archivp de propiedades"+e1.getMessage());
                    gui.log.setForeground(Color.RED);
                    gui.log.setText("Error creando el archivp de propiedades"+e1.getMessage());
                }
                break;
            case "userAdd":
                Registro.getInstance().addUser(gui.username.getText(),gui.userPassword.getText());
                gui.modelUsers.removeAllElements();
                for(User u: Registro.getInstance().getUserlist()){
                    gui.modelUsers.addElement(u);
                }
                break;
            case "userDelete":
                Registro.getInstance().removeUser((User) gui.listUser.getSelectedValue());
                gui.modelUsers.removeAllElements();
                for(User u: Registro.getInstance().getUserlist()){
                    gui.modelUsers.addElement(u);
                }

        }
    }


    private void updateInfoSettings() {

        gui.dbPassw.setText(Config.settings.getProperty("dbPassw",gui.dbPassw.getText()));
        gui.dbUser.setText(Config.settings.getProperty("dbUsername",gui.dbUser.getText()));
        gui.dbPort.setText(Config.settings.getProperty("dbPort",gui.dbPort.getText()));
        gui.dbIp.setText( Config.settings.getProperty("dbIp",gui.dbIp.getText()));
        gui.dbUpdate.setSelected(Config.settings.getProperty("dbUpdate","false").equalsIgnoreCase("true"));
        gui.dbType.setSelectedIndex(Config.settings.getProperty("isMysql","true").equalsIgnoreCase("true")?0:1) ;
        gui.dbupdateInterval.setText(Config.settings.getProperty("dbUpdateInterval","8000"));
    }

    private void addEscuderia() {
        if (gui.escuderiaNombre.getText().isEmpty()) {
            Messages.showError("En nombre es obligatorio");
            return;
        }

        if (!Numeric.isDouble(gui.escuderiaPresdupuesto.getText())) {
            Messages.showError("Presupuesto tiene que ser un numero");
            return;
        }
        if (!gui.escuderiaFecha.isTextFieldValid() && gui.escuderiaFecha.getText().isEmpty()) {
            Messages.showError("La fecha tiene que ser valida");
            return;
        }
        Escuderia escuderia = new Escuderia();
        escuderia.setNombre(gui.escuderiaNombre.getText());
        escuderia.setPresupuesto(Numeric.getDouble(gui.escuderiaPresdupuesto.getText()));
        escuderia.setFechaApertura(gui.escuderiaFecha.getDate());
        model.addOrUpdateEscuderia(escuderia);
        updateLists();

    }

    private void updatePatrocinador() {
        if (gui.patrocinadorNombre.getText().isEmpty()) {
            Messages.showError("En nombre es obligatorio");
            return;
        }

        if (!Numeric.isDouble(gui.patrocinadorPresupuesto.getText())) {
            Messages.showError("Presupuesto tiene que ser un numero");
            return;
        }
        if (!gui.patrocinadorFecha.isTextFieldValid() && gui.patrocinadorFecha.getText().isEmpty()) {
            Messages.showError("La fecha tiene que ser valida");
            return;
        }
        Patrocinador patrocinador = (Patrocinador) gui.listPatrocinador.getSelectedValue();
        patrocinador.setNombre(gui.patrocinadorNombre.getText());
        patrocinador.setPresupuesto(Numeric.getDouble(gui.patrocinadorPresupuesto.getText()));
        patrocinador.setFechaContrato(gui.patrocinadorFecha.getDate());

        model.addOrUpdatePatrocinador(patrocinador);
        updateLists();
    }

    private void deletePiloto() {
        Piloto piloto = (Piloto) gui.listPiloto.getSelectedValue();
        model.deletePiloto(piloto);
        updateLists();
    }

    private void addPatrocinador() {
        if (gui.patrocinadorNombre.getText().isEmpty()) {
            Messages.showError("En nombre es obligatorio");
            return;
        }

        if (!Numeric.isDouble(gui.patrocinadorPresupuesto.getText())) {
            Messages.showError("Presupuesto tiene que ser un numero");
            return;
        }
        if (!gui.patrocinadorFecha.isTextFieldValid() && gui.patrocinadorFecha.getText().isEmpty()) {
            Messages.showError("La fecha tiene que ser valida");
            return;
        }
        Patrocinador patrocinador = new Patrocinador();
        patrocinador.setNombre(gui.patrocinadorNombre.getText());
        patrocinador.setPresupuesto(Numeric.getDouble(gui.patrocinadorPresupuesto.getText()));
        patrocinador.setFechaContrato(gui.patrocinadorFecha.getDate());
        model.addOrUpdatePatrocinador(patrocinador);
        updateLists();
    }

    private void remocePatrocinador() {
        Patrocinador patrocinador = (Patrocinador) gui.listPatrocinador.getSelectedValue();
        model.deletePatrocinador(patrocinador);
        updateLists();
    }

    private void updatePiloto() {
        if (gui.pilotoNombre.getText().isEmpty()) {
            Messages.showError("En nombre es obligatorio");
            return;
        }

        if (!Numeric.isDouble(gui.pilotoSalario.getText())) {
            Messages.showError("salario tiene que ser un numero");
            return;
        }
        if (!gui.pilotoFecha.isTextFieldValid() && gui.pilotoFecha.getText().isEmpty()) {
            Messages.showError("La fecha tiene que ser valida");
            return;
        }
        Piloto piloto = (Piloto) gui.listPiloto.getSelectedValue();


        piloto.setNombre(gui.pilotoNombre.getText());
        piloto.setSalario(Numeric.getDouble(gui.pilotoSalario.getText()));
        piloto.setFechaNacimiento(gui.pilotoFecha.getDate());

        model.addOrUpdatePiloto(piloto);
        updateLists();

    }

    private void clearPiloto() {
        gui.pilotoId.setText("");
        gui.pilotoNombre.setText("");
        gui.pilotoSalario.setText("");
        gui.pilotoFecha.setDate(LocalDate.now());

    }

    private void cocheClear() {
        gui.cocheId.setText("");
        gui.cocheNumero.setText("");
        gui.cocheColor.setText("");
        gui.cocheEstado.setSelected(false);

    }

    private void addCircuito() {
        if (!Numeric.isDouble(gui.circuitoLongitud.getText())) {
            Messages.showError("La longitud tiene que ser un numero");
            return;
        }
        if (!gui.circuitoFecha.isTextFieldValid()) {
            Messages.showError("La fecha tiene que ser valida");
            return;
        }
        Circuito circuito = new Circuito();
        circuito.setNombre(gui.circuitoNombre.getText());
        circuito.setLongitud(Numeric.getDouble(gui.circuitoLongitud.getText()));
        circuito.setFechaEstreno(gui.circuitoFecha.getDate());
        model.addOrUpdateCircuito(circuito);
        updateLists();

    }

    private void removeCircuito() {
        Circuito circuito = (Circuito) gui.listCircuito.getSelectedValue();

        model.deleteCircuito(circuito);
        updateLists();
    }

    private void updateCircuito() {
        if (gui.circuitoNombre.getText().isEmpty()) {
            Messages.showError("Seleciona el circuito a actualizar");
            return;
        }
        if (!Numeric.isDouble(gui.circuitoLongitud.getText())) {
            Messages.showError("La longitud tiene que ser un numero");
            return;
        }
        if (!gui.circuitoFecha.isTextFieldValid()) {
            Messages.showError("La fecha tiene que ser valida");
            return;
        }
        Circuito circuito = gui.modelCircuito.get(gui.listCircuito.getSelectedIndex());
        circuito.setNombre(gui.circuitoNombre.getText());
        circuito.setLongitud(Numeric.getDouble(gui.circuitoLongitud.getText()));
        circuito.setFechaEstreno(gui.circuitoFecha.getDate());
        model.addOrUpdateCircuito(circuito);
        updateLists();

    }

    private void clearCircuito() {
        gui.circuitoId.setText("");
        gui.circuitoNombre.setText("");
        gui.circuitoLongitud.setText("");
        gui.circuitoFecha.setDate(LocalDate.now());

    }

    private void clearCarrera() {
        gui.carreraId.setText("");
        gui.carreraPremio.setText("");
        gui.carreraVueltas.setText("");
        gui.carreraFecha.setDate(LocalDate.now());

    }

    private void addCarrera() {
        Carrera carrera = new Carrera();
        carrera.setPremio(Numeric.getDouble(gui.carreraPremio.getText()));
        carrera.setVueltas(Numeric.getInt(gui.carreraVueltas.getText()));
        carrera.setFecha(gui.carreraFecha.getDate());


        model.addOrUpdateCarrera(carrera);
        updateLists();

    }

    private void removeCarrera() {
        Carrera carrera = (Carrera) gui.listCarrera.getSelectedValue();
        model.deleteCarrera(carrera);
        updateLists();

    }

    private void updateCarrera() {
        if (gui.listCarrera.getSelectedIndex() != -1 && !gui.carreraId.getText().isEmpty()) {

            if (checkCarrera()) return;

            Carrera carrera = (Carrera) gui.listCarrera.getSelectedValue();

            carrera.setPremio(Numeric.getDouble(gui.carreraPremio.getText()));
            carrera.setVueltas(Numeric.getInt(gui.carreraVueltas.getText()));
            carrera.setFecha(gui.carreraFecha.getDate());


            model.addOrUpdateCarrera(carrera);
            updateLists();
        } else {
            Messages.showError("Selecione una carrera");
        }
    }

    private boolean checkCarrera() {
        if (!Numeric.isDouble(gui.carreraPremio.getText())) {
            Messages.showError("El premio tiene que ser un numero");
            return true;
        }
        if (!Numeric.isInt(gui.carreraVueltas.getText())) {
            Messages.showError("Las vueltas tiene que ser un numero");
            return true;
        }
        if (!gui.carreraFecha.isTextFieldValid() && gui.carreraFecha.getText().isEmpty()) {
            Messages.showError("La fecha tiene que ser valida");
            return true;
        }
        return false;
    }
}
