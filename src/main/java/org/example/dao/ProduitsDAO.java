package org.example.dao;

import org.example.Data.ProduitElectro;
import org.example.database.IConnectionProvider;

import java.sql.;
import java.util.ArrayList;
import java.util.List;

public class ProduitsDAO implements IProduitDAO{
    public IConnectionProvider conn;

    public ProduitsDAO(IConnectionProvider conn){
        this.conn = conn;
    }

    @Override
    public void addProduits(ProduitElectro produitElectro) {
        // Ici l'ID est auto-incrémenté, donc on ne l'insère pas manuellement
        String sql = "INSERT INTO produit (NOM, FABRICANT, prix, RAM, ROM, OS, CPU, TYPE, GPU) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = this.conn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produitElectro.getNom());
            stmt.setString(2, produitElectro.getFabricant());
            stmt.setDouble(3, produitElectro.getPrix());
            stmt.setString(4, produitElectro.getRam());
            stmt.setString(5, produitElectro.getRom());
            stmt.setString(6, produitElectro.getOS());
            stmt.setString(7, produitElectro.getCPU());
            stmt.setString(8, produitElectro.getType());
            stmt.setString(9, produitElectro.getGPU());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteProduits(ProduitElectro produitElectro){
        String sql = "DELETE FROM produit WHERE ID = ?";

        try(Connection conn = this.conn.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,produitElectro.getID());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void updateProduits(ProduitElectro produitElectro) {
        String sql = "UPDATE produit SET NOM = ?, FABRICANT = ?, prix = ?, RAM = ?, ROM = ?, OS = ?, CPU = ?, TYPE = ?, GPU = ? WHERE ID = ?";

        try (Connection connection = this.conn.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, produitElectro.getNom());
            stmt.setString(2, produitElectro.getFabricant());
            stmt.setDouble(3, produitElectro.getPrix());
            stmt.setString(4, produitElectro.getRam());
            stmt.setString(5, produitElectro.getRom());
            stmt.setString(6, produitElectro.getOS());
            stmt.setString(7, produitElectro.getCPU());
            stmt.setString(8, produitElectro.getType());
            stmt.setString(9, produitElectro.getGPU());
            stmt.setInt(10, produitElectro.getID());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<ProduitElectro> getProduits() {
        List<ProduitElectro> produits = new ArrayList<>();
        String sql = "SELECT * FROM produit";

        try (Connection connection = this.conn.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ProduitElectro produit = new ProduitElectro();
                produit.setID(rs.getInt("ID"));
                produit.setNom(rs.getString("NOM"));
                produit.setFabricant(rs.getString("FABRICANT"));
                produit.setPrix(rs.getDouble("prix"));
                produit.setRam(rs.getString("RAM"));
                produit.setRom(rs.getString("ROM"));
                produit.setOS(rs.getString("OS"));
                produit.setCPU(rs.getString("CPU"));
                produit.setType(rs.getString("TYPE"));
                produit.setGPU(rs.getString("GPU"));

                produits.add(produit);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return produits;
    }
}
