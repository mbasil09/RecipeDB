package com.example.recipedbnew;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;


public class BackgroundConnector extends AsyncTask<String, Void, String> {

    public AsyncResponse delegate = null;

    public BackgroundConnector(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected String doInBackground(String... strs) {
        Log.d("here", "register");
        try {
            URL url = new URL("https://cosylab.iiitd.edu.in/api/auth/realms/bootadmin/protocol/openid-connect/token");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            Log.d("here", "conn");
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoInput(true);
            OutputStream outputStream = conn.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String data =
                    URLEncoder.encode("client_id", "UTF-8") + "=" + URLEncoder.encode("app-ims", "UTF-8") + "&" +
                            URLEncoder.encode("grant_type", "UTF-8") + "=" + URLEncoder.encode("password", "UTF-8") + "&" +
                            URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode("manas", "UTF-8") + "&" +
                            URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode("manas_cosylab", "UTF-8") + "&" +
                            URLEncoder.encode("scope", "UTF-8") + "=" + URLEncoder.encode("openid", "UTF-8") + "&" ;
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = conn.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String inputLine;
            String result = "";
            while ((inputLine = in.readLine()) != null) {
                result += inputLine;
                Log.d("lul", inputLine + "\n");
            }
            JSONObject jObject = new JSONObject(result);
            in.close();
            inputStream.close();
            conn.disconnect();
            String token = jObject.getString("access_token");
            Log.d("token",token);

            if(strs[0]=="day"){
                Random rd = new Random(); // creating Random object
                String url1 = "https://cosylab.iiitd.edu.in/api/recipeDB/recipeInfo/"+String.valueOf(rd.nextInt((2680 - 2675) + 1) + 2675);
                url = new URL(url1);
                url = new URL("https://cosylab.iiitd.edu.in/api/recipeDB/recipeoftheday/");
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Authorization","Bearer "+token);
                Log.d("here", "bearer done");
                inputStream = conn.getInputStream();
                in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                result = "";
                while ((inputLine = in.readLine()) != null) {
                    result += inputLine;
                    Log.d("lul", inputLine + "\n");
                }
                in.close();
                inputStream.close();
                conn.disconnect();
                return result;
            }
            else if(strs[0]=="recipe"){
                Random rd = new Random(); // creating Random object
                String url1 = "https://cosylab.iiitd.edu.in/api/recipeDB/recipeInfo/"+String.valueOf(rd.nextInt((2680 - 2675) + 1) + 2675);
                url = new URL(url1);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Authorization","Bearer "+token);
                Log.d("here", "bearer done");
                inputStream = conn.getInputStream();
                in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                result = "";
                while ((inputLine = in.readLine()) != null) {
                    result += inputLine;
                    Log.d("lul", inputLine + "\n");
                }
                in.close();
                inputStream.close();
                conn.disconnect();
                return result;
            }
            else if(strs[0]=="ingredient"){
                String url1 = "https://cosylab.iiitd.edu.in/api/recipeDB/searchingredientnutrition/"+strs[1];
                url = new URL(url1);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Authorization","Bearer "+token);
                Log.d("here", "bearer done");
                inputStream = conn.getInputStream();
                in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                result = "";
                while ((inputLine = in.readLine()) != null) {
                    result += inputLine;
                    Log.d("lul", inputLine + "\n");
                }
                in.close();
                inputStream.close();
                conn.disconnect();
                return result;
            }
            else if(strs[0]=="ingredient2"){
                String url1 = "https://cosylab.iiitd.edu.in/api/recipeDB/searchingredientinrecipes/"+strs[1];
                url = new URL(url1);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Authorization","Bearer "+token);
                Log.d("here", "bearer done2");
                inputStream = conn.getInputStream();
                in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                result = "";
                while ((inputLine = in.readLine()) != null) {
                    result += inputLine;
                    Log.d("lul", inputLine + "\n");
                }
                in.close();
                inputStream.close();
                conn.disconnect();
                return result;
            }

        } catch (Exception e) {
            Log.d("error", String.valueOf(e));
        }
        return null;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        delegate.processFinish(s);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}
//
//    Random rd = new Random(); // creating Random object
//    String url1 = "https://cosylab.iiitd.edu.in/api/recipeDB/recipeInfo/"+String.valueOf(rd.nextInt((2680 - 2675) + 1) + 2675);
//                url = new URL(url1);
//                        conn = (HttpURLConnection) url.openConnection();
//                        conn.setRequestProperty("Authorization","Bearer "+token);
//                        Log.d("here", "bearer done");
//                        inputStream = conn.getInputStream();
//                        in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//                        result = "";
//                        while ((inputLine = in.readLine()) != null) {
//                        result += inputLine;
//                        Log.d("lul", inputLine + "\n");
//                        }
//                        in.close();
//                        inputStream.close();
//                        conn.disconnect();
//                        return result;