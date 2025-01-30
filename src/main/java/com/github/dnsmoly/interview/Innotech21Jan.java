package com.github.dnsmoly.interview;

import java.util.Set;
import java.util.TreeSet;

public class Innotech21Jan {
    interface People {
        boolean isWoman();
    }

    public static class Trainee implements People {
        private final String name;

        public Trainee(String name) {
            System.out.println("Create " + name);
            this.name = name;
        }

        public boolean isWoman() {
            return this.name.contains("ова");
        }
    }

    public static void main(String[] args) {
        Set<People> set = new TreeSet<>(); // <? super People> :
                                           // 1. Elements treated as Objects.
                                           // 2. Need to add comparator / implement Comparable
        set.add(new Trainee("Юрий Гагарин"));
        set.add(new Trainee("Валентина Терешкова"));

        set.stream()
                .filter(People::isWoman) // Can't call isWoman() on Object
                .forEach(System.out::println);
    }
}
