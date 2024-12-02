import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class ReadXSD {

    public static void main(String[] args) {
        // Load the XML file that conforms to the EmailRequest schema
        File xmlFile = new File("email_request.xml");
        EmailRequest emailRequest = readXMLToObject(xmlFile);

        if (emailRequest != null) {
            System.out.println("Email Request Object:\n" +
                               "Sender: " + emailRequest.getSender() + "\n" +
                               "Recipient: " + emailRequest.getRecipient() + "\n" +
                               "Subject: " + emailRequest.getSubject() + "\n" +
                               "Body: " + emailRequest.getBody() + "\n" +
                               "Timestamp: " + emailRequest.getTimestamp());
        }
    }

    // Read the XML file and convert it into an EmailRequest object
    public static EmailRequest readXMLToObject(File xmlFile) {
    try {
        // Create a JAXBContext for the EmailRequest class
        JAXBContext context = JAXBContext.newInstance(EmailRequest.class);

        // Create an Unmarshaller from the JAXBContext
        Unmarshaller unmarshaller = context.createUnmarshaller();

        // Unmarshal the XML file into an EmailRequest object
        return (EmailRequest) unmarshaller.unmarshal(xmlFile);
    } catch (JAXBException e) {
        // Handle any exceptions during unmarshalling
        System.out.println("Error reading XML to object: " + e.getLinkedException().getMessage());
        e.printStackTrace();
        return null;
    }
}

}
