package test.java21_fullcourse;


import java.lang.ScopedValue;

// 3. ScopedValue usage (Preview feature - requires JVM flag)
public class ScopedContextDemo {
    static final ScopedValue<String> USER_ID = ScopedValue.newInstance();

    public static void runWithContext(String userId) {

        ScopedValue.runWhere(USER_ID, userId, () -> {
            log("Inside scoped block");
            log("User ID is " + USER_ID.get());
        });
    }

    private static void log(String message) {
        System.out.println("[" + USER_ID.get() + "] " + message);
    }
}
