package pru.lukasz.base;




public class Coche {
    private long id;
    private int numero;
    private String color;
    private byte estrellado;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public byte getEstrellado() {
        return estrellado;
    }

    public void setEstrellado(byte estrellado) {
        this.estrellado = estrellado;
    }


    @Override
    public String toString() {
        return id+" - Numero: "+numero;
    }
}
