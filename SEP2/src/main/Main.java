package main;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import client.Afficheur;
import client.Generateur;
import client.GenerateurImpl;
import client.ObservateurGenerateur;
import proxy.Canal;
import strategy.AlgoDiffusion;
import strategy.AlgoDiffusionAtomique;

public class Main {

	public static  ScheduledExecutorService scheduler =  Executors.newScheduledThreadPool(100);
	public static  BufferedWriter writer;
	
	 public static void main (String[] args) throws IOException{
		 writer = new BufferedWriter(new FileWriter("./main"));
		 AlgoDiffusion atom= new AlgoDiffusionAtomique();
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

		 //for(int i=0; i<20; i++) {
			
		 gen1.tick();
		 writer.close();
		 //}
	 }
}
