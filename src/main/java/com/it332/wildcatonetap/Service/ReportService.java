package com.it332.wildcatonetap.Service;

import com.it332.wildcatonetap.Entity.ReportEntity;
import com.it332.wildcatonetap.Repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public List<ReportEntity> getAllReports() {
        return reportRepository.findAll();
    }

    public Optional<ReportEntity> getReportById(Long id) {
        return reportRepository.findById(id);
    }

    public ReportEntity saveReport(ReportEntity report) {
        return reportRepository.save(report);
    }

    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }

    public ReportEntity updateReport(Long id, ReportEntity updatedReport) {
        Optional<ReportEntity> existingReport = reportRepository.findById(id);
        if (existingReport.isPresent()) {
            ReportEntity report = existingReport.get();
            report.setCreatedAt(updatedReport.getCreatedAt());
            report.setFullName(updatedReport.getFullName());
            report.setIdNumber(updatedReport.getIdNumber());
            report.setLevel(updatedReport.getLevel());
            report.setPhoto(updatedReport.getPhoto());
            report.setType(updatedReport.getType());
            report.setUserId(updatedReport.getUserId());
            report.setApproved(updatedReport.getApproved());
            report.setApprovalDate(updatedReport.getApprovalDate());
            report.setApproverId(updatedReport.getApproverId());
            report.setIsVerified(updatedReport.getIsVerified());
            return reportRepository.save(report);
        } else {
            return null;
        }
    }
}
