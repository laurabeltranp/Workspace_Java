package org.example;

import java.io.*;
import java.nio.file.*;

public class ProfileManager {
    private static final String INPUT_DIRECTORY = "user_profiles/";
    private static final String OUTPUT_DIRECTORY = "user_profiles_output/";

    public ProfileManager() {
        File outputDir = new File(OUTPUT_DIRECTORY);
        if (!outputDir.exists()) {
            outputDir.mkdir();
        }
    }

    /**
     * Método para verificar si un perfil ya está registrado.
     * @param userId El ID del usuario que se desea verificar.
     * @return true si el perfil ya está registrado, false en caso contrario.
     */
    public boolean isProfileRegistered(String userId) {
        String userOutputDirPath = OUTPUT_DIRECTORY + "user_" + userId;
        File profileFile = new File(userOutputDirPath + "/profile.ser");
        return profileFile.exists();
    }

    public void registerProfile(String userId) throws IOException {
        String userDirPath = INPUT_DIRECTORY + "user_" + userId;
        File formFile = new File(userDirPath + "/form.txt");
        File photoFileJpg = new File(userDirPath + "/photo.jpg");
        File photoFilePng = new File(userDirPath + "/photo.png");

        File photoFile = null;
        if (photoFileJpg.exists()) {
            photoFile = photoFileJpg;
        } else if (photoFilePng.exists()) {
            photoFile = photoFilePng;
        } else {
            throw new FileNotFoundException("La imagen de perfil (photo.jpg o photo.png) no fue encontrada.");
        }

        if (!formFile.exists()) {
            throw new FileNotFoundException("El archivo de formulario (form.txt) no fue encontrado en " + userDirPath);
        }

        if (photoFile.length() == 0) {
            throw new IOException("La imagen de perfil no puede estar vacía.");
        }

        // Leer datos del texto
        String name, nickname, email;
        int age;
        try (BufferedReader reader = new BufferedReader(new FileReader(formFile))) {
            reader.readLine(); // Saltar la línea de UserId
            name = reader.readLine().split(": ")[1];
            age = Integer.parseInt(reader.readLine().split(": ")[1]);
            nickname = reader.readLine().split(": ")[1];
            email = reader.readLine().split(": ")[1];
        } catch (IOException | NumberFormatException e) {
            throw new IOException("Error al leer o procesar el formulario.", e);
        }

        // Crear el objeto User
        User user = new User(userId, name, age, nickname, email);

        // Crear directorio de salida para el usuario
        String userOutputDirPath = OUTPUT_DIRECTORY + "user_" + userId;
        File userOutputDir = new File(userOutputDirPath);
        if (!userOutputDir.exists()) {
            userOutputDir.mkdir();
        }

        // Serializar el perfil en el directorio de salida
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(userOutputDirPath + "/profile.ser"))) {
            out.writeObject(user);
            System.out.println("Perfil registrado exitosamente para el usuario " + userId);
        }

        // Copiar la imagen de perfil al directorio de salida
        Path destination = Paths.get(userOutputDirPath + "/" + photoFile.getName());
        Files.copy(photoFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Imagen de perfil copiada exitosamente.");
    }

    public User searchProfile(String userId) throws IOException, ClassNotFoundException {
        String userOutputDirPath = OUTPUT_DIRECTORY + "user_" + userId;
        File profileFile = new File(userOutputDirPath + "/profile.ser");

        if (!profileFile.exists()) {
            System.out.println("El usuario con ID " + userId + " no existe.");
            return null;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(profileFile))) {
            User user = (User) in.readObject();
            System.out.println("Perfil encontrado:");
            System.out.println(user);

            File photoFile = new File(userOutputDirPath + "/photo.jpg");
            if (!photoFile.exists()) {
                photoFile = new File(userOutputDirPath + "/photo.png");
            }

            if (photoFile.exists()) {
                System.out.println("Imagen de perfil localizada en: " + photoFile.getAbsolutePath());
            } else {
                System.out.println("Imagen de perfil no encontrada.");
            }
            return user;
        }
    }
}