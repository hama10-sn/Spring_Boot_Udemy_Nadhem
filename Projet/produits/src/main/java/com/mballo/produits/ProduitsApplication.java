package com.mballo.produits;

import com.mballo.produits.entities.Produit;
import com.mballo.produits.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;

@SpringBootApplication
public class ProduitsApplication implements CommandLineRunner {

	@Autowired
	private ProduitService produitService;

//	Par défaut Spring Data Rest n'expose les id des entity. Donc voici un moyen de la faire avec
//	RepositoryRestConfiguration et de la methode run de CommandLineRunner
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;
	public static void main(String[] args) {
		SpringApplication.run(ProduitsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*produitService.saveProduit(new Produit("PC DELL", 1200.0, new Date()));
		produitService.saveProduit(new Produit("PC ASUS", 2800.0, new Date()));
		produitService.saveProduit(new Produit("Imprimante Epson", 900.0, new Date()));*/

		repositoryRestConfiguration.exposeIdsFor(Produit.class);
	}
}
