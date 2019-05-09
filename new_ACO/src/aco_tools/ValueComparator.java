package aco_tools;

import java.util.Comparator;
import java.util.Map;

/**
 * Compares two maps with key-Integer and value-Double.
 */
public class ValueComparator implements Comparator<Integer> {
	Map<Integer, Double> base;

	/**
	 * Constructor for ValueComparator
	 * 
	 * @param base Map with key-Integer and value-Double
	 */
	public ValueComparator(Map<Integer, Double> base) {
		this.base = base;
	}

	
	/**
	 * Compares two integers, returns -1 if a larger than b, 
	 * otherwise 1.
	 * 
	 * @param a Integer.
	 * @param b Integer.
	 * @return Integer
	 */
	public int compare(Integer a, Integer b) {
		if (base.get(a) >= base.get(b)) {
			return -1;
		} else {
			return 1;
		} // returning 0 would merge keys
	}
}