package Domain;

public abstract class Document {
    private String author;

    public Document(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public abstract boolean isConformant();

    public String toString() {
        return "Document{" + "author='" + author + '\'' + '}';
    }
}
