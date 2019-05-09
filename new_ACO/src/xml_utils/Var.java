package xml_utils;


/**
 * Used to get and store information from a XML file. 
 *
 */
public class Var {

	//Fields
	private double finalinst;
	private double plevel;
	private double alpha;
	private double beta;
	private double delta;
	private double eta;
	private double rho;
	private int antcolsize; 
	private int nbnodes;
	private int nestnode;
	private double[][] weight;
	
	/**
	 * Constructor for class Var. 
	 */
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
	
		/**
		 * Returns final instant. 
		 * @return finalinst Final instant.
		 */
		public double getFinalinst() {
			return finalinst;
		}
		
		/**
		 * Returns pheromone level. 
		 * @return plevel Pheromone level..
		 */
		public double getPlevel() {
			return plevel;
		}
		
		/**
		 * Returns alpha. 
		 * @return alpha Alpha.
		 */
		public double getAlpha() {
			return alpha;
		}
		
		/**
		 * Return beta. 
		 * @return beta Beta.
		 */
		public double getBeta() {
			return beta;
		}
		
		/**
		 * Returns delta. 
		 * @return delta Delta.
		 */
		public double getDelta() {
			return delta;
		}
		
		/**
		 * Returns eta. 
		 * @return eta Eta.
		 */
		public double getEta() {
			return eta;
		}
		
		/**
		 * Returns rho. 
		 * @return rho Rho.
		 */
		public double getRho() {
			return rho;
		}
		
		/**
		 * Returns ant colony size. 
		 * @return antcolsize Ant colony size.
		 */
		public int getAntcolsize() {
			return antcolsize;
		}
		
		/**
		 * Returns number of nodes. 
		 * @return nbnodes Number of nodes.
		 */
		public int getNbnodes() {
			return nbnodes;
		}
		
		/**
		 * Returns nest node index. 
		 * @return nestnode Nest node index.
		 */
		public int getNestnode() {
			return nestnode;
		}
		
		/**
		 * Returns weight. 
		 * @return weight Weight.
		 */
		public double[][] getWeight() {
			return weight;
		}
		
}