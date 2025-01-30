package com.github.dnsmoly.excercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class UniqueCountry {
    // Pool of names
    private static final List<String> NAMES = Arrays.asList(
            "Alice", "Bob", "Charlie", "David", "Eva", "Frank", "Grace", "Henry", "Ivy", "Jack"
    );

    // Pool of countries
    private static final List<String> COUNTRIES = Arrays.asList(
            "USA", "Canada", "UK", "Australia", "Germany", "France", "Japan", "India", "Brazil", "China"
    );

    private static final Random rand = new Random();

    static class Person {
        String name;
        List<String> country;

        @Override
        public String toString() {
            return "Person{name='" + name + "', country=" + country + "}";
        }
    }

    public static void main(String[] args) {
        var persons = new ArrayList<Person>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(generatePerson());
        }
        // Different implementation timings
        var start = System.nanoTime();
        var res1 = getAllUniqCountryStreamDistinct(persons);
        var end = System.nanoTime();
        System.out.println("Stream distinct (ms): " + (end - start) / 1000000);

        start = System.nanoTime();
        var res2 = getAllUniqCountryStreamHashSet(persons);
        end = System.nanoTime();
        System.out.println("Stream HashSet (ms): " + (end - start) / 1000000);

        start = System.nanoTime();
        var res3 = getAllUniqCountryStreamHashSetNewArrayListFromSet(persons);
        end = System.nanoTime();
        System.out.println("Stream HashSet new ArrayList (ms): " + (end - start) / 1000000);

        start = System.nanoTime();
        var res4 = getAllUniqCountryForeachHashSetSingleCountry(persons);
        end = System.nanoTime();
        System.out.println("Foreach HashSet single country (ms): " + (end - start) / 1000000);

        start = System.nanoTime();
        var res5 = getAllUniqCountryForeachHashSetBulkCountry(persons);
        end = System.nanoTime();
        System.out.println("Foreach HashSet bulk country (ms): " + (end - start) / 1000000);

    }

    // Streams and hash set impl and use stream().toList() to get result
    static List<String> getAllUniqCountryStreamHashSet(List<Person> persons) {
        var uniqCountries = new HashSet<String>();
        persons.stream()
                .flatMap(person -> person.country.stream())
                .forEach(uniqCountries::add);
        return uniqCountries.stream().toList();
    }

    // Streams and Hash set impl but use new ArrayList to get result
    static List<String> getAllUniqCountryStreamHashSetNewArrayListFromSet(List<Person> persons) {
        var uniqCountries = new HashSet<String>();
        persons.stream()
                .flatMap(person -> person.country.stream())
                .forEach(uniqCountries::add);
        return new ArrayList<>(uniqCountries);
    }

    // Streams but use distinct
    static List<String> getAllUniqCountryStreamDistinct(List<Person> persons) {
        return persons.stream()
                .filter(p -> p.country != null) // Ignore null country lists
                .flatMap(p -> p.country.stream()) // Flatten all countries into a single stream
                .distinct() // Remove duplicates
                .toList();
    }

    // Foreach and add each country one by one
    static List<String> getAllUniqCountryForeachHashSetSingleCountry(List<Person> persons) {
        var uniqCountries = new HashSet<String>();
        for (Person p : persons) {
            if (p.country != null) { // Handle null checks
                for (String country : p.country) {
                    uniqCountries.add(country);
                }
            }
        }
        return new ArrayList<>(uniqCountries);
    }

    // Foreach and add each country in bulk
    static List<String> getAllUniqCountryForeachHashSetBulkCountry(List<Person> persons) {
        var uniqCountries = new HashSet<String>();
        for (Person p : persons) {
            if (p.country != null) { // Handle null checks
                uniqCountries.addAll(p.country);
            }
        }
        return new ArrayList<>(uniqCountries);
    }

    static Person generatePerson() {
        Person person = new Person();

        // Randomly select a name
        person.name = NAMES.get(rand.nextInt(NAMES.size()));

        // Randomly select the number of countries (0 to 5)
        int numberOfCountries = rand.nextInt(6); // 0 to 5 inclusive
        person.country = new ArrayList<>();

        // Randomly select countries
        for (int i = 0; i < numberOfCountries; i++) {
            String randomCountry = COUNTRIES.get(rand.nextInt(COUNTRIES.size()));
            // Ensure no duplicate countries
            if (!person.country.contains(randomCountry)) {
                person.country.add(randomCountry);
            }
        }
        return person;
    }
}
