package com.example.localdevelopment;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;


public class App 
{
 public static void main(String[] args) {
        Container container = generateContainer();
        writeContainerToJsonFile(container, "container.json");
    }

    public static Container generateContainer() {
        Container container = new Container();
        container.setUuid(generateRandomUuid());
        container.setDateTime(generateCurrentDateTime());
        container.setDecimal1(generateRandomDecimal());
        container.setDecimal2(generateRandomDecimal());
        container.setCalculationResult(calculateResult(container.getDecimal1(), container.getDecimal2()));
        container.setMd5Hash(generateMd5Hash(container));

        return container;
    }

    private static String generateRandomUuid() {
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        return uuid;
    }

    private static String generateCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");
        String dateTime = dateFormat.format(new Date());
        return dateTime;
    }

    private static double generateRandomDecimal() {
        Random random = new Random();
        double randomNumber = random.nextDouble() * 99 + 1;
        return round(randomNumber, 4);
    }

    private static double round(double value, int scale) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(scale, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private static String calculateResult(double decimal1, double decimal2) {
        double divisor = Math.min(decimal1, decimal2);
        double result = Math.ceil(decimal1 / divisor * 100) / 100;
        return String.valueOf(result);
    }

    private static String generateMd5Hash(Container container) {
        String concatenatedInput = container.getUuid() + container.getDateTime() + container.getCalculationResult();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(concatenatedInput.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void writeContainerToJsonFile(Container container, String fileName) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uuid", container.getUuid());
            jsonObject.put("dateTime", container.getDateTime());
            jsonObject.put("decimal1", container.getDecimal1());
            jsonObject.put("decimal2", container.getDecimal2());
            jsonObject.put("calculationResult", container.getCalculationResult());
            jsonObject.put("md5Hash", container.getMd5Hash());

            String jsonString = jsonObject.toString();

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.append(jsonString);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}