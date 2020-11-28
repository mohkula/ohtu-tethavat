package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Sovelluslogiikka {

    private int tulos;

    public void plus(int luku) {
        tulos += luku;
    }

    public void miinus(int luku) {
        tulos -= luku;
    }

    public void nollaa() {
        tulos = 0;
    }

    public int tulos() {
        return tulos;
    }
}

class Summa extends Komento {

    private TextField syote;
    private TextField tulostus;
    private int arvo;
    private int tulos;

    public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        try {
            this.arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
            this.arvo = 0;
        }

        syote = syotekentta;
        tulostus = tuloskentta;

    }

    @Override
    public void suorita() {
        arvo = Integer.parseInt(syote.getText());
        tulos = Integer.parseInt(tulostus.getText());

        syote.setText("");
        tulostus.setText("" + (arvo + tulos));
        tulos = Integer.parseInt(tulostus.getText());

    }

    @Override
    public void peru() {
        tulostus.setText("" + (tulos - arvo));
    }

}

class Erotus extends Komento {

    private TextField syote;
    private TextField tulostus;
    private int arvo;
    private int tulos;

    private TextField syotekentta;
    private TextField tuloskentta;

    public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {

        this.syotekentta = this.syotekentta;
        this.tuloskentta = this.tuloskentta;
        try {
            this.arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
            this.arvo = 0;
        }

        syote = syotekentta;
        tulostus = tuloskentta;
    }

    @Override
    public void suorita() {

        arvo = Integer.parseInt(syote.getText());
        tulos = Integer.parseInt(tulostus.getText());

        syote.setText("");
        tulostus.setText("" + (tulos - arvo));
        tulos = Integer.parseInt(tulostus.getText());

    }

    @Override
    public void peru() {
        tulostus.setText("" + (tulos + arvo));
    }

}

class Nollaa extends Komento {

    private TextField syote;
    private TextField tulostus;
    private int arvo;
    private int tulos;
    private TextField tuloskentta;

    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        try {
            tulos = Integer.parseInt(tulostus.getText());
        } catch (Exception e) {
            tulos = 0;
        }

        syote = syotekentta;
        tulostus = tuloskentta;
    }

    @Override
    public void suorita() {

        syote.setText("");
        tulostus.setText("0");
    }

    @Override
    public void peru() {

        tulostus.setText(Integer.toString(tulos));
    }

}
