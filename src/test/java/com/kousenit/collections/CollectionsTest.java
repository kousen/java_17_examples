package com.kousenit.collections;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CollectionsTest {
    @Test
    void can_not_add_nulls() {
        assertThrows(NullPointerException.class, () -> List.of("a", "b", null));
    }

    @Test
    void sort_a_list() {
        List<String> list = List.of("this", "is", "a", "list");
        List<String> finalList = list;
        assertThrows(UnsupportedOperationException.class, () -> Collections.sort(finalList));

        // Can sort using streams
        List<String> sorted = list.stream()
                .sorted(Comparator.comparing(s -> s.toUpperCase().length()))
                .collect(Collectors.toList());
        System.out.println("Sorted: " + sorted);
        System.out.println("Original: " + list);
        list = sorted;
        System.out.println("Change list reference: " + list);
    }

    @Test
    void create_a_map() {
        var map = Map.of("a", 1, "b", 2, "c", 3);
        System.out.println(map);
        System.out.println(map.getClass().getName());
        var mapFromEntries = ofEntries(
                entry("d", 5),
                entry("e", 6)
        );
        System.out.println(mapFromEntries);

        assertAll(
                () -> assertThrows(Exception.class, () -> map.put("g", 8)),
                () -> assertThrows(Exception.class, () -> map.put("a", 1))
        );
    }
}
