package it.accenture.stream;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Program {

    public static Optional<Boolean> youAreAboutToGetSued(List<Developer> ld) {
        OptionalDouble maxFemale = ld.stream().filter(d -> d.getSex() == Sex.FEMALE).mapToDouble(Developer::getSalary)
                .max();
        OptionalDouble minMale = ld.stream().filter(d -> d.getSex() == Sex.MALE).mapToDouble(Developer::getSalary)
                .min();

        return (minMale.isPresent() && maxFemale.isPresent())
                ? Optional.of(minMale.getAsDouble() > maxFemale.getAsDouble())
                : Optional.empty();
    }

    public static double averageSalaryCPlusPlusDEveloperOlderThanThirty(List<Developer> ld) {
        OptionalDouble averageSalary = ld.stream()
                .filter(d -> d.getFavouriteLanguage().equals("C++") && d.isOlderThan(30))
                .mapToDouble(Developer::getSalary).average();

        return averageSalary.orElse(0);
    }

    public static List<Developer> threeMostExperiencedDevelopers(List<Developer> ld) {
        return ld.stream().sorted((d1, d2) -> d2.getYearsOfExperience() - d1.getYearsOfExperience())
                .limit(3).toList();
    }

    public static List<String> twoMostUsedLanguages(List<Developer> ld) { // DA FARE
        return ld.stream().collect(Collectors.groupingBy(Developer::getFavouriteLanguage)).entrySet()
                .stream().sorted((kv1, kv2) -> kv2.getValue().size() - kv1.getValue().size()).limit(2)
                .map(kv -> kv.getKey()).toList();
    }

    public static double medianSalary(List<Developer> ld) {
        double[] salaries = ld.stream().mapToDouble(Developer::getSalary).sorted().toArray();
        if (salaries.length % 2 == 0) {
            return (salaries[(salaries.length / 2) - 1] + salaries[(salaries.length / 2)]) / 2;
        } else {
            return salaries[salaries.length / 2];
        }
    }

    public static double modeSalary(List<Developer> ld) {
        // return
        // ld.stream().collect(Collectors.groupingBy(Developer::getSalary)).entrySet().stream()
        // .max((kv1, kv2) -> kv1.getValue().size() -
        // kv2.getValue().size()).get().getKey();
        return ld.stream().map(Developer::getSalary)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
                .max((kv1, kv2) -> (int) (kv1.getValue() - kv2.getValue())).get().getKey();
    }
}
