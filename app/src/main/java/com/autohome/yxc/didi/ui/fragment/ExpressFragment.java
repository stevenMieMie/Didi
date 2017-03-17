package com.autohome.yxc.didi.ui.fragment;

import android.graphics.BitmapFactory;
import android.graphics.Interpolator;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.Projection;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.autohome.yxc.didi.R;
import com.autohome.yxc.didi.base.SimpleFragment;
import com.autohome.yxc.didi.util.map.AmapUtil;
import com.autohome.yxc.didi.util.map.LocationTask;
import com.autohome.yxc.didi.util.map.OnLocationGetListener;
import com.autohome.yxc.didi.util.map.PositionEntity;
import com.autohome.yxc.didi.util.map.RegeocodeTask;
import com.autohome.yxc.didi.util.map.RouteTask;

import butterknife.BindView;

/**
 * Description:快车
 * Creator: yxc
 * date: 2017/03/16 9:14
 */
public class ExpressFragment extends SimpleFragment implements AMap.OnCameraChangeListener,
        AMap.OnMapLoadedListener, OnLocationGetListener,
        RouteTask.OnRouteCalculateListener {

    private AMap mAmap;
    private LocationTask mLocationTask;
    private RegeocodeTask mRegeocodeTask;
    private LatLng mStartPosition;
    private Marker mPositionMark;
    private boolean mIsFirst = true;

    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.tv_start)
    TextView tv_start;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_express;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(savedInstanceState);
        mLocationTask = LocationTask.getInstance(mContext.getApplicationContext());
        mLocationTask.setOnLocationGetListener(this);
        mRegeocodeTask = new RegeocodeTask(mContext.getApplicationContext());
        RouteTask.getInstance(mContext.getApplicationContext())
                .addRouteCalculateListener(this);
    }

    private void init(Bundle savedInstanceState) {
        mMapView.onCreate(savedInstanceState);
        if (mAmap == null)
            mAmap = mMapView.getMap();
        mAmap.getUiSettings().setZoomControlsEnabled(false);
        mAmap.getUiSettings().setRotateGesturesEnabled(false);
        mAmap.moveCamera(CameraUpdateFactory.zoomTo(16));//默认显示级别
        mAmap.setOnMapLoadedListener(this);
        mAmap.setOnCameraChangeListener(this);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        AmapUtil.removeMarkers();
        mMapView.onDestroy();
        mLocationTask.onDestroy();
        RouteTask.getInstance(mContext.getApplicationContext()).removeRouteCalculateListener(this);

    }


    @Override
    public void onCameraChange(CameraPosition arg0) {
//        hideView();
    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
//        showView();
        mStartPosition = cameraPosition.target;
        mRegeocodeTask.setOnLocationGetListener(this);
        mRegeocodeTask
                .search(mStartPosition.latitude, mStartPosition.longitude);
        if (mIsFirst) {
            AmapUtil.addEmulateData(mAmap, mStartPosition);
            if (mPositionMark != null) {
                mPositionMark.setToTop();
            }
            mIsFirst = false;
        }
    }

    @Override
    public void onMapLoaded() {

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.setFlat(true);
        markerOptions.anchor(0.5f, 0.5f);
        markerOptions.position(new LatLng(0, 0));
        markerOptions
                .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(),
                                R.mipmap.icon_loaction_start)));
        mPositionMark = mAmap.addMarker(markerOptions);

        mPositionMark.setPositionByPixels(mMapView.getWidth() / 2,
                mMapView.getHeight() / 2);
        mLocationTask.startSingleLocate();
    }

    @Override
    public void onLocationGet(PositionEntity entity) {
        // todo 这里在网络定位时可以减少一个逆地理编码
        tv_start.setText(entity.address);
        RouteTask.getInstance(mContext).setStartPoint(entity);

        mStartPosition = new LatLng(entity.latitue, entity.longitude);
        CameraUpdate cameraUpate = CameraUpdateFactory.newLatLngZoom(
                mStartPosition, mAmap.getCameraPosition().zoom);
        mAmap.animateCamera(cameraUpate);

    }

    @Override
    public void onRegecodeGet(PositionEntity entity) {
        tv_start.setText(entity.address);
        entity.latitue = mStartPosition.latitude;
        entity.longitude = mStartPosition.longitude;
        RouteTask.getInstance(mContext).setStartPoint(entity);
        RouteTask.getInstance(mContext).search();
    }

    @Override
    public void onRouteCalculate(float cost, float distance, int duration) {
//        mDestinationContainer.setVisibility(View.VISIBLE);
//        mIsRouteSuccess = true;
//        mRouteCostText.setVisibility(View.VISIBLE);
//        mDesitinationText.setText(RouteTask
//                .getInstance(getApplicationContext()).getEndPoint().address);
//        mRouteCostText.setText(String.format("预估费用%.2f元，距离%.1fkm,用时%d分", cost,
//                distance, duration));
//        mDestinationButton.setText("我要用车");
//        mCancelButton.setVisibility(View.VISIBLE);
//        mDestinationButton.setOnClickListener(null);
    }

    //掉下来还回弹一次
    private void dropInto(final Marker marker) {

        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        final LatLng markerLatlng = marker.getPosition();
        Projection proj = mAmap.getProjection();
        Point markerPoint = proj.toScreenLocation(markerLatlng);
        Point startPoint = new Point(markerPoint.x, 0);// 从marker的屏幕上方下落
        final LatLng startLatLng = proj.fromScreenLocation(startPoint);
        final long duration = 800;// 动画总时长

        final AccelerateInterpolator interpolator = new AccelerateInterpolator();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);
                double lng = t * markerLatlng.longitude + (1 - t)
                        * startLatLng.longitude;
                double lat = t * markerLatlng.latitude + (1 - t)
                        * startLatLng.latitude;
                marker.setPosition(new LatLng(lat, lng));
                if (t < 1.0) {
                    handler.postDelayed(this, 16);
                }
            }
        });
    }

}
