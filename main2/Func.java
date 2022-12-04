package org.example;

public class Func {
    public double Funcc(int cnt, int line, int cornerOrside) {
        double ans = 0;
        if (line == 1) {
            ans += cnt * 1.0;
        } else if (line == 2) {
            ans += cnt * 2.0;
        }
        if (cornerOrside == 1) {
            ans += 0.8;
        } else if (cornerOrside == 2) {
            ans += 0.4;
        }
        return ans;
    }
}
