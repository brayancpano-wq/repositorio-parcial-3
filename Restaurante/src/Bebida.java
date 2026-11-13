public class Bebida extends Producto {
    public int volumenMililitros;
    public double temperaturaServicio;
    public boolean preparacionEspecial;


    public Bebida(int codigo, String descripcion, int nombre, int precioBase, int tiempoPreparacion, int volumenMililitros, double temperaturaServicio, boolean preparacionEspecial) {
        super(codigo, descripcion, nombre, precioBase, tiempoPreparacion);
        this.volumenMililitros = volumenMililitros;
        this.temperaturaServicio = temperaturaServicio;
        this.preparacionEspecial = preparacionEspecial;
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
