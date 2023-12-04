package com.mballo.produits.restcontrollers;

import com.mballo.produits.entities.Produit;
import com.mballo.produits.service.ProduitService;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProduitRESTController {

    @Autowired
    ProduitService produitService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Produit> getAllProduit(){
        return produitService.getAllProduits();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Produit getProduitById(@PathVariable("id") Long id){
        return produitService.getProduit(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Produit createProduit(@RequestBody Produit produit){
        return produitService.saveProduit(produit);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Produit updateProduit(@RequestBody Produit produit){
        return produitService.updateProduit(produit);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteProduit(@PathVariable("id") Long id){
        produitService.deleteProduitById(id);
    }

    @RequestMapping(value = "/prodscat/{idCat}", method = RequestMethod.GET)
    public List<Produit> getProduitByCatId(@PathVariable("idCat") Long id){
        return produitService.findByCategorieIdCat(id);
    }
}
