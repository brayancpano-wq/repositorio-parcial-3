public class PedidoMesa extends Pedido{
    public int numeroMesa;

    public PedidoMesa(int codigo, String fecha, String cliente, ESTADOPEDIDO estadopedido, int numeroMesa) {
        super(codigo, fecha, cliente, estadopedido);
        this.numeroMesa = numeroMesa;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    @Override
    public String toString() {
        return "PedidoMesa{" +
                "numeroMesa=" + numeroMesa +
                '}';
    }
}
