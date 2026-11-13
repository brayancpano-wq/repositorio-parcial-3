public class Postre extends Producto{
    public int pesoGramos;
    public double tempAlmacenamiento;
    public boolean determinarRefrigeracion;

    public Postre(int codigo, String descripcion, int nombre, int precioBase, int tiempoPreparacion, int pesoGramos, double tempAlmacenamiento, boolean determinarRefrigeracion) {
        super(codigo, descripcion, nombre, precioBase, tiempoPreparacion);
        this.pesoGramos = pesoGramos;
        this.tempAlmacenamiento = tempAlmacenamiento;
        this.determinarRefrigeracion = determinarRefrigeracion;
    }

    public int getPesoGramos() {
        return pesoGramos;
    }

    public void setPesoGramos(int pesoGramos) {
        this.pesoGramos = pesoGramos;
    }

    public double getTempAlmacenamiento() {
        return tempAlmacenamiento;
    }

    public void setTempAlmacenamiento(double tempAlmacenamiento) {
        this.tempAlmacenamiento = tempAlmacenamiento;
    }

    public boolean isDeterminarRefrigeracion() {
        return determinarRefrigeracion;
    }

    public void setDeterminarRefrigeracion(boolean determinarRefrigeracion) {
        this.determinarRefrigeracion = determinarRefrigeracion;
    }

    @Override
    public String toString() {
        return "Postre{" +
                "pesoGramos=" + pesoGramos +
                ", tempAlmacenamiento=" + tempAlmacenamiento +
                ", determinarRefrigeracion=" + determinarRefrigeracion +
                '}';
    }
}
