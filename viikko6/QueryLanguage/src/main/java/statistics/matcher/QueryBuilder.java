package statistics.matcher;

import java.util.ArrayList;

public class QueryBuilder {

    private Matcher matcher;
    private ArrayList<Matcher> lista;

    public QueryBuilder() {
        matcher = new All();
        lista = new ArrayList<>();
    }

    public Matcher build() {

        if(lista.size() == 0){
            return matcher;
        }

        Matcher[] m = new Matcher[lista.size()];

        for(int i = 0; i<lista.size(); i++){
            m[i] = lista.get(i);
        }

        matcher = new And(m);
        lista.clear();

        return matcher;
    }

    public QueryBuilder playsIn(String team) {

        lista.add(new PlaysIn(team));
        return this;
    }

    public QueryBuilder hasAtLeast(int value, String category) {

       lista.add(new HasAtLeast(value, category));
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String category) {
              lista.add(new HasFewerThan(value, category));
        return this;
    }


    public QueryBuilder oneOf(Matcher... matchers){
        lista.add(new Or(matchers));

        return this;
    }

}
