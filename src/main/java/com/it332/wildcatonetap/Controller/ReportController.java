package com.it332.wildcatonetap.Controller;

import com.it332.wildcatonetap.Entity.ReportEntity;
import com.it332.wildcatonetap.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public List<ReportEntity> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/{id}")
    public Optional<ReportEntity> getReportById(@PathVariable Long id) {
        return reportService.getReportById(id);
    }

    @PostMapping
    public ReportEntity createReport(@RequestBody ReportEntity report) {
        return reportService.saveReport(report);
    }

    @PostMapping("/submitReport")
    public ResponseEntity<ReportEntity> submitReport(@RequestBody ReportEntity report) {
        ReportEntity savedReport = reportService.saveReport(report);
        return new ResponseEntity<>(savedReport, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ReportEntity updateReport(@PathVariable Long id, @RequestBody ReportEntity updatedReport) {
        return reportService.updateReport(id, updatedReport);
    }

    @DeleteMapping("/{id}")
    public void deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
    }
}
