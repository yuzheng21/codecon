class Solution {

    private static final long EIGHT_ONES = 0xFFL;
    private static final long ALL_ONES = 0xFFFFFFFFL;

    public List<String> ipToCIDR(String ip, int n) {
        List<String> ret = new ArrayList<>();
        long number = ipToLong(ip);

        while (n > 0) {
            long mask = 1L;
            int lowerBit = 0;
            while ((number & mask) == 0) {
                mask = mask << 1;
                long covered = 1 << (lowerBit + 1);
                if (covered > n) {
                    break;
                } else {
                    lowerBit++;
                }
            }

            ret.add(toCIDR(number, 32 - lowerBit));

            int matched = 1 << lowerBit;

            // flip lower 0s to 1s and then plus 1
            number += matched;

            n -= matched;
        }

        return ret;
    }

    private String toCIDR(long number, int prefixLen) {
        String ip = longToIp(number);
        return ip + '/' + prefixLen;
    }

    private long ipToLong(String ip) {
        long ret = 0L;
        long part = 0L;
        for (char c : ip.toCharArray()) {
            if (c == '.') {
                ret = ((ret << 8) | part);
                part = 0L;
            } else {
                part = part * 10 + (c - '0');
            }
        }
        return ((ret << 8) | part);
    }

    private String longToIp(long number) {
        return (number >> 24) + "."
            + ((number >> 16) & EIGHT_ONES) + "."
            + ((number >> 8) & EIGHT_ONES) + "."
            + (number & EIGHT_ONES);
    }
}
