package WildCatOneTap.TECHHIVE.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import WildCatOneTap.TECHHIVE.Entity.FeedbackEntity;
import WildCatOneTap.TECHHIVE.Repository.FeedbackRepository;

@Service
public class FeedbackService {

    private final FeedbackRepository frepo; 

    @Autowired
    public FeedbackService(FeedbackRepository frepo) { 
        this.frepo = frepo; 
    }

    // Create
    public FeedbackEntity saveFeedback(FeedbackEntity feedback) {
        return frepo.save(feedback); 
    }

    // Read - Method to retrieve all feedbacks
    public List<FeedbackEntity> getAllFeedbacks() {
        return frepo.findAll(); 
    }

    // Read - Method to retrieve a feedback by its ID
    public Optional<FeedbackEntity> getFeedbackById(int feedbackID) {
        return frepo.findById(feedbackID); 
    }

    // Delete
    public void deleteFeedback(int feedbackID) {
        frepo.deleteById(feedbackID); 
    }
}
