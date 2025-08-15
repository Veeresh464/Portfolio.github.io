public class JohnsonTrotter {

    private static final boolean LEFT_TO_RIGHT = true;
    private static final boolean RIGHT_TO_LEFT = false;

    // Helper to find the position of a specific number in the array
    private static int findPosition(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) return i;
        }
        return -1;
    }

    // Find the largest mobile number
    private static int getLargestMobile(int[] arr, boolean[] direction) {
        int mobile = 0;

        for (int i = 0; i < arr.length; i++) {
            int current = arr[i];

            if (direction[current - 1] == RIGHT_TO_LEFT && i > 0 && current > arr[i - 1]) {
                if (current > mobile) mobile = current;
            }

            if (direction[current - 1] == LEFT_TO_RIGHT && i < arr.length - 1 && current > arr[i + 1]) {
                if (current > mobile) mobile = current;
            }
        }

        return mobile;
    }

    // Perform one step of the Johnson-Trotter algorithm
    private static void generateNextPermutation(int[] arr, boolean[] direction) {
        int mobile = getLargestMobile(arr, direction);
        int pos = findPosition(arr, mobile);

        // Swap based on direction
        if (direction[mobile - 1] == RIGHT_TO_LEFT) {
            int temp = arr[pos];
            arr[pos] = arr[pos - 1];
            arr[pos - 1] = temp;
        } else {
            int temp = arr[pos];
            arr[pos] = arr[pos + 1];
            arr[pos + 1] = temp;
        }

        // Flip direction of all elements greater than the current mobile
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > mobile) {
                direction[arr[i] - 1] = !direction[arr[i] - 1];
            }
        }

        // Print the current permutation
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Factorial helper to calculate total permutations
    private static int factorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }

    // Main method to generate all permutations using Johnson-Trotter
    public static void generateAllPermutations(int n) {
        int[] numbers = new int[n];
        boolean[] directions = new boolean[n]; // All start with RIGHT_TO_LEFT

        for (int i = 0; i < n; i++) {
            numbers[i] = i + 1;
            directions[i] = RIGHT_TO_LEFT;
        }

        // Print the first permutation
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Generate remaining permutations
        for (int i = 1; i < factorial(n); i++) {
            generateNextPermutation(numbers, directions);
        }
    }

    public static void main(String[] args) {
        int n = 4;
        generateAllPermutations(n);
    }
}
