public class PedidoDomicilio extends Pedido{
    public double distanciaKm;

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

    public PedidoDomicilio(int codigo, String fecha, String cliente, ESTADOPEDIDO estadopedido, double distanciaKm) {
        super(codigo, fecha, cliente, estadopedido);
        this.distanciaKm = distanciaKm;




    }
}
