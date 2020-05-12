import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;

public class SecondLab {
    public static void main(String[] args) throws Exception {
        double price;
        int traffic = 0;
        String csvFile = "internet.csv";
        BufferedReader br;
        String line;
        br = new BufferedReader(new FileReader(csvFile));
        while ((line = br.readLine()) != null) {
            String[] info = line.split(",");
            if (info.length > 1) {
                if (info[3].equals("192.168.250.59") || info[4].equals("192.168.250.59")) {
                    traffic += Integer.parseInt(info[12]);
                    StringBuilder timestring = new StringBuilder(info[1]);
                    timestring.delete(0, timestring.indexOf(" ") + 1);
                    int time = LocalTime.parse(timestring).toSecondOfDay()-41400;
                    try (FileWriter writer = new FileWriter("Arrays.txt", true)) {
                            writer.write(time + "," + (int)(traffic/Math.pow(2, 10)) + "\n");
                        } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        price = Price(traffic);
        System.out.println("Price: " + price + " R.");
        Process process = Runtime.getRuntime().exec("python3 Draw.py");
        process.waitFor();
        process.destroy();
    }

    private static double Price(int trafficCount) {
        double price = (double) trafficCount / Math.pow(2, 10) - 1000;
        price *= 100;
        price = Math.round(price) / 100.0;
        return price;
    }
}