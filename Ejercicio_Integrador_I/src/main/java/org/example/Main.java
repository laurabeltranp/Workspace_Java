package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProfileManager manager = new ProfileManager();
        Scanner scanner = new Scanner(System.in); // No cerrar el scanner aquí
        boolean running = true;

        while (running) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Registrar un perfil");
            System.out.println("2. Buscar un perfil");
            System.out.println("3. Salir");

            int option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (option) {
                case 1:
                    System.out.print("Ingrese el userId para registrar el perfil: ");
                    String userId = scanner.nextLine();
                    try {
// Verificar si el perfil ya está registrado
                        if (manager.isProfileRegistered(userId)) {
                            System.out.println("El perfil con el ID " + userId + " ya está registrado.");
                        } else {
                            manager.registerProfile(userId);
                        }
                    } catch (IOException e) {
                        System.err.println("Error al registrar el perfil: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Ingrese el userId para buscar el perfil: ");
                    userId = scanner.nextLine();
                    try {
                        if (manager.searchProfile(userId) == null) {
                            System.out.println("El usuario con ID " + userId + " no existe.");
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        System.err.println("Error al buscar el perfil: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Saliendo del programa...");
                    running = false;
                    break;

                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        }

        scanner.close(); // Cerrar el scanner aquí, al final
    }
}