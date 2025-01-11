package com.github.dnsmoly.stream;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class StreamDistinct {
    private static List<String> names = List.of("Abc", "Bcasd", "Casdasd", "Ddsada", "Easdas", "Fasdas", "Gdasdas");

    public static void main(String[] args) {
        var people = new ArrayList<Person>();
        System.out.println("PEOPLE:");
        for (int i = 0; i < 1_000_000; i++) {
            people.add(generatePerson());
        }
        long start;
        long end;

        start = System.nanoTime();
        var res1 = makeDistinctHashSet(people);
        end = System.nanoTime();

        System.out.println("HashSet (ms): " + (end - start) / 1_000_000);

        start = System.nanoTime();
        var res2 = makeDistinctLinkedHashMap(people);
        end = System.nanoTime();

        System.out.println("LinkedHashMap (ms): " + (end - start) / 1_000_000);

        start = System.nanoTime();
        var res3 = makeDistinctTreeMap(people);
        end = System.nanoTime();

        System.out.println("TreeMap (ms): " + (end - start) / 1_000_000);
    }

    public static List<Person> makeDistinctHashSet(List<Person> people) {
        var seenNames = new HashSet<String>();
        var result = new ArrayList<Person>();
        for (var person : people) {
            if (seenNames.contains(person.firstName)) {
                continue;
            }
            seenNames.add(person.firstName);
            result.add(person);
        }
        return result;
    }

    public static List<Person> makeDistinctLinkedHashMap(List<Person> people) {
        return people.stream().collect(Collectors.toMap(
                Person::firstName,
                p -> p,
                (existing, replacement) -> existing,
                LinkedHashMap::new
        )).values().stream().toList();
    }

    public static List<Person> makeDistinctTreeMap(List<Person> people) {
        return new ArrayList<>(
                people.stream()
                        .collect(Collectors.toMap(
                                Person::firstName, // Use `firstName` as the key
                                person -> person,  // Use the `Person` object as the value
                                (existing, replacement) -> existing, // Keep the first occurrence
                                TreeMap::new// Sort keys by natural order
                        ))
                        .values()
        );
    }

    private static Person generatePerson() {
        return new Person(generateRandomString(2), generateRandomString(3));
    }

    private static String generateRandomString(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    public record Person(String firstName, String lastName) {
    }
}
