package WildCatOneTap.TECHHIVE.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblIncidentReport")
public class IncidentReportEntity {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int incidentID;

    @Column(name = "type")
    private String type;

    @Column(name = "location")
    private String location;

    @Column(name = "evidence")
    private String evidence;

    @Column(name = "status")
    private String status;

    // Constructors, getters, and setters
    public IncidentReportEntity() {
    }

    public IncidentReportEntity(int incidentID, String type, String location, String evidence, String status) {
        this.incidentID = incidentID;
        this.type = type;
        this.location = location;
        this.evidence = evidence;
        this.status = status;
    }

    public int getIncidentID() {
        return incidentID;
    }

    public void setIncidentID(int incidentID) {
        this.incidentID = incidentID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
