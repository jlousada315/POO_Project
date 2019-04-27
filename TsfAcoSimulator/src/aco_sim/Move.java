package aco_sim;

import pec.*;
import aco_tools.*;

import java.util.*;

public class Move extends Event{
	//attributes
	final double alpha;
	final double beta;
	final double delta;
	final double plevel;

	//constructor
	public Move(Object obj, double timestamp, double alpha, double beta, double delta, double plevel) {
		super(obj, timestamp);
		this.alpha = alpha;
		this.beta= beta;
		this.delta = delta;
		this.plevel = plevel;
	}

	//simulate this event
	@Override
	public void simulate(PEC pec) {
		// create next event
		pec.addEvPEC(newMove());		
	}

	//calculateProb
	private Double[] calculateProb() {		
		AcoNode Current = ((Ant)obj).path.get(((Ant)obj).path.size()-1); // Current Node
		LinkedList<AcoNode> Adj = new LinkedList<AcoNode>();  //List of Adjacent Nodes of Current Node
		double ci = 0;	//Normalization Constant
		double w[] = new double[Current.getEdgesSize()]; //Array of weights
		double cij[] = new double[w.length]; //Proportional to Probability
		Double probability[] = new Double[w.length]; //probability of tranversing

		//Array with each edge with weight and Adjacent list of Nodes 
		for(int i = 0; i < Current.getEdgesSize();++i) {
			w[i] = Current.edges.get(i).weight;
			Adj.add(Current.edges.get(i).getNode2());
		}

		//Probability Calculation
		for(int k=0; k < w.length ; ++k) {
			cij[k] = (alpha + plevel)/(beta + w[k]); 	 
			ci += (alpha + plevel)/(beta + w[k]); 
		}	

		//Probabilities are stored in array.
		Prob uniform = new Prob();
		for(int k=0; k < w.length ; ++k) {
			probability[k] = (uniform.uniformDist(100))*cij[k]/ci; 
		}

		return probability;
	}

	//calculates the following node
	public int nextNode() {
		AcoNode Current = ((Ant)obj).path.get(((Ant)obj).path.size()-1); // Current Node
		LinkedList<AcoNode> Adj = new LinkedList<AcoNode>();  //List of Adjacent Nodes of Current Node		
		int nbedges = Current.getEdgesSize(); //number of edges adjacent to current node
		Integer adjindex[] = new Integer[nbedges];  
		Object index[] = new Object[nbedges];
		
		Double[] probability = calculateProb(); // array of the probability of tranversing to next node

		//Fills List Adj with adjacent nodes 
		for(int i = 0 ; i < nbedges ; ++i) {
			Adj.add((AcoNode)Current.edges.get(i).getNode2());
			adjindex[i] = Adj.get(i).getIdx();
		}

		//Map to sort node index , by descendent probability 
		Map<Integer,Double> unsortMap = new HashMap<Integer,Double>();
		ValueComparator bvc = new ValueComparator(unsortMap); 
		Map<Integer,Double> treeMap = new TreeMap<Integer,Double>(bvc);

		//Fills unsortMap with Key -> index of adjacent nodes ; Value -> Probabilty 
		for(int k=0; k < nbedges ; ++k) {
			unsortMap.put(adjindex[k],probability[k]);
		}

		//Map is sorted by highest value to lowest
		treeMap.putAll(unsortMap);
		index = treeMap.keySet().toArray();
		
		LinkedList<AcoNode> J1 = new LinkedList<AcoNode>();	//List of non-visited Adjacent Nodes J to current Node i
		Collection<AcoNode> J = new ArrayList<AcoNode>(); //Collection identical to J1
		Prob uniform = new Prob();
		
		//Fills collection with ordered nodes 
		for(int i = 0; i < index.length; ++i) {
			J.add(((Ant)obj).G.nodes[((int)(index[i])-1)]);
		}

		J.removeAll(((Ant)obj).path);	//eliminates nodes that have already been visited
		J1.addAll(J);	//Makes a copy of Collection to LinkedList	

		//Returns next node
		if (J1.isEmpty()) {
			return Adj.get(uniform.uniformDist(Adj.size())).getIdx();	//if J is empty, ant chooses uniformly between already visited adjacent nodes  

		} else {
			return J1.get(0).getIdx();	//ant chooses non visited node with the highest probability
		}		
	}

	//creates next event
	private Move newMove() {
		//computes next node
		/* from node i -> last node of path
		 * to node j -> new node calculated
		 * get weight w for edge from i to j
		 * */
		//returns new event
		return new Move(obj, Prob.expRand(delta/**w*/), alpha, beta, delta, plevel);
	}

	//pretty print a map
	public static <K, V> void printMap(Map<K, V> map) {
		for (Map.Entry<K, V> entry : map.entrySet()) {
			System.out.println("Key : " + entry.getKey() 
			+ " Value : " + entry.getValue());
		}
	}

	//print collection
	void printCollection(Collection<AcoNode> X) {
		for (Iterator<AcoNode> i = X.iterator(); i.hasNext();) {
			AcoNode item = i.next();
			System.out.println(item.toString());
		}
	}
}