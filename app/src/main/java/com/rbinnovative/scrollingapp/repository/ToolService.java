package com.rbinnovative.scrollingapp.repository;

import com.rbinnovative.scrollingapp.model.MyListData;
import com.rbinnovative.scrollingapp.model.Tools;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;


public class ToolService {

    private static List<Tools> tools = Arrays.asList(new Tools(), new Tools() , new Tools());

    public MyListData[] initDataSet() {
        return new MyListData[]{
                new MyListData("Email", android.R.drawable.ic_dialog_email),
                new MyListData("Info", android.R.drawable.ic_dialog_info),
                new MyListData("Delete", android.R.drawable.ic_delete),
                new MyListData("Dialer", android.R.drawable.ic_dialog_dialer),
                new MyListData("Alert", android.R.drawable.ic_dialog_alert),
                new MyListData("Map", android.R.drawable.ic_dialog_map),
                new MyListData("Email", android.R.drawable.ic_dialog_email),
                new MyListData("Info", android.R.drawable.ic_dialog_info),
                new MyListData("Delete", android.R.drawable.ic_delete),
                new MyListData("Dialer", android.R.drawable.ic_dialog_dialer),
                new MyListData("Alert", android.R.drawable.ic_dialog_alert),
                new MyListData("Map", android.R.drawable.ic_dialog_map),
        };
    }
    @Inject
    public ToolService(){
    }
    public static List<Tools> getTools() {
        return tools;
    }
}
