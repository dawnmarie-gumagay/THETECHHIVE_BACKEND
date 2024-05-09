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

import WildCatOneTap.TECHHIVE.Entity.FeedbackEntity;
import WildCatOneTap.TECHHIVE.Service.FeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackService fserv; // Changed variable name

    @Autowired
    public FeedbackController(FeedbackService fserv) { // Changed parameter name
        this.fserv = fserv; // Changed assignment
    }

    // Create
    @PostMapping("/save")
    public FeedbackEntity saveFeedback(@RequestBody FeedbackEntity feedback) {
        return fserv.saveFeedback(feedback); // Changed method call
    }

    // Read - Retrieve all feedbacks
    @GetMapping("/all")
    public List<FeedbackEntity> getAllFeedbacks() {
        return fserv.getAllFeedbacks(); // Changed method call
    }

    // Read - Retrieve a feedback by its ID
    @GetMapping("/{feedbackID}")
    public Optional<FeedbackEntity> getFeedbackById(@PathVariable int feedbackID) {
        return fserv.getFeedbackById(feedbackID); // Changed method call
    }

    // Delete
    @DeleteMapping("/delete/{feedbackID}")
    public void deleteFeedback(@PathVariable int feedbackID) {
        fserv.deleteFeedback(feedbackID); // Changed method call
    }
}
