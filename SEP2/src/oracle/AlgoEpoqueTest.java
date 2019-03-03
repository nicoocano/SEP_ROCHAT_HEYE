package oracle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import client.Afficheur;
import client.Generateur;
import client.GenerateurImpl;
import client.ObservateurGenerateur;
import main.Main;
import proxy.Canal;
import strategy.AlgoDiffusion;
import strategy.AlgoDiffusionEpoque;

public class AlgoEpoqueTest {




	@Test
	void test() throws IOException {
		
		 AlgoDiffusionEpoque atom= new AlgoDiffusionEpoque();
		 Generateur gen1 = new GenerateurImpl(atom,"gen1");
		 ObservateurGenerateur obs1=  new Afficheur("obs1");
		 Canal c1 =new Canal(obs1,gen1);
		 gen1.attach(c1);
	
	
		 ObservateurGenerateur obs2=  new Afficheur("obs2");
		 Canal c2 =new Canal(obs2,gen1);
		 gen1.attach(c2);

		 
	
		 ObservateurGenerateur obs3=  new Afficheur("obs3");
		 Canal c3 =new Canal(obs3,gen1);
		 gen1.attach(c3);
		 gen1.tick();
		System.out.println("Version des valeurs de l'observateur 1 : " +atom.m_mapObservateurValeurs.get(obs1));
	}
}
