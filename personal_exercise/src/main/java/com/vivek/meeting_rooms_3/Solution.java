package com.vivek.meeting_rooms_3;

import java.util.Arrays;

class Solution {
    /*
     * You are given an integer n. There are n rooms numbered from 0 to n - 1.
     * 
     * You are given a 2D integer array meetings where meetings[i] = [starti, endi]
     * means that a meeting will be held during the half-closed time interval
     * [starti, endi). All the values of starti are unique.
     * 
     * Meetings are allocated to rooms in the following manner:
     * 
     * Each meeting will take place in the unused room with the lowest number.
     * If there are no available rooms, the meeting will be delayed until a room
     * becomes free. The delayed meeting should have the same duration as the
     * original meeting.
     * When a room becomes unused, meetings that have an earlier original start time
     * should be given the room.
     * Return the number of the room that held the most meetings. If there are
     * multiple rooms, return the room with the lowest number.
     * 
     * A half-closed interval [a, b) is the interval between a and b including a and
     * not including b.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: n = 2, meetings = [[0,10],[1,5],[2,7],[3,4]]
     * Output: 0
     * Explanation:
     * - At time 0, both rooms are not being used. The first meeting starts in room
     * 0.
     * - At time 1, only room 1 is not being used. The second meeting starts in room
     * 1.
     * - At time 2, both rooms are being used. The third meeting is delayed.
     * - At time 3, both rooms are being used. The fourth meeting is delayed.
     * - At time 5, the meeting in room 1 finishes. The third meeting starts in room
     * 1 for the time period [5,10).
     * - At time 10, the meetings in both rooms finish. The fourth meeting starts in
     * room 0 for the time period [10,11).
     * Both rooms 0 and 1 held 2 meetings, so we return 0.
     * Example 2:
     * 
     * Input: n = 3, meetings = [[1,20],[2,10],[3,5],[4,9],[6,8]]
     * Output: 1
     * Explanation:
     * - At time 1, all three rooms are not being used. The first meeting starts in
     * room 0.
     * - At time 2, rooms 1 and 2 are not being used. The second meeting starts in
     * room 1.
     * - At time 3, only room 2 is not being used. The third meeting starts in room
     * 2.
     * - At time 4, all three rooms are being used. The fourth meeting is delayed.
     * - At time 5, the meeting in room 2 finishes. The fourth meeting starts in
     * room 2 for the time period [5,10).
     * - At time 6, all three rooms are being used. The fifth meeting is delayed.
     * - At time 10, the meetings in rooms 1 and 2 finish. The fifth meeting starts
     * in room 1 for the time period [10,12).
     * Room 0 held 1 meeting while rooms 1 and 2 each held 2 meetings, so we return
     * 1.
     * 
     * 
     * Constraints:
     * 
     * 1 <= n <= 100
     * 1 <= meetings.length <= 105
     * meetings[i].length == 2
     * 0 <= starti < endi <= 5 * 105
     * All the values of starti are unique.
     */
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (o1, o2) -> {if ( o1[0] < o2[0]) return -1; else if ( o1[0] > o2[0] ) return 1; else return 0;});
        int[] bookingCount = new int[n];
        int[] roomStatus = new int[n];
        for ( int i = 0; i < n; i++ ) {
            roomStatus[i] = 0; //if -1 then it means room is available
        }
        for ( int meetingCount = 0; meetingCount < meetings.length; meetingCount++ ) {
            //check which room is available for the start time of this meeting
            //depending onn the end time of the meeting mark the room
            int thisMeetingStart = meetings[meetingCount][0];
            int thisMeetingDuration = meetings[meetingCount][1] - thisMeetingStart;
            
            int nextAvailable = this.firstAvailable(roomStatus, thisMeetingStart);
            int thisMeetingEnd = Math.max(roomStatus[nextAvailable], thisMeetingStart) + thisMeetingDuration;//start time based on room available and duration
            //set the end time for the next available to be the end time for this meeting
            roomStatus[nextAvailable] = thisMeetingEnd;
            //update count to indicate that this room was used
            bookingCount[nextAvailable]++;
            
            // for ( int roomCount = 0; roomCount < n; roomCount++ ) {
            //     if ( roomStatus[roomCount] < 1 ) {
            //         //room is empty so assign the meeting to this room and increment the meeting count
            //         roomStatus[roomCount] = thisMeetingEnd;
            //         bookingCount[roomCount]++;
            //         break;
            //     }
            // }
        }
        int maxBookedCount = 0;
        int maxBookedIndex = 0;
        for ( int i = 0; i < bookingCount.length; i++ ) {
            if (bookingCount[i] > maxBookedCount ) {
                maxBookedIndex = i;
                maxBookedCount = bookingCount[i];
            }
        }
        return maxBookedIndex;
    }

    private int firstAvailable(int[] rooms, int currentTime) {
        int first = 0;
        for ( int i = 0; i < rooms.length; i++ ) {
            //what is the current time
            //did the meeting already end in this room? yes no
            //if the meeting ended then use this room
            //if the meeting did not end then check when it will be available
            if ( rooms[i] == 0 || rooms[i] <= currentTime ) {
                //either nothing was scheduled or it already ended, use this room and break out
                return i;
            }
            //currently in use, check if this is first one to be free
            if ( rooms[i] < rooms[first] ) {
                first = i;
            }
        }
        return first;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // int[][] meetings1 = {{0,10},{1,5},{2,7},{3,4}};
        // System.out.println(solution.mostBooked(2, meetings1)); // Output: 0

        // int[][] meetings2 = {{1,20},{2,10},{3,5},{4,9},{6,8}};
        // System.out.println(solution.mostBooked(3, meetings2)); // Output: 1

        // int[][] meetings3 = {{18,19},{3,12},{17,19},{2,13},{7,10}};
        // System.out.println(solution.mostBooked(4, meetings3)); // Output: 0

        int[][] meetings4 = {{0,10},{1,9},{2,8},{3,7},{4,6}};
        System.out.println(solution.mostBooked(3, meetings4)); // Output: 1
    }
}
