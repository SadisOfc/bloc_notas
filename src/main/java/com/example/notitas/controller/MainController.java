package com.example.notitas.controller;

import com.example.notitas.DAO.NotasDAO;
import org.json.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import com.example.notitas.DAO.NotasDAO.*;

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
    public NotasDAO notasDAO;
    StringBuilder titulos;

    private final String DIR_PATH = "C:/Users/rirfe/Desktop/Notas";
    //private final String FILE_NAME = DIR_PATH + "/notas.txt";
    private final String JSON_NAME=DIR_PATH+"/notasjson.json";


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
            crearArchivoJSON(nota);
            txtNota.clear();
            System.out.println("Listo pai");
            titulos.append(nota.substring(0,10));
            notasDAO.agregarNota(titulos.toString(),nota);
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

    public void cargarArchivo(){
        File file = new File(JSON_NAME);
        if(file.exists()){
            StringBuilder contenido= new StringBuilder();
            try(BufferedReader br = new BufferedReader(new FileReader(file))){
                String linea;
                JSONArray array = new JSONArray();
                while((linea= br.readLine())!=null){
                    contenido.append(linea);
                }
                if(contenido.length()>0){
                    array = new JSONArray(contenido.toString());
                    for(int i=0;i<array.length();i++){
                        JSONObject objJson=array.getJSONObject(i);
                        String contenidoNota = objJson.getString("contenido");
                        listBlocs.getItems().add(contenidoNota);
                    }

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
        try(FileWriter file = new FileWriter(JSON_NAME,false)){
            JSONArray jArray = new JSONArray();
            for(String nota: listBlocs.getItems()){
                JSONObject jObject = new JSONObject();
                jObject.put("contenido",nota);
                jArray.put(jObject);
            }
            file.write(jArray.toString(4));
            file.flush();
            System.out.println("Cambios realiazados con éxito");
        }catch(IOException e){
            System.out.println("Hubo un error: " + e.getMessage());
        }
    }

    public void crearArchivoJSON(String contenido){
        try{
            File carpeta = new File(DIR_PATH);
            if(!carpeta.exists()){
                carpeta.mkdirs();
            }
            JSONArray jArray = new JSONArray();
            File jsonFile = new File(JSON_NAME);
            if(jsonFile.exists()){
                try(BufferedReader br = new BufferedReader(new FileReader(jsonFile))){
                    StringBuilder jsonText = new StringBuilder();
                    String linea;
                    while((linea=br.readLine())!=null){
                        jsonText.append(linea);
                    }
                    if(!jsonText.isEmpty()){
                        jArray = new JSONArray(jsonText.toString());
                    }
                }
            }
            JSONObject nuevaNota = new JSONObject();
            nuevaNota.put("contenido",contenido);
            jArray.put(nuevaNota);
            try(FileWriter fileWriter = new FileWriter(JSON_NAME,false)){
                fileWriter.write(jArray.toString(4));
                fileWriter.flush();
                System.out.println("Se guardó correctamente");
            }
        }catch(IOException e){
            System.out.println("No se ha guardado esa pinga, hubo un error: " + e.getMessage());
        }
    }
}
