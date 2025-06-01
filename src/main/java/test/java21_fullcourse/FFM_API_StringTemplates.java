package test.java21_fullcourse;

import java.lang.foreign.Arena;
import java.lang.foreign.Linker;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.lang.foreign.FunctionDescriptor;

// String Templates (JEP 430)


public class FFM_API_StringTemplates {

    // JEP 442 - Call native strlen() using FFM API
    public static void callNativeStrlen() throws Throwable {
        ///Step 1.This is like saying: “Hey JVM, give me the bridge to the C world.”
        Linker linker = Linker.nativeLinker();
        /// Step 2. This is the default symbol lookup in the standard system libraries (libc.so, msvcrt.dll, etc.).
        /// You’ll use it to find the memory address of the strlen function.
        SymbolLookup stdlib = linker.defaultLookup();
///  Step 3: Resolve strlen and build a MethodHandle

        /// downcallHandle(...): Constructs a Java MethodHandle that wraps the native strlen.
        MethodHandle strlen = linker.downcallHandle(
                /// stdlib.find("strlen"): Looks up the native symbol for the strlen function.
                 /// .orElseThrow(): Throws if strlen is not found (fail-fast).
            stdlib.find("strlen").orElseThrow(),

            /// Defines the native function’s signature:
            /// Return type: JAVA_LONG = long
            /// Argument type: ADDRESS = memory address (pointer to char* in C)
            FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
        );

        /// 🧩 Step 4: Create a native memory segment with a UTF-8 string

        try (Arena arena = Arena.ofConfined()) {
            MemorySegment cStr = arena.allocateUtf8String("Hello, JNI replacement");

            /// Calls the strlen function, passing the pointer to the UTF-8 string.
            /// invokeExact() is used for full type safety and performance. You must match the signature exactly.
            /// The result is returned as a long, which is cast accordingly.
            long len = (long) strlen.invokeExact(cStr);
            System.out.println("Native strlen returned: " + len);
        }
    }

    // JEP 430 - Safer string interpolation for SQL
    public static void sqlTemplateExample() {
        String table = "users";
        String condition = "active = 1";
        String sql = STR."SELECT * FROM \{table} WHERE \{condition}";
        System.out.println("Generated SQL: " + sql);
    }

    // JEP 430 - Logging with template
    public static void logWithTemplate(String userId, String action) {
        String log = STR."User \{userId} performed \{action}";
        System.out.println("LOG: " + log);
    }

    public static void main(String[] args) throws Throwable {
        System.out.println("--- JEP 442: Foreign Function Demo ---");
        callNativeStrlen();

        System.out.println("\n--- JEP 430: SQL Template Demo ---");
        sqlTemplateExample();

        System.out.println("\n--- JEP 430: Log Template Demo ---");
        logWithTemplate("nathan", "login");
    }
}
