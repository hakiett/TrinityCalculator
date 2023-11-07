package edu.trinity.got;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryMemberDAO implements MemberDAO {
    private final Collection<Member> allMembers =
            MemberDB.getInstance().getAllMembers();

    @Override
    public Optional<Member> findById(Long id) {
        return allMembers.stream()
                .filter(member -> member.id().equals(id))
                .findFirst(); // terminal
        // Pass
    }

    @Override
    public Optional<Member> findByName(String name) {
        return allMembers.stream()
                .filter(member -> member.name().equals(name))
                .findFirst();
        // Pass
    }

    @Override
    public List<Member> findAllByHouse(House house) {
        return allMembers.stream()
                .filter(member -> member.house().equals(house))
                .collect(Collectors.toList());
        // Pass
    }

    @Override
    public Collection<Member> getAll() {
        return allMembers;
        // Pass
    }

    /**
     * Find all members whose name starts with S and sort by id (natural sort)
     */
    @Override
    public List<Member> startWithSandSortAlphabetically() {
        return allMembers.stream()
                .filter(member -> member.name().startsWith("S"))
                .sorted() // natural sort
                .collect(Collectors.toList());
        // Pass
    }

    /**
     * Final all Lannisters and sort them by name
     */
    @Override
    public List<Member> lannisters_alphabeticallyByName() {
        return allMembers.stream()
                .filter(member -> member.house().equals(House.LANNISTER)) // enum constant
                .sorted(Comparator.comparing(Member::name))
                .collect(Collectors.toList());
        // Pass
    }

    /**
     * Find all members whose salary is less than the given value and sort by house
     */
    @Override
    public List<Member> salaryLessThanAndSortByHouse(double max) {
        return allMembers.stream()
                .filter(member -> member.salary() < max)
                .sorted(Comparator.comparing(Member::house))
                .collect(Collectors.toList());
        // Pass
    }

    /**
     * Sort members by House, then by name
     */
    @Override
    public List<Member> sortByHouseNameThenSortByNameDesc() {
        return allMembers.stream()
                .sorted(Comparator.comparing(Member::house)
                        // Slide 27
                        // https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html#thenComparing-java.util.function.Function-
                        .thenComparing(Member::name).reversed()) //.reversed() because descending name order
                .collect(Collectors.toList());
        // Usually I would make custom comparators but this is a good example of chaining
        // Pass
    }

    /**
     * Sort the members of a given House by birthdate
     */
    @Override
    public List<Member> houseByDob(House house) {
        return allMembers.stream()
                .filter(member -> member.house().equals(house))
                .sorted(Comparator.comparing(Member::dob))
                .collect(Collectors.toList());
        // Pass
    }

    /**
     * Find all Kings and sort by name in descending order
     */
    @Override
    public List<Member> kingsByNameDesc() {
        return allMembers.stream()
                .filter(member -> member.title().equals(Title.KING))
                .sorted(Comparator.comparing(Member::name).reversed())
                .collect(Collectors.toList());
        // Pass
    }

    /**
     * Find the average salary of all the members
     */
    @Override
    public double averageSalary() {
        return allMembers.stream()
                .mapToDouble(Member::salary)
                .average().getAsDouble();
        // average() returns OptionalDouble
        //  So we call getAsDouble() to get the double value
        // Pass
    }

    /**
     * Get the names of a given house, sorted in natural order
     * (note sort by _names_, not members)
     */
    @Override
    public List<String> namesSorted(House house) {
        return allMembers.stream()
                .filter(member -> member.house().equals(house))
                .map(Member::name) // member object stream to name stream
                .sorted().toList();
        // Pass
    }

    /**
     * Are any of the salaries greater than 100K?
     */
    @Override
    public boolean salariesGreaterThan(double max) {
        return allMembers.stream().anyMatch(member -> member.salary() > max); // are "any" members greater than max? (terminal)
        // Pass
    }

    /**
     * Are there any members of given house?
     */
    @Override
    public boolean anyMembers(House house) {
        return allMembers.stream().anyMatch(member -> member.house().equals(house));
        // Pass greyjoys() test
    }

    /**
     * How many members of a given house are there?
     */
    @Override
    public long howMany(House house) {
        return 0;
    }

    /**
     * Return the names of a given house as a comma-separated string
     */
    @Override
    public String houseMemberNames(House house) {
        return "";
    }

    /**
     * Who has the highest salary?
     */
    @Override
    public Optional<Member> highestSalary() {
        return Optional.empty();
    }

    /**
     * Partition members into royalty and non-royalty
     * (note: royalty are KINGs and QUEENs only)
     */
    @Override
    public Map<Boolean, List<Member>> royaltyPartition() {
        return Collections.emptyMap();
    }

    /**
     * Group members into Houses
     */
    @Override
    public Map<House, List<Member>> membersByHouse() {
        return Collections.emptyMap();
    }

    /**
     * How many members are in each house?
     * (group by house, downstream collector using counting
     */
    @Override
    public Map<House, Long> numberOfMembersByHouse() {
        return Collections.emptyMap();
    }

    /**
     * Get the max, min, and ave salary for each house
     */
    @Override
    public Map<House, DoubleSummaryStatistics> houseStats() {
        return Collections.emptyMap();
    }

}
