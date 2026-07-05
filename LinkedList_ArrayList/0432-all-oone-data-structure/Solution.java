class AllOne {
    private class Bucket {
        int count;
        Set<String> keys = new HashSet<>();
        Bucket prev, next;

        Bucket(int count) {
            this.count = count;
        }
    }

    private Map<String, Integer> keyCount;
    private Map<Integer, Bucket> countBucket;
    private Bucket head, tail;

    public AllOne() {
        keyCount = new HashMap<>();
        countBucket = new HashMap<>();

        head = new Bucket(0);
        tail = new Bucket(0);
        head.next = tail;
        tail.prev = head;
    }

    public void inc(String key) {
        int oldCount = keyCount.getOrDefault(key, 0);
        int newCount = oldCount + 1;

        keyCount.put(key, newCount);

        Bucket oldBucket = countBucket.get(oldCount);
        Bucket newBucket = countBucket.get(newCount);

        if (newBucket == null) {
            newBucket = new Bucket(newCount);
            countBucket.put(newCount, newBucket);

            if (oldBucket == null) {
                insertAfter(head, newBucket);
            } else {
                insertAfter(oldBucket, newBucket);
            }
        }

        newBucket.keys.add(key);

        if (oldBucket != null) {
            oldBucket.keys.remove(key);

            if (oldBucket.keys.isEmpty()) {
                removeBucket(oldBucket);
                countBucket.remove(oldCount);
            }
        }
    }

    public void dec(String key) {
        int oldCount = keyCount.get(key);
        int newCount = oldCount - 1;

        Bucket oldBucket = countBucket.get(oldCount);
        oldBucket.keys.remove(key);

        if (newCount == 0) {
            keyCount.remove(key);
        } else {
            keyCount.put(key, newCount);

            Bucket newBucket = countBucket.get(newCount);

            if (newBucket == null) {
                newBucket = new Bucket(newCount);
                countBucket.put(newCount, newBucket);
                insertBefore(oldBucket, newBucket);
            }

            newBucket.keys.add(key);
        }

        if (oldBucket.keys.isEmpty()) {
            removeBucket(oldBucket);
            countBucket.remove(oldCount);
        }
    }

    public String getMaxKey() {
        if (tail.prev == head) return "";
        return tail.prev.keys.iterator().next();
    }

    public String getMinKey() {
        if (head.next == tail) return "";
        return head.next.keys.iterator().next();
    }

    private void insertAfter(Bucket prevBucket, Bucket newBucket) {
        Bucket nextBucket = prevBucket.next;

        prevBucket.next = newBucket;
        newBucket.prev = prevBucket;

        newBucket.next = nextBucket;
        nextBucket.prev = newBucket;
    }

    private void insertBefore(Bucket nextBucket, Bucket newBucket) {
        Bucket prevBucket = nextBucket.prev;

        prevBucket.next = newBucket;
        newBucket.prev = prevBucket;

        newBucket.next = nextBucket;
        nextBucket.prev = newBucket;
    }

    private void removeBucket(Bucket bucket) {
        bucket.prev.next = bucket.next;
        bucket.next.prev = bucket.prev;
    }
}