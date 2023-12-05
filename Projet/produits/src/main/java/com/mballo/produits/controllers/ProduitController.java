package com.mballo.produits.controllers;

import com.mballo.produits.entities.Produit;
import com.mballo.produits.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ProduitController {

    @Autowired
    ProduitService produitService;

    /*@RequestMapping("/showCreate")
    public String showCreate() {
        return "createProduit";
    }*/

    @RequestMapping("/showCreate")
    public String showCreate(ModelMap modelMap) {
        modelMap.addAttribute("produit", new Produit());
        modelMap.addAttribute("mode", "new");
        return "formProduit";
    }

   /* @RequestMapping("/saveProduit")
    public String saveProduit(@ModelAttribute("produit") Produit produit,
                              @RequestParam("date") String date,
                              ModelMap modelMap) throws ParseException {

        //conversion de la date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreation = dateFormat.parse(String.valueOf(date));

        produit.setDateCreation(dateCreation);

        Produit saveProduit = produitService.saveProduit(produit);
        String msg = "produit enregistré avec Id " + saveProduit.getIdProduit();
        modelMap.addAttribute("msg", msg);

        return "createProduit";
    }*/

    /*@RequestMapping("/saveProduit")
    public String saveProduit(@ModelAttribute("produit") Produit produit) {
        produitService.saveProduit(produit);
        return "createProduit";
    }*/

    @RequestMapping("/saveProduit")
    public String saveProduit(@Valid Produit produit, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) return "formProduit";

        produitService.saveProduit(produit);
        return "formProduit";
    }

   /* @RequestMapping("/ListeProduits")
    public String listeProduits(ModelMap modelMap) {

        List<Produit> prods = produitService.listProduits();
        modelMap.addAttribute("produits", prods);

        return "listeProduits";
    }*/

//    Liste produit qui prend en compte la pagination
    @RequestMapping("/ListeProduits")
    public String listeProduits(ModelMap modelMap,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "3") int size) {

        Page<Produit> prods = produitService.getAllProduitsParPage(page, size);
        modelMap.addAttribute("produits", prods);
        modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listeProduits";
    }

    /*@RequestMapping("/supprimerProduit")
    public String supprimerProduit(@RequestParam("id") Long id, ModelMap modelMap) {

        produitService.deleteProduitById(id);
        List<Produit> prods = produitService.listProduits();
        modelMap.addAttribute("produits", prods);
        return "listeProduits";
    }*/

    @RequestMapping("/supprimerProduit")
    public String supprimerProduit(@RequestParam("id") Long id,
                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "3") int size,
                                   ModelMap modelMap) {

        produitService.deleteProduitById(id);
        Page<Produit> prods = produitService.getAllProduitsParPage(page, size);
        modelMap.addAttribute("produits", prods);
        modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listeProduits";
    }

    @RequestMapping("/modifierProduit")
    public String editerProduit(@RequestParam("id") Long id, ModelMap modelMap) {

        Produit prod = produitService.getProduit(id);
        modelMap.addAttribute("produit", prod);
        modelMap.addAttribute("mode", "edit");
        return "formProduit";
    }

    @RequestMapping("/updateProduit")
    public String updateProduit(@ModelAttribute("produit") Produit produit,
                                @RequestParam("date") String date,
                                ModelMap modelMap) throws ParseException {

        //conversion de la date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreation = dateFormat.parse(String.valueOf(date));

        produit.setDateCreation(dateCreation);

        produitService.updateProduit(produit);
        List<Produit> prods = produitService.listProduits();
        modelMap.addAttribute("produits", prods);
        return "listeProduits";
    }
}