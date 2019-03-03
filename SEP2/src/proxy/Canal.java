package proxy;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;

import client.Generateur;
import client.ObservateurGenerateur;
import main.Main;
import service.GenerateurAsync;
import service.GenerateurObservateurAsync;

public class Canal implements GenerateurAsync, GenerateurObservateurAsync {

	
	private ObservateurGenerateur observateur;
	private Generateur generateur;

	
	public Canal(ObservateurGenerateur og,Generateur g ) {
		observateur=og;
		generateur =g;
	}
	
	public ScheduledFuture<Void> update(Generateur g){
		Canal thisObject =this;
		ScheduledFuture result =Main.scheduler.schedule(new Runnable() {
			public void run() {
				observateur.update(thisObject);
				
			}
		},getRandom(),MILLISECONDS);
		return result;
		
	}
	
	
	public ScheduledFuture <Integer> getValue(){
		ScheduledFuture<Integer> result = Main.scheduler.schedule(new Callable<Integer>() {
			
			public Integer call() {
				
				return generateur.getValue(observateur);
			}
		},getRandom(),MILLISECONDS);
		
		return result;
	}
	
	public void attach(ObservateurGenerateur o) {
		observateur = o;
	}
	public void detach(ObservateurGenerateur o) {
		
	}
	
	public void attach(Generateur g) {
		generateur = g;
	}
	public void detach(Generateur g) {
		
	}
	
	public void remove() {
		generateur.remove(this);
	}
	
	public int getRandom() {
		return (int)(Math.random() *(500));
	}
}
