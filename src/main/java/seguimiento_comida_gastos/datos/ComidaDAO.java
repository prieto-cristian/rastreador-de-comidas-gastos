package seguimiento_comida_gastos.datos;

import seguimiento_comida_gastos.conexion.Conexion;
import seguimiento_comida_gastos.dominio.Comida;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComidaDAO implements IComidaDAO{
    @Override
    public List<Comida> listarComidasDelDia() {
        Connection con = Conexion.getConexion();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String consultaSQL = "SELECT * FROM comida WHERE (fecha = ?);";
        List<Comida> comidasDelDia = new ArrayList<>();
        ResultSet rs;
        PreparedStatement ps;
        try{
            ps = con.prepareStatement(consultaSQL);
            ps.setString(1, formato.format(new Date()));
            rs = ps.executeQuery();
            while(rs.next()){
                Comida comida = new Comida();
                comida.setId(rs.getInt("id"));
                comida.setNombre(rs.getString("nombre"));
                comida.setPrecio(rs.getInt("precio"));
                comida.setFechaDeConsumo(LocalDate.ofInstant(rs.getDate("fecha").toInstant(), ZoneId.systemDefault()));

                comidasDelDia.add(comida);
            }
            return comidasDelDia;
        }catch (Exception e){
            System.out.println("Error al listar las comidas del dia: " + e.getMessage());
        }finally {
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Error al cerrar la base de datos: " + ex.getMessage());
            }
        }
        return comidasDelDia;
    }

    @Override
    public List<Comida> listarComidasPorFechaPersonalizada(LocalDate fechaInicial, LocalDate fechaFinal) {
        String consultaSQL = "SELECT * FROM comida WHERE (fecha BETWEEN ? AND ?);";
        Connection con = Conexion.getConexion();
        PreparedStatement ps;
        ResultSet rs;
        List<Comida> listaComidas = new ArrayList<>();
        try{
            ps = con.prepareStatement(consultaSQL);
            ps.setString(1, fechaInicial.toString());
            ps.setString(2, fechaFinal.toString());
            rs = ps.executeQuery();
            while(rs.next()){
                Comida unaComida = new Comida();
                unaComida.setId(rs.getInt("id"));
                unaComida.setNombre(rs.getString("nombre"));
                unaComida.setFechaDeConsumo(Instant.ofEpochMilli(rs.getDate("fecha").getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
                unaComida.setPrecio(rs.getInt("precio"));

                listaComidas.add(unaComida);
            }
        }catch (Exception e){
            System.out.println("Error al listar comidas por fechas personalizadas: " + e.getMessage());
        }finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la base de datos: " + e.getMessage());
            }
        }
        return listaComidas;
    }

    @Override
    public boolean crearComida(Comida unaComida) {
        String consultaSQL = "INSERT INTO comida (nombre, fecha, precio) VALUES (?, ?, ?);";
        Connection con = Conexion.getConexion();
        PreparedStatement ps;
        try{
            ps = con.prepareStatement(consultaSQL);
            ps.setString(1, unaComida.getNombre());
            ps.setString(2, unaComida.getFechaDeConsumo().toString());
            ps.setInt(3, unaComida.getPrecio());

            var resultado = ps.executeUpdate();
            return resultado != 0;
        }catch (Exception e){
            System.out.println("No se pudo crear la comida: " + e.getMessage());
        }finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la base de datos: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean modificarComida(Comida unaComida) {
        return false;
    }

    @Override
    public boolean eliminarComida(Comida unaComida) {
        return false;
    }

    @Override
    public int mostrarGastosMensuales() {
        return 0;
    }
    
}
