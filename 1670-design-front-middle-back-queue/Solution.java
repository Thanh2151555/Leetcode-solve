class FrontMiddleBackQueue {

    private Deque<Integer> left;
    private Deque<Integer> right;

    public FrontMiddleBackQueue() {
        left = new ArrayDeque<>();
        right = new ArrayDeque<>();
    }

    public void pushFront(int val) {
        left.addFirst(val);
        balance();
    }

    public void pushMiddle(int val) {
        if (left.size() > right.size()) {
            right.addFirst(left.removeLast());
        }

        left.addLast(val);
        balance();
    }

    public void pushBack(int val) {
        right.addLast(val);
        balance();
    }

    public int popFront() {
        if (isEmpty()) return -1;

        int val;

        if (!left.isEmpty()) {
            val = left.removeFirst();
        } else {
            val = right.removeFirst();
        }

        balance();
        return val;
    }

    public int popMiddle() {
        if (isEmpty()) return -1;

        int val = left.removeLast();

        balance();
        return val;
    }

    public int popBack() {
        if (isEmpty()) return -1;

        int val;

        if (!right.isEmpty()) {
            val = right.removeLast();
        } else {
            val = left.removeLast();
        }

        balance();
        return val;
    }

    private void balance() {
        while (left.size() > right.size() + 1) {
            right.addFirst(left.removeLast());
        }

        while (left.size() < right.size()) {
            left.addLast(right.removeFirst());
        }
    }

    private boolean isEmpty() {
        return left.isEmpty() && right.isEmpty();
    }
}