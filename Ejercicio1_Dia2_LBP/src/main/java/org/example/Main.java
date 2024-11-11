package org.example;

import java.io.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Definir nombres de los archivos
        String archivoEntrada = "input.txt";
        String archivoSalida = "output.txt";
        //Inicializamos los contadores
        int totalLineas = 0;
        int totalPalabras = 0;
        int totalCaracteres = 0;

        try (BufferedReader lector = new BufferedReader((new FileReader((archivoEntrada))))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                totalLineas++;
                totalCaracteres += linea.length();
                String[] palabras = linea.split("\\s+");
                totalPalabras += palabras.length;
            }
            } catch(IOException e){
                System.out.println("Error al leer el archivo" + e.getMessage());
                return;
            }

            //Escribimos el resultado
            try (BufferedWriter escritor = new BufferedWriter(new FileWriter((archivoSalida)))) {
                escritor.write("Estadisticas del archivo:\n");
                escritor.write("Lineas: :" + totalLineas + "\n");
                escritor.write("Palabras: :" + totalPalabras + "\n");
                escritor.write("Caracteres: :" + totalCaracteres + "\n");
                System.out.println("Estadisticas guardadas en " + archivoSalida);

            } catch (IOException e) {
                System.out.println("Error al escribir el archivo de resultados:" + e.getMessage());
            }
        }
    }