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

    public void deleteDocument(int index) {
        if (index >= 1 && index <= documentList.size()) {
            Document deletedDocument = documentList.remove(index - 1);
            System.out.println("Documentul șters: " + deletedDocument);
        } else {
            throw new IllegalArgumentException("Indexul nu există. Încearcă din nou.");
        }
    }
}
