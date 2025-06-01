package test.java21_fullcourse;

import java.util.*;

public class SequencedCollectionsDemo {

    public static void demoSequencedCollection() {
        SequencedCollection<String> sc = new LinkedHashSet<>();
        sc.add("A");
        sc.add("B");
        sc.add("C");

        System.out.println("First element: " + sc.getFirst());  // A
        System.out.println("Last element: " + sc.getLast());    // C

        sc = sc.reversed();
        System.out.println("Reversed: " + sc);
    }

    public static void demoSequencedMap() {
        SequencedMap<Integer, String> sm = new LinkedHashMap<>();
        sm.put(1, "One");
        sm.put(2, "Two");
        sm.put(3, "Three");

        System.out.println("First entry: " + sm.firstEntry());  // 1=One
        System.out.println("Last entry: " + sm.lastEntry());    // 3=Three

        sm = sm.reversed();
        System.out.println("Reversed map: " + sm);
    }

    public static void demoSequencedSet() {
        SequencedSet<String> ss = new LinkedHashSet<>();
        ss.add("X");
        ss.add("Y");
        ss.add("Z");

        System.out.println("First: " + ss.getFirst());
        System.out.println("Last: " + ss.getLast());

        ss = ss.reversed();
        System.out.println("Reversed set: " + ss);
    }

    public static void main(String[] args) {
        System.out.println("--- SequencedCollection ---");
        demoSequencedCollection();

        System.out.println("\n--- SequencedMap ---");
        demoSequencedMap();

        System.out.println("\n--- SequencedSet ---");
        demoSequencedSet();
    }
} 
