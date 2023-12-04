package com.mballo.produits;

import com.mballo.produits.entities.Categorie;
import com.mballo.produits.entities.Produit;
import com.mballo.produits.repository.ProduitRepository;
import com.mballo.produits.service.ProduitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

@SpringBootTest
class ProduitsApplicationTests {

	@Autowired
	private ProduitRepository produitRepository;

	@Autowired
	ProduitService produitService;

	/*@Test
	void contextLoads() {
	}*/

	@Test
	public void testCreateProduit(){
		Produit prod = new Produit("PC ASUS", 2500.500, new Date());
		produitRepository.save(prod);
	}

	@Test
	public void testFindProduit(){
		Produit prod = produitRepository.findById(1L).get();
		System.out.println(prod);
	}

	@Test
	public void testUpdateProduit(){
		Produit prod = produitRepository.findById(1L).get();
		prod.setPrixProduit(2000.0);
		System.out.println(prod);
	}

	@Test
	public void testDeleteProduit(){
		produitRepository.deleteById(1L);
	}

	@Test
	public void testFindAllProduit(){
		List<Produit> prods = produitRepository.findAll();

		for (Produit p: prods){
			System.out.println(p);
		}
	}

	@Test
	public void testFindByNomProduitsContains(){
		Page<Produit> prods = produitService.getAllProduitsParPage(0, 2);

		System.out.println(prods.getSize());
		System.out.println(prods.getTotalElements());
		System.out.println(prods.getTotalPages());

//		Expression lambda
		prods.getContent().forEach(p -> {
			System.out.println(p.toString());
		});

//		Equivalent de l'expression lambda précédent
/*		for (Produit p: prods.getContent()){
			System.out.println(p);
		}*/
	}

	@Test
	public void testFindByNomProduit(){
		List<Produit> prods = produitRepository.findByNomProduit("PC ASUS");

		for (Produit p: prods)
			System.out.println(p);
	}

	@Test
	public void testFindByNomProduitContains(){
		List<Produit> prods = produitRepository.findByNomProduitContains("DELL");

		for (Produit p: prods)
			System.out.println(p);
	}

	@Test
	public void testFindNomPrix(){
		List<Produit> prods = produitRepository.findByNomPrix("PC ASUS", 1000.0);

		for (Produit p: prods)
			System.out.println(p);
	}

	@Test
	public void testFindByCategorie(){
		Categorie cat = new Categorie();
		cat.setIdCat(1L);

		List<Produit> prods = produitRepository.findByCategorie(cat);
		for (Produit p: prods){
			System.out.println(p);
		}
	}

	@Test
	public void findByCategorieIdCat(){
		List<Produit> prods = produitRepository.findByCategorieIdCat(1L);

		for (Produit p: prods)
			System.out.println(p);
	}

	@Test
	public void testFindByOrderByNomProduitAsc(){
		List<Produit> prods = produitRepository.findByOrderByNomProduitAsc();

		for (Produit p: prods)
			System.out.println(p);
	}

	@Test
	public void testTrierProduitsNomsPrix(){
		List<Produit> prods = produitRepository.trierProduitsNomsPrix();

		for (Produit p: prods)
			System.out.println(p);
	}
}
