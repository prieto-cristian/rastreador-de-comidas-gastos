package seguimiento_comida_gastos.dominio;

import java.time.LocalDate;
import java.util.Objects;

public class Comida {
    private int id;
    private int precio;
    private String nombre;
    private LocalDate fechaDeConsumo;

    public Comida(){}

    // Se utilizara para eliminar una comidaw
    public Comida(int id){
        this.id = id;
    }

    // Se utilizara para crear una comida. El ID no se pasa porque lo maneja la base de datos
    public Comida(int precio, String nombre, LocalDate fechaDeConsumo){
        this.precio = precio;
        this.fechaDeConsumo = fechaDeConsumo;
        this.nombre = nombre;
    }

    // Este constructor se va a usar para modificar. El usuario debe pasarnos el ID
    public Comida (int id, int precio, String nombre, LocalDate fechaDeConsumo){
        this(precio, nombre, fechaDeConsumo);
        this.id = id;
    }

    @Override
    public String toString() {
        return "Comida{" +
                "ID: " + id +
                ", Precio: " + precio +
                ", Nombre: '" + nombre + '\'' +
                ", Fecha: " + fechaDeConsumo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Comida comida = (Comida) o;
        return id == comida.id && precio == comida.precio && Objects.equals(nombre, comida.nombre) && Objects.equals(fechaDeConsumo, comida.fechaDeConsumo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, precio, nombre, fechaDeConsumo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaDeConsumo() {
        return fechaDeConsumo;
    }

    public void setFechaDeConsumo(LocalDate fechaDeConsumo) {
        this.fechaDeConsumo = fechaDeConsumo;
    }
}
