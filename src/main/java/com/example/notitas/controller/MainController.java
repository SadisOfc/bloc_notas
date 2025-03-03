package com.example.notitas.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.io.*;

public class MainController {
    @FXML
    private Button btnConfiguracion;
    @FXML
    private Button btnCrearNota;
    @FXML
    private Button btnActualizarNota;
    @FXML
    private ListView<String> listBlocs;
    @FXML
    private TextArea txtNota;
    private final String DIR_PATH = "C:/Users/rirfe/Desktop/Notas";
    private final String FILE_NAME = DIR_PATH + "/notas.txt";


    @FXML
    public void initialize(){
        System.out.println("Interfaz Cargada correctamente");
        cargarArchivo();
    }

    @FXML
    public void crearNota(){
        String nota = txtNota.getText().trim();
        if(!nota.isEmpty()){
            listBlocs.getItems().add(nota);
            crearArchivoTxt(nota);
            txtNota.clear();
            System.out.println("Listo pai");
        }else{
            System.out.println("Paila pa");
        }
    }
    @FXML
    public void leerNota (){
        String seleccionarNota = listBlocs.getSelectionModel().getSelectedItem();
        if(!seleccionarNota.isEmpty()){
            txtNota.setText(seleccionarNota);
        }else{
            System.out.println("No hay nota seleccionada");
        }
    }

    @FXML
    public void actualizarNota(){
        int selectedIndex = listBlocs.getSelectionModel().getSelectedIndex();
        String act = txtNota.getText().trim();
        if(selectedIndex>=0){
            if(!act.isEmpty()){
                String actualizacion = txtNota.getText().trim();
                listBlocs.getItems().set(selectedIndex,actualizacion);
                actualizarArchivo();
            }else{
                System.out.println("Error: texto vacío");
            }
        }else{
            System.out.println("seleccione una nota");
        }
    }

    @FXML
    public void eliminarNota(){
        int selectedIndex = listBlocs.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0){
            listBlocs.getItems().remove(selectedIndex);
            actualizarArchivo();
            txtNota.clear();

        }
    }
    public void crearArchivoTxt(String contenido){
        try{
            File carpeta = new File(DIR_PATH);
            if(!carpeta.exists()){
                carpeta.mkdirs();
            }
            FileWriter file = new FileWriter(FILE_NAME,true); //True es para no sobreescribir siempre el Archivo txt
            file.write(contenido+"\n");
            file.close();
            System.out.println("Se ha guardado una nota correctamente");
        }catch(IOException e){
            System.out.println("No se ha guardado esa pinga, hubo un error: " + e.getMessage());
        }
    }

    public void cargarArchivo(){
        File file = new File(FILE_NAME);
        if(file.exists()){
            try(BufferedReader br = new BufferedReader(new FileReader(file))){
                String linea;
                while((linea= br.readLine())!=null){
                    listBlocs.getItems().add(linea);
                }
                System.out.println("Notas cargadas correctamente");
            }catch(IOException e){
                System.out.println("Hubo un error: " + e.getMessage());
            }
        }else{
            System.out.println("No hay blocs de notas disponibles");
        }
    }
public void actualizarArchivo(){
        try(FileWriter file = new FileWriter(FILE_NAME,false)){
            for(String nota: listBlocs.getItems()){
                file.write(nota+"\n");
            }
            System.out.println("Cambios realiazados con éxito");
        }catch(IOException e){
            System.out.println("Hubo un error: " + e.getMessage());
        }
    }
}
