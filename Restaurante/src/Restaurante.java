import java.util.ArrayList;
import java.util.List;

public class Restaurante {
    public String nombreComercial;
    public int nit;
    public String direccion;
    public int telefono;
    private List<Producto> listProducto;
    private List<Cliente> listCliente;
    private List<Pedido> listPedido;

    public Restaurante(String nombreComercial, String direccion, int nit, int telefono) {
        this.nombreComercial = nombreComercial;
        this.direccion = direccion;
        this.nit = nit;
        this.telefono = telefono;
        this.listProducto = new ArrayList<>();
        this.listCliente = new ArrayList<>();
        this.listPedido = new ArrayList<>();
    }

    public boolean registrarCliente(Cliente cliente) {
        if (cliente == null) {
            return false;
        }
        for (Cliente c : listCliente) {
            if (c.getDocumento().equals(cliente.getDocumento())) {
                return false; // Ya existe un cliente con ese documento
            }
        }
        listCliente.add(cliente);
        return true;
    }

    public List<Pedido> consultarHistorialCliente(String documento) {
        // Asumiendo que usamos la lógica integrada que hicimos antes
        for (Cliente cliente : listCliente) { // listCliente según el diagrama
            if (cliente.getDocumento().equals(documento)) {
                // El cliente ya tiene su lista lista, solo se la pedimos
                return cliente.consultarHistorialCompras();
            }
        }
        return new ArrayList<>();
    }


    // Método para buscar si un producto ya existe por su código
    public Producto buscarProducto(int codigo) {
        for (Producto p : listProducto) {
            if (p.getCodigo() == codigo) {
                return p; //
            }
        }
        return null;
    }

    // Método para buscar si un cliente ya existe por su documento
    public Cliente buscarCliente(String documento) {
        for (Cliente c : listCliente) {
            if (c.getDocumento().equals(documento)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Valida todas las reglas de negocio y confirma un pedido en el sistema.
     *
     * @param pedido El pedido (Mesa o Domicilio) a confirmar.
     * @return true si se confirmó y registró exitosamente, false si falló alguna validación.
     */
    public boolean procesarConfirmacionPedido(Pedido pedido) {

        // Seguridad básica
        if (pedido == null) {
            return false;
        }

        // 1. Evitar confirmaciones repetidas
        if (pedido.getEstado() == EstadoPedido.CONFIRMADO) {
            return false;
        }

        // 2. Evitar códigos duplicados en el historial del restaurante
        for (Pedido pedidoExistente : listPedido) {
            if (pedidoExistente.getCodigo() == pedido.getCodigo()) {
                return false;
            }
        }

        // 3. Verificar que el cliente esté registrado en el sistema
        boolean clienteRegistrado = false;
        for (Cliente c : listCliente) {
            if (c.getDocumento().equals(pedido.getCliente().getDocumento())) {
                clienteRegistrado = true;
                break;
            }
        }
        if (clienteRegistrado == false) {
            return false;
        }

        // 4. Verificar los productos del pedido
        for (DetallePedido detalle : pedido.getListDetallePedido()) {

            // 4.1 Cantidades mayores que cero
            if (detalle.cantidad() <= 0) {
                return false;
            }

            // 4.2 Cumplen condiciones de calidad
            if (detalle.producto().verificarCondicionesCalidad() == false) {
                return false;
            }

            // 4.3 Pertenecen al menú del restaurante
            boolean enMenu = false;
            for (Producto prodMenu : listProducto) {
                if (prodMenu.getCodigo() == detalle.producto().getCodigo()) {
                    enMenu = true;
                    break;
                }
            }
            if (enMenu == false) {
                return false;
            }
        }
        return pedido.confirmarPedido();
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    /**
     * Retorna los productos del menú que cumplen con los estándares de calidad.
     */
    public List<Producto> consultarProductosDisponibles() {
        List<Producto> disponibles = new ArrayList<>();
        for (Producto p : listProducto) {
            if (p.verificarCondicionesCalidad()) {
                disponibles.add(p);
            }
        }
        return disponibles;
    }

    /**
     * Identifica los productos que NO cumplen las condiciones de calidad.
     */
    public List<Producto> identificarProductosNoCumplenCalidad() {
        List<Producto> defectuosos = new ArrayList<>();
        for (Producto p : listProducto) {
            if (!p.verificarCondicionesCalidad()) {
                defectuosos.add(p);
            }
        }
        return defectuosos;
    }

    /**
     * Calcula únicamente los ingresos propios del restaurante (ventas de comida),
     * excluyendo pedidos pendientes, cancelados y el costo de los envíos.
     */
    public double calcularIngresosPropiosRestaurante() {
        double ingresosComida = 0.0;

        for (Pedido pedido : listPedido) {
            // Solo se contabilizan pedidos válidos (confirmados o entregados)
            if (pedido.getEstado() == EstadoPedido.CONFIRMADO || pedido.getEstado() == EstadoPedido.ENTREGADO) {
                // Sumamos solo el subtotal de los productos
                ingresosComida += pedido.calcularValorTotalProductos();
            }
        }
        return ingresosComida;
    }

    /**
     * Calcula únicamente el dinero recaudado por concepto de domicilios.
     */
    public double calcularIngresosDomicilios() {
        double ingresosEnvios = 0.0;

        for (Pedido pedido : listPedido) {
            if (pedido.getEstado() == EstadoPedido.CONFIRMADO || pedido.getEstado() == EstadoPedido.ENTREGADO) {
                // Verificamos si el pedido es una instancia de PedidoDomicilio
                if (pedido instanceof PedidoDomicilio) {
                    PedidoDomicilio pedDom = (PedidoDomicilio) pedido;
                    // El costo del envío es la diferencia entre el total final y los productos
                    double costoEnvio = pedDom.calcularTotal() - pedDom.calcularValorTotalProductos();
                    ingresosEnvios += costoEnvio;
                }
            }
        }
        return ingresosEnvios;
    }

    /**
     * Encuentra el producto que más unidades ha vendido en total.
     *
     * @return El objeto Producto más vendido, o null si aún no hay ventas.
     */
    public Producto obtenerProductoMasVendido() {
        Producto masVendido = null;
        int maxUnidades = 0;

        for (Producto prodCatalogo : listProducto) {
            int unidadesEsteProducto = 0;

            // Buscamos este producto en todos los pedidos válidos
            for (Pedido pedido : listPedido) {
                if (pedido.getEstado() == EstadoPedido.CONFIRMADO || pedido.getEstado() == EstadoPedido.ENTREGADO) {
                    for (DetallePedido detalle : pedido.getListDetallePedido()) {
                        if (detalle.producto().getCodigo() == prodCatalogo.getCodigo()) {
                            unidadesEsteProducto += detalle.cantidad();
                        }
                    }
                }
            }

            // Si supera el récord actual, lo guardamos
            if (unidadesEsteProducto > maxUnidades) {
                maxUnidades = unidadesEsteProducto;
                masVendido = prodCatalogo;
            }
        }
        return masVendido;
    }

    /**
     * Determina cuál de las tres categorías genera mayores ingresos económicos.
     *
     * @return Un String indicando la categoría ganadora (o "Sin ventas" si está en 0).
     */
    public String obtenerCategoriaMayoresIngresos() {
        double ingresosPlatos = 0.0;
        double ingresosBebidas = 0.0;
        double ingresosPostres = 0.0;

        for (Pedido pedido : listPedido) {
            if (pedido.getEstado() == EstadoPedido.CONFIRMADO || pedido.getEstado() == EstadoPedido.ENTREGADO) {
                for (DetallePedido detalle : pedido.getListDetallePedido()) {

                    Producto prod = detalle.producto();
                    double totalLinea = prod.calcularPrecioVenta() * detalle.cantidad();

                    if (prod instanceof PlatoFuerte) {
                        ingresosPlatos += totalLinea;
                    } else if (prod instanceof Bebida) {
                        ingresosBebidas += totalLinea;
                    } else if (prod instanceof Postre) {
                        ingresosPostres += totalLinea;
                    }
                }
            }
        }

        if (ingresosPlatos == 0 && ingresosBebidas == 0 && ingresosPostres == 0) {
            return "Sin ventas registradas";
        }

        if (ingresosPlatos >= ingresosBebidas && ingresosPlatos >= ingresosPostres) {
            return "Platos Fuertes";
        } else if (ingresosBebidas >= ingresosPlatos && ingresosBebidas >= ingresosPostres) {
            return "Bebidas";
        } else {
            return "Postres";
        }
    }

    // 1. Registrar producto en el menú del restaurante
    public boolean registrarProducto(Producto producto) {
        if (producto == null) {
            return false;
        }
        for (Producto p : listProducto) {
            if (p.getCodigo() == producto.getCodigo()) {
                return false; // Ya existe un producto con ese código
            }
        }
        listProducto.add(producto);
        return true;
    }

    // 2. Registrar pedido en el historial general del restaurante
    public boolean registrarPedido(Pedido pedido) {
        if (pedido == null) {
            return false;
        }
        for (Pedido ped : listPedido) {
            if (ped.getCodigo() == pedido.getCodigo()) {
                return false; // Ya existe un pedido con ese código
            }
        }
        listPedido.add(pedido);
        return true;
    }


    public List<Producto> getListProducto() {
        return listProducto;
    }

    public List<Cliente> getListCliente() {
        return listCliente;
    }

    public List<Pedido> getListPedido() {
        return listPedido;
    }
        public void setNombreComercial (String nombreComercial) {
            this.nombreComercial = nombreComercial;
        }


        public int getNIT () {
            return nit;
        }

        public void setNIT ( int NIT){
            this.nit = nit;
        }

        public String getDireccion () {
            return direccion;
        }

        public void setDireccion (String direccion){
            this.direccion = direccion;
        }

        public int getTelefono () {
            return telefono;
        }

        public void setTelefono ( int telefono){
            this.telefono = telefono;
        }

        @Override
        public String toString () {
            return "Restaurante{" +
                    "nombreComercial='" + nombreComercial + '\'' +
                    ", nit=" + nit +
                    ", direccion='" + direccion + '\'' +
                    ", telefono=" + telefono +
                    '}';
        }
    }
