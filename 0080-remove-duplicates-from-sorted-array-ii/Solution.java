class Solution {
    public int removeDuplicates(int[] nums) {

        // Nếu mảng có <= 2 phần tử thì giữ nguyên
        if (nums.length <= 2) {
            return nums.length;
        }

        // i là vị trí ghi tiếp theo
        int i = 2;

        // j dùng để duyệt mảng
        for (int j = 2; j < nums.length; j++) {

            // So sánh với phần tử cách i 2 vị trí
            if (nums[j] != nums[i - 2]) {
                nums[i] = nums[j];
                i++;
            }
        }

        return i;
    }
}