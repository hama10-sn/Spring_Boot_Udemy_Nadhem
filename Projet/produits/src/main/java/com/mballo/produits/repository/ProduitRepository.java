package com.mballo.produits.repository;

import com.mballo.produits.entities.Categorie;
import com.mballo.produits.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

    List<Produit> findByNomProduit(String nom);
    List<Produit> findByNomProduitContains(String nom);

//    Ici ?1 ou ?2 signifie 1er parametre, 2em parametre etc...
    /*@Query("select p from Produit p where p.nomProduit LIKE %?1 and p.prixProduit > ?2")
    List<Produit> findByNomPrix(String nom, Double prix);*/
    @Query("select p from Produit p where p.nomProduit LIKE %:nom and p.prixProduit > :prix")
    List<Produit> findByNomPrix(@Param("nom") String nom, @Param("prix") Double prix);
    @Query("select p from Produit p where p.categorie = :categorie")
    List<Produit> findByCategorie(Categorie categorie);
    List<Produit> findByCategorieIdCat(Long id);
    List<Produit> findByOrderByNomProduitAsc();
    @Query("SELECT p FROM Produit p ORDER BY p.nomProduit ASC, p.prixProduit DESC")
    List<Produit> trierProduitsNomsPrix();
}
