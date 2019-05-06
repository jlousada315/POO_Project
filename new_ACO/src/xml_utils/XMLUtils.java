package xml_utils;

import java.io.IOException;
//DOM
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.InputSource;
import org.w3c.dom.*;

/**
 * A XML file loader and validator.
 */
public class XMLUtils {

	private static Document document;
	private static Element rootElement;
	Var v;
	
	//Constructor
	public XMLUtils(String xml) {
		v = new Var(xml) ;
	}
	
	public Var getV() {
		return v;
	}
	
	/**
	 * Returns a boolean that indicates if XML file follows the  
	 * DTD rules.
	 *
	 * @param  xml is the directoy of the xml file
	 * @return      boolean
	 */
	public static boolean validateWithDTDUsingDOM(String xml) 
		  throws ParserConfigurationException, IOException
		  {
		    try {
		      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		      factory.setValidating(true);
		      factory.setNamespaceAware(true);

		      DocumentBuilder builder = factory.newDocumentBuilder();

		      builder.setErrorHandler(
		          new ErrorHandler() {
		            public void warning(SAXParseException e) throws SAXException {
		              System.out.println("WARNING : " + e.getMessage()); // do nothing
		            }

		            public void error(SAXParseException e) throws SAXException {
		              System.out.println("ERROR : " + e.getMessage());
		              throw e;
		            }

		            public void fatalError(SAXParseException e) throws SAXException {
		              System.out.println("FATAL : " + e.getMessage());
		              throw e;
		            }
		          }
		          );
		      document = builder.parse(new InputSource(xml));
		      rootElement = document.getDocumentElement();
		      return true;
		    }
		    catch (ParserConfigurationException pce) {
		      throw pce;
		    } 
		    catch (IOException io) {
		      throw io;
		    }
		    catch (SAXException se){
		      return false;
		    }
		  }
	
	
	/**
	 * Returns the double values stored in the XML file 
	 * by this order (finalinst,plevel,alpha,beta,delta,eta,rho).
	 *  Can only be used after validating.
	 *
	 * @return     Array of doubles in XML file
	 */
	public static double[] getDouble() {
		double i[] = {0 , 0 , 0 , 0 , 0 , 0 , 0};
		String s = rootElement.getAttribute("finalinst");
		i[0]= Double.parseDouble(s); 
		s = rootElement.getAttribute("plevel");
		i[1]= Double.parseDouble(s); 
		NodeList n = rootElement.getElementsByTagName("events");
		Element event = (Element) n.item(0);
		NodeList n1 = event.getElementsByTagName("move");
		Element move = (Element) n1.item(0);
		s = move.getAttribute("alpha");
		i[2] = Double.parseDouble(s); 
		s = move.getAttribute("beta");
		i[3] = Double.parseDouble(s); 
		s = move.getAttribute("delta");
		i[4] = Double.parseDouble(s);
		NodeList n2 = event.getElementsByTagName("evaporation");
		Element evap = (Element) n2.item(0);
		s = evap.getAttribute("eta");
		i[5] = Double.parseDouble(s);
		s = evap.getAttribute("rho");
		i[6] = Double.parseDouble(s);
		return i;
	}
	
	/**
	 * Returns the int values stored in the XML file 
	 * by this order (antclosize,nbnodes,nestnode).
	 *  Can only be used after validating.
	 *
	 * @return     Array of ints in XML file
	 */
	public static int[] getInt() {
		int i[] = {0 , 0 , 0 };
		String s = rootElement.getAttribute("antcolsize");
		i[0]= Integer.parseInt(s); 
		NodeList n = rootElement.getElementsByTagName("graph");
		Element graph = (Element) n.item(0);
		s = graph.getAttribute("nbnodes");
		i[1]= Integer.parseInt(s); 
		s = graph.getAttribute("nestnode");
		i[2]= Integer.parseInt(s);
		return i;
	}
	
	/**
	 * Returns Weight values stored in the XML file, 
	 * in a double matrix. 
	 * Can only be used after validating.
	 *
	 * @return     Matrix of Weights in XML file.
	 */
	public static double[][] getWeights() {
		NodeList n = rootElement.getElementsByTagName("graph");
		Element graph = (Element) n.item(0);
		String s = graph.getAttribute("nbnodes");
		int i= Integer.parseInt(s); 
		double m_weights[][] = new double[i][i];
		int comp[] = new int[i];
 		//Filling weight with zeros 
		for(int l=0; l<i ; l++) {
			comp[l]=0;
		    for(int j=0; j<i ; j++) {
		        m_weights[l][j] = 0;
		    }
		}	
		NodeList n1 = graph.getElementsByTagName("node");
		for(int l=0; l<n1.getLength() ; l++) {
			Element node = (Element) n1.item(l);
			s = node.getAttribute("nodeidx");
			int nodeidx= Integer.parseInt(s)-1; 
			comp[l]=nodeidx;
			for(int p=0; p<l;p++) {
				if(nodeidx==comp[p]) {
					System.out.println("Error: Repeated node index!");
					System.exit(1);
				}
			}
			NodeList n2 = node.getElementsByTagName("weight");
			
			int comp2[] = new int[n2.getLength()];
			
			for(int j=0 ; j<n2.getLength() ; j++) {
				Element weight = (Element) n2.item(j);
				s = weight.getAttribute("targetnode");
				int target = Integer.parseInt(s)-1;
				comp2[j] = target;
				for(int p=0; p<j;p++) {
					if(target==comp2[p]) {
						System.out.println("Error: Repeated weight target!");
						System.exit(1);
					}
				}
				s = weight.getChildNodes().item(0).getTextContent();
				double w = Double.parseDouble(s);
				m_weights[nodeidx][target] = w;///////////
			}
		}
		return m_weights;
	}
}