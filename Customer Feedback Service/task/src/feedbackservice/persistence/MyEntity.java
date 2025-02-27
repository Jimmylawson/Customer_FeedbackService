package feedbackservice.persistence;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.testcontainers.shaded.org.checkerframework.checker.interning.qual.InternedDistinct;
import org.testcontainers.shaded.org.checkerframework.common.value.qual.MinLen;

import java.util.List;


@Document(collection = "feedback")
public class MyEntity {
    @Id
    private String id;
    @NotNull
    private Integer rating;

    private String feedback;
    private String customer;

    private String product;
    private String vendor;

    public MyEntity() {
    }

    public MyEntity(Integer rating, String feedback, String customer, String product, String vendor) {
        this.rating = rating;
        this.feedback = feedback;
        this.customer = customer;
        this.product = product;
        this.vendor = vendor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }




}
