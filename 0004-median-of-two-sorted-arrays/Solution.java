class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // luôn binary search trên mảng ngắn hơn
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        int left = 0;
        int right = m;

        while (left <= right) {

            int partitionA = (left + right) / 2;
            int partitionB = (m + n + 1) / 2 - partitionA;

            int maxLeftA =
                    (partitionA == 0) ? Integer.MIN_VALUE
                                      : nums1[partitionA - 1];

            int minRightA =
                    (partitionA == m) ? Integer.MAX_VALUE
                                      : nums1[partitionA];

            int maxLeftB =
                    (partitionB == 0) ? Integer.MIN_VALUE
                                      : nums2[partitionB - 1];

            int minRightB =
                    (partitionB == n) ? Integer.MAX_VALUE
                                      : nums2[partitionB];

            if (maxLeftA <= minRightB &&
                maxLeftB <= minRightA) {

                // tổng lẻ
                if ((m + n) % 2 == 1) {
                    return Math.max(maxLeftA, maxLeftB);
                }

                // tổng chẵn
                return (
                    Math.max(maxLeftA, maxLeftB) +
                    Math.min(minRightA, minRightB)
                ) / 2.0;
            }

            if (maxLeftA > minRightB) {
                right = partitionA - 1;
            } else {
                left = partitionA + 1;
            }
        }

        return 0.0;
    }
}