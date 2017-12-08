package com.example.f4cmpro.evdictionaryapp.Adapter.View;


import android.support.v4.app.Fragment;
import android.view.View;

public interface AdapterDataView {
    interface PagerAdapterView<T>{
        Fragment create(T t);
    }
    void refresh();
}
