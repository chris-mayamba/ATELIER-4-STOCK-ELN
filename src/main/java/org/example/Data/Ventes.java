package org.example.Data;


public class Ventes {
    private String NomCl;
    private String NomProd;
    private int Quantite;
    private int PrixU;
    private ProduitElectro produit;

    public Ventes(String nomCl, String nomProd, int quantite, int prixU, Stock stock) {
        NomCl = nomCl;
        NomProd = nomProd;
        Quantite = quantite;
        PrixU = prixU;

        // Recherche du produit par son nom dans le stock
        ProduitElectro produit = null;
        for (ProduitElectro p : stock.getProduits().keySet()) {
            if (p.getNom().equals(nomProd)) {
                produit = p;
                break;
            }
        }

        if (produit != null) {
            this.produit = produit;
            // Retirer le produit du stock
            stock.retirerProduit(produit);  // Utilisation de l'objet data.ProduitElectro
            System.out.println("Produit " + produit.getNom() + " vendu à " + NomCl + ".");
        } else {
            System.out.println("Produit " + nomProd + " non trouvé dans le stock.");
        }
    }

    public String getNomCl() {
        return NomCl;
    }

    public String getNomProd() {
        return NomProd;
    }

    public int getQuantite() {
        return Quantite;
    }

    public int getPrixU() {
        return PrixU;
    }

    public ProduitElectro getProduit() {
        return produit;
    }

    @Override
    public String toString() {
        return "data.Ventes{" +
                "NomCl='" + NomCl + '\'' +
                ", NomProd='" + NomProd + '\'' +
                ", Quantite=" + Quantite +
                ", PrixU=" + PrixU +
                '}';
    }
}

