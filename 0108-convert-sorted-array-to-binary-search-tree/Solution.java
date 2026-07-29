/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        // Gọi hàm đệ quy với toàn bộ mảng
        return build(nums, 0, nums.length - 1);
    }
    
    // Hàm đệ quy xây dựng BST từ đoạn left - right
    private TreeNode build(int[] nums, int left, int right){
        // Nếu khoảng xem sét không còn phần từ => không tạo node nữa
        if (left > right){
            return null;
        }
        // Tìm vị trí chính giữ 
        // Dùng công thức này để tránh tràn số
        int mid = left + (right - left) / 2;
        // Tạo node gốc từ phần tử giữa
        TreeNode root = new TreeNode(nums[mid]);
        // Các phần tử bên trái sẽ tạo thành cây con bên trái
        root.left = build(nums, left, mid - 1);
        // Các phần tử bên trái sẽ tạo thành cây con bên phải
        root.right = build(nums, mid + 1, right);
        
        // Trả về cây vừa tạo
        return root;

    }
}