package client;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.util.concurrent.ScheduledFuture;

import main.Main;
import service.GenerateurObservateurAsync;
import strategy.AlgoDiffusion;

public class GenerateurImpl implements Generateur {
	private AlgoDiffusion algo;
	private String name;
	int value;

	
	public GenerateurImpl(AlgoDiffusion alg, String n) {
		algo = alg;
		name=n;
		value = 0;
	}
	
	public void attach(GenerateurObservateurAsync o) {
		algo.attach(o);
	}
	public void detach(GenerateurObservateurAsync o) {
		//todo
	}
	public int getValue(ObservateurGenerateur o){
		algo.getValue(o);
		return value;
	}
	
	public void tick() {
		GenerateurImpl temp =this;
		ScheduledFuture result = Main.scheduler.scheduleAtFixedRate(new Runnable() {
			public void run() {
				// si on est pas verrouillé
				if(algo.getLocked()) {
					value += 1;
				}
				algo.execute(temp);
				
			}
		},0,1000,MILLISECONDS);
		
		 try {
			Thread.sleep(9999);
			result.cancel(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void remove(GenerateurObservateurAsync g) {
		algo.remove(g);
	}
}
