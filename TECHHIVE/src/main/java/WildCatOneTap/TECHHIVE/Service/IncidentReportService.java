package WildCatOneTap.TECHHIVE.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import WildCatOneTap.TECHHIVE.Entity.IncidentReportEntity;
import WildCatOneTap.TECHHIVE.Repository.IncidentReportRepository;

@Service
public class IncidentReportService {

    private final IncidentReportRepository irepo; 

    @Autowired
    public IncidentReportService(IncidentReportRepository irepo) {
        this.irepo = irepo; 
    }

    // Create
    public IncidentReportEntity saveIncidentReport(IncidentReportEntity incidentReport) {
        return irepo.save(incidentReport); 
    }

    // Read - Method to retrieve all incident reports
    public List<IncidentReportEntity> getAllIncidentReports() {
        return irepo.findAll(); 
    }

    // Read - Method to retrieve an incident report by its ID
    public Optional<IncidentReportEntity> getIncidentReportById(int incidentID) {
        return irepo.findById(incidentID); 
    }

    // Delete
    public void deleteIncidentReport(int incidentID) {
        irepo.deleteById(incidentID); 
    }
}
