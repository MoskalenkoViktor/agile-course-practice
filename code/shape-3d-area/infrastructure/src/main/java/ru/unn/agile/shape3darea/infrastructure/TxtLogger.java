package ru.unn.agile.shape3darea.infrastructure;

import ru.unn.agile.shape3darea.viewmodel.ILogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TxtLogger implements ILogger {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private BufferedWriter writer = null;
    private String fileName;

    public TxtLogger(final String fileName) {
        this.fileName = fileName;
        try {
            FileWriter file = new FileWriter(fileName);
            writer = new BufferedWriter(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void log(final String message) {
        try {
            writer.write(getCurrentTime() + " > " + message);
            writer.newLine();
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getLog() {
        ArrayList<String> result = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                result.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        return sdf.format(cal.getTime());
    }
}