class Solution {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {

        Arrays.sort(nums);

        backtrack(nums, 0, new ArrayList<>());

        return result;
    }

    private void backtrack(int[] nums, int start, List<Integer> current) {

        // Thêm subset hiện tại
        result.add(new ArrayList<>(current));

        for (int i = start; i < nums.length; i++) {

            // Bỏ qua phần tử trùng ở cùng level
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            current.add(nums[i]);

            backtrack(nums, i + 1, current);

            current.remove(current.size() - 1);
        }
    }
}