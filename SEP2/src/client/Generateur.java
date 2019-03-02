package client;

import service.GenerateurObservateurAsync;

public interface Generateur {

	public void attach(GenerateurObservateurAsync o);
	public void detach(GenerateurObservateurAsync o);
	public int  getValue(ObservateurGenerateur o);
	public void remove(GenerateurObservateurAsync g);
	public void tick();
}
