import java.util.Date;

public class Message {
    private String sender;
    private String receiver;
    private String content;
    private Date timestamp;

    public Message(String sender, String receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = new Date(); // Capture the current time when the message is created
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "[" + timestamp.toString() + "] " + sender + " to " + receiver + ": " + content;
    }
}
