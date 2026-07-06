class Solution {

    static class Node {
        long val;
        int idx;
        Node prev, next;
        boolean alive = true;

        Node(long v, int i) {
            val = v;
            idx = i;
        }
    }

    static class Pair {
        long sum;
        int idx;
        Node left;
        Node right;

        Pair(Node l, Node r) {
            left = l;
            right = r;
            sum = l.val + r.val;
            idx = l.idx;
        }
    }

    public int minimumPairRemoval(int[] nums) {

        int n = nums.length;

        if (n <= 1)
            return 0;

        Node[] nodes = new Node[n];

        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(nums[i], i);
        }

        for (int i = 0; i < n - 1; i++) {
            nodes[i].next = nodes[i + 1];
            nodes[i + 1].prev = nodes[i];
        }

        int badCount = 0;

        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                badCount++;
            }
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>(
                (a, b) -> {
                    if (a.sum != b.sum) {
                        return Long.compare(a.sum, b.sum);
                    }
                    return Integer.compare(a.idx, b.idx);
                });

        for (int i = 0; i < n - 1; i++) {
            pq.offer(new Pair(nodes[i], nodes[i + 1]));
        }

        int operations = 0;

        while (badCount > 0) {

            Pair p = pq.poll();

            Node L = p.left;
            Node R = p.right;

            if (!L.alive || !R.alive || L.next != R) {
                continue;
            }
            if (p.sum != L.val + R.val) {
                continue;
            }

            Node A = L.prev;
            Node B = R.next;

            // remove old bad edges
            if (A != null && A.val > L.val)
                badCount--;
            if (L.val > R.val)
                badCount--;
            if (B != null && R.val > B.val)
                badCount--;

            // merge into L
            L.val += R.val;

            R.alive = false;

            L.next = B;
            if (B != null) {
                B.prev = L;
            }

            // add new bad edges
            if (A != null && A.val > L.val)
                badCount++;
            if (B != null && L.val > B.val)
                badCount++;

            if (A != null) {
                pq.offer(new Pair(A, L));
            }

            if (B != null) {
                pq.offer(new Pair(L, B));
            }

            operations++;
        }

        return operations;
    }
}