public class Bebida extends Producto {
    public int volumenMililitros;
    public double temperaturaServicio;
    public boolean preparacionEspecial;
    private TipoBebida tipoBebida;

    public Bebida(int codigo, String nombre, String descripcion, double precioBase, int tiempoPreparacion, int volumenMililitros, double temperaturaServicio, boolean preparacionEspecial) {
        super(codigo, descripcion, nombre, precioBase, tiempoPreparacion);
        this.volumenMililitros = volumenMililitros;
        this.temperaturaServicio = temperaturaServicio;
        this.preparacionEspecial = preparacionEspecial;
        this.tipoBebida = tipoBebida;
    }
//ss
    @Override
    public double calcularPrecioVenta() {
        // 1. Calculamos el incremento base del 10%
        double precioFinal = getPrecioBase() + (getPrecioBase() * 0.10);

        // 2. Validamos el recargo adicional de la preparación especial
        if (this.preparacionEspecial == true) { // preparacionEspecial según tu diagrama UML
            precioFinal = precioFinal + 3000.0;
        }

        return precioFinal;
    }

    @Override
    public boolean verificarCondicionesCalidad() {
        boolean volumenValido = this.volumenMililitros >= 150 && this.volumenMililitros <= 1500;
        boolean temperaturaValida = false;

        // Validamos la temperatura dependiendo del tipo de bebida
        if (this.tipoBebida == TipoBebida.FRIA) {
            temperaturaValida = this.temperaturaServicio >= 0 && this.temperaturaServicio <= 10;
        } else if (this.tipoBebida == TipoBebida.CALIENTE) {
            temperaturaValida = this.temperaturaServicio >= 55 && this.temperaturaServicio <= 85;
        }

        return volumenValido && temperaturaValida;
    }

    public int getVolumenMililitros() {
        return volumenMililitros;
    }

    public void setVolumenMililitros(int volumenMililitros) {
        this.volumenMililitros = volumenMililitros;
    }

    public double getTemperaturaServicio() {
        return temperaturaServicio;
    }

    public void setTemperaturaServicio(double temperaturaServicio) {
        this.temperaturaServicio = temperaturaServicio;
    }

    public boolean isPreparacionEspecial() {
        return preparacionEspecial;
    }

    public void setPreparacionEspecial(boolean preparacionEspecial) {
        this.preparacionEspecial = preparacionEspecial;
    }

    @Override
    public String toString() {
        return "Bebida{" +
                "volumenMililitros=" + volumenMililitros +
                ", temperaturaServicio=" + temperaturaServicio +
                ", preparacionEspecial=" + preparacionEspecial +
                '}';
    }
}
