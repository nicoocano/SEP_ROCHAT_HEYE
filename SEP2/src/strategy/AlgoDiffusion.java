package strategy;

import client.Generateur;
import client.ObservateurGenerateur;
import service.GenerateurObservateurAsync;

public interface AlgoDiffusion {

	public void execute(Generateur gen);
	public void attach(GenerateurObservateurAsync obs);
	public boolean getLocked();
	public void remove(GenerateurObservateurAsync g);
	
	public void getValue(ObservateurGenerateur o);
}
