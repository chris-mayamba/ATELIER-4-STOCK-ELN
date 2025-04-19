package org.example.Json;

import org.example.Data.Stock;

import java.io.*;

public class StockFileRepository implements StockRepository {
    private final String filename;

    public StockFileRepository(String filename) {
        this.filename = filename;
    }

    @Override
    public void save(Stock stock) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(stock);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'enregistrement du stock", e);
        }
    }

    @Override
    public Stock load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Stock) ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du chargement du stock", e);
        }
    }
}
