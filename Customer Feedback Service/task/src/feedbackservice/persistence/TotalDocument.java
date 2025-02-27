package feedbackservice.persistence;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
public class TotalDocument {
    private Long total_documents;
    private boolean is_first_page;
    private boolean is_last_page;
    private List<MyEntity> documents;

    public TotalDocument(Long total_documents, boolean is_first_page, boolean is_last_page, List<MyEntity> documents) {
        this.total_documents = total_documents;
        this.is_first_page = is_first_page;
        this.is_last_page = is_last_page;
        this.documents = documents;
    }

    public Long getTotal_documents() {
        return total_documents;
    }

    public void setTotal_documents(Long total_documents) {
        this.total_documents = total_documents;
    }

    // Use consistent snake_case for boolean getters/setters
    public boolean getIs_first_page() {
        return is_first_page;
    }

    public void setIs_first_page(boolean is_first_page) {
        this.is_first_page = is_first_page;
    }

    public boolean getIs_last_page() {
        return is_last_page;
    }

    public void setIs_last_page(boolean is_last_page) {
        this.is_last_page = is_last_page;
    }

    public List<MyEntity> getDocuments() {
        return documents;
    }

    public void setDocuments(List<MyEntity> documents) {
        this.documents = documents;
    }
}
