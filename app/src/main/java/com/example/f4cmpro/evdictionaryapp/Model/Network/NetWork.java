package com.example.f4cmpro.evdictionaryapp.Model.Network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetWork implements INetWork {
    private Context mContext;
    public NetWork(Context context){
        mContext = context;
    }
    @Override
    public boolean isNextWork() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.isConnectedOrConnecting())
            return true;
        return false;
    }

    @Override
    public HttpURLConnection connectingServer(URL url) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new Exception("HTTP response code: "
                        + String.valueOf(responseCode));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
