package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        /*
        Scanner scanner = new Scanner(System.in);
        Stock stock = new Stock();
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

                    // Création d'un produit en fonction du nom
                    ProduitElectro produitAjoute = null;
                    System.out.print("Type du produit (1: Téléphone, 2: data.Ordinateur, 3: data.Accessoires): ");
                    int typeProduit = scanner.nextInt();
                    scanner.nextLine(); // consommer retour chariot

                    if (typeProduit == 1) {
                        // Création d'un téléphone
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
                        System.out.print("Double SIM (true/false): ");
                        boolean doubleSim = scanner.nextBoolean();
                        scanner.nextLine(); // consommer retour chariot

                        produitAjoute = new Telephone(0, nom, fabricant, prix, ram, rom, os, cpu, doubleSim);
                    } else if (typeProduit == 2) {
                        // Création d'un ordinateur
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
                        System.out.print("GPU: ");
                        String gpu = scanner.nextLine();
                        System.out.print("Type data.Ordinateur: ");
                        String typeOrdinateur = scanner.nextLine();

                        produitAjoute = new Ordinateur(0, nom, fabricant, prix, ram, rom, os, cpu, gpu, typeOrdinateur);
                    } else if (typeProduit == 3) {
                        // Création d'un accessoire
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
                        System.out.print("Type d'accessoire: ");
                        String typeAccessoires = scanner.nextLine();

                        produitAjoute = new Accessoires(0, nom, fabricant, prix, ram, rom, os, cpu, typeAccessoires);
                    }

                    if (produitAjoute != null) {
                        System.out.print("Quantité à ajouter: ");
                        int qteAjoute = scanner.nextInt();
                        scanner.nextLine(); // consommer retour chariot

                        stock.ajouterProduit(produitAjoute, qteAjoute);
                    } else {
                        System.out.println("Type de produit invalide.");
                    }
                    break;

                case 2:
                    System.out.print("Nom du produit à retirer: ");
                    String nomRetirer = scanner.nextLine();

                    // Recherche du produit par nom dans le stock
                    ProduitElectro produitRetire = stock.getProduits().keySet().stream()
                            .filter(p -> p.getNom().equalsIgnoreCase(nomRetirer))
                            .findFirst()
                            .orElse(null);

                    if (produitRetire == null) {
                        System.out.println("Produit non trouvé dans le stock.");
                        break;
                    }

                    stock.retirerProduit(produitRetire); // Retirer le produit en utilisant l'objet data.ProduitElectro
                    break;


                case 3:
                    stock.afficherStock();
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

         */





    }



}
