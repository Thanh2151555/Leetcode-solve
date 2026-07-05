class Solution {
    public int myAtoi(String s) {

        int n = s.length();
        int i = 0;

        // Step 1: skip leading spaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // Step 2: sign
        int sign = 1;

        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            if (s.charAt(i) == '-') {
                sign = -1;
            }
            i++;
        }

        // Step 3: build number
        int result = 0;

        while (i < n && Character.isDigit(s.charAt(i))) {

            int digit = s.charAt(i) - '0';

            // positive overflow
            if (result > Integer.MAX_VALUE / 10 ||
                (result == Integer.MAX_VALUE / 10 && digit > 7)) {

                return sign == 1
                        ? Integer.MAX_VALUE
                        : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        return sign * result;
    }
}