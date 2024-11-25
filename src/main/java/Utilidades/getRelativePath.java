/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilidades;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ian
 */
public class getRelativePath {
    File archivo = new File("src\\main\\java\\Recursos");

    public String getFatherPath() {
        System.out.println(archivo.getAbsolutePath());
        return archivo.getAbsolutePath();
    }
    
    public String getMainPath(){
        File archivo = new File("src\\main\\");
        return archivo.getAbsolutePath();
    }

    // Método para obtener las imágenes de una carpeta específica
    public List<File> getImagenesEnCarpeta(String nombreCarpeta) {
        List<File> imagenes = new ArrayList<>();
        File carpeta = new File("src\\main\\java\\" + nombreCarpeta);

        if (carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles((dir, nombre) ->
                    nombre.toLowerCase().endsWith(".png") ||
                    nombre.toLowerCase().endsWith(".jpg") ||
                    nombre.toLowerCase().endsWith(".jpeg"));
            if (archivos != null) {
                for (File archivo : archivos) {
                    imagenes.add(archivo);
                }
            }
        }
        return imagenes;
    }
}

