package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MultipleExceptionsDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {

            System.out.println("ingrese el primer numero");
            int num1 = scanner.nextInt();

            System.out.println("ingrese el segundo numero");
            int num2 = scanner.nextInt();

            int result = num1/num2;
            System.out.println("el resultado de la division es:" + result);

            try (BufferedReader br = new BufferedReader(new FileReader("datos.txt"))) {
                String linea = br.readLine();
                System.out.println("Contenido del archivo:" + linea);

            }
        }catch (FileNotFoundException e) {
            System.out.println("Error: archivo no encontrado");
        }catch (InputMismatchException e) {
            System.out.println("error: entrada valida. Se esperaba un numero entero.");

        } catch (ArithmeticException e){
            System.out.println("error: division por cero");
        }  catch (IOException e) {
            System.out.println("eeror de E/S:" + e.getMessage());
        } catch (Exception e){
            System.out.println("Error inserperado:" + e.getMessage());
        } finally {
            scanner.close();
        }

    }
}