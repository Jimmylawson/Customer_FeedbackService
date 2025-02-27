package feedbackservice.controller;

import feedbackservice.Business.FeedBackService;
import feedbackservice.persistence.FeedBackRepository;
import feedbackservice.persistence.MyEntity;
import feedbackservice.persistence.TotalDocument;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
public class FeedBackController {
    private final FeedBackService feedBackService;


    public FeedBackController(FeedBackService feedBackService) {

        this.feedBackService = feedBackService;
    }

    @PostMapping("/feedback")
    public ResponseEntity<Object> createFeedback(@RequestBody MyEntity myEntity) {
        feedBackService.saveFeedback(myEntity);

        return ResponseEntity
                .created(URI.create("/feedback/"  + myEntity.getId()))
                .build();

    }

    @GetMapping("/feedback/{id}")
    public ResponseEntity<MyEntity> getFeedbackById(@PathVariable String id){
        Optional<MyEntity> getFeedBack = feedBackService.findById(id);
        if(getFeedBack.isPresent()){
            return ResponseEntity.ok(getFeedBack.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/feedback")
    public ResponseEntity<TotalDocument> getAllFeedBacks(@RequestParam(name="page", defaultValue = "1") int page, @RequestParam(name="perPage",defaultValue = "10") int perPage,
                                                         @RequestParam(name="rating",required = false) Integer rating,
                                                        @RequestParam(name="product",required = false) String product,
                                                        @RequestParam(name="vendor",required = false) String vendor,
                                                        @RequestParam(name="feedback",required = false) String feedback,
                                                        @RequestParam(name="customer",required = false) String customer){

        ///Validate and Sanitize the page and perPage parameters
        if(page < 1) page = 1;
        if(perPage < 5 || perPage > 20) perPage = 10;

        /// Create a probe entity
        MyEntity probe = new MyEntity();
        if(rating != null) probe.setRating(rating);
        if(product != null) probe.setProduct(product);
        if(vendor != null) probe.setVendor(vendor);
        if(feedback != null) probe.setFeedback(feedback);
        if(customer != null) probe.setCustomer(customer);

        /// Define a matcher for flexible search
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase() /// Case-sensitive search
                .withIgnoreNullValues() /// Ignore null values
                .withStringMatcher(ExampleMatcher.StringMatcher.EXACT);///Exact String match

        /// Find all the feedbacks that match the example
        Example<MyEntity> example = Example.of(probe,matcher);

        long totalDocument = feedBackService.count(example);
        ///Create PageRequest with sorting by ID in descending order
        PageRequest pageRequest = PageRequest.of(page -1,perPage,Sort.by(Sort.Direction.DESC,"_id"));
       /// Perform query with filtering and pagination
        Page<MyEntity> feedbackPage = feedBackService.findAll(example,pageRequest);




        ///Create totalDocument instance
        TotalDocument response = new TotalDocument(
                feedbackPage.getTotalElements(),
                feedbackPage.isFirst(),
                feedbackPage.isLast(),
                feedbackPage.getContent()
        );

        /// Return the totalDocument response
        return ResponseEntity.ok(response);

    }

}
