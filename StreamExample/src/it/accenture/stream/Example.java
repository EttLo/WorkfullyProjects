package it.accenture.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Example {
    public static void main(String[] args) {
        List<String> ls = new ArrayList<>();
        ls.add("alessio");
        ls.add("cimaglia");

        ls.sort((l1, l2) -> l1.length() - l2.length()); // regole ad una riga
        ls.sort((l1, l2) -> {// regole a più righe
            int x = l1.length();
            int y = l2.length();
            return x - y;
        });
        ls.sort(Example::myFunct);
        // Comparator<String> x = (String l1, String l2) -> l1.length() - l2.length();
        var z = funct();
        System.out.println(z.apply(10));
        // stream, trasformazione in flusso di dati in modo che possano essere
        // analizzati in modo dichiarativo e funzionale
        var x = ls.stream().filter(s -> s.length() >= 5).limit(2).map(String::toUpperCase);
        // Optional<String> y = x.findFirst();
        x.forEach(System.out::println); // gli stream eseguono solo quando vai ad eseguire un operatore terminale, che
                                        // trasforma da stream a risultato concreto. Lazy: eseguono solo se costretti
        // uno stream può essere eseguito una volta sola, si può però salvare il
        // risultato usando Collect
    }

    public static int myFunct(String s1, String s2) {
        return s1.length() - s2.length();
    }

    public static Function<Integer, Integer> funct() {
        int x = 3;
        return i -> i * x;
        // return new Multiplier(x); // espressione equivalente alla lambda sopra
    }

    // una lambda esegue una closure su tutte le variabili che referenzia, la lambda
    // creata in funct viene ritornata all'esterno e riporta tutte le variabili
    // dichiarate nella funct e usate dalla lambda. In Java le variabili su cui la
    // lambda fa closure possono essere solo effectively final: cioè che non si ha
    // modo di modificarle. (non posso entrare dentro funct da un'altra parte del
    // programma e cambiare x)
}

class Multiplier implements Function<Integer, Integer> {
    public int mult;

    Multiplier(int m) {
        this.mult = m;
    }

    @Override
    public Integer apply(Integer t) {
        return t * mult;
    }
}
