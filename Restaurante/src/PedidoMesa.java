public class PedidoMesa extends Pedido{
    public int numeroMesa;

    public PedidoMesa(int codigo, String fecha, Cliente cliente, EstadoPedido estadopedido, int numeroMesa) {
        super(codigo, fecha, cliente, estadopedido);
        this.numeroMesa = numeroMesa;

    }

    @Override
    public double calcularTotal() {
        return super.calcularValorTotalProductos();
    }

    /**
     * Confirma el pedido en mesa.
     * @return true si se confirmó, false si está vacío o ya estaba confirmado.
     */
    public boolean confirmarPedido() {

        // 1. Verificar que tenga productos
        if (this.getListDetallePedido().isEmpty()) {
            return false;
        }

        // 2. Confirmar y agregar al historial
        if (this.getEstado() != EstadoPedido.CONFIRMADO) {
            this.setEstado(EstadoPedido.CONFIRMADO);
            this.getCliente().agregarPedido(this);
            return true;
        }

        return false;
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
