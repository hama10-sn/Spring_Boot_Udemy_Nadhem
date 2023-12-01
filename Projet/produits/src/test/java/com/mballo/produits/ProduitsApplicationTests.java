package com.mballo.produits;

import com.mballo.produits.entities.Produit;
import com.mballo.produits.repository.ProduitRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class ProduitsApplicationTests {

	@Autowired
	private ProduitRepository produitRepository;

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



}
