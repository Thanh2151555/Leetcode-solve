class Solution {

    // Hàm chính trả về toàn bộ tam giác Pascal
    public List<List<Integer>> generate(int numRows) {

        // Danh sách chứa tất cả các hàng
        List<List<Integer>> result = new ArrayList<>();

        // Duyệt từng hàng từ 0 đến numRows-1
        for (int i = 0; i < numRows; i++) {

            // Tạo một hàng mới
            List<Integer> row = new ArrayList<>();

            // Mỗi hàng có i+1 phần tử
            for (int j = 0; j <= i; j++) {

                // Phần tử đầu hoặc cuối luôn bằng 1
                if (j == 0 || j == i) {
                    row.add(1);
                } else {

                    // Lấy hàng trước đó
                    List<Integer> prev = result.get(i - 1);

                    // Quy luật Pascal:
                    // = số phía trên bên trái + số phía trên bên phải
                    row.add(prev.get(j - 1) + prev.get(j));
                }
            }

            // Thêm hàng vừa tạo vào kết quả
            result.add(row);
        }

        // Trả về toàn bộ tam giác
        return result;
    }
}