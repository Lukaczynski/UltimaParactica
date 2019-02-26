package pru.lukasz.base;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Circuito {
    private long id;
    private String nombre;
    private double longitud;
    private LocalDate fechaEstreno;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }


    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }



    @Override
    public String toString() {
        return id+" - "+nombre;
    }
}
