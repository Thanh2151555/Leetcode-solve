class Skiplist {

    private static final int MAX_LEVEL = 16;
    private static final double P = 0.5;

    private Node head;
    private Random random;

    class Node {
        int val;
        Node[] next;

        Node(int val, int level) {
            this.val = val;
            this.next = new Node[level];
        }
    }

    public Skiplist() {
        head = new Node(-1, MAX_LEVEL);
        random = new Random();
    }

    public boolean search(int target) {
        Node curr = head;

        for (int level = MAX_LEVEL - 1; level >= 0; level--) {
            while (curr.next[level] != null && curr.next[level].val < target) {
                curr = curr.next[level];
            }
        }

        curr = curr.next[0];

        return curr != null && curr.val == target;
    }

    public void add(int num) {
        Node[] update = new Node[MAX_LEVEL];
        Node curr = head;

        for (int level = MAX_LEVEL - 1; level >= 0; level--) {
            while (curr.next[level] != null && curr.next[level].val < num) {
                curr = curr.next[level];
            }

            update[level] = curr;
        }

        int level = randomLevel();
        Node newNode = new Node(num, level);

        for (int i = 0; i < level; i++) {
            newNode.next[i] = update[i].next[i];
            update[i].next[i] = newNode;
        }
    }

    public boolean erase(int num) {
        Node[] update = new Node[MAX_LEVEL];
        Node curr = head;

        for (int level = MAX_LEVEL - 1; level >= 0; level--) {
            while (curr.next[level] != null && curr.next[level].val < num) {
                curr = curr.next[level];
            }

            update[level] = curr;
        }

        curr = curr.next[0];

        if (curr == null || curr.val != num) {
            return false;
        }

        for (int i = 0; i < curr.next.length; i++) {
            if (update[i].next[i] == curr) {
                update[i].next[i] = curr.next[i];
            }
        }

        return true;
    }

    private int randomLevel() {
        int level = 1;

        while (level < MAX_LEVEL && random.nextDouble() < P) {
            level++;
        }

        return level;
    }
}