import static de.thl.jedunit.DSL.*;
import de.thl.jedunit.*;

/**
 * Please add your test cases for evaluation here.
 * - Please provide meaningful remarks for your students in grading() calls.
 * - All test weights should sum up to 1.0.
 * - If the weights sum up to more than 1.0, points it will be truncated. 
 * - This enable bonus rules to tolerate some errors. 
 * - First, all methods with an @Inspection annotation will be executed (in alphabetical order)
 * - Then, all methods with a @Test annotation will be executed automatically (in alphabetical order).
 * - If this sounds similar to JUnit - this is intended ;-)
 */
public class Checks extends Constraints {

    @Override
    public void configure() {
        super.configure();

        // Config.CHECKSTYLE = false;                  // default: true
        // Config.CHECKSTYLE_PENALITY = 5;
        // Config.CHECKSTYLE_IGNORES.add("[NeedBraces]")
        // Config.CHECKSTYLE_IGNORES.remove("[MagicNumber]");

        // Config.ALLOWED_IMPORTS = Arrays.asList("java.util");

        // Config.ALLOW_LOOPS = false;                 // default: true
        // Config.LOOP_PENALTY = 100;

        // Config.ALLOW_METHODS = false;               // default: true
        // Config.METHOD_PENALTY = 100;
    
        // Config.ALLOW_LAMBDAS = false;               // default: true
        // Config.LAMBDA_PENALITY = 25;
    
        // Config.ALLOW_INNER_CLASSES = true;          // default: false
        // Config.INNER_CLASS_PENALTY = 25;
    
        // Config.ALLOW_GLOBAL_VARIABLES = true;       // default: false
        // Config.GLOBAL_VARIABLE_PENALTY = 25;
    
        // Config.CHECK_COLLECTION_INTERFACES = false; // default: true
        // Config,COLLECTION_INTERFACE_PENALTY = 25;
    
        // Config.ALLOW_CONSOLE_OUTPUT = true;         // default: false
        // Config.CONSOLE_OUTPUT_PENALTY = 25;
    }

    @Test(weight=0.25, description="Provided example calls")
    public void examples() {
        testWith(
            t('o', "Hello World", 5),
            t('w', "Hello World", 5)
        ).forEach(d -> {
            int expected = Solution.countChars(d._1, d._2);
            grading(d._3, String.format("Counting '%s' in \"%s\" must return %d.", d._1, d._2, expected), 
                () -> Main.countChars(d._1, d._2) == expected
            );            
        });
    }

    @Test(weight=0.25, description="Boundary testcases (unknown test cases)")
    public void furtherTestcases() {
        testWith(
            t('x', "", 10),
            t('X', "", 10),
            t('x', "x", 5),
            t('X', "x", 5),
            t('x', "X", 5),
            t('X', "X", 5),
            t('X', "ax", 5),
            t('x', "Xa", 5)
        ).forEach(d -> {
            int expected = Solution.countChars(d._1, d._2);
            String msg = String.format("countChars('%s', \"%s\") -> %d", d._1, d._2, expected);
            grading(d._3, msg, () -> Main.countChars(d._1, d._2) == expected);
        });
    }

    @Test(weight=0.5, description="Randomized testcases")
    public void randomizedTestcases() {
        String r = "[a-zA-Z!?&%$§]{5,17}";
        char c = c("[a-z]");
        char C = Character.toUpperCase(c);

        testWith(
            t(c, s(c + "{1,7}", r, r), 5),
            t(C, s(c + "{1,7}", r, r), 5),
            t(c, s(r.toUpperCase(), r, C + "{1,7}"), 5),
            t(C, s(r.toUpperCase(), r, C + "{1,7}"), 5),
            t(c, s(r, C + "{1,7}", r.toLowerCase()), 5),
            t(C, s(r, C + "{1,7}", r.toLowerCase()), 5),
            t(c, s(r, c + "{1,7}", r.toUpperCase()), 5),
            t(C, s(r, c + "{1,7}", r.toUpperCase()), 5)
        ).forEach(d -> {
            int expected = Solution.countChars(d._1, d._2);
            String msg = String.format("countChars('%s', \"%s\") -> %d", d._1, d._2, expected);
            grading(d._3, msg, () -> Main.countChars(d._1, d._2) == expected);
        });
    }
}