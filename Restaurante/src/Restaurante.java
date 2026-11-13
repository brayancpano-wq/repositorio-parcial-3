public class Restaurante {
    public String nombreComercial;
    public int NIT;
    public String direccion;
    public int telefono;

    public Restaurante(String nombreComercial, String direccion, int NIT, int telefono) {
        this.nombreComercial = nombreComercial;
        this.direccion = direccion;
        this.NIT = NIT;
        this.telefono = telefono;

    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public int getNIT() {
        return NIT;
    }

    public void setNIT(int NIT) {
        this.NIT = NIT;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Restaurante{" +
                "nombreComercial='" + nombreComercial + '\'' +
                ", NIT=" + NIT +
                ", direccion='" + direccion + '\'' +
                ", telefono=" + telefono +
                '}';
    }
}
