/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapp;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author vincentdu
 */
public class Seat {

    Integer id;
    Integer trainId;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
    boolean taken = false;
    String description = "Seat for someone to sit i n.";
    SeatType seatType;

    public Seat(Integer trainId,
                String description,
                boolean taken,
                String seatTypeString) {
        this.trainId = trainId;
        this.description = description;
        this.taken = taken;
        this.seatType = SeatType.findSeatType(seatTypeString);
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public Seat(Integer id,
                Integer trainId,
                String description,
                boolean taken,
                String seatTypeString,
                LocalDateTime createdAt,
                LocalDateTime modifiedAt) {
        this.id = id;
        this.trainId = trainId;
        this.description = description;
        this.taken = taken;
        this.seatType = SeatType.valueOf(seatTypeString);
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    
    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public SeatType getSeatType() { return seatType; }

    public Integer getId() { return id; }

    public Integer getTrainId() { return trainId; }

    public boolean isTaken() {
        return taken;
    }
    
    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
