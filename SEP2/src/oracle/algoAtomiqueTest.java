package oracle;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import client.Afficheur;
import client.Generateur;
import client.GenerateurImpl;
import client.ObservateurGenerateur;
import main.Main;
import proxy.Canal;
import strategy.AlgoDiffusion;
import strategy.AlgoDiffusionAtomique;

class algoAtomiqueTest {

	static  Generateur gen1;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		 AlgoDiffusion atom= new AlgoDiffusionAtomique();
		 gen1 = new GenerateurImpl(atom,"gen1");
		 ObservateurGenerateur obs1=  new Afficheur("obs1");
		 Canal c1 =new Canal(obs1,gen1);
		 gen1.attach(c1);
	
	
		 ObservateurGenerateur obs2=  new Afficheur("obs2");
		 Canal c2 =new Canal(obs2,gen1);
		 gen1.attach(c2);

		 
	
		 ObservateurGenerateur obs3=  new Afficheur("obs3");
		 Canal c3 =new Canal(obs3,gen1);
		 gen1.attach(c3);
		 
		 Main.writer = new BufferedWriter(new FileWriter("./testAtomique"));

	}

	@Test
	void test() throws IOException {
		
		 for(int i=0; i<10; i++) {
			 gen1.tick();
			 }
		 Main.writer.close();
		 InputStreamReader lecture=new InputStreamReader(new FileInputStream("./testAtomique"));
		 BufferedReader buff=new BufferedReader(lecture);
		 String ligne;
		 int nbOservateurs =1;
		 int compare =1;
		 while ((ligne=buff.readLine())!=null){
			 assertEquals(compare,Integer.parseInt(ligne));
			 if(nbOservateurs%3 ==0) compare++;
			 nbOservateurs++;
		 }
		 buff.close();

	}

}
