
public class RapidoExpress implements IServicioDomicilio {
    public String direccion;
    public int telefono;

    @Override
    public boolean aceptarEntrega(double distanciaKm) {
        // Acepta recorridos de hasta 12 kilómetros
        return distanciaKm <= 12;
    }

    @Override
    public double calcularCostoDomicilio(double distanciaKm) {
        // Cobra una tarifa base de $5.000 más $1.500 por kilómetro
        return 5000 + (1500 * distanciaKm);
    }
}


