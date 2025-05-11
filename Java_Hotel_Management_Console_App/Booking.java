public class Booking {
    private String customerName;
    private String customerPhone;
    private Room room;
    private String checkInDate;
    private String checkOutDate;

    public Booking(String customerName, String customerPhone, Room room, String checkInDate, String checkOutDate) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public String getCustomerName() { return customerName; }
    public String getCustomerPhone() { return customerPhone; }
    public Room getRoom() { return room; }
    public String getCheckInDate() { return checkInDate; }
    public String getCheckOutDate() { return checkOutDate; }

    public void setCustomerPhone(String phone) { this.customerPhone = phone; }
    public void setRoom(Room room) { this.room = room; }
    public void setCheckInDate(String date) { this.checkInDate = date; }
    public void setCheckOutDate(String date) { this.checkOutDate = date; }
}