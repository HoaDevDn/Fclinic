package com.framgia.capstone.ui.nhathuoc;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.framgia.capstone.R;
import com.framgia.capstone.data.model.NhaThuoc;
import com.framgia.capstone.utils.ParseDuLieuJson;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by tri on 18/03/2017.
 */
public class NhaThuocFragment extends Fragment
    implements android.location.LocationListener, View.OnClickListener {
    List<NhaThuoc> list;
    Button btnTimNhaThuoc;
    private GoogleMap myMap;
    private ProgressDialog myProgress;
    private static final String MYTAG = "MYTAG";
    String duongdan1 = "https://maps.googleapis.com/maps/api/place/search/json?location=";
    String duongdan2 =
        "&radius=1000&keyword=nha%20thuoc&sensor=false&key=AIzaSyBkjo2uc5s15TCldrs1y_fvz99GP0sUPEM";
    public static final int REQUEST_ID_ACCESS_COURSE_FINE_LOCATION = 100;

    public NhaThuocFragment() {
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nha_thuoc, container, false);
        btnTimNhaThuoc = (Button) view.findViewById(R.id.btn_tim_nha_thuoc);
        btnTimNhaThuoc.setOnClickListener(this);
        myProgress = new ProgressDialog(getActivity());
        myProgress.setTitle("Đang tải bản đồ ...");
        myProgress.setMessage("Vui lòng đợi giây lát...");
        myProgress.setCancelable(true);
        myProgress.show();
        MapFragment mapFragment = (MapFragment) getActivity().getFragmentManager()
            .findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                onMyMapReady(googleMap);
            }
        });
        return view;
    }

    private void onMyMapReady(GoogleMap googleMap) {
        myMap = googleMap;
        myMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                myProgress.dismiss();
                askPermissionsAndShowMyLocation();
            }
        });
        myMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        myMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat
            .checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED &&
            ActivityCompat
                .checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            return;
        }
        myMap.setMyLocationEnabled(true);
    }

    private void askPermissionsAndShowMyLocation() {
        if (Build.VERSION.SDK_INT >= 23) {
            int accessCoarsePermission =
                ContextCompat
                    .checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION);
            int accessFinePermission
                = ContextCompat
                .checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
            if (accessCoarsePermission != PackageManager.PERMISSION_GRANTED
                || accessFinePermission != PackageManager.PERMISSION_GRANTED) {
                String[] permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION};
                ActivityCompat.requestPermissions(getActivity(), permissions,
                    REQUEST_ID_ACCESS_COURSE_FINE_LOCATION);
                return;
            }
        }
        this.showMyLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //
        switch (requestCode) {
            case REQUEST_ID_ACCESS_COURSE_FINE_LOCATION: {
                if (grantResults.length > 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "Permission granted!", Toast.LENGTH_LONG).show();
                    this.showMyLocation();
                } else {
                    Toast.makeText(getActivity(), "Permission denied!", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }

    private String getEnabledLocationProvider() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService
            (LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, true);
        boolean enabled = locationManager.isProviderEnabled(bestProvider);
        if (!enabled) {
            Toast.makeText(getActivity(), "No location provider enabled!", Toast.LENGTH_LONG)
                .show();
            Log.i(MYTAG, "No location provider enabled!");
            return null;
        }
        return bestProvider;
    }

    private void showMyLocation() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService
            (LOCATION_SERVICE);
        String locationProvider = this.getEnabledLocationProvider();
        if (locationProvider == null) {
            return;
        }
        // Millisecond
        final long MIN_TIME_BW_UPDATES = 1000;
        // Met
        final float MIN_DISTANCE_CHANGE_FOR_UPDATES = 1;
        Location myLocation = null;
        try {
            // Đoạn code nay cần người dùng cho phép (Hỏi ở trên ***).
            locationManager.requestLocationUpdates(locationProvider, MIN_TIME_BW_UPDATES,
                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
            // Lấy ra vị trí.
            myLocation = locationManager
                .getLastKnownLocation(locationProvider);
            if (myLocation == null) {
                myLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
        }
        // Với Android API >= 23 phải catch SecurityException.
        catch (SecurityException e) {
            Toast.makeText(getActivity(), "Show My Location Error: " + e.getMessage(),
                Toast.LENGTH_LONG)
                .show();
            Log.e(MYTAG, "Show My Location Error:" + e.getMessage());
            e.printStackTrace();
            return;
        }
        if (myLocation != null) {
            LatLng latLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
            myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)             // Sets the center of the map to location user
                .zoom(15)                   // Sets the zoom
                //.bearing(180)                // Sets the orientation of the camera to east
                .build();                   // Creates a CameraPosition from the builder
            myMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            // Thêm Marker cho Map:
            MarkerOptions option = new MarkerOptions();
            option.title("Vị trí của bạn");
            option.snippet("....");
            option.position(latLng);
            option.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
            Marker currentMarker = myMap.addMarker(option);
            currentMarker.showInfoWindow();
            String duongdan =
                duongdan1 + myLocation.getLatitude() + "," + myLocation.getLongitude() + duongdan2;
            //Log.d("ketqua",duongdan);
            if (isOnline()) {
                DownLoadDL downLoadDL = new DownLoadDL();
                downLoadDL.execute(duongdan);
            } else {
                Toast.makeText(getActivity(),
                    "Không có kết nối mạng, vui lòng bật kết nối và thử lại!", Toast
                        .LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getActivity(), "Không thể tìm được vị trí của bạn", Toast.LENGTH_SHORT)
                .show();
            //  Log.i(MYTAG, "Location not found");
        }
    }

    @Override
    public void onClick(View v) {
        myMap.clear();
        showMyLocation();
    }

    public class DownLoadDL extends AsyncTask<String, Void, String> {
        StringBuilder dulieu;

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String dong = "";
                dulieu = new StringBuilder();
                while ((dong = bufferedReader.readLine()) != null) {
                    dulieu.append(dong);
                }
                bufferedReader.close();
                reader.close();
                inputStream.close();
                httpURLConnection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return dulieu.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ParseDuLieuJson parseDuLieuJson = new ParseDuLieuJson(s);
            list = parseDuLieuJson.layDiaChi();
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    LatLng latLng = new LatLng(Double.parseDouble(list.get(i).getLatitude()),
                        Double.parseDouble(list.get(i).getLongtitude()));
                    MarkerOptions option = new MarkerOptions();
                    option.title(list.get(i).getTenNhaThuoc());
                    option.snippet(list.get(i).getDiaChi());
                    option.position(latLng);
                    option.icon(
                        BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    myMap.addMarker(option);
                }
            }
        }
    }

    private Boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context
            .CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.isConnected()) {
            return true;
        }
        return false;
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }
}
