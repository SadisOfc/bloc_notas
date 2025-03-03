package com.example.notitas.DAO;
import com.example.notitas.model.Usuario;
import com.example.notitas.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuariosDAO {
    public boolean registrarUsuario(String username, String email, String password){
        String sql = "insert into users (username, password, email) values (?,?,?)";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,username);
            stmt.setString(2,password);
            stmt.setString(3,email);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected>0;

        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public Usuario obtenerUsuario(String username){
        Usuario usuario = null;
        String sql = "Select * from users where username = ?";
        try( Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,username);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email")
                );
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return usuario;
    }
    public boolean autenticarUsuario(String username, String password){
        String sql = "Select * from users where email = ? and password = ?";
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,username);
            stmt.setString(2,password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
