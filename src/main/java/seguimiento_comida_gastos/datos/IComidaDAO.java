package seguimiento_comida_gastos.datos;

import seguimiento_comida_gastos.dominio.Comida;

import java.time.LocalDate;
import java.util.List;

public interface IComidaDAO {
    public List<Comida> listarComidasDelDia();
    public List<Comida> listarComidasPorFechaPersonalizada(LocalDate fechaInicial, LocalDate fechaFinal);

    public boolean crearComida(Comida unaComida);
    public boolean modificarComida(Comida unaComida);
    public boolean eliminarComida(Comida unaComida);

    public int mostrarGastosMensuales();
}
