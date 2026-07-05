class Solution {

    public List<List<Integer>> permuteUnique(int[] nums) {

        // Sắp xếp để xử lý duplicate
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];

        backtrack(nums, used, path, result);

        return result;
    }

    private void backtrack(int[] nums,
                           boolean[] used,
                           List<Integer> path,
                           List<List<Integer>> result) {

        // Điều kiện dừng
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        // Thử chọn từng phần tử
        for (int i = 0; i < nums.length; i++) {

            // Đã dùng rồi
            if (used[i]) {
                continue;
            }

            // Bỏ qua duplicate
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            // Chọn
            used[i] = true;
            path.add(nums[i]);

            // Đệ quy
            backtrack(nums, used, path, result);

            // Quay lui
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}
