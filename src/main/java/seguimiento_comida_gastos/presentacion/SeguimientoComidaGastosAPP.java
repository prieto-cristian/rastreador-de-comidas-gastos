package seguimiento_comida_gastos.presentacion;

import seguimiento_comida_gastos.datos.ComidaDAO;
import seguimiento_comida_gastos.datos.IComidaDAO;
import seguimiento_comida_gastos.dominio.Comida;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class SeguimientoComidaGastosAPP {
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        int opcion = -1;
        IComidaDAO negocio = new ComidaDAO();
        while(opcion != 7){
            mostrarMenu();
            opcion = Integer.parseInt(lector.nextLine());
            System.out.println();
            realizarAccion(opcion, lector, negocio);
        }
    }
    private static void realizarAccion(int opcion, Scanner lector, IComidaDAO negocio){
        int id, precio;
        switch (opcion){
            case 1 -> {// Registrar una comida
                System.out.println("----- DATOS DE LA COMIDA -----");
                System.out.print("Nombre: ");
                String nombre = lector.nextLine();
                System.out.print("Precio: ");
                precio = Integer.parseInt(lector.nextLine());
                LocalDate fecha = LocalDate.now();
                System.out.print("Usar la fecha actual? (SI O NO): ");
                if(lector.nextLine().toLowerCase().equals("no")){
                    fecha = obtenerFecha(lector, "INGRESE LA FECHA");
                }
                Comida nuevaComida = new Comida(precio, nombre, fecha);
                boolean seRegistro = negocio.crearComida(nuevaComida);
                if(seRegistro){
                    System.out.println("Registrado con exito!");
                }else{
                    System.out.println("No se pudo registrar la comida");
                }
            }
            case 2 -> { // Modificar Comida
                System.out.println("----- MODIFICAR COMIDA -----");
                System.out.print("Ingrese el ID de la comida a modificar: ");
                id = Integer.parseInt(lector.nextLine());
                Comida comidaAModificar = negocio.buscarComidaPorId(new Comida(id));
                if(comidaAModificar != null){
                    System.out.print("Nombre actual: " + comidaAModificar.getNombre() + ". Nuevo Nombre: ");
                    comidaAModificar.setNombre(lector.nextLine());
                    System.out.print("Precio actual: " + comidaAModificar.getPrecio() + ". Nuevo Precio: ");
                    comidaAModificar.setPrecio(Integer.parseInt(lector.nextLine()));
                    System.out.print("Fecha actual: " + comidaAModificar.getFechaDeConsumo() +". Desea modificarlo (SI O NO): ");
                    if(lector.nextLine().toLowerCase().equals("si")){
                        LocalDate fecha = obtenerFecha(lector, "INGRESE LA FECHA");
                        comidaAModificar.setFechaDeConsumo(fecha);
                    }
                    boolean seModifico = negocio.modificarComida(comidaAModificar);
                    if(seModifico){
                        System.out.println("Modificado con exito!");
                    }else{
                        System.out.println("No se pudo modificar la comida");
                    }
                }else{
                    System.out.println("No encontramos la comida con el ID: " + id);
                }
            }
            case 3 -> { // Eliminar una comida
                System.out.println("----- ELIMINAR COMIDA -----");
                System.out.print("Indique el ID de la comida a eliminar: ");
                id = Integer.parseInt(lector.nextLine());
                boolean seElimino = negocio.eliminarComida(new Comida(id));
                if(seElimino){
                    System.out.println("Eliminado con exito!");
                }else{
                    System.out.println("No pudimos eliminar la comida con ID: " + id);
                }
            }
            case 4 -> { // Listar la comidas del dia
                System.out.println("----- COMIDAS DEL DIA -----");
                List<Comida> listaComidas = negocio.listarComidasDelDia();
                listaComidas.forEach(System.out::println);
            }
            case 5 -> { // Listado de comidas por fechas
                System.out.println("----- LISTAR COMIDAS POR RANGO DE FECHA -----");
                LocalDate fechaInicial = obtenerFecha(lector, "Indique la fecha inicial de busqueda:");
                LocalDate fechaFinal = obtenerFecha(lector, "Indique la fecha final de busqueda");
                List<Comida> listaComidasPorFecha = negocio.listarComidasPorFechaPersonalizada(fechaInicial, fechaFinal);
                if(listaComidasPorFecha.size() > 0){
                    System.out.println("Aqui tienes el listado: ");
                    listaComidasPorFecha.forEach(System.out::println);
                }else{
                    System.out.println("No se encontraron comidas en el rango establecido");
                }
            }
            case 6 -> { // Mostrar total gastado del mes
                System.out.println("----- MONTO GASTADO EN EL MES -----");
                System.out.println("Este mes llevas gastando: " + negocio.mostrarGastosMensuales());
            }
            case 7 -> { // Salir del sistema
                System.out.println("Saliendo del sistema....");
            }
            default -> { // Para cualquier otro valor
                System.out.println("La opcion ingresada '" + opcion + "' no es correcta!");
            }
        }
        System.out.println();
    }
    private static LocalDate obtenerFecha(Scanner lector, String mensaje){
        int anio, mes, dia;
        System.out.println(mensaje);
        System.out.print("ANIO (yyyy): ");
        anio = Integer.parseInt(lector.nextLine());
        System.out.print("MES (1 al 12): ");
        mes = Integer.parseInt(lector.nextLine());
        System.out.print("DIA: ");
        dia = Integer.parseInt(lector.nextLine());

        return LocalDate.of(anio, mes, dia);
    }
    private static void mostrarMenu(){
        System.out.print("""
                *** Bienvenido al Seguidor de Comidas y Gastos APP ***
                1. Registrar comida
                2. Modificar comida
                3. Eliminar comida
                4. Listar comidas del dia
                5. Listar comidas por rango personalizado
                6. Mostrar total gastado del mes
                7. Salir
                
                Seleccione una opcion:""");
    }
}
