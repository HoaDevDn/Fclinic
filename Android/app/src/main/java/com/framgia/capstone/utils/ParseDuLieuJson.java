package com.framgia.capstone.utils;

import com.framgia.capstone.data.model.NhaThuoc;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParseDuLieuJson {
    String dulieu;

    public ParseDuLieuJson() {
        super();
    }

    public ParseDuLieuJson(String dulieu) {
        this.dulieu = dulieu;
    }

    public List<NhaThuoc> layDiaChi() {
        List<NhaThuoc> list = new ArrayList<NhaThuoc>();
        try {
            JSONObject object = new JSONObject(dulieu);
            JSONArray results = object.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                JSONObject objectResult = results.getJSONObject(i);
                NhaThuoc item = new NhaThuoc();
                item.setTenNhaThuoc(objectResult.getString("name"));
                item.setDiaChi(objectResult.getString("vicinity"));
                JSONObject geometry = objectResult.getJSONObject("geometry");
                JSONObject location = geometry.getJSONObject("location");
                item.setLatitude(location.getString("lat"));
                item.setLongtitude(location.getString("lng"));
                list.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
