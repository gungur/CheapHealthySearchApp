import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PostReaderBD implements PostReaderInterface {

    public PostReaderBD() {}

    @Override
    public List<PostInterface> readPostsFromFile(String filename) throws FileNotFoundException {
        List<PostInterface> testPosts = new ArrayList<>(3);
        if (filename.equals("test.txt")) {
            // hardcoded 3 example posts
            PostInterface post1 = new PostBD("I have eggs. I need recipes.",
                    "https://www.reddit.com/r/EatCheapAndHealthy/comments/113b2ps/i_have_eggs_i_need_recipes/",
                    "My hens are laying and I'm getting plenty of eggs. Besides scrambled, fried, and hard boiled," +
                            " how can I prepare my abundance of eggs? Thank you.");
            PostInterface post2 = new PostBD("Warm drink that doesn't have caffeine?",
                    "https://www.reddit.com/r/EatCheapAndHealthy/comments/112l4fr/warm_drink_that_dosent_have_caffeine/",
                    "hi guys, so basically i am looking for some warm/hot drinks that i can drink at night that dont have caffeine. " +
                            "i am not really a fan of fruit teas (such as lemon, orange, berry, etc...) my current goto is " +
                            "hot chocolate but i always feel a bit bad due to the high sugar. i would appreciate your suggestions.");
            PostInterface post3 = new PostBD("Soups and chili’s?",
                    "https://www.reddit.com/r/EatCheapAndHealthy/comments/yxb2vi/soups_and_chilis/",
                    "Anyone know a cheap soup or chili that’s fast and easy to make or buy?");

            testPosts.add(post1);
            testPosts.add(post2);
            testPosts.add(post3);

            return testPosts;
        } else {
            throw new FileNotFoundException();
        }
    }
}
