package com.example.sampat.brillsalon;

import android.content.Context;

        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.toolbox.Volley;

/**
 * Created by W4R10CK on 13-09-2016.
 */
public class NetworkCalls {
    private RequestQueue requestQueue;
    private static Context context;

    private static NetworkCalls ourInstance = new NetworkCalls();

    public static NetworkCalls getInstance() {
        return ourInstance;
    }

    private NetworkCalls() {
    }

    public RequestQueue getRequestQueue(){
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }
}
