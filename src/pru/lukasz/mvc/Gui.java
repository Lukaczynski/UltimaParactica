package pru.lukasz.mvc;

import com.github.lgooddatepicker.components.DatePicker;
import pru.lukasz.base.*;
import pru.lukasz.login.User;

import javax.swing.*;

public class Gui {
    JPanel panel1;
    JList listCircuito;
    JTextField circuitoId;
    JTextField circuitoNombre;
    JTextField circuitoLongitud;
    DatePicker circuitoFecha;
    JButton circuitoClear;
    JButton circuitoDelete;
    JButton circuitoEdit;
    JButton circuitoAdd;
    JList listCircuitoCarreras;
    JList listCarrera;
    JTextField carreraId;
    JTextField carreraPremio;
    JTextField carreraVueltas;
    DatePicker carreraFecha;
    JComboBox carreraCircuito;
    JButton carreraShowDetail;
    JButton carreraClear;
    JButton carreraEdit;
    JButton carreraDelete;
    JButton carreraAdd;
    JList listPiloto;
    JComboBox pilotoCoche;
    DatePicker pilotoFecha;
    JTextField pilotoSalario;
    JTextField pilotoNombre;
    JTextField pilotoId;
    JList listPilotoCarrera;
    JList listCoches;
    JCheckBox cocheEstado;
    JTextField cocheId;
    JTextField cocheNumero;
    JTextField cocheColor;
    JComboBox cocheEscuderia;
    JButton pilotoClear;
    JButton pilotoEdit;
    JButton pilotoDelete;
    JButton pilotoAdd;
    JButton cocheClear;
    JButton cocheEdit;
    JButton cocheDelete;
    JButton cocheAdd;
    JButton cocheShowEscuderia;
    JList listEscuderia;
    JButton escuderiaClear;
    JButton escuderiaEdit;
    JButton escuderiaDelete;
    JButton escuderiaAdd;
    JTextField escuderiaId;
    JTextField escuderiaNombre;
    JTextField escuderiaPresdupuesto;
    DatePicker escuderiaFecha;
    JList listPatrocinador;
    JButton patrocinadorClear;
    JButton patrocinadorEdit;
    JButton patrocinadorDelete;
    JButton patrocinadorAdd;
    JTextField patrocinadorId;
    JTextField patrocinadorNombre;
    JTextField patrocinadorPresupuesto;
    DatePicker patrocinadorFecha;
    JComboBox patrocinadorCoche;
    JButton patrocinadorShowDetail;
    JLabel log;
    JList listCarreraPiloto;
    JButton pilotoShowDetail;
    JList listCochePatrocinador;
    JList listCochePiloto;
    JList listEscuderiaCoche;
    JList listRelationPiloto;
    JList listRelationCarrera;
    JList listRelationPilotoCarrera;
    JButton realationAddCarrera;
    JButton relationDeleteCarrera;
    JTabbedPane tabbedPane;
     JList listUser;
     JButton userAdd;
     JButton userDelete;
    JTextField username;
     JTextField userPassword;
     JComboBox dbType;
     JTextField dbupdateInterval;
     JTextField dbPassw;
     JTextField dbUser;
     JFormattedTextField dbIp;
     JFormattedTextField dbPort;
     JCheckBox dbUpdate;
     JButton settingsSave;
     JButton settingDisconnect;
     JButton settingsConnect;

    DefaultListModel<Carrera> modelCarrera;
    DefaultListModel<Circuito> modelCircuito;
    DefaultListModel<Coche> modelCoche;
    DefaultListModel<Escuderia> modelEscuderia;
    DefaultListModel<Patrocinador> modelPatrocinador;
    DefaultListModel<Piloto> modelPiloto;


    DefaultListModel<User> modelUsers;

    JFrame frame;

    public Gui() {
        frame = new JFrame("Vista");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initModels();
        initMenu();
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    void initModels() {
        modelUsers=new DefaultListModel<>();
        listUser.setModel(modelUsers);



        modelCarrera = new DefaultListModel<>();
        modelCircuito = new DefaultListModel<>();
        modelCoche = new DefaultListModel<>();
        modelEscuderia = new DefaultListModel<>();
        modelPatrocinador = new DefaultListModel<>();
        modelPiloto = new DefaultListModel<>();

        listCarrera.setModel(modelCarrera);
        listCircuito.setModel(modelCircuito);
        listCoches.setModel(modelCoche);
        listEscuderia.setModel(modelEscuderia);
        listPatrocinador.setModel(modelPatrocinador);
        listPiloto.setModel(modelPiloto);


    }

    void initMenu() {
    }


}
