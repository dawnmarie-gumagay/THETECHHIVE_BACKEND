package WildCatOneTap.TECHHIVE.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import WildCatOneTap.TECHHIVE.Entity.IncidentReportEntity;
import WildCatOneTap.TECHHIVE.Service.IncidentReportService;

@RestController
@RequestMapping("/incident-report")
public class IncidentReportController {

    private final IncidentReportService iserv; // Changed variable name

    @Autowired
    public IncidentReportController(IncidentReportService iserv) { // Changed parameter name
        this.iserv = iserv; // Changed assignment
    }

    // Create
    @PostMapping("/save")
    public IncidentReportEntity saveIncidentReport(@RequestBody IncidentReportEntity incidentReport) {
        return iserv.saveIncidentReport(incidentReport); // Changed method call
    }

    // Read - Retrieve all incident reports
    @GetMapping("/all")
    public List<IncidentReportEntity> getAllIncidentReports() {
        return iserv.getAllIncidentReports(); // Changed method call
    }

    // Read - Retrieve an incident report by its ID
    @GetMapping("/{incidentID}")
    public Optional<IncidentReportEntity> getIncidentReportById(@PathVariable int incidentID) {
        return iserv.getIncidentReportById(incidentID); // Changed method call
    }

    // Delete
    @DeleteMapping("/delete/{incidentID}")
    public void deleteIncidentReport(@PathVariable int incidentID) {
        iserv.deleteIncidentReport(incidentID); // Changed method call
    }
}
