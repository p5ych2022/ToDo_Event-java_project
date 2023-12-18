package org.example;
import com.google.gson.Gson;
import org.example.Model.SingleDate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CalendarApi {
    public static SingleDate transferSinfleDate(String INPUT_DATE) throws IOException {
        String url_high = "https://www.mxnzp.com/api/holiday/single/";
        String url_low = INPUT_DATE;
        String url = url_high + url_low;
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("app_id","klodjegihxmfwlgi");
        conn.setRequestProperty("app_secret","U4AaqhmHypGnqx4Wa7cyTli8IMNaYrnT");
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        Gson gson = new Gson();
        SingleDate singleDate = gson.fromJson(br.readLine(), SingleDate.class);
        return singleDate;
    }
}
