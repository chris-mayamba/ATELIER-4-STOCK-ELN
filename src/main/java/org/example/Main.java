package org.example;

import org.example.Data.*;
import org.example.Json.StockJsonRepository;
import org.example.database.DatabaseManager;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StockJsonRepository repo = new StockJsonRepository("stock.json");

        // Charger le stock depuis le fichier JSON
        Stock stock1 = repo.load();

        DatabaseManager dbManager = new DatabaseManager();

        // Charger les produits depuis la base de données
        Stock stock = new Stock();
        List<ProduitElectro> produitsBDD = dbManager.lireTousProduits();
        for (ProduitElectro p : produitsBDD) {
            stock.ajouterProduit(p, 1); // Ajoute avec quantité 1 par défaut
        }
        boolean running = true;

        while (running) {
            System.out.println("\n=== MENU STOCK ===");
            System.out.println("1. Ajouter un produit au stock");
            System.out.println("2. Retirer un produit du stock");
            System.out.println("3. Afficher le stock");
            System.out.println("4. Quitter");
            System.out.print("Choix: ");

            int choix = scanner.nextInt();
            scanner.nextLine(); // consommer retour chariot

            switch (choix) {
                case 1:
                    System.out.print("Nom du produit: ");
                    String nom = scanner.nextLine();

                    ProduitElectro produitAjoute = null;
                    System.out.print("Type du produit (1: Téléphone, 2: Ordinateur, 3: Accessoires): ");
                    int typeProduit = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Fabricant: ");
                    String fabricant = scanner.nextLine();
                    System.out.print("Prix: ");
                    long prix = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("RAM: ");
                    String ram = scanner.nextLine();
                    System.out.print("ROM: ");
                    String rom = scanner.nextLine();
                    System.out.print("OS: ");
                    String os = scanner.nextLine();
                    System.out.print("CPU: ");
                    String cpu = scanner.nextLine();

                    if (typeProduit == 1) {
                        System.out.print("Double SIM (true/false): ");
                        boolean doubleSim = scanner.nextBoolean();
                        scanner.nextLine();
                        produitAjoute = new Telephone(0, nom, fabricant, prix, ram, rom, os, cpu);

                    } else if (typeProduit == 2) {
                        System.out.print("GPU: ");
                        String gpu = scanner.nextLine();
                        System.out.print("Type Ordinateur: ");
                        String typeOrdinateur = scanner.nextLine();
                        produitAjoute = new Ordinateur(0, nom, fabricant, prix, ram, rom, os, cpu, gpu, typeOrdinateur);

                    } else if (typeProduit == 3) {
                        System.out.print("Type d'accessoire: ");
                        String typeAccessoires = scanner.nextLine();
                        produitAjoute = new Accessoires(0, nom, fabricant, prix, ram, rom, os, cpu, typeAccessoires);
                    }

                    if (produitAjoute != null) {
                        System.out.print("Quantité à ajouter: ");
                        int qteAjoute = scanner.nextInt();
                        scanner.nextLine();
                        stock.ajouterProduit(produitAjoute, qteAjoute);
                        dbManager.ajouterProduit(produitAjoute, qteAjoute); // Enregistre dans la BDD
                    } else {
                        System.out.println("Type de produit invalide.");
                    }
                    break;

                case 2:
                    System.out.print("Nom du produit à retirer: ");
                    String nomRetirer = scanner.nextLine();

                    ProduitElectro produitRetire = stock.getProduits().stream()
                            .map(ProduitQuantite::getProduit)
                            .filter(p -> p.getNom().equalsIgnoreCase(nomRetirer))
                            .findFirst()
                            .orElse(null);

                    if (produitRetire == null) {
                        System.out.println("Produit non trouvé dans le stock.");
                    } else {
                        stock.retirerProduit(produitRetire);
                        dbManager.supprimerStock(produitRetire.getID());
                        dbManager.supprimerProduit(produitRetire.getID());
                    }
                    break;

                case 3:
                    stock.afficherStock();
                    System.out.println("=== Stock actuel ===");
                    List<ProduitQuantite> stockBDD = dbManager.lireStockComplet(); // méthode à créer
                    if (stockBDD.isEmpty()) {
                        System.out.println("Le stock est vide.");
                    } else {
                        for (ProduitQuantite pq : stockBDD) {
                            System.out.println(pq.getProduit().getNom() + " - " + pq.getQuantite() + " unités");
                        }
                    }
                    break;

                case 4:
                    running = false;
                    break;

                default:
                    System.out.println("Choix invalide. Réessayer.");
            }
        }

        System.out.println("Programme terminé.");
        scanner.close();
    }
}