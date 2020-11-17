package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class VarastoTest {
	Pankki pankki;
	Viitegeneraattori viite;
	Kauppa kauppa;
	Varasto v;
	Kirjanpito kirj;
	@Before
	public void setUp() {
    	pankki = mock(Pankki.class);
    	viite = mock(Viitegeneraattori.class);
    	kirj = mock(Kirjanpito.class);
    	kauppa = mock(Kauppa.class);
    	v = new Varasto(kirj);
	}
	
	
	
	@Test
	public void haeTuoteToimii(){
	
		
		assertEquals(v.haeTuote(1).toString(), "Koff Portteri");
		
		
		
	}
	
	
}
	