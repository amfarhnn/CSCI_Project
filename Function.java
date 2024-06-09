import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Function {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompting for input and output variables
        System.out.print("Enter input, X: ");
        // Read input variables as space-separated strings
        String[] inputVariables = scanner.nextLine().split("\\s+");

        System.out.print("Enter output, Y: ");
        // Read output variables as space-separated strings
        String[] outputVariables = scanner.nextLine().split("\\s+");

        // Prompting for ordered pairs
        System.out.print("Enter ordered pairs: ");
        // Read ordered pairs as space-separated strings
        String[] orderedPairs = scanner.nextLine().split("\\s+");

        // Mapping input and output variables to pairs
        HashMap<String, String> pairs = new HashMap<>();
        // Associate each input variable with its corresponding output variable
        for (int i = 0; i < inputVariables.length; i++) {
            pairs.put(inputVariables[i], outputVariables[i]);
        }

        // Checking for properties
        boolean isEverywhereDefined = true;
        // Keep track of mapped outputs to check for injectivity
        Set<String> mappedOutputs = new HashSet<>();
        // Keep track of mapped inputs to check for surjectivity
        Set<String> mappedInputs = new HashSet<>();

        // Iterate through ordered pairs
        for (String pair : orderedPairs) {
            // Extract input and output from each pair
            String input = pair.substring(0, 1);
            String output = pair.substring(1);

            // Check Everywhere Defined
            // If there's an input not present in the function definition, it's not Everywhere Defined
            if (!pairs.containsKey(input)) {
                isEverywhereDefined = false;
            }

            // Check Injective
            // If an output is mapped to more than one input, it's not Injective
            if (mappedOutputs.contains(output)) {
                System.out.println("Injective");
            }
            mappedOutputs.add(output);

            // Check Surjective
            // If an output is mapped to at least one input, mark the input as mapped
            if (pairs.containsValue(output)) {
                mappedInputs.add(input);
            }

            // Check Bijective
            // If each output is mapped to exactly one input, and each input is mapped to exactly one output,
            // the function is Bijective
            if (mappedOutputs.size() == outputVariables.length && mappedInputs.size() == inputVariables.length) {
                System.out.println("Bijective");
            }
        }

        // Check Invertible
        // If Everywhere Defined and each output is mapped to exactly one input, the function is Invertible
        if (isEverywhereDefined && mappedOutputs.size() == outputVariables.length) {
            System.out.println("Invertible");
        }

        // Check Surjective
        // If each output has at least one input mapped to it, the function is Surjective
        if (mappedInputs.size() == inputVariables.length) {
            System.out.println("Surjective");
        }

        // Check Everywhere Defined
        // If all inputs are mapped in the function definition, the function is Everywhere Defined
        if (isEverywhereDefined) {
            System.out.println("Everywhere Defined");
        }

        scanner.close();
    }
}
