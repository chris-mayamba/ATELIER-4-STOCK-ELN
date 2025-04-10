package org.example.Data;



public class ProduitElectro {
    private int ID;
    private String Nom;
    private String Fabricant;
    private long Prix;
    private String Ram;
    private String Rom;
    private String OS;
    private String CPU;
    private Stock stock ;

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Stock getStock() {
        return stock;
    }



    public ProduitElectro() {
    }

    public ProduitElectro(int ID, String nom, String fabricant, long prix, String ram, String rom, String OS, String CPU) {
        this.ID = ID;
        this.Nom = nom;
        this.Fabricant = fabricant;
        this.Prix = prix;
        this.Ram = ram;
        this.Rom = rom;
        this.OS = OS;
        this.CPU = CPU;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        this.Nom = nom;
    }

    public String getFabricant() {
        return Fabricant;
    }

    public void setFabricant(String fabricant) {
        this.Fabricant = fabricant;
    }

    public long getPrix() {
        return Prix;
    }

    public void setPrix(long prix) {
        this.Prix = prix;
    }

    public String getRam() {
        return Ram;
    }

    public void setRam(String ram) {
        this.Ram = ram;
    }

    public String getRom() {
        return Rom;
    }

    public void setRom(String rom) {
        this.Rom = rom;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }
    @Override
    public String toString() {
        return "data.ProduitElectro{" +
                "ID=" + ID +
                ", Nom='" + Nom + '\'' +
                ", Fabricant='" + Fabricant + '\'' +
                ", Prix=" + Prix +
                ", Ram='" + Ram + '\'' +
                ", Rom='" + Rom + '\'' +
                ", OS='" + OS + '\'' +
                ", CPU='" + CPU + '\'' +
                ", stock=" + stock +
                '}';
    }
}

