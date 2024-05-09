package WildCatOneTap.TECHHIVE.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import WildCatOneTap.TECHHIVE.Entity.AnnouncementEntity;
import WildCatOneTap.TECHHIVE.Repository.AnnouncementRepository;

@Service
public class AnnouncementService {

    private final AnnouncementRepository arepo;

    @Autowired
    public AnnouncementService(AnnouncementRepository arepo) { 
        this.arepo = arepo; 
    }

    // Create
    public AnnouncementEntity saveAnnouncement(AnnouncementEntity announcement) {
        return arepo.save(announcement); 
    }

    // Read - Method to retrieve all announcements
    public List<AnnouncementEntity> getAllAnnouncements() {
        return arepo.findAll(); 
    }

    // Read - Method to retrieve an announcement by its ID
    public Optional<AnnouncementEntity> getAnnouncementById(int announcementID) {
        return arepo.findById(announcementID); 
    }

    // Update
    public AnnouncementEntity updateAnnouncement(int announcementID, AnnouncementEntity newAnnouncementDetails) {
        AnnouncementEntity announcement = arepo.findById(announcementID).orElse(null);
        if (announcement != null) {
            announcement.setTitle(newAnnouncementDetails.getTitle());
            announcement.setContent(newAnnouncementDetails.getContent());
           
            return arepo.save(announcement);
        }
        return null;
    }

    // Delete
    public void deleteAnnouncement(int announcementID) {
        arepo.deleteById(announcementID); 
    }
}
