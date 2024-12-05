import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;

public class ReadXSD {

    public static void main(String[] args) {
        File xsdFile = new File("email_request.xsd");
        readXSDFile(xsdFile);
    }

    public static void readXSDFile(File xsdFile) {
        try {
            // Create a DocumentBuilderFactory and enable namespace awareness
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);

            // Build a Document from the XSD file
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xsdFile);

            // Create an XPath instance
            XPath xpath = XPathFactory.newInstance().newXPath();

            // Find all <xs:element> nodes in the document
            NodeList elementNodes = (NodeList) xpath.evaluate(
                    "//*[local-name()='element']", // XPath query to find all `xs:element` tags
                    document,
                    XPathConstants.NODESET
            );

            // Print details about each <xs:element> node
            System.out.println("Elements defined in the XSD:");
            for (int i = 0; i < elementNodes.getLength(); i++) {
                Node node = elementNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String name = element.getAttribute("name");
                    String type = element.getAttribute("type");
                    System.out.println("Element Name: " + name + (type.isEmpty() ? "" : ", Type: " + type));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error reading the XSD file: " + e.getMessage());
        }
    }
}
