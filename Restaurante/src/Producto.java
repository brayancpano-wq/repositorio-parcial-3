public abstract class Producto {
    public int codigo;
    public String nombre;
    public String descripcion;
    public double precioBase;
    public int tiempoPreparacion;

    public abstract double calcularPrecioVenta();
    public abstract boolean verificarCondicionesCalidad();

    public Producto(int codigo, String descripcion, String nombre, double precioBase, int tiempoPreparacion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.tiempoPreparacion = tiempoPreparacion;

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(int precioBase) {
        this.precioBase = precioBase;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(int tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo=" + codigo +
                ", nombre=" + nombre +
                ", descripcion='" + descripcion + '\'' +
                ", precioBase=" + precioBase +
                ", tiempoPreparacion=" + tiempoPreparacion +
                '}';
    }
}
