package it.accenture.stream;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExamples {
    public static void main(String[] args) {
        Developer md1 = new Developer(1L, "Andrea", "Rossi", LocalDate.of(1998, 9, 17),
                "Java", 25, Sex.MALE, 2500);
        Developer fd1 = new Developer(2L, "Sara", "Bianchi", LocalDate.of(1995, 5, 19),
                "PHP", 30, Sex.FEMALE, 3000);
        Developer md2 = new Developer(3L, "Carlo", "LAquila", LocalDate.of(1994, 8, 15),
                "Java", 20, Sex.MALE, 2500);
        Developer fd2 = new Developer(4L, "Luigia", "Roma", LocalDate.of(1996, 12, 3),
                "C++", 50, Sex.UNDECIDED, 5000);
        Developer fd3 = new Developer(5L, "Alfie", "Solomons", LocalDate.of(1946, 12, 3),
                "C++", 50, Sex.UNDECIDED, 5000);

        List<Developer> ld = List.of(md1, fd1, md2, fd2, fd3);

        System.out.println("You are about to get sued from angry Karen:");
        System.out.println(Program.youAreAboutToGetSued(ld));

        System.out.println("Average salary C++ developer older thatn 30:");
        System.out.println(Program.averageSalaryCPlusPlusDEveloperOlderThanThirty(ld));

        System.out.println("The three most experienced developers:");
        List<Developer> topThree = Program.threeMostExperiencedDevelopers(ld);
        for (Developer developer : topThree) {
            System.out.println(developer.getFirstName());
        }

        System.out.println("The two most used languages:");
        List<String> ls = Program.twoMostUsedLanguages(ld);
        for (String language : ls) {
            System.out.println(language);
        }

        System.out.println("Median salary:");
        System.out.println(Program.medianSalary(ld));

        System.out.println("Salary mode:");
        System.out.println(Program.modeSalary(ld));

    }

    public double femaleOver30YearsSalarySum(List<Developer> ld) {
        double sum = 0;
        for (Developer d : ld) {
            if (d.getSex() == Sex.FEMALE && d.isOlderThan(30)) {
                sum += d.getSalary();
            }
        }
        return sum;
    }

    public double streamFemaleOver30YearsSalarySum(List<Developer> ld) {
        /*
         * Predicate<Developer> pd = new Predicate<Developer>() {
         * 
         * @Override
         * public boolean test(Developer developer) {
         * return false;
         * }
         * };
         */
        return ld.stream().filter(d -> d.getSex() == Sex.FEMALE && d.isOlderThan(30))
                .mapToDouble(Developer::getSalary).sum();
    }

    public double topNSeniorFemaleSalarySum(List<Developer> ld) {
        return ld.stream().filter(d -> d.getSex() == Sex.FEMALE && d.isOlderThan(30))
                .mapToDouble(d -> d.getSalary()).sorted().limit(10).sum(); // sum() operatore terminale
    }

    public List<Developer> malesExpertInCPlusPlus(List<Developer> ld) {
        return ld.stream().filter(d -> d.getSex() == Sex.MALE &&
                d.getFavouriteLanguage().equals("C++")).collect(Collectors.toList());
        // double salarySum = x.stream().mapToDouble(Developer::getSalary).reduce(0,
        // (d1,d2) -> d1+d2);
        // int ageProduct = x.stream().mapToInt(Developer::getAge).reduce(1, (d1,d2) ->
        // d1*d2);
        // OptionalInt ageProduct2 = x.stream().mapToInt(Developer::getAge).reduce((d1,
        // d2) -> d1*d2);

        // var y = x.limit(3); //stream sono detti "lazy"
        // var z = y.findFirst(); //operatore terminale
        // var w = y.collect(Collectors.toList()); //operatore terminale
        // riutilizzare uno stream impatta sulla performance
    }

    public DeveloperSummaryData calculateSummaryData(List<Developer> ld) {

        return ld.stream().reduce(new DeveloperSummaryData(),
                DeveloperSummaryData::addDeveloper, DeveloperSummaryData::combine);
        /*
         * DeveloperSummaryData result = x.stream()
         * .reduce(new DeveloperSummaryData(),
         * (dsd, d) -> DeveloperSummaryData.addDeveloper(dsd, d),
         * (dsd1, dsd2) -> DeveloperSummaryData.combine(dsd1, dsd2));
         */
        // reduce(oggetto allo stato iniziale, lambda/method reference per accumulatore,
        // lambda/mf per combinatore se parallelismo)
    }
}
