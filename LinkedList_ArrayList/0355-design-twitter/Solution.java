import java.util.*;

class Twitter {

    private int time;

    private Map<Integer, Set<Integer>> followMap;
    private Map<Integer, List<Tweet>> tweetMap;

    private class Tweet {
        int tweetId;
        int time;

        Tweet(int tweetId, int time) {
            this.tweetId = tweetId;
            this.time = time;
        }
    }

    public Twitter() {
        time = 0;
        followMap = new HashMap<>();
        tweetMap = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        tweetMap.putIfAbsent(userId, new ArrayList<>());
        tweetMap.get(userId).add(new Tweet(tweetId, time));
        time++;
    }

    public List<Integer> getNewsFeed(int userId) {

        PriorityQueue<Tweet> pq = new PriorityQueue<>(
            (a, b) -> b.time - a.time
        );

        // tweet cua chinh user
        if (tweetMap.containsKey(userId)) {
            pq.addAll(tweetMap.get(userId));
        }

        // tweet cua nhung nguoi user follow
        if (followMap.containsKey(userId)) {
            for (int followeeId : followMap.get(userId)) {
                if (tweetMap.containsKey(followeeId)) {
                    pq.addAll(tweetMap.get(followeeId));
                }
            }
        }

        List<Integer> feed = new ArrayList<>();

        while (!pq.isEmpty() && feed.size() < 10) {
            feed.add(pq.poll().tweetId);
        }

        return feed;
    }

    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }

        followMap.putIfAbsent(followerId, new HashSet<>());
        followMap.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (!followMap.containsKey(followerId)) {
            return;
        }

        followMap.get(followerId).remove(followeeId);
    }
}