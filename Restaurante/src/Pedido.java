import java.util.ArrayList;
import java.util.List;

public abstract class Pedido {
    public int codigo;
    public String fecha;
    Cliente cliente;
    EstadoPedido estado;
    private List<DetallePedido> listDetallePedido;

    public abstract double calcularTotal();

    public Pedido(int codigo, String fecha, Cliente cliente, EstadoPedido estadopedido) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.cliente = cliente;
        this.estado = estadopedido;
        this.listDetallePedido = new ArrayList<>();
    }

    public List<DetallePedido> getListDetallePedido() {
        return listDetallePedido;
    }
    /**
     * Agrega un producto al pedido verificando primero sus estándares de calidad.
     * @param producto El producto a agregar (PlatoFuerte, Bebida o Postre).
     * @param cantidad La cantidad deseada.
     * @return true si cumplió la calidad y se agregó, false si fue rechazado.
     */
    public boolean agregarProductoAlPedido(Producto producto, int cantidad) {
        // Verificamos si pasa la prueba de calidad
        if (producto.verificarCondicionesCalidad()) {
            // Si cumple, creamos el detalle (record) y lo agregamos a la lista
            DetallePedido detalle = new DetallePedido(producto, cantidad);
            this.listDetallePedido.add(detalle);
            return true; // Se agregó exitosamente
        }
        // Si no cumple las condiciones de calidad
        return false;
    }

    public abstract boolean confirmarPedido();

    /**
     * Calcula el valor total de todos los productos en el pedido,
     * multiplicando la cantidad por el precio de venta de cada uno.
     * @return El subtotal a pagar solo por los productos.
     */
    public double calcularValorTotalProductos() {
        double subtotal = 0.0;

        // Recorremos la lista de detalles
        for (DetallePedido detalle : listDetallePedido) {

            // Obtenemos el producto y la cantidad directamente del record
            Producto producto = detalle.producto();
            int cantidad = detalle.cantidad();

            // Sumamos al acumulador aplicando el polimorfismo del cálculo del precio
            subtotal = subtotal + (producto.calcularPrecioVenta() * cantidad);
        }

        return subtotal;
    }

    /**
     * Marca un pedido como entregado.
     * Regla de negocio: Solo un pedido previamente CONFIRMADO puede ser entregado.
     * @return true si se actualizó el estado, false si no cumplía la condición.
     */
    public boolean entregarPedido() {
        // Validamos que el pedido esté confirmado antes de entregarlo
        if (this.estado == EstadoPedido.CONFIRMADO) {
            this.estado = EstadoPedido.ENTREGADO;
            return true;
        }

        // Si estaba pendiente o cancelado, no se puede entregar
        return false;
    }

    /**
     * Marca un pedido como cancelado.
     * Regla de negocio: Un pedido ya entregado o cancelado no debería volver a cancelarse.
     * @return true si se canceló exitosamente, false en caso contrario.
     */
    public boolean cancelarPedido() {
        // Validamos que no esté ya entregado ni cancelado
        if (this.estado != EstadoPedido.ENTREGADO && this.estado != EstadoPedido.CANCELADO) {
            this.estado = EstadoPedido.CANCELADO;
            return true;
        }

        return false;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {}



    public void setEstadopedido(EstadoPedido estadopedido) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "pedido{" +
                "codigo=" + codigo +
                ", fecha='" + fecha + '\'' +
                ", cliente='" + cliente + '\'' +
                ", estado=" + estado +
                '}';
    }
}
