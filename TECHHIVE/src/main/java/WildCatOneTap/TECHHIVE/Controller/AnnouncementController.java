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

import WildCatOneTap.TECHHIVE.Entity.AnnouncementEntity;
import WildCatOneTap.TECHHIVE.Service.AnnouncementService;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    private final AnnouncementService aserv;

    @Autowired
    public AnnouncementController(AnnouncementService aserv) {
        this.aserv = aserv;
    }

    // Create
    @PostMapping("/save")
    public AnnouncementEntity saveAnnouncement(@RequestBody AnnouncementEntity announcement) {
        return aserv.saveAnnouncement(announcement);
    }

    // Read - Retrieve all announcements
    @GetMapping("/all")
    public List<AnnouncementEntity> getAllAnnouncements() {
        return aserv.getAllAnnouncements();
    }

    // Read - Retrieve an announcement by its ID
    @GetMapping("/{announcementID}")
    public Optional<AnnouncementEntity> getAnnouncementById(@PathVariable int announcementID) {
        return aserv.getAnnouncementById(announcementID);
    }

    // Update
    @PostMapping("/update/{announcementID}")
    public AnnouncementEntity updateAnnouncement(@PathVariable int announcementID, @RequestBody AnnouncementEntity newAnnouncementDetails) {
        return aserv.updateAnnouncement(announcementID, newAnnouncementDetails);
    }

    // Delete
    @DeleteMapping("/delete/{announcementID}")
    public void deleteAnnouncement(@PathVariable int announcementID) {
        aserv.deleteAnnouncement(announcementID);
    }
}
