package client;

import service.GenerateurAsync;

public interface ObservateurGenerateur {
	
	//  Future<Integer>
	public void update(GenerateurAsync g);
}
