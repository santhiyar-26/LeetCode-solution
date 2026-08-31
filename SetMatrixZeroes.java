class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int firstCritical = -1;
        int previousCritical = -1;
        int minDistance = Integer.MAX_VALUE;

        int index = 1;

        ListNode prev = head;
        ListNode curr = head.next;

        while (curr != null && curr.next != null) {

            // Check whether current node is a critical point
            boolean isCritical =
                (curr.val > prev.val && curr.val > curr.next.val) ||
                (curr.val < prev.val && curr.val < curr.next.val);

            if (isCritical) {

                // First critical point
                if (firstCritical == -1) {
                    firstCritical = index;
                }

                // Distance from previous critical point
                if (previousCritical != -1) {
                    minDistance = Math.min(
                        minDistance,
                        index - previousCritical
                    );
                }

                previousCritical = index;
            }

            prev = curr;
            curr = curr.next;
            index++;
        }

        // Less than 2 critical points
        if (firstCritical == -1 || firstCritical == previousCritical) {
            return new int[]{-1, -1};
        }

        // Maximum distance
        int maxDistance = previousCritical - firstCritical;

        return new int[]{minDistance, maxDistance};
    }
}
