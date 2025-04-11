package org.example.Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.Data.ProduitElectro;

import java.io.*;
import java.util.List;

public class ProduitJsonRepository {

    private final String fileName;
    private final Gson gson;

    public ProduitJsonRepository(String fileName) {
        this.fileName = fileName;
        this.gson = new GsonBuilder().setPrettyPrinting().create(); // Création du Gson
    }

    // Méthode pour sauvegarder une liste de produits (Stock) dans le fichier JSON
    public void save(List<ProduitElectro> produits) {
        try (Writer writer = new FileWriter(fileName)) {
            gson.toJson(produits, writer);  // Sérialisation de la liste de produits
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'enregistrement dans le fichier JSON", e);
        }
    }

    // Méthode pour charger les produits depuis le fichier JSON
    public List<ProduitElectro> load() {
        try (Reader reader = new FileReader(fileName)) {
            // Désérialisation du fichier JSON dans une liste de produits
            return gson.fromJson(reader, List.class);
        } catch (FileNotFoundException e) {
            return null;  // Si le fichier n'existe pas, on retourne null
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors du chargement du fichier JSON", e);
        }
    }
}

