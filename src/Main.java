import java.util.Scanner;

class PatratePerfecte {

    public static void main(String[] args) {
        int[] valori;

        if (args.length == 0) {
            valori = citireDeLaTastatura();
        } else {
            valori = citire(args);
        }
        int[] pp = determinapp(valori);
        afiseaza_pp(pp);
    }

    private static int[] citire(String[] args) {
        int[] valori = new int[args.length];

        for (int i = 0; i < args.length; i++) {
            try {
                valori[i] = Integer.parseInt(args[i]);
            } catch (NumberFormatException e) {
                System.out.println(args[i] + " nu este un număr întreg valid.");
                valori[i] = -1;
            }
        }

        return valori;
    }

    private static int[] citireDeLaTastatura() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceți numărul de valori: ");
        int n = scanner.nextInt();

        int[] valori = new int[n];

        System.out.println("Introduceți valorile:");
        for (int i = 0; i < n; i++) {
            valori[i] = scanner.nextInt();
        }

        return valori;
    }

    private static int[] determinapp(int[] valori) {
        int count = 0;
        for (int valoare : valori) {
            if (estePatratPerfect(valoare)) {
                count++;
            }
        }
        int[] pp = new int[count];
        int index = 0;
        for (int valoare : valori) {
            if (estePatratPerfect(valoare)) {
                pp[index++] = valoare;
            }
        }
        return pp;
    }

    private static boolean estePatratPerfect(int numar) {
        if (numar < 0) {
            return false;
        }
        int r = (int) Math.sqrt(numar);
        return r * r == numar;
    }
    private static void afiseaza_pp(int[] pp) {
        if (pp.length == 0) {
            System.out.println("Nu există pătrate perfecte în valorile furnizate.");
        } else {
            System.out.print("Pătratele perfecte sunt: ");
            for (int patrat : pp) {
                System.out.print(patrat + " ");
            }
            System.out.println();
        }
    }
}
