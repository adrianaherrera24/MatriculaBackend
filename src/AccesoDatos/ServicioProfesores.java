/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import LogicaNegocio.Profesor;
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
public class ServicioProfesores extends AccesoServicios {
    
    private static final String insertarProfesores = "{call insertarProfesores  (?,?,?,?)}";
    private static final String listarProfesores  = "{?=call listarProfesores ()}";
    private static final String buscarProfesores  = "{?=call buscarProfesores (?)}";
    private static final String buscarProfesoresById  = "{?=call buscarProfesoresById (?)}";
    private static final String modificarProfesores = "{call modificarProfesores  (?,?,?,?)}";
    private static final String eliminarProfesores  = "{call eliminarProfesores(?)}";

    
    public void ServicioProfesores(){}
    
    public List<Profesor> listarProfesores() throws GlobalException, NoDataException
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
        List<Profesor> coleccion = new ArrayList();
        Profesor profe = null;
        CallableStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(listarProfesores);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while (rs.next())
            {
                profe = new Profesor(rs.getString("id"),
                                    rs.getString("nombre"),
                                    rs.getString("telefono"),
                                    rs.getString("email"));
                coleccion.add(profe);
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
    
    
    public void insertarProfesores(Profesor profe) throws GlobalException, NoDataException
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
                pstmt = conexion.prepareCall(insertarProfesores);
                pstmt.setString(1, profe.getId());
                pstmt.setString(2, profe.getNombre());
                pstmt.setString(3, profe.getTelefono());
                pstmt.setString(4, profe.getEmail());
                            
      
                boolean resultado = pstmt.execute();
                if (resultado == true)
                {
                    throw new NoDataException("No se realizo la inserción");
                }

            }
            catch (SQLException e)
            {
                System.out.println(e);
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
    
    public void eliminarProfesores(String id) throws GlobalException, NoDataException
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
                pstmt = conexion.prepareStatement(eliminarProfesores);
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
    
    public void modificarProfesores(Profesor profe) throws GlobalException, NoDataException
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
            pstmt = conexion.prepareCall(modificarProfesores);
            pstmt.setString(1, profe.getId());
            pstmt.setString(2, profe.getNombre());
            pstmt.setString(3, profe.getTelefono());
            pstmt.setString(4, profe.getEmail());
            
           
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
    
    public List<Profesor> buscarProfesores(String id) throws GlobalException, NoDataException
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
        Profesor profe = null;
        CallableStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(buscarProfesores);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while (rs.next())
            {
                profe = new Profesor(rs.getString("id"),
                                    rs.getString("nombre"),
                                    rs.getString("telefono"),
                                    rs.getString("email"));
                coleccion.add(profe);
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
    
    public List<Profesor> buscarProfesoresById(String id) throws GlobalException, NoDataException
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
        Profesor profe = null;
        CallableStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(buscarProfesoresById);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while (rs.next())
            {
                profe = new Profesor(rs.getString("id"),
                                    rs.getString("nombre"),
                                    rs.getString("telefono"),
                                    rs.getString("email"));
                coleccion.add(profe);
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
}