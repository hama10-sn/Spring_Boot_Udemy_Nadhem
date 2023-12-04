package com.mballo.produits.service;

import com.mballo.produits.entities.Produit;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProduitService {

    Produit saveProduit(Produit p);
    Produit updateProduit(Produit p);
    void deleteProduit(Produit p);
    void deleteProduitById(Long id);
    Produit getProduit(Long id);
    List<Produit> listProduits();

    Page<Produit> getAllProduitsParPage(int page, int size);
}
