public class Utils {

    // Cleaned up but same behavior
    public static boolean checkName(String name) {
        return name != null && !name.isEmpty();
    }

    // Reverted bug
    public static boolean isValidAge(int age) {
        return age >= 0 && age <= 120;
    }
}
