/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;
import LogicaNegocio.Curso;
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
public class ServicioCursos extends AccesoServicios {
    
    private static final String insertarCursos = "{call insertarCursos (?,?,?,?,?,?,?)}";
    private static final String listarCursos = "{?=call listarCursos()}";
    private static final String buscarCursos = "{?=call buscarCursos(?)}";
    private static final String buscarCursosPorCarrera = "{?=call buscarCursosPorCarrera(?)}";
    private static final String buscarCursosById = "{?=call buscarCursosById(?)}";
    private static final String modificarCursos= "{call modificarCursos (?,?,?,?,?,?,?)}";
    private static final String eliminarCursos = "{call eliminarCursos(?)}";
    
    public List<Curso> listarCursos() throws GlobalException, NoDataException
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
            List<Curso> coleccion = new ArrayList();
            Curso curso = null;
            CallableStatement pstmt = null;
            try
            {
                pstmt = conexion.prepareCall(listarCursos);
                pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                pstmt.execute();
                rs = (ResultSet)pstmt.getObject(1);
                while (rs.next())
                {
                    curso = new Curso(rs.getString("codigo"),
                                    rs.getString("nombre"),
                                    rs.getInt("creditos"),
                                    rs.getInt("horas_semanales"),
                                    rs.getString("carrera"),
                                    rs.getInt("ciclo"),
                                    rs.getString("anno"));
                        coleccion.add(curso);
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
    
    
    public void insertarCursos(Curso curso) throws GlobalException, NoDataException
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
                pstmt = conexion.prepareCall(insertarCursos);
                pstmt.setString(1, curso.getId());
                pstmt.setString(2, curso.getNombre());
                pstmt.setInt(3, curso.getCreditos());
                pstmt.setInt(4, curso.getHorasSemanales());
                pstmt.setString(5, curso.getCarrera());
                pstmt.setInt(6, curso.getCiclo());
                pstmt.setString(7, curso.getAnno());
                
      
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
    
    public void eliminarCursos(String id) throws GlobalException, NoDataException
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
                pstmt = conexion.prepareStatement(eliminarCursos);
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
    
    public void modificarCursos(Curso curso) throws GlobalException, NoDataException
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
            pstmt = conexion.prepareStatement(modificarCursos);
            pstmt.setString(1, curso.getId());
            pstmt.setString(2, curso.getNombre());
            pstmt.setInt(3, curso.getCreditos());
            pstmt.setInt(4, curso.getHorasSemanales());
            pstmt.setString(5, curso.getCarrera());
            pstmt.setInt(6, curso.getCiclo());
            pstmt.setString(7, curso.getAnno());
            
           
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
    
    public List<Curso> buscarCursos(String id) throws GlobalException, NoDataException
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
        List<Curso> coleccion = new ArrayList();
        Curso curso = null;
        CallableStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(buscarCursos);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while (rs.next())
            {
                curso = new Curso(rs.getString("codigo"),
                                rs.getString("nombre"),
                                rs.getInt("creditos"),
                                rs.getInt("horas_semanales"),
                                rs.getString("carrera"),
                                rs.getInt("ciclo"),
                                rs.getString("anno"));
                coleccion.add(curso);
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
                System.out.println(e);
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion.isEmpty())
        {
            throw new NoDataException("No hay datos");
        }
        return coleccion;
    }
    
    public List<Curso> buscarCursosPorCarrera(String id) throws GlobalException, NoDataException
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
        List<Curso> coleccion = new ArrayList();
        Curso curso = null;
        CallableStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(buscarCursosPorCarrera);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while (rs.next())
            {
                curso = new Curso(rs.getString("codigo"),
                                rs.getString("nombre"),
                                rs.getInt("creditos"),
                                rs.getInt("horas_semanales"),
                                rs.getString("carrera"),
                                rs.getInt("ciclo"),
                                rs.getString("anno"));
                coleccion.add(curso);
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
                System.out.println(e);
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion.isEmpty())
        {
            throw new NoDataException("No hay datos");
        }
        return coleccion;
    }
    
    public List<Curso> buscarCursosById(String id) throws GlobalException, NoDataException
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
        List<Curso> coleccion = new ArrayList();
        Curso curso = null;
        CallableStatement pstmt = null;
        try
        {
            pstmt = conexion.prepareCall(buscarCursosById);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1);
            while (rs.next())
            {
                curso = new Curso(rs.getString("codigo"),
                                rs.getString("nombre"),
                                rs.getInt("creditos"),
                                rs.getInt("horas_semanales"),
                                rs.getString("carrera"),
                                rs.getInt("ciclo"),
                                rs.getString("anno"));
                coleccion.add(curso);
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
                System.out.println(e);
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
