package Repository;

import Domain.Document;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private List<Document> documentList = new ArrayList<>();

    public void add(Document document) {
        documentList.add(document);
    }

    public List<Document> getAllEntities() {
        return documentList;
    }
}
