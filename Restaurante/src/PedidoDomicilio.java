public class PedidoDomicilio extends Pedido{
    public double distanciaKm;
    private IServicioDomicilio empresa;

    public PedidoDomicilio(int codigo, String fecha, Cliente cliente, EstadoPedido estado,
                           double distanciaKm, IServicioDomicilio empresaTransporte) {
        super(codigo, fecha, cliente, estado);
        this.distanciaKm = distanciaKm;
        this.empresa = empresa;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    @Override
    public String toString() {
        return "PedidoDomicilio{" +
                "distanciaKm=" + distanciaKm +
                '}';
    }

    @Override
    public double calcularTotal() {
        double totalProductos = super.calcularValorTotalProductos();
        double costoEnvio = empresa.calcularCostoDomicilio(this.distanciaKm);
        return totalProductos + costoEnvio;
    }

    /**
     * Confirma el pedido si cumple con las reglas del negocio.
     * @return true si se confirmó, false si fue rechazado por alguna regla.
     */
    public boolean confirmarPedido() {

        // 1. Verificar que el pedido tenga al menos un producto
        if (this.getListDetallePedido().isEmpty()) {
            return false;
        }

        // 2. Verificar que la empresa de transporte acepte el recorrido
        if (empresa.aceptarEntrega(this.distanciaKm) == false) {
            return false;
        }

        // 3. Confirmar y agregar al historial evitando duplicados
        if (this.getEstado() != EstadoPedido.CONFIRMADO) {
            this.setEstado(EstadoPedido.CONFIRMADO);
            this.getCliente().agregarPedido(this);
            return true;
        }

        return false;
    }

}

