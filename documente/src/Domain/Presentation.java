package Domain;

public class Presentation extends Document {
    private int numberOfSlides;
    private String text;

    public Presentation(String author, int numberOfSlides, String text) {
        super(author);
        this.numberOfSlides = numberOfSlides;
        this.text = text;
    }

    public boolean isConformant() {
        return (text.length() / numberOfSlides) <= 200;
    }

    public String toString() {
        return "Presentation { Author='" + getAuthor() + "', numberOfSlides=" + numberOfSlides + ", text='" + text + "' }";
    }

    public int getNumberOfSlides() {
        return numberOfSlides;
    }

    public String getText() {
        return text;
    }

}
