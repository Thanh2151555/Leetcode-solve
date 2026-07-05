class Solution {
    public List<Integer> findSubstring(String s, String[] words) {

        List<Integer> result = new ArrayList<>();

        if (s == null || s.length() == 0 || words.length == 0) {
            return result;
        }

        // Độ dài mỗi từ
        int wordLen = words[0].length();

        // Số lượng từ
        int wordCount = words.length;

        // Tổng độ dài cần tìm
        int totalLen = wordLen * wordCount;

        if (s.length() < totalLen) {
            return result;
        }

        // Lưu số lần xuất hiện của mỗi từ
        Map<String, Integer> target = new HashMap<>();

        for (String word : words) {
            target.put(word, target.getOrDefault(word, 0) + 1);
        }

        // Có wordLen cách bắt đầu
        for (int offset = 0; offset < wordLen; offset++) {

            int left = offset;
            int right = offset;

            Map<String, Integer> window = new HashMap<>();

            // Số từ hợp lệ trong cửa sổ
            int count = 0;

            while (right + wordLen <= s.length()) {

                // Lấy từ bên phải
                String word = s.substring(right, right + wordLen);

                right += wordLen;

                // Không tồn tại trong target
                if (!target.containsKey(word)) {

                    window.clear();
                    count = 0;
                    left = right;

                    continue;
                }

                // Thêm vào cửa sổ
                window.put(word, window.getOrDefault(word, 0) + 1);
                count++;

                // Nếu vượt quá số lần cho phép
                while (window.get(word) > target.get(word)) {

                    String leftWord = s.substring(left, left + wordLen);

                    window.put(leftWord, window.get(leftWord) - 1);

                    left += wordLen;

                    count--;
                }

                // Đủ số từ
                if (count == wordCount) {

                    result.add(left);

                    // Dịch cửa sổ sang phải 1 block
                    String leftWord = s.substring(left, left + wordLen);

                    window.put(leftWord, window.get(leftWord) - 1);

                    left += wordLen;

                    count--;
                }
            }
        }

        return result;
    }
}