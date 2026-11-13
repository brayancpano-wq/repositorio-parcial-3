public abstract class Pedido {
    public int codigo;
    public String fecha;
    public String cliente;
    ESTADOPEDIDO estadopedido;


    public Pedido(int codigo, String fecha, String cliente, ESTADOPEDIDO estadopedido) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.cliente = cliente;
        this.estadopedido = estadopedido;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public ESTADOPEDIDO getEstadopedido() {
        return estadopedido;
    }

    public void setEstadopedido(ESTADOPEDIDO estadopedido) {
        this.estadopedido = estadopedido;
    }

    @Override
    public String toString() {
        return "pedido{" +
                "codigo=" + codigo +
                ", fecha='" + fecha + '\'' +
                ", cliente='" + cliente + '\'' +
                ", estadopedido=" + estadopedido +
                '}';
    }
}
