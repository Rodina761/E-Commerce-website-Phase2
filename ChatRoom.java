import java.util.ArrayList;

public class ChatRoom {
    private ArrayList<Message> messages;

    public ChatRoom() {
        this.messages = new ArrayList<>();
    }

    // Add a message to the chat
    public void sendMessage(String sender, String receiver, String content) {
        Message message = new Message(sender, receiver, content);
        messages.add(message);
    }

    // Display the entire conversation
    public void showConversation()
     {
        for (Message message : messages)
         {
            System.out.println(message);
        }
    }
}