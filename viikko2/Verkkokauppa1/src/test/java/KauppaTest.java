package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
	Pankki pankki;
	Viitegeneraattori viite;
	Varasto varasto;
	Kauppa k;
	
	@Before
	public void setUp() {
    	pankki = mock(Pankki.class);
    	viite = mock(Viitegeneraattori.class);
    	varasto = mock(Varasto.class);
    	k = new Kauppa(varasto, pankki, viite);
	}
	
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
       
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

       
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
                      

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    
    @Test
    public void oikeatArvotMuutenSamaTesti(){
        when(viite.uusi()).thenReturn(42);

       
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

                      

        k.aloitaAsiointi();
        k.lisaaKoriin(1);     
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(),eq(5));   
    
    }
    
    
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanKunOstetaanKaksiTuotetta() {
               when(viite.uusi()).thenReturn(42);

                  when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
                  when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "maito2", 4));
               
       
        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

  verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(),eq(9));   
    }
    
    
     @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanKunOstetaanKaksiSamaaTuotetta() {
               when(viite.uusi()).thenReturn(42);

               
                  when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
               
       
        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

  verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(),eq(10));   
    }
    
         @Test
         public void OstetaanTuotettaJokaOnLoppu() {
               when(viite.uusi()).thenReturn(42);

       
                when(varasto.saldo(1)).thenReturn(10); 
                when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
                when(varasto.saldo(2)).thenReturn(0); 
                when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "maito2", 5));
               
               
        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

  verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(),eq(5));   
    }
    
    
      @Test
    public void aloitAsiointiNollaaEdellisenOstoksenTiedot() {
               when(viite.uusi()).thenReturn(42);

               
                  when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
               
                  when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "maito2", 5));
       
        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        
        
        k.tilimaksu("pekka", "12345");

  verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(),eq(5));   
    }
    
    
    @Test
    public void uusiViitePerMaksutapahtuma(){
    	when(viite.uusi()).
    	thenReturn(1).
    	thenReturn(2).
    	thenReturn(3);
    	
    	       
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        
         k.aloitaAsiointi();
        k.lisaaKoriin(1);  
    
        k.tilimaksu("pekka", "12345");
        
               verify(pankki).tilisiirto(eq("pekka"), eq(1), eq("12345"), anyString(),eq(5));   
               
               
                  k.aloitaAsiointi();
        k.lisaaKoriin(1);  
    
        k.tilimaksu("pekka", "12345");
        
               verify(pankki).tilisiirto(eq("pekka"), eq(2), eq("12345"), anyString(),eq(5));   
               
               
               
                  k.aloitaAsiointi();
        k.lisaaKoriin(1);  
    
        k.tilimaksu("pekka", "12345");
        
               verify(pankki).tilisiirto(eq("pekka"), eq(3), eq("12345"), anyString(),eq(5));   
               
    	
    }
    
    
    
}

