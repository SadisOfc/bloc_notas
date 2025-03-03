package com.example.notitas.model;
import java.sql.Timestamp;

public class Nota {
    private String titulo;
    private String descripcion;
    private int id;
    private int usersId;
    private Timestamp created_at;

    public Nota(int id, int usersId, String titulo, String descripcion, Timestamp created_at) {
        this.id = id;
        this.usersId = usersId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public int getUsersId() {
        return usersId;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setCreated_at(Timestamp created_at){
        this.created_at = created_at;
    }
}
