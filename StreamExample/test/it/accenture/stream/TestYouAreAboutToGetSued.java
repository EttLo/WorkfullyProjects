package it.accenture.stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class TestYouAreAboutToGetSued {
    Developer md1 = new Developer(1L, "Andrea", "Rossi", LocalDate.of(1998, 9, 17),
            "Java", 25, Sex.MALE, 2500);
    Developer fd1 = new Developer(2L, "Sara", "Bianchi", LocalDate.of(1995, 5, 19),
            "PHP", 30, Sex.FEMALE, 3000);
    Developer md2 = new Developer(3L, "Carlo", "LAquila", LocalDate.of(1994, 8, 15),
            "Java", 20, Sex.MALE, 2500);
    Developer ud2 = new Developer(4L, "Luigia", "Roma", LocalDate.of(1996, 12, 3),
            "C++", 50, Sex.UNDECIDED, 5000);
    Developer ud3 = new Developer(5L, "Alfie", "Solomons", LocalDate.of(1946, 12, 3),
            "C++", 50, Sex.UNDECIDED, 5000);
    Developer fd2 = new Developer(6L, "Florinda", "Bianchi", LocalDate.of(1995, 5, 19),
            "Python", 30, Sex.FEMALE, 5);

    @Test
    public void testYouAreNotAboutToGetSued() {
        List<Developer> ld = List.of(md1, fd1, md2, ud2, ud3);
        Optional<Boolean> x = Program.youAreAboutToGetSued(ld);

        assertFalse(x.isEmpty());
        assertFalse(x.get());
    }

    @Test
    public void testWithoutMales() {
        List<Developer> ld = List.of(fd1, ud2, ud3);
        Optional<Boolean> x = Program.youAreAboutToGetSued(ld);
        assertTrue(x.isEmpty());
    }

    @Test
    public void testYouAreAboutToGetSued() {
        List<Developer> ld = List.of(md1, fd2, md2, ud2, ud3);
        Optional<Boolean> x = Program.youAreAboutToGetSued(ld);
        assertFalse(x.isEmpty());
        assertTrue(x.get());
    }
}
