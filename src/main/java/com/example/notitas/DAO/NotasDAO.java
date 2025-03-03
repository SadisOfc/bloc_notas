package com.example.notitas.DAO;

import com.example.notitas.database.DatabaseConnection;
import com.example.notitas.model.Nota;

import java.sql.ResultSet;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NotasDAO {
    public boolean agregarNota(String title, String description){
        String sql = "insert into notes (users_id, title, description) values (?,?,?)";
        int userId=1;
    try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
        stmt.setInt(1,userId);
        stmt.setString(2,title);
        stmt.setString(3,description);
        int rowsAffect = stmt.executeUpdate();
        return rowsAffect >0;
    }catch(SQLException e){
        System.out.println(e.getMessage());
        return false;
    }
    }

    public List<Nota> obtenerNotasTitulos(String title){
        List<Nota> notas = new ArrayList<>();
        String sql = "select * from notes where lower(title) like lower(?)";
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,"%"+title+"%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Nota nota = new Nota(
                        rs.getInt("id"),
                        rs.getInt("users_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getTimestamp("created_at")
                );
                notas.add(nota);
            }

        }catch(SQLException e){
        System.out.println(e.getMessage());
        }
        return notas;
    }

    public List<Nota> obtenerNotasUsuario(int userId){
        List<Nota>notas=new ArrayList<>();
        String sql = "select * from notes where users_id=?";
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,userId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                        Nota nota = new Nota(
                        rs.getInt("id"),
                        rs.getInt("users_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getTimestamp("created_at"));
                        notas.add(nota);
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return notas;
    }
    public boolean actualizarNota(int id, String title, String description){
        String sql ="update notes set title=?, description = ? where id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,title);
            stmt.setString(2,description);
            stmt.setInt(3,id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows>0;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean eliminarNota(int id){
        String sql = "delete from notes where id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows>0;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
