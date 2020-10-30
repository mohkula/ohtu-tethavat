/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import ohtuesimerkki.Player;
import ohtuesimerkki.Reader;
import ohtuesimerkki.Statistics;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class StatisticsTest {


    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;


    public StatisticsTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @After
    public void tearDown() {
    }


     @Test
     public void searchToimii(){
         Player p = stats.search("Gretzky");


         assertEquals(p.toString(),"Gretzky              EDM 35 + 89 = 124");
     }

     @Test
    public void searchPalauttaaNullin() {
        Player p = stats.search("b");

        assertEquals(p,null);
    }

    @Test
    public void teamToimii(){

        assertEquals(stats.team("PIT").get(0).toString(),"Lemieux              PIT 45 + 54 = 99");
    }

    @Test
    public void teamPalauttaaTyhjanListan(){

        assertEquals(stats.team("b").size(),0);
    }

    @Test
    public void topScorersToimii(){

        assertEquals(stats.topScorers(2).get(0).toString(),"Gretzky              EDM 35 + 89 = 124");
        assertEquals(stats.topScorers(2).get(1).toString(),"Lemieux              PIT 45 + 54 = 99");

    }


}


