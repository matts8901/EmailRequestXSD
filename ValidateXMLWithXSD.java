import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

import java.io.File;

public class ValidateXMLWithXSD {

    public static void main(String[] args) {
        File xsdFile = new File("email_request.xsd");
        File xmlFile = new File("email_request.xml");

        if (validateXMLSchema(xsdFile, xmlFile)) {
            System.out.println("Validation successful! The XML is valid against the XSD.");
        } else {
            System.out.println("Validation failed! The XML is not valid against the XSD.");
        }
    }

    public static boolean validateXMLSchema(File xsdFile, File xmlFile) {
        try {
            // Create a SchemaFactory for W3C XML Schema
            SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

            // Load the XSD schema file
            Schema schema = factory.newSchema(xsdFile);

            // Create a JAXB context for the XML object
            JAXBContext jaxbContext = JAXBContext.newInstance(EmailRequest.class);

            // Set up an unmarshaller with the schema
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            unmarshaller.setSchema(schema);

            // Perform the unmarshalling (validation happens here)
            unmarshaller.unmarshal(xmlFile);

            return true; // XML is valid
        } catch (JAXBException | SAXException e) {
            System.out.println("Error during validation: " + e.getMessage());
            return false; // XML is not valid
        }
    }
}
