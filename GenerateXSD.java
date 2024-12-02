import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateXSD {

    public static void main(String[] args) {
        String xsdContent = generateXSDSchema();
        writeToFile(xsdContent, "email_request.xsd");
    }

    // Generate the XSD schema for the EmailRequest object
    public static String generateXSDSchema() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
               "<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\">\n" +
               "\n" +
               "    <!-- Root element -->\n" +
               "    <xs:element name=\"EmailRequest\">\n" +
               "        <xs:complexType>\n" +
               "            <xs:sequence>\n" +
               "                <xs:element name=\"Sender\" type=\"xs:string\" />\n" +
               "                <xs:element name=\"Recipient\" type=\"xs:string\" />\n" +
               "                <xs:element name=\"Subject\" type=\"xs:string\" />\n" +
               "                <xs:element name=\"Body\" type=\"xs:string\" />\n" +
               "                <xs:element name=\"Timestamp\" type=\"xs:dateTime\" />\n" +
               "            </xs:sequence>\n" +
               "        </xs:complexType>\n" +
               "    </xs:element>\n" +
               "\n" +
               "</xs:schema>";
    }

    // Write the XSD content to a file
    public static void writeToFile(String content, String filename) {
        try (FileWriter writer = new FileWriter(new File(filename))) {
            writer.write(content);
            System.out.println("XSD schema written to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
