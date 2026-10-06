package Domain;

public class Manuscript extends Document {
    private int numberOfWords;
    private int numberOfPages;

    public Manuscript(String author, int numberOfWords, int numberOfPages) {
        super(author);
        this.numberOfWords = numberOfWords;
        this.numberOfPages = numberOfPages;
    }

    public boolean isConformant() {
        return numberOfWords >= 2000 && numberOfPages <= 5;
    }

    public String toString() {
        return "Manuscript { Author='" + getAuthor() + "', numberOfWords=" + numberOfWords + ", numberOfPages=" + numberOfPages + " }";
    }

    public int getNumberOfWords() {
        return numberOfWords;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }
}
