package org.example;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        File file = new File("ejemplo.txt");
        //Crear archivo
        try{
            if (file.createNewFile()){
                System.out.println("Archivo creado: " + file.getName());
                System.out.println("Ruta absoluta: " + file.getAbsolutePath());
            } else{
                System.out.println("El archivo ya existe");
            }
        }catch (IOException e){
            System.out.println("Ocurrió un error al crear el archivo");
            e.printStackTrace();
        }
        //Leer archivo
        try (FileReader reader = new FileReader("ejemplo.txt")){
            int character;
            while ((character = reader.read()) != -1){
                System.out.println((char) character);
            }
            System.out.println();
        }catch(IOException e){
            System.out.println("ocurrió un error al leer el archivo");
            e.printStackTrace();
        }
        //Escribir archivo
                String firstFileName = "output.txt";
        try (FileWriter writer = new FileWriter(firstFileName)){
            writer.write("-----------------------------------\n");
            writer.write("Hola esto es una prueba");
            System.out.println("Escritura completada");
        }catch (IOException e){
            System.out.println("Ocurrió un error");
            e.printStackTrace();
        }
    }
}