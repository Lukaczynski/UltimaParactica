package pru.lukasz.mvc;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import pru.lukasz.base.*;
import pru.lukasz.utils.Config;
import pru3.lukasz.base.*;

import java.sql.*;
import java.util.*;
import java.util.concurrent.Executor;


import org.hibernate.boot.spi.MetadataImplementor;

/**
 * Class responsible for storing and providing data stored in a database or mysql postgre
 *
 * @author lukasz kaczynski
 * @version 0.0.1
 * @since 0.0.1
 */
public class Model {

    private MongoDatabase db;


    /**
     * Establishes the connection to the selected database
     *
     * @since 0.0.1
     */
    public void connect() {
        MongoClient mongoClient = new MongoClient();
         db = mongoClient.getDatabase("baseDatos");
    }

    public boolean testConnection(boolean mysql) {
        if (mysql) {

            String jdbcUrl = "jdbc:mysql://" + Config.settings.getProperty("dbIp", "localhost") + ":" + Config.settings.getProperty("dbPort", "3306") + "/";
            try {
                //Class.forName("com.mysql.jdbc.Driver");
                System.out.println(jdbcUrl);
                Executor sd = new Executor() {
                    @Override
                    public void execute(Runnable command) {

                    }
                };
                Connection conn = DriverManager.getConnection(jdbcUrl,
                        Config.settings.getProperty("dbUsername", "root"),
                        Config.settings.getProperty("dbPassw", ""));
                conn.setNetworkTimeout(sd, 100);
                conn.close();
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                return false;
            }
        } else {
            try {
                System.out.print("comprobando conexion postgre...");
                Connection c = DriverManager.getConnection("jdbc:postgresql://" + Config.settings.getProperty("dbIp", "localhost") + ":" + Config.settings.getProperty("dbPort", "3306") + "/", Config.settings.getProperty("dbUsername", "root"), Config.settings.getProperty("dbPassw", ""));
                Statement statement = c.createStatement();
                ResultSet rs = statement.executeQuery("select datname from pg_database");
                boolean existe = false;
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                    if (rs.getString(1).equalsIgnoreCase("pru3lukasz")) {
                        existe = true;
                        break;
                    }
                }

                if (!existe) {
                    statement.executeUpdate("CREATE DATABASE pru3lukasz;");
                }
                System.out.println("\tok");
                c.close();
                return true;
            } catch (Exception e) {
                System.out.println("\tError");
                System.out.println(e.getMessage());
                e.printStackTrace();
                return false;
            }

        }

    }

    /**
     * Returns the basic status of the connection
     *
     * @return boolean | <code>true</code> for established connection and <code>false</code> if there is no connection.
     * @since 0.0.1
     */
    public boolean getSesion() {
        return sesion != null && sesion.isConnected();
    }

    /**
     * Close the connection to the database
     *
     * @since 0.0.1
     */
    public void disconnect() {
        if (sesion != null) {
            sesion.close();
        }
        if (factory != null) {
            factory.close();
        }
        System.out.println("Desconectado de lservidor");
    }

    ///////////////////////// CIRCUITO /////////////////////////////////////////////////////////////////////////////////

    /**
     * Create a new circuit
     *
     * @param circuito Circuito
     * @see Circuito
     * @see Carrera
     * @since 0.0.1
     */
    public void addOrUpdateCircuito(Circuito circuito) {
        sesion.beginTransaction();
        sesion.saveOrUpdate(circuito);
        sesion.getTransaction().commit();
    }


    /**
     * Remove the specified Circuito
     *
     * @param circuito Circuito
     * @see Circuito
     * @since 0.0.1
     */
    public void deleteCircuito(Circuito circuito) {
        sesion.beginTransaction();
        sesion.delete(circuito);
        sesion.getTransaction().commit();
    }

    /**
     * Returns the list of Circuito stored in the database
     *
     * @return List of Circuito
     * @see Circuito
     * @since 0.0.1
     */
    public List<Circuito> getCircuitos() {
        Query query = sesion.createQuery("FROM Circuito");
        return (List<Circuito>) query.list();
    }

    ///////////////////////// CARRERA //////////////////////////////////////////////////////////////////////////////////

    /**
     * Create or Update if exists a Carrera
     *
     * @param carrera Carrera
     * @see Carrera
     * @see Circuito
     * @since 0.0.1
     */
    public void addOrUpdateCarrera(Carrera carrera) {
        sesion.beginTransaction();
        sesion.saveOrUpdate(carrera);
        sesion.getTransaction().commit();
    }

    /**
     * Remove the specified Carrera
     *
     * @param carrera Carrera
     * @see Carrera
     * @since 0.0.1
     */
    public void deleteCarrera(Carrera carrera) {
        sesion.beginTransaction();
        sesion.delete(carrera);
        sesion.getTransaction().commit();
    }

    /**
     * Returns the list of Carrera stored in the database
     *
     * @return List of Carrera
     * @see Carrera
     * @since 0.0.1
     */
    public List<Carrera> getCarrera() {
        Query query = sesion.createQuery("FROM Carrera ");
        return (List<Carrera>) query.list();
    }

    ///////////////////////// COCHE ////////////////////////////////////////////////////////////////////////////////////

    /**
     * Create a new Carrera
     *
     * @param coche Coche
     * @see Escuderia
     * @see Piloto
     * @see Patrocinador
     * @see Carrera
     * @since 0.0.1
     */
    public void addOrUpdateCoche(Coche coche) {
        sesion.beginTransaction();
        sesion.saveOrUpdate(coche);
        sesion.getTransaction().commit();
    }

    public void updateCoche(Coche coche) {
        sesion.beginTransaction();
        sesion.saveOrUpdate(coche);
        sesion.getTransaction().commit();
    }

    /**
     * Remove the specified Coche
     *
     * @param coche Coche
     * @see Coche
     * @since 0.0.1
     */
    public void deleteCoche(Coche coche) {
        sesion.beginTransaction();
        sesion.delete(coche);
        sesion.getTransaction().commit();
    }

    /**
     * Returns the list of Coche stored in the database
     *
     * @return List of Coche
     * @see Coche
     * @since 0.0.1
     */
    public List<Coche> getCoche() {
        Query query = sesion.createQuery("FROM Coche ");
        return (List<Coche>) query.list();
    }

    ///////////////////////// ESCUDERIA ////////////////////////////////////////////////////////////////////////////////

    /**
     * Create a new Escuderia
     *
     * @param escuderia Escuderia
     * @see Escuderia
     * @see Coche
     * @since 0.0.1
     */
    public void addOrUpdateEscuderia(Escuderia escuderia) {

        sesion.beginTransaction();
        sesion.saveOrUpdate(escuderia);
        sesion.getTransaction().commit();
    }

    /**
     * Remove the specified Escuderia
     *
     * @param escuderia Escuderia
     * @see Escuderia
     * @since 0.0.1
     */
    public void deleteEscuderia(Escuderia escuderia) {
        escuderia.setCoches(null);
        sesion.beginTransaction();
        sesion.delete(escuderia);
        sesion.getTransaction().commit();
    }

    /**
     * Returns the list of Escuderia stored in the database
     *
     * @return List of Escuderia
     * @see Escuderia
     * @since 0.0.1
     */
    public List<Escuderia> getEscuderia() {
        Query query = sesion.createQuery("FROM Escuderia ");
        return (List<Escuderia>) query.list();
    }

    ///////////////////////// PATROCINADOR /////////////////////////////////////////////////////////////////////////////

    /**
     * Create a new Patrocinador
     *
     * @param patrocinador Patrrocinador
     * @see Patrocinador
     * @see Coche
     * @since 0.0.1
     */
    public void addOrUpdatePatrocinador(Patrocinador patrocinador) {
        sesion.beginTransaction();
        sesion.saveOrUpdate(patrocinador);
        sesion.getTransaction().commit();
    }

    /**
     * Remove the specified Patrocinador
     *
     * @param patrocinador Patrocinador
     * @see Patrocinador
     * @since 0.0.1
     */
    public void deletePatrocinador(Patrocinador patrocinador) {

        patrocinador.getCoche().setPatrocinadores(null);

        sesion.beginTransaction();
        sesion.delete(patrocinador);
        sesion.getTransaction().commit();
    }

    /**
     * Returns the list of Patrocinador stored in the database
     *
     * @return List of Patrocinador
     * @see Patrocinador
     * @since 0.0.1
     */
    public List<Patrocinador> getPatrocinador() {
        Query query = sesion.createQuery("FROM Patrocinador ");
        return (List<Patrocinador>) query.list();
    }

    ///////////////////////// PILOTO ///////////////////////////////////////////////////////////////////////////////////


    /**
     * Update an existing Piloto
     *
     * @param piloto Piloto
     * @see Piloto
     * @since 0.0.1
     */
    public void addOrUpdatePiloto(Piloto piloto) {

        sesion.beginTransaction();
        sesion.saveOrUpdate(piloto);
        sesion.getTransaction().commit();
    }

    /**
     * Remove the specified Piloto
     *
     * @param piloto Piloto
     * @see Piloto
     * @since 0.0.1
     */
    public void deletePiloto(Piloto piloto) {
        piloto.getCoche().setPilotos(null);
        sesion.beginTransaction();
        sesion.delete(piloto);
        sesion.getTransaction().commit();
    }

    /**
     * Returns the list of Piloto stored in the database
     *
     * @return List of Piloto
     * @see Piloto
     * @since 0.0.1
     */
    public List<Piloto> getPiloto() {
        Query query = sesion.createQuery("FROM Piloto ");
        return (List<Piloto>) query.list();
    }

}