package com.linsizhe.coinbase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HttpRequestDemo {
    public static void main(String[] args) throws IOException {

        URL url = new URL("https://www.google.com/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        con.connect();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String line = "";
        StringBuilder sb = new StringBuilder();

        while (line != null) {
            sb.append(line);
            line = in.readLine();
        }

        in.close();
        System.out.println(sb.toString());
    }
}
