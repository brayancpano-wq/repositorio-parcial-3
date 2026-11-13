import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Instanciar el restaurante y precargar datos iniciales para pruebas rápidas
        Restaurante restaurante = new Restaurante("El Buen Comer", "Calle 15 # 12-30", 900456789, 7451234);

        Cliente cliente1 = new Cliente("1094888111", "Laura Gómez", 7451122, "laura@correo.com", "Barrio Norte");
        Cliente cliente2 = new Cliente("1094999222", "Andrés Martínez", 7453344, "andres@correo.com", "Avenida Bolívar");
        restaurante.registrarCliente(cliente1);
        restaurante.registrarCliente(cliente2);

        PlatoFuerte bandejaPaisa = new PlatoFuerte(101, "Bandeja típica completa", "Bandeja Paisa", 28000, 25, 550, true);
        Bebida limonadaCoco = new Bebida(201, "Limonada natural con coco", "Limonada de Coco", 9000, 5, 350, 6.0, false);
        Postre cheesecake = new Postre(301, "Cheesecake de frutos rojos", "Cheesecake", 11000, 5, 180, 4.0, true);
        Postre heladoDerretido = new Postre(302, "Copa de helado fuera de temperatura", "Copa de Helado", 8500, 3, 150, 12.0, true);

        restaurante.registrarProducto(bandejaPaisa);
        restaurante.registrarProducto(limonadaCoco);
        restaurante.registrarProducto(cheesecake);
        restaurante.registrarProducto(heladoDerretido);

        IServicioDomicilio rapidoExpress = new RapidoExpress();
        IServicioDomicilio entregaSegura = new EntregaSegura();

        int opcion = 0;

        while (opcion != 9) {
            String input = JOptionPane.showInputDialog(null, """
                    ========== EL BUEN COMER ==========
                    1. Registrar producto (Plato Fuerte / Bebida / Postre)
                    2. Registrar cliente
                    3. Crear y gestionar pedido en mesa
                    4. Crear y gestionar pedido a domicilio
                    5. Consultar productos disponibles (cumplen calidad)
                    6. Consultar productos que NO cumplen calidad
                    7. Consultar historial de pedidos por cliente
                    8. Ver reportes financieros y estadísticas
                    9. Salir
                    Seleccione una opción:
                    """);

            if (input == null) {
                break;
            }

            opcion = Integer.parseInt(input);

            if (opcion == 1) {
                int tipo = Integer.parseInt(JOptionPane.showInputDialog(null, """
                        Seleccione el tipo de producto:
                        1. Plato Fuerte
                        2. Bebida
                        3. Postre
                        """));

                int codigo = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el código del producto:"));
                if (restaurante.buscarProducto(codigo) != null) {
                    JOptionPane.showMessageDialog(null, "Error: Ya existe un producto registrado con el código " + codigo + ".\nIntente con uno diferente.");
                    continue;
                }
                String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del producto:");
                String descripcion = JOptionPane.showInputDialog(null, "Ingrese la descripción:");
                double precioBase = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el precio base:"));
                int tiempoPrep = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el tiempo de preparación (min):"));

                Producto nuevoProducto = null;

                if (tipo == 1) {
                    int pesoGramos = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el peso en gramos (200 - 900):"));
                    int coccionOpc = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Tiene control de cocción?\n1. Sí\n2. No"));
                    boolean controlCoccion = (coccionOpc == 1);

                    int prepOpc = Integer.parseInt(JOptionPane.showInputDialog(null, """
                            Tipo de preparación:
                            1. ASADO
                            2. FRITO
                            3. HORNEADO
                            4. A_LA_PLANCHA
                            """));
                    TipoPreparacion tipoPrep = TipoPreparacion.ASADO;
                    if (prepOpc == 2) tipoPrep = TipoPreparacion.FRITO;
                    else if (prepOpc == 3) tipoPrep = TipoPreparacion.HORNEADO;
                    else if (prepOpc == 4) tipoPrep = TipoPreparacion.A_LA_PLANCHA;

                    nuevoProducto = new PlatoFuerte(codigo, nombre, descripcion, precioBase, tiempoPrep, pesoGramos, controlCoccion);

                } else if (tipo == 2) {
                    int volumen = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el volumen en mililitros (100 - 1000):"));
                    double temp = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese la temperatura de servicio (°C):"));
                    int espOpc = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Requiere preparación especial?\n1. Sí\n2. No"));
                    boolean prepEspecial = (espOpc == 1);

                    int bebOpc = Integer.parseInt(JOptionPane.showInputDialog(null, "Tipo de bebida:\n1. FRIA\n2. CALIENTE"));
                    TipoBebida tipoBebida = (bebOpc == 2) ? TipoBebida.CALIENTE : TipoBebida.FRIA;

                    nuevoProducto = new Bebida(codigo, descripcion, nombre, precioBase, tiempoPrep, volumen, temp, prepEspecial);

                } else if (tipo == 3) {
                    int pesoGramos = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el peso en gramos (50 - 400):"));
                    double tempAlm = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese la temperatura de almacenamiento (°C):"));
                    int refOpc = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Requiere refrigeración?\n1. Sí\n2. No"));
                    boolean reqRefrig = (refOpc == 1);

                    nuevoProducto = new Postre(codigo, descripcion, nombre, precioBase, tiempoPrep, pesoGramos, tempAlm, reqRefrig);
                }

                if (nuevoProducto != null) {
                    boolean registrado = restaurante.registrarProducto(nuevoProducto);
                    if (registrado) {
                        JOptionPane.showMessageDialog(null, "Producto registrado correctamente.\nPrecio de venta calculado: $" + nuevoProducto.calcularPrecioVenta());
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo registrar el producto (código duplicado o nulo).");
                    }
                }

            } else if (opcion == 2) {
                String documento = JOptionPane.showInputDialog(null, "Ingrese el documento del cliente:");
                if (restaurante.buscarCliente(documento) != null) {
                    JOptionPane.showMessageDialog(null, "Error: Ya existe un cliente con el documento " + documento + ".\nIntente con uno diferente.");
                    continue;
                }
                String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre completo:");
                int telefono = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el teléfono:"));
                String correo = JOptionPane.showInputDialog(null, "Ingrese el correo electrónico:");
                String direccion = JOptionPane.showInputDialog(null, "Ingrese la dirección:");

                Cliente cliente = new Cliente(documento, nombre, telefono, correo, direccion);
                boolean registrado = restaurante.registrarCliente(cliente);

                if (registrado) {
                    JOptionPane.showMessageDialog(null, "Cliente registrado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Ya existe un cliente con ese documento.");
                }

            } else if (opcion == 3) {
                String docCliente = JOptionPane.showInputDialog(null, "Ingrese el documento del cliente (ej. 1094888111):");
                Cliente cliente = buscarClienteLocal(restaurante, docCliente);

                if (cliente != null) {
                    int codigoPedido = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el código del pedido:"));
                    String fecha = JOptionPane.showInputDialog(null, "Ingrese la fecha (AAAA-MM-DD):");
                    int numeroMesa = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de mesa:"));

                    PedidoMesa pedidoMesa = new PedidoMesa(codigoPedido, fecha, cliente, EstadoPedido.PENDIENTE, numeroMesa);
                    agregarProductosAlPedidoUI(restaurante, pedidoMesa);

                    if (restaurante.procesarConfirmacionPedido(pedidoMesa)) {
                        restaurante.registrarPedido(pedidoMesa);
                        int entregar = Integer.parseInt(JOptionPane.showInputDialog(null,
                                "Pedido en mesa #" + numeroMesa + " CONFIRMADO.\nTotal a pagar: $" + pedidoMesa.calcularTotal() +
                                        "\n\n¿Marcar como ENTREGADO ahora?\n1. Sí\n2. No"));
                        if (entregar == 1) {
                            pedidoMesa.entregarPedido();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo confirmar el pedido (lista vacía o producto no disponible en menú).");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                }

            } else if (opcion == 4) {
                String docCliente = JOptionPane.showInputDialog(null, "Ingrese el documento del cliente (ej. 1094999222):");
                Cliente cliente = buscarClienteLocal(restaurante, docCliente);

                if (cliente != null) {
                    int codigoPedido = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el código del pedido:"));
                    String fecha = JOptionPane.showInputDialog(null, "Ingrese la fecha (AAAA-MM-DD):");
                    double distanciaKm = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese la distancia en km:"));

                    int empOpc = Integer.parseInt(JOptionPane.showInputDialog(null, """
                            Seleccione la empresa de domicilio:
                            1. RapidoExpress (máx 10 km)
                            2. EntregaSegura (máx 20 km)
                            """));
                    IServicioDomicilio empresa = (empOpc == 2) ? entregaSegura : rapidoExpress;

                    if (!empresa.aceptarEntrega(distanciaKm)) {
                        JOptionPane.showMessageDialog(null, "Error: La empresa seleccionada no cubre la distancia de " + distanciaKm + " km.\nPor favor intente nuevamente.");
                        continue;
                    }

                    PedidoDomicilio pedidoDom = new PedidoDomicilio(codigoPedido, fecha, cliente, EstadoPedido.PENDIENTE, distanciaKm, empresa);
                    agregarProductosAlPedidoUI(restaurante, pedidoDom);

                    if (restaurante.procesarConfirmacionPedido(pedidoDom)) {
                        restaurante.registrarPedido(pedidoDom);
                        int entregar = Integer.parseInt(JOptionPane.showInputDialog(null,
                                "Pedido a domicilio CONFIRMADO.\nCosto envío: $" + empresa.calcularCostoDomicilio(distanciaKm) +
                                        "\nTotal pedido (productos + envío): $" + pedidoDom.calcularTotal() +
                                        "\n\n¿Marcar como ENTREGADO ahora?\n1. Sí\n2. No"));
                        if (entregar == 1) {
                            pedidoDom.entregarPedido();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo confirmar el domicilio (distancia fuera de cobertura, pedido vacío o producto inválido).");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                }

            } else if (opcion == 5) {
                List<Producto> disponibles = restaurante.consultarProductosDisponibles();
                StringBuilder sb = new StringBuilder("=== PRODUCTOS DISPONIBLES (CUMPLEN CALIDAD) ===\n");
                for (Producto p : disponibles) {
                    sb.append("- [").append(p.getCodigo()).append("] ")
                            .append(p.getNombre()).append(" | Precio Venta: $")
                            .append(p.calcularPrecioVenta()).append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString());

            } else if (opcion == 6) {
                List<Producto> noCumplen = restaurante.identificarProductosNoCumplenCalidad();
                StringBuilder sb = new StringBuilder("=== PRODUCTOS QUE NO CUMPLEN CALIDAD ===\n");
                for (Producto p : noCumplen) {
                    sb.append("- [").append(p.getCodigo()).append("] ").append(p.getNombre()).append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString());

            } else if (opcion == 7) {
                String docCliente = JOptionPane.showInputDialog(null, "Ingrese el documento del cliente:");
                List<Pedido> historial = restaurante.consultarHistorialCliente(docCliente);
                StringBuilder sb = new StringBuilder("=== HISTORIAL DE PEDIDOS DEL CLIENTE ===\n");
                for (Pedido ped : historial) {
                    sb.append("- Pedido #").append(ped.getCodigo())
                            .append(" | Fecha: ").append(ped.getFecha())
                            .append(" | Estado: ").append(ped.getEstado())
                            .append(" | Total: $").append(ped.calcularTotal()).append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString());

            } else if (opcion == 8) {
                Producto masVendido = restaurante.obtenerProductoMasVendido();
                String nombreMasVendido = (masVendido != null) ? masVendido.getNombre() : "Sin ventas confirmadas";

                String reporte = "=== REPORTE FINANCIERO Y ESTADÍSTICAS ===\n"
                        + "Ingresos propios del restaurante (productos): $" + restaurante.calcularIngresosPropiosRestaurante() + "\n"
                        + "Ingresos generados por domicilios (envíos): $" + restaurante.calcularIngresosDomicilios() + "\n"
                        + "Producto más vendido: " + nombreMasVendido + "\n"
                        + "Categoría con mayores ingresos: " + restaurante.obtenerCategoriaMayoresIngresos();

                JOptionPane.showMessageDialog(null, reporte);

            } else if (opcion == 9) {
                JOptionPane.showMessageDialog(null, "Saliendo del sistema El Buen Comer.");
            } else {
                JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        }
    }

    // Métodos auxiliares para mantener limpio el menú principal
    private static Cliente buscarClienteLocal(Restaurante restaurante, String documento) {
        for (Cliente c : restaurante.getListCliente()) {
            if (c.getDocumento().equals(documento)) {
                return c;
            }
        }
        return null;
    }

    private static Producto buscarProductoLocal(Restaurante restaurante, int codigo) {
        for (Producto p : restaurante.getListProducto()) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    private static void agregarProductosAlPedidoUI(Restaurante restaurante, Pedido pedido) {
        int continuar = 1;
        while (continuar == 1) {
            int codProd = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Productos precargados: 101 (Bandeja Paisa), 201 (Limonada), 301 (Cheesecake), 302 (Helado inválido)\n" +
                            "Ingrese el código del producto a agregar:"));
            Producto prod = buscarProductoLocal(restaurante, codProd);

            if (prod != null) {
                int cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad:"));
                boolean agregado = pedido.agregarProductoAlPedido(prod, cantidad);
                if (agregado) {
                    JOptionPane.showMessageDialog(null, "Producto agregado al pedido.");
                } else {
                    JOptionPane.showMessageDialog(null, "Producto RECHAZADO: No cumple las condiciones de calidad o cantidad inválida.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado.");
            }

            continuar = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Desea agregar otro producto al pedido?\n1. Sí\n2. No"));
        }
    }
}