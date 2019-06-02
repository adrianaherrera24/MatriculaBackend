/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import LogicaNegocio.Carrera;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author leohi
 */
public class ServicioCarreras extends AccesoServicios {
    
    private static final String insertarCarreras = "{call insertarCarreras(?,?,?)}";
    private static final String listarCarreras  = "{?=call listarCarreras()}";
    private static final String mostrarCarreras  = "{?=call mostrarCarreras()}";
    private static final String buscarCarreras  = "{?=call buscarCarreras(?)}";
    private static final String buscarCarrerasById  = "{?=call buscarCarrerasById(?)}";
    private static final String modificarCarreras= "{call modificarCarreras(?,?,?)}";
    private static final String eliminarCarreras = "{call eliminarCarreras(?)}";

    
    public void ServicioCarreras(){}
    
    public List<Carrera> listarCarreras() throws GlobalException, NoDataException
    {
        try
        {
           conectar();
        }
        catch (ClassNotFoundException ex)
        {
            throw new GlobalException("No se ha localizado el Driver");
        }

        catch (SQLException e)
        {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        ResultSet rs = null;
        List<Carrera> coleccion = new ArrayList();
        Carrera carrera = null;
        CallableStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(listarCarreras);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while (rs.next())
            {
                carrera = new Carrera(
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("titulo"));
                coleccion.add(carrera);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();

            throw new GlobalException("Sentencia no valida");
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (pstmt != null)
                {
                    pstmt.close();
                }
                desconectar();
            }
            catch (SQLException e)
            {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion.isEmpty())
        {
            throw new NoDataException("No hay datos");
        }
        return coleccion;
    }
    
    public void insertarCarreras(Carrera carrera) throws GlobalException, NoDataException
	{
            try
            {
                conectar();
            }
            catch (ClassNotFoundException e)
            {
                throw new GlobalException("No se ha localizado el driver");
            }
            catch (SQLException e)
            {
                throw new NoDataException("La base de datos no se encuentra disponible");
            }
            CallableStatement pstmt = null;

            try
            {
                pstmt = conexion.prepareCall(insertarCarreras);
                pstmt.setString(1, carrera.getCodigo());
                pstmt.setString(2, carrera.getNombre());
                pstmt.setString(3, carrera.getTitulo());           
                
      
                boolean resultado = pstmt.execute();
                if (resultado == true)
                {
                    throw new NoDataException("No se realizo la inserción");
                }

            }
            catch (SQLException e)
            {
                e.printStackTrace();
                throw new GlobalException("Llave duplicada");
            }
            finally
            {
                try
                {
                    if (pstmt != null)
                    {
                        pstmt.close();
                    }
                    desconectar();
                }
                catch (SQLException e)
                {
                    throw new GlobalException("Estatutos invalidos o nulos");
                }
            }
	}
    
    public void eliminarCarreras(String id) throws GlobalException, NoDataException
	{
            try
            {
                conectar();
            }
            catch (ClassNotFoundException e)
            {
                throw new GlobalException("No se ha localizado el driver");
            }
            catch (SQLException e)
            {
                throw new NoDataException("La base de datos no se encuentra disponible");
            }
            PreparedStatement pstmt = null;
            try
            {
                pstmt = conexion.prepareStatement(eliminarCarreras);
                pstmt.setString(1, id);

                int resultado = pstmt.executeUpdate();

                if (resultado != 0)
                {
                    System.out.println("\nEliminación Satisfactoria!");
                }
                else
                {
                    throw new NoDataException("No se realizo el borrado");
                }
            }
            catch (SQLException e)
            {
                System.out.println(e);
                throw new GlobalException("Sentencia no valida");
            }
            finally
            {
                try
                {
                    if (pstmt != null)
                    {
                        pstmt.close();
                    }
                    desconectar();
                }
                catch (SQLException e)
                {
                    throw new GlobalException("Estatutos invalidos o nulos");
                }
            }
	}
    
    public void modificarCarreras(Carrera carrera) throws GlobalException, NoDataException
    {
        try
        {
            conectar();
        }
        catch (ClassNotFoundException e)
        {
            throw new GlobalException("No se ha localizado el driver");
        }
        catch (SQLException e)
        {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareStatement(modificarCarreras);
            pstmt.setString(1, carrera.getCodigo());
            pstmt.setString(2, carrera.getNombre());
            pstmt.setString(3, carrera.getTitulo());

            int resultado = pstmt.executeUpdate();

            //si es diferente de 0 es porq si afecto un registro o mas
            if (resultado != 0)
            {
                System.out.println("\nModificación Satisfactoria!");
            }
            else
            {
                throw new NoDataException("No se realizo la actualización");
            }
        }
        catch (SQLException e)
        {
            throw new GlobalException("Sentencia no valida");
        }
        finally
        {
            try
            {
                if (pstmt != null)
                {
                    pstmt.close();
                }
                desconectar();
            }
            catch (SQLException e)
            {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }
    
    public List<Carrera> buscarCarreras(String id) throws GlobalException, NoDataException
    {
        try
        {
            conectar();
        }
        catch (ClassNotFoundException e)
        {
            throw new GlobalException("No se ha localizado el driver");
        }
        catch (SQLException e)
        {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Carrera carrera = null;
        CallableStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(buscarCarreras);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while (rs.next())
            {
                carrera = new Carrera(rs.getString("codigo"),
                                    rs.getString("nombre"),
                                    rs.getString("titulo"));
                coleccion.add(carrera);
            }
        }
        catch (SQLException e)
        {
                e.printStackTrace();

                throw new GlobalException("Sentencia no valida");
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (pstmt != null)
                {
                    pstmt.close();
                }
                desconectar();
            }
            catch (SQLException e)
            {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion.isEmpty())
        {
                throw new NoDataException("No hay datos");
        }
        return coleccion;
    }
    
    public List<Carrera> buscarCarrerasById(String id) throws GlobalException, NoDataException
    {
        try
        {
            conectar();
        }
        catch (ClassNotFoundException e)
        {
            throw new GlobalException("No se ha localizado el driver");
        }
        catch (SQLException e)
        {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Carrera carrera = null;
        CallableStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(buscarCarrerasById);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while (rs.next())
            {
                carrera = new Carrera(rs.getString("codigo"),
                                    rs.getString("nombre"),
                                    rs.getString("titulo")
                );
                coleccion.add(carrera);
            }
        }
        catch (SQLException e)
        {
                System.out.println(e);
                throw new GlobalException("Sentencia no valida");
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (pstmt != null)
                {
                    pstmt.close();
                }
                desconectar();
            }
            catch (SQLException e)
            {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion.isEmpty())
        {
                throw new NoDataException("No hay datos");
        }
        return coleccion;
    }
}
