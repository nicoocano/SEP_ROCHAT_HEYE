package client;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;

import main.Main;
import service.GenerateurAsync;

public class Afficheur implements ObservateurGenerateur {

	private String name;

	public Afficheur(String n) {
		name = n;
	}
	
	public void update(GenerateurAsync g) {
		
		ScheduledFuture<Integer> valueFuture= g.getValue();
		int result;
			try {
				result = valueFuture.get();
				System.out.println("GetValue pour : " +name + " : "+ result);
				g.remove();
			
				Main.writer.write(result +"\n");
			} catch (InterruptedException | ExecutionException | IOException  e) {
				e.printStackTrace();
			}
		

	}

}
