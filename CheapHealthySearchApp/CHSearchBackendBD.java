import java.util.*;
import java.io.FileNotFoundException;

/**
 * The class is the responsibility of the Backend Developer (BD). They implement the Data Wrangler (DW) and
 * Algorithm Engineer's (AE) code into their own class. However, we only have placeholders as they are not finished yet.
 */
public class CHSearchBackendBD implements CHSearchBackendInterface {
    // data fields
    HashtableWithDuplicateKeysInterface<String,PostInterface> hashtable;
    PostReaderInterface postReader;

    /**
     * Constructor
     *
     * @param hashtable from AE
     * @param postReader from DW
     */
    public CHSearchBackendBD (HashtableWithDuplicateKeysInterface<String,PostInterface> hashtable, PostReaderInterface postReader) {
        this.hashtable = hashtable;
        this.postReader = postReader;
    }

    @Override
    public void loadData(String filename) throws FileNotFoundException {

        List<PostInterface> list = postReader.readPostsFromFile(filename);
        String[] splitTitle;
        String[] splitBody;

        for (int i = 0; i < list.size(); i++) {
            String title = list.get(i).getTitle().replaceAll("\\p{P}", "").toLowerCase();
            splitTitle = title.split(" ");
            String body = list.get(i).getBody().replaceAll("\\p{P}", "").toLowerCase();
            splitBody = title.split(" ");
            for (int j = 0; j < splitTitle.length; j++) {
                splitTitle[j] = "TITLE:" + splitTitle[j];
                hashtable.putOne(splitTitle[j], list.get(i));
            }
            for (int j = 0; j < splitBody.length; j++) {
                splitBody[j] = "BODY:" + splitBody[j];
                hashtable.putOne(splitBody[j], list.get(i));
            }
        }
    }

    @Override
    public List<String> findPostsByTitleWords(String words) {
        List<String> postStrings = new ArrayList<>();
        List<PostInterface> posts = new ArrayList<>();
        List<PostInterface> allPosts = new ArrayList<>();
        words = words.replaceAll("\\p{P}", "").toLowerCase();
        String[] splitWords = words.split(" ");
        for (int i = 0; i < splitWords.length; i++) {
            try {
                splitWords[i] = "TITLE:" + splitWords[i];
                posts = hashtable.get(splitWords[i]);
                allPosts.addAll(posts);
            } catch (NoSuchElementException e) {

            }
        }

        return getStringListHelper(postStrings, allPosts);
    }

    private List<String> getStringListHelper(List<String> postStrings, List<PostInterface> allPosts) {
        Set<PostInterface> set = new HashSet<>(allPosts); // removes duplicate posts
        allPosts.clear();
        allPosts.addAll(set);

        for (int i = 0; i < allPosts.size(); i++) {
            String title = allPosts.get(i).getTitle();
            String url = allPosts.get(i).getUrl();
            String body = allPosts.get(i).getBody();
            String postString = title + "+++" + url + "+++" + body;
            postStrings.add(postString);
        }

        return postStrings;
    }

    @Override
    public List<String> findPostsByBodyWords(String words) {
        List<String> postStrings = new ArrayList<>();
        List<PostInterface> posts = new ArrayList<>();
        List<PostInterface> allPosts = new ArrayList<>();
        words = words.replaceAll("\\p{P}", "").toLowerCase();
        String[] splitWords = words.split(" ");
        for (int i = 0; i < splitWords.length; i++) {
            try {
                splitWords[i] = "BODY:" + splitWords[i];
                posts = hashtable.get(splitWords[i]);
                allPosts.addAll(posts);
            } catch (NoSuchElementException e) {

            }
        }

        return getStringListHelper(postStrings, allPosts);
    }

    @Override
    public List<String> findPostsByTitleOrBodyWords(String words) {
        List<String> allPosts = new ArrayList<>();
        List<String> titlePosts = findPostsByTitleWords(words);
        List<String> bodyPosts = findPostsByBodyWords(words);
        allPosts.addAll(titlePosts);
        allPosts.addAll(bodyPosts);
        Set<String> set = new HashSet<>(allPosts); // removes duplicate posts
        allPosts.clear();
        allPosts.addAll(set);

        return allPosts;
    }

    @Override
    public String getStatisticsString() {
        int hashtableCapacity = hashtable.getCapacity();
        int numberOfValues = hashtable.getNumberOfValues();
        int size = hashtable.getSize();
        String statistics = "Hashtable Capacity = " + hashtableCapacity + "\n" + "Number of Values = " + numberOfValues
                + "\n" + "Hashtable Size = " + size;

        return statistics;
    }
}
