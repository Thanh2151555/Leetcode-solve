class AuthenticationManager {

    private int timeToLive;
    private Map<String, Integer> tokenExpireTime;

    public AuthenticationManager(int timeToLive) {
        this.timeToLive = timeToLive;
        this.tokenExpireTime = new HashMap<>();
    }

    public void generate(String tokenId, int currentTime) {
        tokenExpireTime.put(tokenId, currentTime + timeToLive);
    }

    public void renew(String tokenId, int currentTime) {
        if (!tokenExpireTime.containsKey(tokenId)) {
            return;
        }

        int expireTime = tokenExpireTime.get(tokenId);

        if (expireTime <= currentTime) {
            return;
        }

        tokenExpireTime.put(tokenId, currentTime + timeToLive);
    }

    public int countUnexpiredTokens(int currentTime) {
        int count = 0;

        for (int expireTime : tokenExpireTime.values()) {
            if (expireTime > currentTime) {
                count++;
            }
        }

        return count;
    }
}