package CoinbaseInterview;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ParseCSV {

    static class DataEntry {
        String varChar;
        Boolean bool;
        Float num;
        LocalDate date;

        @Override
        public String toString() {
            if (varChar != null) {
                return varChar;
            } else if (bool != null) {
                return bool.toString();
            } else if (num != null) {
                return num.toString();
            } else if (date != null) {
                return date.toString();
            } else {
                return "NULL";
            }
        }
    }

    static class SQLFile {

        String[] colNames;

        List<List<DataEntry>> datas;

        public SQLFile() {
            this.datas = new ArrayList<>();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("colnames: \n");
            for (String col : colNames) {
                sb.append(col);
                sb.append(", ");
            }

            for (List<DataEntry> entries: datas) {
                sb.append("[line]");
                for (DataEntry data : entries) {
                    sb.append(data.toString());
                    sb.append(",");
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }

    private static void parseColNames(String line, SQLFile sqlFile) {
        String[] names = line.split(",");
        sqlFile.colNames = names;
    }

    private static void parseData(String line, SQLFile sqlFile) {
        String[] rowData = line.split(",");
        List<DataEntry> lineData = new ArrayList<>();
        sqlFile.datas.add(lineData);
        for (String str : rowData) {
            DataEntry entry = new DataEntry();
            // parse varchar
            if (str.startsWith("\"") && str.endsWith("\"")) {

                entry.varChar = str.substring(1, str.length() - 1);

            } else if (str.contains(".") || (!str.isEmpty() && Character.isDigit(str.charAt(0)) && !str.contains("\\") && !str.contains(":")) ) {
                entry.num = Float.parseFloat(str);
            } else if (str.equals("true") || str.equals("false")) {
                entry.bool = Boolean.parseBoolean(str);
            } else if (!str.isEmpty()) {
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("m/d/yy H:mm:ss");
                entry.date = LocalDate.parse(str, formatter1);
            }
            lineData.add(entry);
        }
    }

    public static void main(String[] args) throws IOException {
        //String path = args[0];
        String path = "F:\\ideaprojects\\src\\CoinbaseInterview\\data.txt";
        FileInputStream in = new FileInputStream(path);

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = null;
        int count = 0;
        SQLFile file = new SQLFile();
        while ((line = reader.readLine()) != null) {
            if (count == 0) {
                parseColNames(line, file);
            } else {
                parseData(line, file);
            }
            count++;
        }
        reader.close();
        in.close();
    }

}
