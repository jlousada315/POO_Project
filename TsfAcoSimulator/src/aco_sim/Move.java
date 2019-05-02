package aco_sim;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import pec.*;
import aco_tools.*;

public class Move extends Event{

	/*Fields*/
	final double alpha;
	final double beta;
	final double delta;
	final double plevel;
	double a_ij;

	/*Constructor*/

	public Move(Object obj,double timestamp, double alpha, double beta, double delta, double plevel) {
		super(obj, timestamp);
		this.alpha = alpha;
		this.beta= beta;
		this.delta = delta;
		this.plevel = plevel;
	}

	/*Methods*/

	@Override
	public void simulate(PEC pec) {
		// create next event
		int next_nodeidx = this.nextAcoNode();
		((Ant)obj).updatePath(next_nodeidx);
		pec.addEvPEC(newMove());	
	}

	private Double[] calculateProb() {		
		AcoNode Current = ((Ant)obj).path.get(((Ant)obj).path.size()-1); // Current AcoNode
		LinkedList<AcoNode> Adj = new LinkedList<AcoNode>();  //List of Adjacent AcoNodes of Current AcoNode
		double ci = 0;	//Normalization Constant
		double w[] = new double[Current.edges.size()]; //Array of weights
		double cij[] = new double[w.length]; //Proportional to Probability
		Double probability[] = new Double[w.length]; //probability of tranversing

		//Array with each edge with weight and Adjacent list of AcoNodes 
		for(int i = 0; i < Current.edges.size();++i) {
			w[i] = Current.edges.get(i).weight;
			Adj.add(Current.edges.get(i).node2);
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

	//Calculates the following AcoNode
	public int nextAcoNode() {
		AcoNode Current = ((Ant)obj).path.get(((Ant)obj).path.size()-1); // Current AcoNode
		LinkedList<AcoNode> Adj = new LinkedList<AcoNode>();  //List of Adjacent AcoNodes of Current AcoNode		
		int nbedges = Current.edges.size(); //number of edges adjacent to current AcoNode
		Integer adjindex[] = new Integer[nbedges];  
		Object index[] = new Object[nbedges];
		
		Double[] probability = calculateProb(); // array of the probability of tranversing to next AcoNode

		//Fills List Adj with adjacent AcoNodes 
		for(int i = 0 ; i < nbedges ; ++i) {
			Adj.add(Current.edges.get(i).node2);
			adjindex[i] = Adj.get(i).nodeidx;
		}

		//Map to sort AcoNode index , by descendent probability 
		Map<Integer,Double> unsortMap = new HashMap<Integer,Double>();
		ValueComparator bvc = new ValueComparator(unsortMap); 
		Map<Integer,Double> treeMap = new TreeMap<Integer,Double>(bvc);

		//Fills unsortMap with Key -> index of adjacent AcoNodes ; Value -> Probabilty 
		for(int k=0; k < nbedges ; ++k) {
			unsortMap.put(adjindex[k],probability[k]);
		}

		//Map is sorted by highest value to lowest
		treeMap.putAll(unsortMap);
		index = treeMap.keySet().toArray();
		
		LinkedList<AcoNode> J1 = new LinkedList<AcoNode>();	//List of non-visited Adjacent AcoNodes J to current AcoNode i
		Collection<AcoNode> J = new ArrayList<AcoNode>();		//Collection identical to J1
		Prob uniform = new Prob();
		
		//Fills collection with ordered AcoNodes 
		for(int i = 0; i < index.length; ++i) {
			J.add(((Ant)obj).G.nodes[((int)(index[i])-1)]);
		}

		J.removeAll(((Ant)obj).path);	//eliminates AcoNodes that have already been visited
		J1.addAll(J);	//Makes a copy of Collection to LinkedList	
			

		//Returns next AcoNode
		if(J1.isEmpty()) {		
			AcoNode nextnode = Adj.get(uniform.uniformDist(Adj.size()));
			a_ij = (Current.getEdge(nextnode)).weight;
			return nextnode.nodeidx;	//if J is empty, ant chooses uniformly between already visited adjacent AcoNodes  

		}else {
			AcoNode nextnode = J1.getFirst();
			a_ij = (Current.getEdge(nextnode)).weight;
			return nextnode.nodeidx;	//ant chooses non visited AcoNode with the highest probability
		}		
	}

	//creates next event
	Move newMove() {
		return new Move(obj, Prob.expRand(delta*a_ij), alpha, beta, delta, plevel);
	}

	//pretty print a map
	public static <K, V> void printMap(Map<K, V> map) {
		for (Map.Entry<K, V> entry : map.entrySet()) {
			System.out.println("Key : " + entry.getKey() 
			+ " Value : " + entry.getValue());
		}
	}

	void printCollection(Collection<AcoNode> X) {
		for (Iterator<AcoNode> i = X.iterator(); i.hasNext();) {
			AcoNode item = i.next();
			System.out.println(item.toString());		
			}

	}
}
