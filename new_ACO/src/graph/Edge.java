package graph;

/**
 * Used to represent a weighted edge in a graph. Stores both nodes 
 * associated with it, the weight of the connection and
 * the pheromone level. Also has methods to get information
 * about the edge.
 *
 */
public class Edge{
	//attributes
	protected final Node node1;
	protected final Node node2;
	protected final double weight;
	protected double pheromone=0;
	
	/**
	 * Constructor for edge
	 * @param node1 1st node.
	 * @param node2 2nd node.
	 * @param weight Weight.
	 */
	Edge(Node node1, Node node2, double weight){
		this.node1 = node1;
		this.node2 = node2;
		this.weight = weight; 
	}
	
	/**
	 * Returns 2nd node.
	 * @return node2 2nd node.
	 */
	public Node getNode2() {
		return node2;
	}
	
	/**
	 * Returns weight.
	 * @return weight Edge's weight.
	 */
	public double getWeight() {
		return weight;
	}
	
	//generated hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((node1 == null) ? 0 : node1.hashCode());
		result = prime * result + ((node2 == null) ? 0 : node2.hashCode());
		return result;
	}

	//generated equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (node1 == null) {
			if (other.node1 != null)
				return false;
		} else if (!node1.equals(other.node1))
			return false;
		if (node2 == null) {
			if (other.node2 != null)
				return false;
		} else if (!node2.equals(other.node2))
			return false;
		return true;
	}

	//generated toString
	@Override
	public String toString() {
		return "Edge [("+node1+","+node2+"),w="+weight+",p="+pheromone+"]";
	}
}