package hello;

public class Message {

    private String tableId;

    public Message() {
    }

    public Message(String tableId) {
        this.tableId = tableId;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
}
