package trainapp;

/**
 * Created by vincentdu on 4/30/17.
 */
public enum SeatType {

    REGULAR_SEAT("Regular Seat"),
    SPECIAL_RESERVED_SEAT("Special Reserved Seat"),
    TABLE_SEAT("Table Seat");

    String seat;

    SeatType(String seat) {
        this.seat = seat;
    }

    public String getSeat() {
        return seat;
    }

    public static SeatType findSeatType(String value) {
        if (value.equals(SeatType.SPECIAL_RESERVED_SEAT.getSeat())) {
            return SeatType.SPECIAL_RESERVED_SEAT;
        } else if (value.equals(SeatType.REGULAR_SEAT.getSeat())) {
            return SeatType.REGULAR_SEAT;
        } else {
            return SeatType.TABLE_SEAT;
        }
    }

}
