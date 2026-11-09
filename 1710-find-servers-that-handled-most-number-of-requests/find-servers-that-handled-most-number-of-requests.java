import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // TreeSet to store currently available servers
        TreeSet<Integer> availableServers = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            availableServers.add(i);
        }

        // Min-heap to store busy servers as int[]: {free_time, server_id}
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        int[] requestCount = new int[k];
        int maxRequests = 0;

        for (int i = 0; i < arrival.length; i++) {
            int arrivalTime = arrival[i];
            int endTime = arrivalTime + load[i];

            // Free up servers whose tasks have finished by the current arrival time
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= arrivalTime) {
                availableServers.add(busyServers.poll()[1]);
            }

            // If no servers are available, drop the request
            if (availableServers.isEmpty()) {
                continue;
            }

            // Find the preferred server using i % k
            Integer serverId = availableServers.ceiling(i % k);
            
            // If none found at or after i % k, wrap around to the first available server from 0
            if (serverId == null) {
                serverId = availableServers.first();
            }

            // Assign the request to the chosen server
            availableServers.remove(serverId);
            busyServers.offer(new int[] { endTime, serverId });
            
            requestCount[serverId]++;
            maxRequests = Math.max(maxRequests, requestCount[serverId]);
        }

        // Collect all servers that handled the maximum number of requests
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCount[i] == maxRequests) {
                result.add(i);
            }
        }

        return result;
    }
}