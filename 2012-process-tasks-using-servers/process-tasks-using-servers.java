class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        // Priority queue for free servers: sorted by weight, then by index
        // Each element is stored as an int[]: {weight, index}
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        // Priority queue for busy servers: sorted by free time, then weight, then index
        // Each element is stored as long[]: {free_time, weight, index}
        PriorityQueue<long[]> busyServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Long.compare(a[0], b[0]);
            }
            if (a[1] != b[1]) {
                return Long.compare(a[1], b[1]);
            }
            return Long.compare(a[2], b[2]);
        });

        // Initialize free servers
        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[] { servers[i], i });
        }

        long currentTime = 0;

        for (int j = 0; j < m; j++) {
            // Time cannot be less than the task's arrival time j
            currentTime = Math.max(currentTime, j);

            // If there are no free servers, jump time to when the next server becomes free
            if (freeServers.isEmpty()) {
                currentTime = Math.max(currentTime, busyServers.peek()[0]);
            }

            // Release all servers that finish at or before currentTime
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                long[] busy = busyServers.poll();
                freeServers.offer(new int[] { (int) busy[1], (int) busy[2] });
            }

            // Assign the current task j to the best available free server
            int[] server = freeServers.poll();
            ans[j] = server[1];

            // Push the server to the busy queue with its new free-up time
            busyServers.offer(new long[] { currentTime + tasks[j], server[0], server[1] });
        }

        return ans;
    }
}