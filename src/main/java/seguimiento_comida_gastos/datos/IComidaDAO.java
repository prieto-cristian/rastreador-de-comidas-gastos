package seguimiento_comida_gastos.datos;

import seguimiento_comida_gastos.dominio.Comida;

import java.util.Date;
import java.util.List;

public interface IComidaDAO {
    public List<Comida> listarComidasDelDia();
    public List<Comida> listarComidasPorFechaPersonalizada(Date fechaInicial, Date fechaFinal);

    public boolean crearComida(Comida unaComida);
    public boolean modificarComida(Comida unaComida);
    public boolean eliminarComida(Comida unaComida);

    public int mostrarGastosMensuales();
}
