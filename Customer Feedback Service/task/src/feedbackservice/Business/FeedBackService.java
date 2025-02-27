package feedbackservice.Business;

import feedbackservice.persistence.FeedBackRepository;
import feedbackservice.persistence.MyEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeedBackService {
    private final FeedBackRepository feedBackRepository;


    public FeedBackService(FeedBackRepository feedBackRepository) {
        this.feedBackRepository = feedBackRepository;
    }

    public void saveFeedback(MyEntity myEntity){
        feedBackRepository.save(myEntity);
    }

  public Optional<MyEntity> findById(String id){
        return feedBackRepository.findById(id);
  }


    public Page<MyEntity> findAll(Example<MyEntity> example,PageRequest pageRequest) {

        return feedBackRepository.findAll(example,pageRequest);
    }

    public Long count(Example<MyEntity> example) {
        return feedBackRepository.count();
    }
}
