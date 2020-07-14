package org.ayush.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

@Entity
public class Alert {

    @Id
    String id;

    String vin;
    String name;
    String priority;

    @Column(columnDefinition = "DATETIME")
    @DateTimeFormat(pattern = "YYYY-MM-DDThh:mm:ss.sTZD")
    private Date timestamp;

    public Alert(){}

    public Alert(String vin, String name, String priority, Timestamp timestamp) {
        this.id = UUID.randomUUID().toString();
        this.vin = vin;
        this.name = name;
        this.priority = priority;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTimestamp() {
        return DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG).format(timestamp);
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}