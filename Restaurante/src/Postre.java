public class Postre extends Producto{
    public int pesoGramos;
    public double tempAlmacenamiento;
    public boolean determinarRefrigeracion;

    public Postre(int codigo, String nombre, String descripcion, double precioBase, int tiempoPreparacion, int pesoGramos, double tempAlmacenamiento, boolean determinarRefrigeracion) {
        super(codigo, descripcion, nombre, precioBase, tiempoPreparacion);
        this.pesoGramos = pesoGramos;
        this.tempAlmacenamiento = tempAlmacenamiento;
        this.determinarRefrigeracion = determinarRefrigeracion;

    }

    @Override
    public double calcularPrecioVenta() {
        double incrementoPorcentaje = 0.15; // 15% base

        // Si requiere refrigeración, el incremento total será del 20% (15% + 5%)
        if (this.determinarRefrigeracion == true) {
            incrementoPorcentaje = 0.20;
        }

        // Retornamos el precio base más el porcentaje calculado
        return getPrecioBase() + (getPrecioBase() * incrementoPorcentaje);
    }

    @Override
    public boolean verificarCondicionesCalidad() {
        boolean pesoValido = this.pesoGramos >= 80 && this.pesoGramos <= 500;
        boolean temperaturaValida = false;

        // Validamos la temperatura dependiendo de si es refrigerado o no
        if (this.determinarRefrigeracion == true) {
            temperaturaValida = this.tempAlmacenamiento >= 0 && this.tempAlmacenamiento <= 6;
        } else {
            temperaturaValida = this.tempAlmacenamiento >= 15 && this.tempAlmacenamiento <= 30;
        }

        return pesoValido && temperaturaValida;
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
