package edu.uchicago.gerber._03objects.P8_16;

public class Message {
    private String sender;
    private String recipient;
    private StringBuilder text;

    public Message(String sender, String recipient) {
        this.sender = sender;
        this.recipient = recipient;
        this.text = new StringBuilder();
    }

    public void append(String l) {
        this.text.append(l).append("\n");
    }

    @Override
    public String toString() {
        String s = "From: " + sender + '\n' + "To: "+ recipient + '\n' + '\n' + this.text.toString();
        return s;
    }
}

