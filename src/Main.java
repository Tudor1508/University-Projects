class PatratePerfecte {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Nu ați furnizat argumente. Introduceți valori în linia de comandă.");
            return;
        }
        int[] valori = citire(args);
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
        int radacina = (int) Math.sqrt(numar);
        return radacina * radacina == numar;
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
