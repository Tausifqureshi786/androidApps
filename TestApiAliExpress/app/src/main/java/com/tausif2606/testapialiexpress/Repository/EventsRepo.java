package com.tausif2606.testapialiexpress.Repository;

import android.util.Log;

import com.tausif2606.testapialiexpress.MainActivity;
import com.tausif2606.testapialiexpress.Models.EventModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EventsRepo {
    JSONArray jsonArr= MainActivity.productListArr;
    private static EventsRepo eventsRepo;
    private ArrayList<EventModel> eventModelList = new ArrayList<>();

    public EventsRepo() {
        // pass and json array of products in the constructor

        try {

        for(int i=0; i<jsonArr.length();i++)
        {
            JSONObject product= null;

                product = jsonArr.getJSONObject(i);

            String imageUrl=product.getJSONObject("image").getString("imageUrl");

            String title = product.getJSONObject("title").getString("displayTitle");

            String price = product.getJSONObject("prices").getJSONObject("sale_price").getString("formattedPrice");

            Long id= product.getLong("productId");

            Log.i("JSONOBJ",imageUrl);
            Log.i("JSONOBJ",title);
            Log.i("JSONOBJ",price);
            Log.i("JSONOBJ",id.toString());

//            eventModelList.add(new EventModel(imageUrl,title,price,id));

        }


        }
         catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<EventModel> getEventModelList() {
        return eventModelList;
    }

    public static EventsRepo getEventsRepo()
    {
        if(eventsRepo ==null)
        {
            eventsRepo = new EventsRepo();
        }
        return eventsRepo;
    }
}
