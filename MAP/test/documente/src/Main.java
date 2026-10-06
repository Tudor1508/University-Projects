import Repository.Repository;
import Service.Service;
import UI.UI;

public class Main {
    public static void main(String[] args) {
        Repository repository = new Repository();
        Service service = new Service(repository);
        UI ui = new UI(service);
        ui.ShowUserInterface();
    }
}


//fisier.txt fara modificari(neordoante conforma si neconforme

//Manuscript { Author='Victor Ponta', numberOfWords=2500, numberOfPages=4 }
//Presentation { Author='Mircea Bravo', numberOfSlides=25, text=''Aceasta prezentare are o cantitate adecvată de text.'' }
//Manuscript { Author='John Pork', numberOfWords=1800, numberOfPages=6 }
//Presentation { Author='Alexandru Balan', numberOfSlides=15, text=''Text de lungime moderata.'' }

