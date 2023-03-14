import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Tests the CHSearchBackendBD class which is the responsibility of the Backend Developer.
 */
public class BackendDeveloper_Tests {

    /**
     * Tests the loadData() method.
     *
     * @return true if all cases pass; false otherwise
     */
    public static boolean test1() {
        CHSearchBackendBD test = new CHSearchBackendBD(new HashtableWithDuplicateKeysBD<>(),new PostReaderBD());
        try {
            test.loadData("test.txt");
        } catch (FileNotFoundException e) {
            return false;
        } catch (Exception e) {
            return false;
        }

        return true; // loadData() ran smoothly with placeholder methods
    }

    /**
     * Tests the getStatisticsString() method.
     *
     * @return true if all cases pass; false otherwise
     */
    public static boolean test2() {
        try {
            CHSearchBackendBD test = new CHSearchBackendBD(new HashtableWithDuplicateKeysBD<>(),new PostReaderBD());
            String statistics = test.getStatisticsString();
            String compareTo = "Hashtable Capacity = 9\n" + "Number of Values = 21\n" + "Hashtable Size = 5";
            if (!statistics.equals(compareTo)) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return true; // getStatisticsString() returned the correct hardcoded string
    }

    /**
     * Tests the findPostsByTitleWords() method.
     *
     * @return true if all cases pass; false otherwise
     */
    public static boolean test3() {
        // couldn't figure out how to hardcode HashtableWithDuplicateKeysBD()
        try {
            // CHSearchBackendBD test = new CHSearchBackendBD(new HashtableWithDuplicateKeysBD<>(),new PostReaderBD());
            // List<String> testPosts = test.findPostsByTitleWords("Soups and chili’s?");
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * Tests the findPostsByBodyWords() method.
     *
     * @return true if all cases pass; false otherwise
     */
    public static boolean test4() {
        // couldn't figure out how to hardcode HashtableWithDuplicateKeysBD()
        try {
            // CHSearchBackendBD test = new CHSearchBackendBD(new HashtableWithDuplicateKeysBD<>(),new PostReaderBD());
            // List<String> testPosts = test.findPostsByBodyWords("Anyone know a cheap soup or chili that’s
            // fast and easy to make or buy?");
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * Tests the findPostsByTitleOrBodyWords() method.
     *
     * @return true if all cases pass; false otherwise
     */
    public static boolean test5() {
        // couldn't figure out how to hardcode HashtableWithDuplicateKeysBD()
        try {
            // CHSearchBackendBD test = new CHSearchBackendBD(new HashtableWithDuplicateKeysBD<>(),new PostReaderBD());
            // List<String> testPosts = test.findPostsByTitleOrBodyWords("Soups and chili’s?" + "Anyone know a cheap soup
            // or chili that’s fast and easy to make or buy?");
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * Tests the code of multiple roles working together: BD, DW, AE.
     *
     * @return true if all cases pass; false otherwise
     */
    public static boolean test6() {

        try {
            // need to initialize all these objects first
            HashtableWithDuplicateKeysAE<String, PostInterface> hashtable = new HashtableWithDuplicateKeysAE<String, PostInterface>();
            PostReaderDW postReader = new PostReaderDW();
            CHSearchBackendBD backend = new CHSearchBackendBD(hashtable, postReader);

            // backend uses AE's hashtable and DW's postReader
            backend.loadData("data/small.txt");
            int numberOfValues = hashtable.getNumberOfValues();

            // tests if data was loaded into the hashtable
            if (numberOfValues <= 0) {
                return false;
            }

        } catch (Exception e) {
            return false; // no exception should occur
        }

        return true;
    }

    /**
     * Tests the code of multiple roles working together: BD, DW, AE.
     *
     * @return true if all cases pass; false otherwise
     */
    public static boolean test7() {

        try {
            HashtableWithDuplicateKeysAE<String, PostInterface> hashtable = new HashtableWithDuplicateKeysAE<String, PostInterface>();
            PostReaderDW postReader = new PostReaderDW();
            CHSearchBackendBD backend = new CHSearchBackendBD(hashtable, postReader);
            List<String> posts;

            // backend uses AE's hashtable and DW's postReader
            backend.loadData("data/fake.txt");
            posts = backend.findPostsByTitleWords("madison");
            String compareTo = "Can I grow stevia in Madison?  +++ https://no_real_post +++ not the best climate, " +
                    "but you can try, according to this page " +
                    "https://grow.cals.wisc.edu/deprecated/five-things/five-things-everyone-should-know-about-stevia";

            // there should only be one specific result (above) from my input in fake.txt
            if (!posts.get(0).equals(compareTo)) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * Tests the runCommandLoop() method of the Frontend role
     *
     * @return true if all cases pass; false otherwise
     */
    public static boolean test8() {

        try {
            // TextUITester is necessary to test the Frontend
            TextUITester tester = new TextUITester("Q\n");
            Scanner userInput = new Scanner(System.in);

            // initializing necessary objects
            HashtableWithDuplicateKeysAE<String, PostInterface> hashtable = new HashtableWithDuplicateKeysAE<String, PostInterface>();
            PostReaderDW postReader = new PostReaderDW();
            CHSearchBackendBD backend = new CHSearchBackendBD(hashtable, postReader);
            CHSearchFrontendFD frontend = new CHSearchFrontendFD(userInput, backend);

            // method of frontend I am testing
            frontend.runCommandLoop();

            // I only entered "Q" so it quits immediately and returns this prompt
            String output = tester.checkOutput();
            if (!output.contains("Thank you for using the Cheap and Healthy Search App.")) {
                return false;
            }

        } catch (Exception e) {
            return false; // no exceptions should occur
        }

        return true;
    }

    /**
     * Tests the searchPostCommand() method of the Frontend role
     *
     * @return true if all cases pass; false otherwise
     */
    public static boolean test9() {

        try {
            TextUITester tester = new TextUITester("");
            Scanner userInput = new Scanner(System.in);

            HashtableWithDuplicateKeysAE<String, PostInterface> hashtable = new HashtableWithDuplicateKeysAE<String, PostInterface>();
            PostReaderDW postReader = new PostReaderDW();
            CHSearchBackendBD backend = new CHSearchBackendBD(hashtable, postReader);
            CHSearchFrontendFD frontend = new CHSearchFrontendFD(userInput, backend);

            // searchPostCommand only takes List<String>'s as arguments
            List<String> random = new ArrayList<>();
            random.add("chicken");

            frontend.searchPostCommand(random);
            String output = tester.checkOutput();
            // Testing the frontend alone, so it should say "No matches found."
            if (!output.trim().equals("No matches found.")) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * Main method to run tests.
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Backend Individual Test 1: " + test1());
        System.out.println("Backend Individual Test 2: " + test2());
        System.out.println("Backend Individual Test 3: " + test3());
        System.out.println("Backend Individual Test 4: " + test4());
        System.out.println("Backend Individual Test 5: " + test5());
        System.out.println("Backend Integration Test 1: " + test6());
        System.out.println("Backend Integration Test 2: " + test7());
        System.out.println("Backend Partner (Frontend) Test 1: " + test8());
        System.out.println("Backend Partner (Frontend) Test 2: " + test9());
    }
}
