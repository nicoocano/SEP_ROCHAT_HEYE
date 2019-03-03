package strategy;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;

import client.Generateur;
import client.ObservateurGenerateur;
import service.GenerateurObservateurAsync;

public class AlgoDiffusionSequentielle implements AlgoDiffusion{

	private Set<GenerateurObservateurAsync> m_observateurs;
	
	public AlgoDiffusionSequentielle() {
	
	}
	public boolean getLocked() {
		return false;
	}
	public void execute(Generateur gen) {

	}
	
	public void getValue(ObservateurGenerateur o) {
		
	}
	
	public void attach(GenerateurObservateurAsync obs) {
		m_observateurs.add(obs);
	}
	
	public void remove(GenerateurObservateurAsync g) {
		//m_observateurs_waiting.remove(g);
	}
}
