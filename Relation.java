import java.util.*;

public class Relation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt for set input
        // Ask the user to enter a set of elements separated by commas
        System.out.print("Enter a set (comma-separated, e.g., 1,2,3,4): ");
        String setStr = scanner.nextLine();
        Set<String> set = parseSet(setStr); // Parse the set input
        
        // Prompt for ordered pairs input
        // Ask the user to enter ordered pairs separated by spaces
        System.out.print("Enter ordered pairs (space-separated, e.g., 1 1  1 2  1 3): ");
        String pairsStr = scanner.nextLine();
        List<Pair> pairs = parsePairs(pairsStr); // Parse the ordered pairs input
        
        // Identify properties
        // Identify the properties of the relation based on the set and ordered pairs
        List<String> properties = identifyProperties(set, pairs);
        
        // Output identified properties
        // Display the identified properties of the relation
        System.out.println("Properties identified are:");
        for (String property : properties) {
            System.out.println(property);
        }
        
        scanner.close();
    }
    
    // Parse set input
    // Parse the input string representing the set into a Set data structure
    private static Set<String> parseSet(String setStr) {
        Set<String> set = new HashSet<>(Arrays.asList(setStr.split(" ")));
        return set;
    }

    // Parse ordered pairs input
    // Parse the input string representing the ordered pairs into a List of Pair objects
    private static List<Pair> parsePairs(String pairsStr) {
        List<Pair> pairs = new ArrayList<>();
        pairsStr = pairsStr.replaceAll("\\s+", " "); // Replace multiple spaces with single space
        String[] pairsArr = pairsStr.split("  "); // Split pairs by '  '
        for (String pairStr : pairsArr) {
            String[] pair = pairStr.split(" "); // Split each pair by ' '
            pairs.add(new Pair(pair[0], pair[1]));
        }
        return pairs;
    }
    
    // Identify properties
    // Identify the properties of the relation: Reflexive, Symmetric, Transitive
    private static List<String> identifyProperties(Set<String> set, List<Pair> pairs) {
        List<String> properties = new ArrayList<>();
        boolean reflexive = true;
        boolean symmetric = true;
        boolean transitive = true;
        
        // Check reflexive
        // Check if every element in the set is related to itself
        for (String element : set) {
            boolean found = false;
            for (Pair pair : pairs) {
                if (pair.getFirst().equals(element) && pair.getSecond().equals(element)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                reflexive = false;
                break;
            }
        }
        if (reflexive) {
            properties.add("Reflexive");
        }
        
        // Check symmetric
        // Check if every pair has its reverse pair in the set of ordered pairs
        for (Pair pair : pairs) {
            Pair reversePair = new Pair(pair.getSecond(), pair.getFirst());
            if (!pairs.contains(reversePair)) {
                symmetric = false;
                break;
            }
        }
        if (symmetric) {
            properties.add("Symmetric");
        }
        
        // Check transitive
        // Check if the relation is transitive
        for (Pair pair1 : pairs) {
            for (Pair pair2 : pairs) {
                if (pair1.getSecond().equals(pair2.getFirst())) {
                    Pair transitivePair = new Pair(pair1.getFirst(), pair2.getSecond());
                    if (!pairs.contains(transitivePair)) {
                        transitive = false;
                        break;
                    }
                }
            }
            if (!transitive) {
                break;
            }
        }
        if (transitive) {
            properties.add("Transitive");
        }
        
        return properties;
    }

}

// Helper class for pairs
// Represents a pair of elements in the relation
class Pair {
    private String first;
    private String second;
    
    public Pair(String first, String second) {
        this.first = first;
        this.second = second;
    }
    
    public String getFirst() {
        return first;
    }
    
    public String getSecond() {
        return second;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pair pair = (Pair) obj;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
