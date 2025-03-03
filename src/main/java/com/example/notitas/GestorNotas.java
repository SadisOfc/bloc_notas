package com.example.notitas;

import com.example.notitas.model.Nota;

import java.util.ArrayList;

public class GestorNotas {
    private ArrayList<Nota> lista =new ArrayList<>();

    public void agregarNota(Nota nota){
        lista.add(nota);
    }
    public Nota buscarNota(String titulo){
        for(Nota e : lista){
            if(e.getTitulo().equals(titulo)){
                return e;
            }
        }
        return null;
    }
    public void eliminarNota(String titulo){
        Nota e = buscarNota(titulo);
        if(e!=null){
            lista.remove(e);
        }
    }
    public ArrayList<Nota> leerNotas(){
        return lista;
    }
}
