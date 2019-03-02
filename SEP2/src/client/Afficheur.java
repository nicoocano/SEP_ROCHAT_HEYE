package client;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;

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
			g.remove();
			System.out.println("GetValue pour : " +name + " : "+ result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

}
