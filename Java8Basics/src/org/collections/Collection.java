package org.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jamithi.City;
import org.jamithi.Person;

public class Collection {
	public static void main(String[] args) {
		Person p1 = new Person("Sandeep", "Jamithi", 28);
		Person p2 = new Person("Hemanth", "Jamithi", 31);
		Person p3 = new Person("RamaDevi", "Jamithi", 52);
		Person p4 = new Person("Ranga Rao", "Jamithi", 55);
		Person p5 = new Person("Divya", "Jamithi", 31);
		Person p6 = new Person("Shrika", "Jamithi", 1);
		
		List<Person> people = new ArrayList(Arrays.asList(p1, p2, p3, p4, p5, p6));
		
		//First method of displying
		people.forEach(person -> System.out.println(person));
		System.out.println();
		System.out.println("Another method of displaying");
		System.out.println();
		//Second method
		people.forEach(System.out::println);
		
		people.removeIf(person -> person.getAge() < 30);
		System.out.println();
		System.out.println("Remove if example");
		System.out.println();
		people.forEach(System.out::println);
		
		people.replaceAll(person -> new Person(person.getFirstName().toUpperCase(), person.getLastName().toLowerCase(), person.getAge()));
		System.out.println();
		System.out.println("Replace all example");
		System.out.println();
		people.forEach(System.out::println);
		
		people.sort(Comparator.comparing(Person::getAge).reversed());
		System.out.println();
		System.out.println("Sort example");
		System.out.println();
		people.forEach(System.out::println);
		
		City chicago = new City("Chicago");
		City tempe = new City("Tempe");
		City houston = new City("Houston");
		
		Map<City, List<Person>> map = new HashMap<>();
		System.out.println();
		System.out.println("map get default example");
		System.out.println("people from tempe: " + map.getOrDefault(tempe, Collections.EMPTY_LIST));
		
		map.putIfAbsent(tempe, new ArrayList<Person>());
		map.get(tempe).add(p1);
		System.out.println();
		System.out.println("map put if absent example");
		System.out.println("people from tempe: " + map.getOrDefault(tempe, Collections.EMPTY_LIST));
		
		map.computeIfAbsent(chicago, city -> new ArrayList<>()).add(p2);
		map.computeIfAbsent(chicago, city -> new ArrayList<>()).add(p6);
		System.out.println();
		System.out.println("map compute if absent example");
		System.out.println("people from chicago: " + map.getOrDefault(chicago, Collections.EMPTY_LIST));
		
		//Example of merge
		Map<City, List<Person>> map1 = new HashMap<>();
		Map<City, List<Person>> map2 = new HashMap<>();
		
		map1.computeIfAbsent(houston, city -> new ArrayList<>()).add(p2);
		map1.computeIfAbsent(houston, city -> new ArrayList<>()).add(p5);
		map1.computeIfAbsent(houston, city -> new ArrayList<>()).add(p6);
		
		System.out.println(map1);
		
		map2.computeIfAbsent(chicago, person -> new ArrayList<>()).add(p1);
		map2.computeIfAbsent(chicago, person -> new ArrayList<>()).add(p3);
		map2.computeIfAbsent(chicago, person -> new ArrayList<>()).add(p4);
		
		System.out.println(map2);
		
		System.out.println();
		System.out.println("Merge map example");
		
		map2.forEach(
				(city, people1) -> {
					map1.merge(city, people1, 
							(peopleFromMap1, peopleFromMap2) -> {
								peopleFromMap1.addAll(peopleFromMap2);
								return peopleFromMap1;
							});
				}
				);
		
		map1.forEach(
				(city, people2) -> System.out.println(city + ": "+ people2)
				);
		
	}
}
