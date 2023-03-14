public class PostBD implements PostInterface {

    public PostBD(String title, String url, String body) {

    }
    // hardcoded for one of the three examples
    @Override
    public String getTitle() {
        return "Soups and chili’s?";
    }

    @Override
    public String getUrl() {
        return "https://www.reddit.com/r/EatCheapAndHealthy/comments/yxb2vi/soups_and_chilis/";
    }

    @Override
    public String getBody() {
        return "Anyone know a cheap soup or chili that’s fast and easy to make or buy?";
    }
}
