package org.example;
import org.example.View.Mainview;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        /*System.out.println(CalendarApi.transferSinfleDate("20231003").getData().getTypeDes());*/
        Mainview mainview = new Mainview();
        mainview.Init();
        mainview.changeMonthHandler();
        mainview.changeYearHandler();

    }
}