/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import LogicaNegocio.Alumno;
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
public class ServicioAlumnos extends AccesoServicios {
    
    private static final String insertarAlumnos = "{call insertarAlumnos (?,?,?,?,?,?)}";
    private static final String listarAlumnos  = "{?=call listarAlumnos ()}";
    private static final String buscarAlumnos = "{?=call buscarAlumnos (?)}";
    private static final String buscarAlumnosById = "{?=call buscarAlumnosById (?)}";
    private static final String modificarAlumnos = "{call modificarAlumnos  (?,?,?,?,?,?)}";
    private static final String eliminarAlumnos  = "{call eliminarAlumnos(?)}";

    
    public void ServicioAlumnos(){}
    
    public List<Alumno> listarAlumnos() throws GlobalException, NoDataException
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
        List<Alumno> coleccion = new ArrayList();
        Alumno alumno = null;
        CallableStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(listarAlumnos);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while (rs.next())
            {
                alumno = new Alumno(rs.getString("id"),
                                    rs.getString("nombre"),
                                    rs.getString("telefono"),
                                    rs.getString("email"),
                                    rs.getString("fecha_nacimiento"),
                                    rs.getString("carrera"));
                coleccion.add(alumno);
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
    
    
    public void insertarAlumnos(Alumno alumno) throws GlobalException, NoDataException
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
                pstmt = conexion.prepareCall(insertarAlumnos);
                pstmt.setString(1, alumno.getId());
                pstmt.setString(2, alumno.getNombre());
                pstmt.setString(3, alumno.getTelefono());
                pstmt.setString(4, alumno.getEmail());
                pstmt.setString(5, alumno.getFechaNacimiento());
                pstmt.setString(6, alumno.getCarrera());
                
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
    
    public void eliminarAlumnos(String id) throws GlobalException, NoDataException
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
                pstmt = conexion.prepareStatement(eliminarAlumnos);
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
    
    public void modificarAlumnos(Alumno alumno) throws GlobalException, NoDataException
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
            pstmt = conexion.prepareStatement(modificarAlumnos);
            pstmt.setString(1, alumno.getId());
            pstmt.setString(2, alumno.getNombre());
            pstmt.setString(3, alumno.getTelefono());
            pstmt.setString(4, alumno.getEmail());
            pstmt.setString(5, alumno.getFechaNacimiento());
            pstmt.setString(6, alumno.getCarrera());
            
           
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
    
    public List<Alumno> buscarAlumnos(String id) throws GlobalException, NoDataException
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
        Alumno alumno = null;
        CallableStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(buscarAlumnos);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while (rs.next())
            {
                alumno = new Alumno(rs.getString("id"),
                                    rs.getString("nombre"),
                                    rs.getString("telefono"),
                                    rs.getString("email"),
                                    rs.getString("fecha_nacimiento"),
                                    rs.getString("carrera"));
                coleccion.add(alumno);
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
    
    public List<Alumno> buscarAlumnosById(String id) throws GlobalException, NoDataException
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
        Alumno alumno = null;
        CallableStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(buscarAlumnosById);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while (rs.next())
            {
                alumno = new Alumno(rs.getString("id"),
                                    rs.getString("nombre"),
                                    rs.getString("telefono"),
                                    rs.getString("email"),
                                    rs.getString("fecha_nacimiento"),
                                    rs.getString("carrera"));
                coleccion.add(alumno);
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