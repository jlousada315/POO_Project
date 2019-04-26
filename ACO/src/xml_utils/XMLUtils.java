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

public class XMLUtils {

	static Document document;
	static Element rootElement;
	
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
	
	public static double getFinalinst() {
		String s = rootElement.getAttribute("finalinst");
		double i= Double.parseDouble(s); 
		return i;
	}
	
	public static double getPLevel() {
		String s = rootElement.getAttribute("plevel");
		double i= Double.parseDouble(s); 
		return i;
	}
	
	public static int getAntCol() {
		String s = rootElement.getAttribute("antcolsize");
		int i= Integer.parseInt(s); 
		return i;
	}
	
	public static int getNbNodes() {
		NodeList n = rootElement.getElementsByTagName("graph");
		Element graph = (Element) n.item(0);
		String s = graph.getAttribute("nbnodes");
		int i= Integer.parseInt(s); 
		return i;
	}
	
	public static int getNestNode() {
		NodeList n = rootElement.getElementsByTagName("graph");
		Element graph = (Element) n.item(0);
		String s = graph.getAttribute("nestnode");
		int i= Integer.parseInt(s); 
		return i;
	}
	
	public static double[] getGreek() {
		double i[] = {0,0,0,0,0};
		NodeList n = rootElement.getElementsByTagName("events");
		Element event = (Element) n.item(0);
		NodeList n1 = event.getElementsByTagName("move");
		Element move = (Element) n1.item(0);
		String s = move.getAttribute("alpha");
		i[0] = Double.parseDouble(s); 
		s = move.getAttribute("beta");
		i[1] = Double.parseDouble(s); 
		s = move.getAttribute("delta");
		i[2] = Double.parseDouble(s);
		NodeList n2 = event.getElementsByTagName("evaporation");
		Element evap = (Element) n2.item(0);
		s = evap.getAttribute("eta");
		i[3] = Double.parseDouble(s);
		s = evap.getAttribute("rho");
		i[4] = Double.parseDouble(s);
		return i;
	}
	
	public static double[][] getWeights() {
		NodeList n = rootElement.getElementsByTagName("graph");
		Element graph = (Element) n.item(0);
		String s = graph.getAttribute("nbnodes");
		int i= Integer.parseInt(s); 
		double m_weights[][] = new double[i][i];
		//Filling weight with zeros 
		for(int l=0; l<i ; l++) {
		    for(int j=0; j<i ; j++) {
		        m_weights[l][j] = 0;
		    }
		}	
		NodeList n1 = graph.getElementsByTagName("node");
		for(int l=0; l<n1.getLength() ; l++) {
			Element node = (Element) n1.item(l);
			s = node.getAttribute("nodeidx");
			int nodeidx= Integer.parseInt(s)-1; 
			NodeList n2 = node.getElementsByTagName("weight");
			for(int j=0 ; j<n2.getLength() ; j++) {
				Element weight = (Element) n2.item(j);
				s = weight.getAttribute("targetnode");
				int target = Integer.parseInt(s)-1;
				s = weight.getChildNodes().item(0).getTextContent();
				double w = Double.parseDouble(s);
				m_weights[nodeidx][target] = w;///////////
			}
		}
		return m_weights;
	}
}