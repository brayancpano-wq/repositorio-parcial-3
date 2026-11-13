
public class EntregaSegura implements IServicioDomicilio {
    public String direccion;
    public int telefono;



        @Override
        public boolean aceptarEntrega(double distanciaKm) {
            // Acepta recorridos de hasta 20 kilómetros
            return distanciaKm <= 20;
        }

        @Override
        public double calcularCostoDomicilio(double distanciaKm) {
            // Cobra $7.000 más $1.000 por kilómetro
            double costoBase = 7000 + (1000 * distanciaKm);

            // Adiciona $3.000 cuando la distancia supera cinco kilómetros
            if (distanciaKm > 5) {
                costoBase = costoBase + 3000;
            }

            return costoBase;
        }

    }


