package aco_sim;

public class Ant{
	
	int[] path;
	
	
	public Ant() {
		// TODO Auto-generated constructor stub
	}

	public double tranverseProb(Move m ,double aij[]) {
		double probability = 0;
		double cij[] = new double[aij.length];
		double ci = 0;
		
		for(int k=0; k < aij.length ; ++k) {
			ci += aij[k]; 
		}
		
		for(int k=0; k < aij.length ; ++k) {
			cij[k] = (m.alpha + 0)/(m.beta + aij[k]); 
			if(cij[k]/ci > probability) {probability = cij[k]/ci;} 
		}
		
		return probability;
	}
}
