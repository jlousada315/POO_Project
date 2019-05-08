package xml_utils;

public class Var {

	//Fields
	double finalinst;
	double plevel;
	double alpha;
	double beta;
	double delta;
	double eta;
	double rho;
	int antcolsize; 
	int nbnodes;
	int nestnode;
	double[][] weight;
	
	//constructor
		Var() {
			try {
				XMLUtils.getDouble();
			} catch (Exception e) {
				System.out.println("ERROR: Wrong input in XML"); 
				System.exit(-1);
			}
			double aux[] = XMLUtils.getDouble();
			finalinst = aux[0];
			plevel = aux[1];
			alpha = aux[2];
			beta = aux[3];
			delta = aux[4];
			eta = aux[5];
			rho = aux[6];

			try {
				XMLUtils.getInt();
			} catch (Exception e) {
				System.out.println("ERROR: Wrong input in XML");
				System.exit(-1);
			}
			int aux1[] = XMLUtils.getInt();
			antcolsize = aux1[0]; 
			nbnodes = aux1[1];
			nestnode = aux1[2];

			try {
				XMLUtils.getWeights();
			} catch (Exception e) {
				System.out.println("ERROR: Wrong input in XML");
				System.exit(-1);
			}
			weight = XMLUtils.getWeights(); 
		}
	
		public double getFinalinst() {
			return finalinst;
		}
		
		public double getPlevel() {
			return plevel;
		}
		
		public double getAlpha() {
			return alpha;
		}
		public double getBeta() {
			return beta;
		}
		
		public double getDelta() {
			return delta;
		}
		
		public double getEta() {
			return eta;
		}
		
		public double getRho() {
			return rho;
		}
		
		public int getAntcolsize() {
			return antcolsize;
		}
		
		public int getNbnodes() {
			return nbnodes;
		}
		
		public int getNestnode() {
			return nestnode;
		}
		
		public double[][] getWeight() {
			return weight;
		}
		
}