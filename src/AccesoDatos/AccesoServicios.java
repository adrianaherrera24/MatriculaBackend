/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 *
 * @author leohi
 */
public class AccesoServicios {
    
     protected Connection conexion= null;
     
     public AccesoServicios(){}
     
     protected void conectar() throws SQLException, ClassNotFoundException
     {
         Class.forName("oracle.jdbc.driver.OracleDriver");
        // try {
            conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","UNV","root123");
            //conexion = getJdbcMydbsource();
       // } catch (NamingException ex) {
            //System.out.println(ex);
       // }
     }
     
     protected void desconectar() throws SQLException{
        if(!conexion.isClosed())
        {
            conexion.close();
        }
    }

    private Connection getJdbcMydbsource() throws NamingException {
        Context c = new InitialContext();
        try {
            return ((DataSource) c.lookup("jdbc/Mydbsource")).getConnection();
        } catch (NamingException | SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
     
}
