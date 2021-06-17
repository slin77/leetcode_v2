package com.linsizhe.facebook;

//https://leetcode.com/problems/robot-bounded-in-circle/
public class RobotBoundedInCircle {
    char curD;
    int x;
    int y;
    public boolean isRobotBounded(String instructions) {
        curD = 'N';
        for (char c : instructions.toCharArray()) {
            update(c);
        }
        // Robot reach goal iff it is at orignal. OR it is facing left or right (then triangle).
        return (x == 0 && y == 0) || curD != 'N';
    }

    public void update(char c) {
        if (c == 'G') {
            switch (curD) {
                case 'N':
                    y++;
                    break;
                case 'S':
                    y--;
                    break;
                case 'E':
                    x++;
                    break;
                case 'W':
                    x--;
                    break;
            }
        } else if (c == 'L') {
            switch (curD) {
                case 'N':
                    curD = 'W';
                    break;
                case 'S':
                    curD = 'E';
                    break;
                case 'E':
                    curD = 'N';
                    break;
                case 'W':
                    curD = 'S';
                    break;
            }
        } else if (c == 'R') {
            switch (curD) {
                case 'N':
                    curD = 'E';
                    break;
                case 'S':
                    curD = 'W';
                    break;
                case 'E':
                    curD = 'S';
                    break;
                case 'W':
                    curD = 'N';
                    break;
            }
        }
    }
}
