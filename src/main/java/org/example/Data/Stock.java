package org.example.Data;



import java.util.HashMap;
import java.util.Map;

public class Stock {
    private Map<ProduitElectro, Integer> produits;

    public Stock() {
        produits = new HashMap<>();
    }

    // Ajouter un produit au stock
    public void ajouterProduit(ProduitElectro produit, int quantite) {
        produit.setStock(this); // Lien bidirectionnel
        int ancienneQuantite = produits.getOrDefault(produit, 0);
        produits.put(produit, ancienneQuantite + quantite);
        System.out.println(quantite + " unités de " + produit.getNom() + " ajoutées au stock.");
    }

    // Retirer un produit du stock par son nom
    public void retirerProduit(ProduitElectro produit) {
        if (produits.containsKey(produit)) {
            int quantite = produits.get(produit);
            if (quantite > 0) {
                produits.put(produit, 0);  // Retirer tout le stock du produit
                System.out.println("Produit retiré du stock : " + produit.getNom());
            } else {
                System.out.println("Aucun stock disponible pour ce produit.");
            }
        } else {
            System.out.println("Produit non trouvé dans le stock.");
        }
    }


    // Afficher le stock
    public void afficherStock() {
        System.out.println("=== data.Stock actuel ===");
        if (produits.isEmpty()) {
            System.out.println("Aucun produit en stock.");
            return;
        }
        for (Map.Entry<ProduitElectro, Integer> entry : produits.entrySet()) {
            ProduitElectro p = entry.getKey();
            int quantite = entry.getValue();
            System.out.println(p.getNom() + " - " + quantite + " unités");
        }
    }

    // Récupérer la quantité totale
    public int getQuantiteTotale() {
        return produits.values().stream().mapToInt(Integer::intValue).sum();
    }

    public Map<ProduitElectro, Integer> getProduits() {
        return produits;
    }
}

