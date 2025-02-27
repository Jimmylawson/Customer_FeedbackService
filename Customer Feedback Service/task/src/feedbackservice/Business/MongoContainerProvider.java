package feedbackservice.Business;

import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.List;


@Component
public class MongoContainerProvider {
    private final MongoDBContainer container;

    public MongoContainerProvider(@Value("${mongodb.image}") String mongodbImage) {
        container = new MongoDBContainer(DockerImageName.parse(mongodbImage)); // image name
        container.withCreateContainerCmdModifier(cmd -> cmd.withName("feedback-service")); // container name
        container.addEnv("MONGO_INITDB_DATABASE", "feedback_db"); // init database
        container.setPortBindings(List.of("27017:27017")); // expose port 27017
        container.start();
    }

    @PreDestroy
    public void tearDown() {
        container.stop();
    }
}
