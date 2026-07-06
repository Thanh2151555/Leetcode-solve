class LFUCache {

    class Node {
        int key;
        int value;
        int freq;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }

    class DoublyLinkedList {
        Node head;
        Node tail;
        int size;

        DoublyLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);

            head.next = tail;
            tail.prev = head;
        }

        void addFirst(Node node) {
            Node nextNode = head.next;

            head.next = node;
            node.prev = head;

            node.next = nextNode;
            nextNode.prev = node;

            size++;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;

            size--;
        }

        Node removeLast() {
            if (size == 0) return null;

            Node lastNode = tail.prev;
            remove(lastNode);

            return lastNode;
        }
    }

    private int capacity;
    private int minFreq;

    private Map<Integer, Node> keyToNode;
    private Map<Integer, DoublyLinkedList> freqToList;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;

        keyToNode = new HashMap<>();
        freqToList = new HashMap<>();
    }

    public int get(int key) {
        if (!keyToNode.containsKey(key)) {
            return -1;
        }

        Node node = keyToNode.get(key);
        increaseFreq(node);

        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (keyToNode.containsKey(key)) {
            Node node = keyToNode.get(key);
            node.value = value;
            increaseFreq(node);
            return;
        }

        if (keyToNode.size() == capacity) {
            DoublyLinkedList minList = freqToList.get(minFreq);
            Node removedNode = minList.removeLast();
            keyToNode.remove(removedNode.key);
        }

        Node newNode = new Node(key, value);
        keyToNode.put(key, newNode);

        DoublyLinkedList list = freqToList.getOrDefault(1, new DoublyLinkedList());
        list.addFirst(newNode);
        freqToList.put(1, list);

        minFreq = 1;
    }

    private void increaseFreq(Node node) {
        int oldFreq = node.freq;

        DoublyLinkedList oldList = freqToList.get(oldFreq);
        oldList.remove(node);

        if (oldFreq == minFreq && oldList.size == 0) {
            minFreq++;
        }

        node.freq++;

        DoublyLinkedList newList = freqToList.getOrDefault(node.freq, new DoublyLinkedList());
        newList.addFirst(node);
        freqToList.put(node.freq, newList);
    }
}