package com.autohome.yxc.didi.util.map;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.autohome.yxc.didi.R;

import java.util.ArrayList;

/**
 * 地图辅助工具类
 * Created by yuexingchuan on 17/3/16.
 */

public class AmapUtil {

    private static ArrayList<Marker> markers=new ArrayList<Marker>();

    /**
     * 添加模拟测试的车的点
     */
    public static void addEmulateData(AMap amap, LatLng center){
        if(markers.size()==0){
            BitmapDescriptor bitmapDescriptor= BitmapDescriptorFactory
                    .fromResource(R.mipmap.icon_car);

            for(int i=0;i<20;i++){
                double latitudeDelt=(Math.random()-0.5)*0.1;
                double longtitudeDelt=(Math.random()-0.5)*0.1;
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.setFlat(true);
                markerOptions.anchor(0.5f, 0.5f);
                markerOptions.icon(bitmapDescriptor);
                markerOptions.position(new LatLng(center.latitude+latitudeDelt, center.longitude+longtitudeDelt));
                Marker marker=amap.addMarker(markerOptions);
                markers.add(marker);
            }
        }
        else{
            for(Marker marker:markers){
                double latitudeDelt=(Math.random()-0.5)*0.1;
                double longtitudeDelt=(Math.random()-0.5)*0.1;
                marker.setPosition(new LatLng(center.latitude+latitudeDelt, center.longitude+longtitudeDelt));

            }
        }
    }

    /**
     * 移除marker
     */
    public static void removeMarkers() {
        for(Marker marker:markers){
            marker.remove();
            marker.destroy();
        }
        markers.clear();
    }

}
