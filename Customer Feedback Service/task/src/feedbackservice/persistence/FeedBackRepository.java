package feedbackservice.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface FeedBackRepository extends MongoRepository<MyEntity, String>, PagingAndSortingRepository<MyEntity, String>, QueryByExampleExecutor<MyEntity> {

}
