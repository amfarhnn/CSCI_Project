import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Modular {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter integers separated by spaces: ");
        String input = scanner.nextLine();
        scanner.close();

        // Split the input string by spaces and convert each part to an integer
        String[] integers = input.split("\\s+");
        List<Integer> nums = new ArrayList<>();
        for (String str : integers) {
            nums.add(Integer.parseInt(str));
        }

        // Print header for the congruent modulo pairs
        System.out.println("Congruent modulo pairs:");
        // Call method to print the congruences
        printCongruences(nums);
    }

    private static void printCongruences(List<Integer> nums) {
        // List to store the congruences
        List<String> congruences = new ArrayList<>();
        // Calculate congruences for pairs of numbers
        for (int i = 0; i < nums.size() - 1; i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                // Calculate the modulus (absolute difference) between two numbers
                int modulus = Math.abs(nums.get(i) - nums.get(j));
                // Exclude congruences with modulus 1 (as they are trivial)
                if (modulus != 1) {
                    // Add the congruence to the list
                    congruences.add(nums.get(i) + " " + nums.get(j) + " are congruent modulo " + modulus);
                }
            }
        }
        
        // Calculate congruences for triplets of numbers
        for (int i = 0; i < nums.size() - 2; i++) {
            for (int j = i + 1; j < nums.size() - 1; j++) {
                for (int k = j + 1; k < nums.size(); k++) {
                    // Calculate the moduli (absolute differences) between three numbers
                    int modulus1 = Math.abs(nums.get(i) - nums.get(j));
                    int modulus2 = Math.abs(nums.get(i) - nums.get(k));
                    int modulus3 = Math.abs(nums.get(j) - nums.get(k));
                    // Determine the maximum modulus among the three
                    int maxModulus = Math.max(modulus1, Math.max(modulus2, modulus3));
                    // Exclude congruences with modulus 1 (as they are trivial)
                    if (maxModulus != 1) {
                        // Add the congruence to the list
                        congruences.add(nums.get(i) + " " + nums.get(j) + " " + nums.get(k) + 
                                       " are congruent modulo " + maxModulus);
                    }
                }
            }
        }
        
        // Sort the congruences based on the modulus
        Collections.sort(congruences, (c1, c2) -> {
            int mod1 = Integer.parseInt(c1.substring(c1.lastIndexOf(" ") + 1));
            int mod2 = Integer.parseInt(c2.substring(c2.lastIndexOf(" ") + 1));
            return mod1 - mod2;
        });
        
        // Print the sorted congruences
        for (String congruence : congruences) {
            System.out.println(congruence);
        }
    }
}
