public class PlatoFuerte extends Producto{
    public int pesoGramos;
    public boolean controlCoccion;

    public PlatoFuerte(int codigo, String nombre, String descripcion, double precioBase, int tiempoPreparacion, int pesoGramos, boolean controlCoccion) {
        super(codigo, descripcion, nombre, precioBase, tiempoPreparacion);
        this.pesoGramos = pesoGramos;
        this.controlCoccion = controlCoccion;
    }

    @Override
    public double calcularPrecioVenta() {
        // Incremento del 20% (precioBase * 1.20)
        return getPrecioBase() + (getPrecioBase() * 0.20);
    }

    @Override
    public boolean verificarCondicionesCalidad() {
        boolean pesoValido = this.pesoGramos >= 200 && this.pesoGramos <= 900;
        boolean tiempoValido = getTiempoPreparacion() >= 10 && getTiempoPreparacion() <= 60;

        // Retorna true solo si TODAS las condiciones se cumplen
        return pesoValido && tiempoValido && this.controlCoccion;
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
