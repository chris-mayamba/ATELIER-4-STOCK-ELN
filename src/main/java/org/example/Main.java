package org.example;

import org.example.Data.*;
import org.example.database.DatabaseManager;
import org.example.dao.ProduitsDAO;
import org.example.database.IDatabaseConfig;
import org.example.database.MYSQLConfig;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        IDatabaseConfig config = new MYSQLConfig();

        DatabaseManager manager = new DatabaseManager(config);

        ProduitsDAO dao = new ProduitsDAO(manager);
        boolean running = true;

        while (running) {
            System.out.println("\n=== MENU STOCK ===");
            System.out.println("1. Ajouter un produit au stock");
            System.out.println("2. Retirer un produit du stock");
            System.out.println("3. Afficher le stock");
            System.out.println("4. Modifier un produit du stock");
            System.out.println("5. Quitter");
            System.out.print("Choix: ");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer le retour chariot

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
                    double prix = scanner.nextDouble();
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
                        produitAjoute = new Telephone(0, nom, fabricant, (long) prix, ram, rom, os, cpu);
                    } else if (typeProduit == 2) {
                        System.out.print("GPU: ");
                        String gpu = scanner.nextLine();
                        System.out.print("Type Ordinateur: ");
                        String typeOrdinateur = scanner.nextLine();
                        produitAjoute = new Ordinateur(0, nom, fabricant, (long) prix, ram, rom, os, cpu, gpu, typeOrdinateur);
                    } else if (typeProduit == 3) {
                        System.out.print("Type d'accessoire: ");
                        String typeAccessoires = scanner.nextLine();
                        produitAjoute = new Accessoires(0, nom, fabricant, (long) prix, ram, rom, os, cpu, typeAccessoires);
                    }

                    if (produitAjoute != null) {
                        System.out.print("Quantité à ajouter: ");
                        int qteAjoute = scanner.nextInt();
                        scanner.nextLine();
                        produitAjoute.setQuantite(qteAjoute);
                        dao.addProduits(produitAjoute);
                        System.out.println("✅ Produit ajouté avec succès !");
                    } else {
                        System.out.println("Type de produit invalide.");
                    }
                    break;

                case 2:
                    System.out.print("Nom du produit à retirer: ");
                    String nomRetirer = scanner.nextLine();

                    List<ProduitElectro> produits = dao.getProduits();
                    ProduitElectro produitRetire = produits.stream()
                            .filter(p -> p.getNom().equalsIgnoreCase(nomRetirer))
                            .findFirst()
                            .orElse(null);

                    if (produitRetire == null) {
                        System.out.println("Produit non trouvé.");
                    } else {
                        dao.deleteProduits(produitRetire);
                        System.out.println("✅ Produit retiré du stock !");
                    }
                    break;

                case 3:
                    System.out.println("=== Stock actuel MYSQL ===");
                    List<ProduitElectro> stockBDD = dao.getProduits();
                    if (stockBDD.isEmpty()) {
                        System.out.println("Le stock est vide.");
                    } else {
                        for (ProduitElectro p : stockBDD) {
                            afficherProduit(p);
                        }
                    }
                    break;

                case 4:
                    System.out.print("Nom du produit à modifier: ");
                    String nomModifier = scanner.nextLine();

                    List<ProduitElectro> produitsExistants =dao.getProduits();
                    ProduitElectro produitAModifier = produitsExistants.stream()
                            .filter(p -> p.getNom().equalsIgnoreCase(nomModifier))
                            .findFirst()
                            .orElse(null);

                    if (produitAModifier == null) {
                        System.out.println("Produit non trouvé.");
                    } else {
                        System.out.println("Modification du produit :");

                        System.out.print("Nouveau nom (" + produitAModifier.getNom() + "): ");
                        String nouveauNom = scanner.nextLine();
                        if (!nouveauNom.isEmpty()) produitAModifier.setNom(nouveauNom);

                        System.out.print("Nouveau fabricant (" + produitAModifier.getFabricant() + "): ");
                        String nouveauFabricant = scanner.nextLine();
                        if (!nouveauFabricant.isEmpty()) produitAModifier.setFabricant(nouveauFabricant);

                        System.out.print("Nouveau prix (" + produitAModifier.getPrix() + "): ");
                        String prixInput = scanner.nextLine();
                        if (!prixInput.isEmpty()) produitAModifier.setPrix(Double.parseDouble(prixInput));

                        System.out.print("Nouvelle RAM (" + produitAModifier.getRam() + "): ");
                        String nouvelleRam = scanner.nextLine();
                        if (!nouvelleRam.isEmpty()) produitAModifier.setRam(nouvelleRam);

                        System.out.print("Nouvelle ROM (" + produitAModifier.getRom() + "): ");
                        String nouvelleRom = scanner.nextLine();
                        if (!nouvelleRom.isEmpty()) produitAModifier.setRom(nouvelleRom);

                        System.out.print("Nouveau OS (" + produitAModifier.getOS() + "): ");
                        String nouvelOs = scanner.nextLine();
                        if (!nouvelOs.isEmpty()) produitAModifier.setOS(nouvelOs);

                        System.out.print("Nouveau CPU (" + produitAModifier.getCPU() + "): ");
                        String nouveauCpu = scanner.nextLine();
                        if (!nouveauCpu.isEmpty()) produitAModifier.setCPU(nouveauCpu);

                        System.out.print("Nouveau type (" + produitAModifier.getType() + "): ");
                        String nouveauType = scanner.nextLine();
                        if (!nouveauType.isEmpty()) produitAModifier.setType(nouveauType);

                        if (produitAModifier instanceof Ordinateur) {
                            Ordinateur ordi = (Ordinateur) produitAModifier;
                            System.out.print("Nouveau GPU (" + ordi.getGPU() + "): ");
                            String nouveauGpu = scanner.nextLine();
                            if (!nouveauGpu.isEmpty()) ordi.setGPU(nouveauGpu);

                            System.out.print("Nouveau type ordinateur (" + ordi.getTypeOrdinateur() + "): ");
                            String nouveauTypeOrdi = scanner.nextLine();
                            if (!nouveauTypeOrdi.isEmpty()) ordi.setTypeOrdinateur(nouveauTypeOrdi);

                        } else if (produitAModifier instanceof Accessoires) {
                            Accessoires acc = (Accessoires) produitAModifier;
                            System.out.print("Nouveau type accessoire (" + acc.getTypeAccessoires() + "): ");
                            String nouveauTypeAcc = scanner.nextLine();
                            if (!nouveauTypeAcc.isEmpty()) acc.setTypeAccessoires(nouveauTypeAcc);
                        }

                        System.out.print("Nouvelle quantité (" + produitAModifier.getQuantite() + "): ");
                        String quantiteInput = scanner.nextLine();
                        if (!quantiteInput.isEmpty()) produitAModifier.setQuantite(Integer.parseInt(quantiteInput));

                        dao.updateProduits(produitAModifier);
                        System.out.println("✅ Produit modifié avec succès !");
                    }
                    break;

                case 5:
                    running = false;
                    break;

                default:
                    System.out.println("Choix invalide. Réessayer.");
            }
        }

        System.out.println("Programme terminé.");
        scanner.close();
    }

    private static void afficherProduit(ProduitElectro p) {
        System.out.println("ID: " + p.getID());
        System.out.println("Nom: " + p.getNom());
        System.out.println("Fabricant: " + p.getFabricant());
        System.out.println("Prix: " + p.getPrix() + "€");
        System.out.println("RAM: " + p.getRam());
        System.out.println("ROM: " + p.getRom());
        System.out.println("OS: " + p.getOS());
        System.out.println("CPU: " + p.getCPU());
        System.out.println("Type: " + p.getType());
        if (p instanceof Ordinateur) {
            Ordinateur ordi = (Ordinateur) p;
            System.out.println("GPU: " + ordi.getGPU());
            System.out.println("Type d'ordinateur: " + ordi.getTypeOrdinateur());
        } else if (p instanceof Accessoires) {
            Accessoires acc = (Accessoires) p;
            System.out.println("Type d'accessoire: " + acc.getTypeAccessoires());
        }
        System.out.println("Quantité: " + p.getQuantite());
        System.out.println("====================================");
    }
}
