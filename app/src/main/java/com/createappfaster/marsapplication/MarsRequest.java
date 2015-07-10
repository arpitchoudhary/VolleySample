package com.createappfaster.marsapplication;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by arpit on 09/07/15.
 */
public class MarsRequest {

    private RequestQueue requestQueue;
    private Context context;
    private static MarsRequest marsRequest;
    


    private MarsRequest(Context context){
        this.context = context;
        requestQueue = getRequestQueue();
    }

    public static MarsRequest getInstance(Context context){
        if(marsRequest == null){
            marsRequest = new MarsRequest(context);
        }
        return marsRequest;
    }

    public RequestQueue getRequestQueue() {
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public void addToRequestQueue(Request request){
        getRequestQueue().add(request);
    }
}
