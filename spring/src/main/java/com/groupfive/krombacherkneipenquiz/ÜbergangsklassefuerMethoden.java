package com.groupfive.krombacherkneipenquiz;


import com.groupfive.krombacherkneipenquiz.controller.FragenPaketController;
import com.groupfive.krombacherkneipenquiz.controller.QuizRundeController;
import com.groupfive.krombacherkneipenquiz.models.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.*;
import java.net.*;

public class ÜbergangsklassefuerMethoden {
    // Rückgabetypen müssen noch angepasst werden
    // Im mom nur void

public ÜbergangsklassefuerMethoden(){

}
    // spielerpunkte berchnen
    public void berechnePunkte() {   // Methode FrageAnzeigen hat eine Variable sek für die verbleibenden sekunden für die antwort
        // wir können diese Variable für die punkte nutzen

    }


    //fur spieler
    public static void signUp(String benutzername, String email, String password) throws IOException {
        final String POST_PARAMS = "{\"benutzername:\" \"" + benutzername + "\",\"eMail\":\"" + email + "\"pw\":\"" + password + "\"}";
        URL url = new URL("http://localhost:8080/signupplayer");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        OutputStream os = httpURLConnection.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();

    }

    //fur kneipenbesitzer
    public static void signupbesitzer(String benutzername, String email, String password) throws IOException {
        final String POST_PARAMS = "{\"benutzername:\" \"" + benutzername + "\",\"eMail\":\"" + email + "\"pw\":\"" + password + "\"}";
        URL url = new URL("http://localhost:8080/signupbesitzer");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        OutputStream os = httpURLConnection.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();

    }

    //for administrator
    public static void registrateKneipe(String adresse, long benutzerId, String name) throws IOException {
        final String POST_PARAMS = "{\"name:\" \"" + name + "\",\"adresse\":\"" + adresse + "\"benutzer_id\":\"" + benutzerId + "\"}";
        URL url = new URL("http://localhost:8080/addkneipe");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        OutputStream os = httpURLConnection.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
    }

    //Kneipe dem Kneipenbesitzer zuordnen

    public static void addKneipetoBesitzer(long userid, long kneipenid) throws IOException {
        final String PUT_PARAMS = "{\"kneipen_id\":\"\"" + kneipenid + "\"}";
        String urlstr = "http://localhost:8080/addkneipetobesitzer/" + userid;
        URL url = new URL(urlstr);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("PUT");
        httpURLConnection.setDoOutput(true);
        OutputStream os = httpURLConnection.getOutputStream();
        os.write(PUT_PARAMS.getBytes());
        os.flush();
        os.close();
    }

    public String getIP2() throws Exception{

        InetAddress ip;
        String clientIPasString = new String();

        try {
            ip = InetAddress.getLocalHost();
            clientIPasString = ip.getHostAddress();
        }
        catch (
        UnknownHostException e)

        {
            e.printStackTrace();
        }
        return clientIPasString;
}

    public static String getIp() throws Exception {
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
            String ip = in.readLine();
            return ip;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
/*
    public static void QuizStarten(ArrayList<Frage> fragen) {
         for(int i=0; i<fragen.size(); i++) {
             frageAnzeigen(fragen.get(i));
         }
    }

    public static boolean loginSpieler(String username, String password) throws Exception {
         String str = "http://localhost:8080/api/";
         String url = str.concat(username);
         HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
         httpURLConnection.setRequestMethod("GET");
         int responseCode = httpURLConnection.getResponseCode();
         if(responseCode==HttpURLConnection.HTTP_OK) {
             BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
             String inputline;
             StringBuffer response = new StringBuffer();
             while ((inputline = in.readLine()) != null) {
                 response.append(inputline);
             }
             in.close();

             JSONObject jsonObject = new JSONObject(response.toString());
             JSONString jsonString = new JSONString() {
                 @Override
                 public String toJSONString() {
                     return response.toString();
                 }
             };
             Gson g = new Gson();
             Spieler spieler = g.fromJson(String.valueOf(jsonObject), Spieler.class);
             if(spieler.getPw().equals(password)) {
                 return true;
             }
         }
    return false;}

    public static boolean loginKneipenBesitzer(String username, String password) throws Exception {
        String str = "http://localhost:8080/api/";
        String url = str.concat(username);
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();
        if(responseCode==HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputline;
            StringBuffer response = new StringBuffer();
            while ((inputline = in.readLine()) != null) {
                response.append(inputline);
            }
            in.close();

            JSONObject jsonObject = new JSONObject(response.toString());
            JSONString jsonString = new JSONString() {
                @Override
                public String toJSONString() {
                    return response.toString();
                }
            };
            Gson g = new Gson();
            Kneipenbesitzer kneipenbesitzer = g.fromJson(String.valueOf(jsonObject), Kneipenbesitzer.class);
            if(kneipenbesitzer.getPw().equals(password)) {
                return true;
            }
        }
        return false;
    }

*/




}
