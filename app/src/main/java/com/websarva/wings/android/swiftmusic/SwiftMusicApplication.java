package com.websarva.wings.android.swiftmusic;

import android.app.Application;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Activityで利用する際に
 * フィールドで
 * > SwiftMusicApplication *****
 * onCreateで
 * > ***** = (SwiftMusicApplication) getApplication();
 *
 * 詳細設計 handa
 * Created by hotta on 2018/03/07-
 */

public class SwiftMusicApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

    }



    /**
     * 検索文字で検索依頼し、
     * 戻ってきたJSONを解析して
     * Listで出力する。
     * @param searchStr 検索文字。""なら全件
     * @return List<String>
     */
    public List<String> allsearch(String searchStr) {
        try {
            searchStr = URLEncoder.encode(searchStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String urlAddString = "";
        String jsonString;

        if (searchStr.length() == 0) {
            urlAddString = "all";
        } else {
            urlAddString = "search?name=" + searchStr;
        }
        jsonString = acsess(urlAddString);

        List<String> list = new ArrayList<String>();
        try {
            JSONArray loots = new JSONArray(jsonString);
            for (int i=0; i<loots.length(); i++) {
                JSONObject str = loots.getJSONObject(i);
                list.add(str.getString("name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 名前を元に音声ファイルのある場所（url）を返す。
     * @param searchStr
     * @return String.
     */
    public String url(String searchStr) {
        try {
            searchStr = URLEncoder.encode(searchStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String urlAddString = "";
        String jsonString;

        urlAddString = "url?name=" + searchStr;
        jsonString = acsess(urlAddString);

        String result = "";

        try {
            JSONObject obj = new JSONObject(jsonString);
            result = obj.getString("url");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }



    /**
     * データベースに接続してJSON文字列を取り出す。
     * @param searchStr あいまい検索する文字列。""の時はALL検索。
     * @return JSON文字列。
     */
    public String acsess(String searchStr) {

        StringBuffer jsonData = new StringBuffer();
        AcsessAsyncTask aat = new AcsessAsyncTask(jsonData);
        aat.execute(searchStr);

        int loopCnt = 10;
        loop:for (int i=0; i<loopCnt; i++) {
            if (jsonData.toString().length() != 0){
                break loop;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        if (jsonData.toString().length() == 0) {
            System.err.println("時間内に、データが取れなかった。アクセスできなかったよ！");
        }

        return jsonData.toString();
    }



    private class AcsessAsyncTask extends AsyncTask<String, String, String> {

        private StringBuffer jsonResult;

        public AcsessAsyncTask(StringBuffer stringBuffer) {
            stringBuffer.reverse();
            jsonResult = stringBuffer;
        }


        @Override
        protected String doInBackground(String... string) {


            String urlStr = "http://192.168.1.200:8080/SwiftMusic/" + string[0];


            HttpURLConnection con = null;
            InputStream is = null;
            String result = "";
            try {
                URL url = new URL(urlStr);
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                is = con.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();

                result = sb.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (con != null) {
                    con.disconnect();
                }
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            jsonResult.append(result);
            return result;
        }
    }





}
