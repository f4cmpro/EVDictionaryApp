package com.example.f4cmpro.evdictionaryapp.Model.Network;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;

public interface INetWork{
    boolean isNextWork();
    HttpURLConnection connectingServer(URL url);
}
