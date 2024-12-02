import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlRootElement(name = "EmailRequest")
public class EmailRequest {

    private String sender;
    private String recipient;
    private String subject;
    private String body;
    private Date timestamp;

    // No-argument constructor required by JAXB
    public EmailRequest() {}

    @XmlElement
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @XmlElement
    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @XmlElement
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @XmlElement
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class) // Custom adapter for date formatting
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    // Custom Date Adapter to format Date to/from XML
    public static class DateAdapter extends XmlAdapter<String, Date> {

        @Override
        public Date unmarshal(String v) throws Exception {
            // Use a simple date format or a more complex one
            return new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(v);
        }

        @Override
        public String marshal(Date v) throws Exception {
            return new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(v);
        }
    }
}
