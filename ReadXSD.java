import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

import java.io.File;

public class ReadXSD {

    public static void main(String[] args) {
        File xsdFile = new File("email_request.xsd");
        readAndDisplayXSDStructure(xsdFile);
    }

    // Function to read and display the structure of the XSD file
    public static void readAndDisplayXSDStructure(File xsdFile) {
        try {
            // Initialize a DocumentBuilder for parsing the XSD file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XSD file and get the DOM representation
            Document document = builder.parse(xsdFile);

            // Normalize the document to merge adjacent text nodes
            document.getDocumentElement().normalize();

            // Get the root element (xs:schema)
            Element rootElement = document.getDocumentElement();
            System.out.println("Root Element: " + rootElement.getNodeName());

            // Process all child nodes of the root element
            NodeList nodeList = rootElement.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                // Only process element nodes
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    if (element.getNodeName().equals("xs:element")) {
                        // Print details for xs:element
                        System.out.println("Element Name: " + element.getAttribute("name"));
                        String type = element.getAttribute("type");
                        if (!type.isEmpty()) {
                            System.out.println("    Type: " + type);
                        } else {
                            System.out.println("    Type: Complex Type (Defined Inline)");
                        }
                    } else if (element.getNodeName().equals("xs:complexType")) {
                        // Process complex type structure
                        processComplexType(element);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading XSD file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Helper method to process complex types
    public static void processComplexType(Element complexTypeElement) {
        NodeList sequenceNodes = complexTypeElement.getElementsByTagName("xs:sequence");
        if (sequenceNodes.getLength() > 0) {
            Node sequenceNode = sequenceNodes.item(0);

            if (sequenceNode.getNodeType() == Node.ELEMENT_NODE) {
                NodeList childElements = ((Element) sequenceNode).getElementsByTagName("xs:element");
                System.out.println("    Complex Type Sequence:");

                for (int i = 0; i < childElements.getLength(); i++) {
                    Element childElement = (Element) childElements.item(i);
                    System.out.println("        Element Name: " + childElement.getAttribute("name"));
                    System.out.println("        Type: " + childElement.getAttribute("type"));
                }
            }
        }
    }
}
