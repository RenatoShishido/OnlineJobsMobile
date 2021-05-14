package com.example.cadastro.util;

import android.content.Context;
import android.widget.Toast;

public class UtilAlert {

    public static void alert(Context context , String message){
        Toast.makeText(context , message, Toast.LENGTH_SHORT).show();
    }
}
