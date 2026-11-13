public class PlatoFuerte extends Producto{
    public int pesoGramos;
    public boolean controlCoccion;

    public PlatoFuerte(int codigo, String descripcion, int nombre, int precioBase, int tiempoPreparacion, int pesoGramos, boolean controlCoccion) {
        super(codigo, descripcion, nombre, precioBase, tiempoPreparacion);
        this.pesoGramos = pesoGramos;
        this.controlCoccion = controlCoccion;
    }

    public int getPesoGramos() {
        return pesoGramos;
    }

    public void setPesoGramos(int pesoGramos) {
        this.pesoGramos = pesoGramos;
    }

    public boolean isControlCoccion() {
        return controlCoccion;
    }

    public void setControlCoccion(boolean controlCoccion) {
        this.controlCoccion = controlCoccion;
    }

    @Override
    public String toString() {
        return "PlatoFuerte{" +
                "pesoGramos=" + pesoGramos +
                ", controlCoccion=" + controlCoccion +
                '}';
    }
}
