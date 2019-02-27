package pru.lukasz.mvc;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import pru.lukasz.base.*;
import java.util.*;

/**
 * Class responsible for storing and providing data stored in a database or mysql postgre
 *
 * @author lukasz kaczynski
 * @version 0.0.1
 * @since 0.0.1
 */
public class Model {

    private MongoDatabase db;
    MongoClient mongoClient;

    /**
     * Establishes the connection to the selected database
     *
     * @since 0.0.1
     */
    public void connect() {
        mongoClient = new MongoClient();
        mongoClient = new MongoClient( "localhost" , 27017 );
        db = mongoClient.getDatabase("baseDatos");
    }



    /**
     * Close the connection to the database
     *
     * @since 0.0.1
     */
    public void disconnect() {
        mongoClient.close();
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
       /* sesion.beginTransaction();
        sesion.saveOrUpdate(circuito);
        sesion.getTransaction().commit();*/
    }


    /**
     * Remove the specified Circuito
     *
     * @param circuito Circuito
     * @see Circuito
     * @since 0.0.1
     */
    public void deleteCircuito(Circuito circuito) {
        /*.beginTransaction();
        sesion.delete(circuito);
        sesion.getTransaction().commit();*/
    }

    /**
     * Returns the list of Circuito stored in the database
     *
     * @return List of Circuito
     * @see Circuito
     * @since 0.0.1
     */
    public List<Circuito> getCircuitos() {
        /*Query query = sesion.createQuery("FROM Circuito");
        return (List<Circuito>) query.list();*/
        return null;
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
        /*sesion.beginTransaction();
        sesion.saveOrUpdate(carrera);
        sesion.getTransaction().commit();*/
    }

    /**
     * Remove the specified Carrera
     *
     * @param carrera Carrera
     * @see Carrera
     * @since 0.0.1
     */
    public void deleteCarrera(Carrera carrera) {
        /*sesion.beginTransaction();
        sesion.delete(carrera);
        sesion.getTransaction().commit();*/
    }

    /**
     * Returns the list of Carrera stored in the database
     *
     * @return List of Carrera
     * @see Carrera
     * @since 0.0.1
     */
    public List<Carrera> getCarrera() {
        /*Query query = sesion.createQuery("FROM Carrera ");
        return (List<Carrera>) query.list();*/
        return null;
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
        /*sesion.beginTransaction();
        sesion.saveOrUpdate(coche);
        sesion.getTransaction().commit();
    }

    public void updateCoche(Coche coche) {
        sesion.beginTransaction();
        sesion.saveOrUpdate(coche);
        sesion.getTransaction().commit();*/
    }

    /**
     * Remove the specified Coche
     *
     * @param coche Coche
     * @see Coche
     * @since 0.0.1
     */
    public void deleteCoche(Coche coche) {
        /*sesion.beginTransaction();
        sesion.delete(coche);
        sesion.getTransaction().commit();*/
    }

    /**
     * Returns the list of Coche stored in the database
     *
     * @return List of Coche
     * @see Coche
     * @since 0.0.1
     */
    public List<Coche> getCoche() {
        /*Query query = sesion.createQuery("FROM Coche ");
        return (List<Coche>) query.list();*/
        return null;
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

        /*sesion.beginTransaction();
        sesion.saveOrUpdate(escuderia);
        sesion.getTransaction().commit();*/
    }

    /**
     * Remove the specified Escuderia
     *
     * @param escuderia Escuderia
     * @see Escuderia
     * @since 0.0.1
     */
    public void deleteEscuderia(Escuderia escuderia) {
        /*escuderia.setCoches(null);
        sesion.beginTransaction();
        sesion.delete(escuderia);
        sesion.getTransaction().commit();*/
    }

    /**
     * Returns the list of Escuderia stored in the database
     *
     * @return List of Escuderia
     * @see Escuderia
     * @since 0.0.1
     */
    public List<Escuderia> getEscuderia() {
        /*Query query = sesion.createQuery("FROM Escuderia ");
        return (List<Escuderia>) query.list();*/
        return null;
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
       /* sesion.beginTransaction();
        sesion.saveOrUpdate(patrocinador);
        sesion.getTransaction().commit();*/
    }

    /**
     * Remove the specified Patrocinador
     *
     * @param patrocinador Patrocinador
     * @see Patrocinador
     * @since 0.0.1
     */
    public void deletePatrocinador(Patrocinador patrocinador) {
/*
        patrocinador.getCoche().setPatrocinadores(null);

        sesion.beginTransaction();
        sesion.delete(patrocinador);
        sesion.getTransaction().commit();*/
    }

    /**
     * Returns the list of Patrocinador stored in the database
     *
     * @return List of Patrocinador
     * @see Patrocinador
     * @since 0.0.1
     */
    public List<Patrocinador> getPatrocinador() {
        /*Query query = sesion.createQuery("FROM Patrocinador ");
        return (List<Patrocinador>) query.list();*/
        return null;
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
/*
        sesion.beginTransaction();
        sesion.saveOrUpdate(piloto);
        sesion.getTransaction().commit();*/
    }

    /**
     * Remove the specified Piloto
     *
     * @param piloto Piloto
     * @see Piloto
     * @since 0.0.1
     */
    public void deletePiloto(Piloto piloto) {
        /*piloto.getCoche().setPilotos(null);
        sesion.beginTransaction();
        sesion.delete(piloto);
        sesion.getTransaction().commit();*/
    }

    /**
     * Returns the list of Piloto stored in the database
     *
     * @return List of Piloto
     * @see Piloto
     * @since 0.0.1
     */
    public List<Piloto> getPiloto() {
        /*Query query = sesion.createQuery("FROM Piloto ");
        return (List<Piloto>) query.list();*/
        return null;
    }

}