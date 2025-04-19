package org.example.Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import org.example.Data.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StockJsonRepository implements StockRepository {
    private final String filename;
    private final Gson gson;

    public StockJsonRepository(String filename) {
        this.filename = filename;

        RuntimeTypeAdapterFactory<ProduitElectro> typeFactory = RuntimeTypeAdapterFactory
                .of(ProduitElectro.class, "type")
                .registerSubtype(Telephone.class, "telephone")
                .registerSubtype(Ordinateur.class, "ordinateur")
                .registerSubtype(Accessoires.class, "accessoires");

        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapterFactory(typeFactory)
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @Override
    public void save(Stock stock) {
        try (FileWriter writer = new FileWriter(filename)) {
            // Utilise prepareForSerialization pour éviter les références circulaires
            gson.toJson(stock.prepareForSerialization(), writer);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'enregistrement JSON du stock", e);
        }
    }

    @Override
    public Stock load() {
        try (FileReader reader = new FileReader(filename)) {
            Stock loadedStock = gson.fromJson(reader, Stock.class);
            // Réétablir les références après chargement
            if (loadedStock != null) {
                for (ProduitQuantite pq : loadedStock.getProduits()) {
                    pq.getProduit().setStock(loadedStock);
                }
            }
            return loadedStock != null ? loadedStock : new Stock();
        } catch (FileNotFoundException e) {
            return new Stock();
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors du chargement du fichier JSON", e);
        }
    }
}