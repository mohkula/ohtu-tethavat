package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] alkuPaajono;      // Joukon luvut säilytetään taulukon alkupäässä.
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla.

    public IntJoukko() {
        alkuPaajono = new int[KAPASITEETTI];
        for (int i = 0; i < alkuPaajono.length; i++) {
            alkuPaajono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        alkuPaajono = new int[kapasiteetti];

        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;

    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");//heitin vaan jotain :D
        }
        alkuPaajono = new int[kapasiteetti];

        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;

    }

    public boolean lisaa(int luku) {

        if (alkioidenLkm == 0) {
            alkuPaajono[0] = luku;
            alkioidenLkm++;
            return true;
        }
        if (!kuuluu(luku)) {
            alkuPaajono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % alkuPaajono.length == 0) {
                luoVanhaTaulukko();
            }
            return true;
        }
        return false;
    }

    public void luoVanhaTaulukko() {
        int[] vanhaTaulukko = new int[alkuPaajono.length];
        vanhaTaulukko = alkuPaajono;
        kopioiTaulukko(alkuPaajono, vanhaTaulukko);
        alkuPaajono = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(vanhaTaulukko, alkuPaajono);

    }

    public boolean kuuluu(int luku) {

        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == alkuPaajono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {

        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == alkuPaajono[i]) {

                alkuPaajono[i] = 0;
                siirraVasemmalle(i);
                return true;

            }
        }

        return false;
    }

    public void siirraVasemmalle(int kohta) {
        int apu;
        if (kohta != -1) {
            for (int j = kohta; j < alkioidenLkm - 1; j++) {
                apu = alkuPaajono[j];
                alkuPaajono[j] = alkuPaajono[j + 1];
                alkuPaajono[j + 1] = apu;
            }
            alkioidenLkm--;
        }

    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + alkuPaajono[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += alkuPaajono[i];
                tuotos += ", ";
            }
            tuotos += alkuPaajono[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = alkuPaajono[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(bTaulu[i]);
        }

        return z;
    }

}
