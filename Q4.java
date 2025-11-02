
class Solution {

    private String res;
    private String trg;
    private char midch;

    private String build(char[] h) {
        StringBuilder p = new StringBuilder();
        p.append(h);
        if (midch != 0) {
            p.append(midch);
        }
        StringBuilder r = new StringBuilder(new String(h));
        p.append(r.reverse());
        return p.toString();
    }

    private boolean find(int k, char[] h, int[] hc, boolean iG) {
        if (k == h.length) {
            String pStr = build(h);
            if (pStr.compareTo(trg) > 0) {
                res = pStr;
                return true;
            }
            return false;
        }

        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            if (hc[i] == 0) {
                continue;
            }
            if (!iG && c < trg.charAt(k)) {
                continue;
            }

            h[k] = c;
            hc[i]--;
            boolean nG = iG || c > trg.charAt(k);
            if (find(k + 1, h, hc, nG)) {
                return true;
            }
            hc[i]++;
        }
        return false;
    }

    public String lexPalindromicPermutation(String s, String target) {
        this.trg = target;
        this.res = null;
        this.midch = 0;

        int[] cnts = new int[26];
        for (char c : s.toCharArray()) {
            cnts[c - 'a']++;
        }

        int n = s.length();
        int odd = 0;
        for (int i = 0; i < 26; i++) {
            if (cnts[i] % 2 != 0) {
                odd++;
                this.midch = (char) ('a' + i);
            }
        }

        // Palindrome impossible cases
        if ((n % 2 == 0 && odd > 0) || (n % 2 != 0 && odd != 1)) {
            return "";
        }

        int[] hc = new int[26];
        for (int i = 0; i < 26; i++) {
            hc[i] = cnts[i] / 2;
        }

        char[] h = new char[n / 2];
        find(0, h, hc, false);

        return this.res == null ? "" : this.res;
    }
}