package br.com.sutel.mymaps;

import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.security.PrivateKey;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        //Requisicao assincriona - o que permite fazer outras cargas de dados em paralelo ao carregamento do mapa
        mapFragment.getMapAsync(this);
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    //método chamado após o mapa ser carregado
    public void onMapReady(GoogleMap googleMap) {



        // Adicionando evento de clique no mapa
        //OnCLickListener

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //capturamos lat e lon para criar o toast
                Double lat = latLng.latitude;
                Double lon = latLng.longitude;
                //criamos o Toast
                Toast.makeText(MapsActivity.this,
                        "EPTC adicionadalo0 Lat:" + lat + " Long:" + lon,
                        Toast.LENGTH_SHORT).show();

                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Local Clicado")
                        .snippet("EPTC")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.eptc))
                );
                //Iniciamos a classe polyline
                PolylineOptions poli = new PolylineOptions();
                LatLng poa = new LatLng(-30.0277, -51.2287);
                poli.add(poa);
                poli.add(latLng);
                poli.color(Color.RED);
                poli.width(20);
                mMap.addPolyline(poli);
                LatLng temp = latLng;

            }
        });


        //OnLongCLickListener
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                //capturamos lat e lon para criar o toast
                Double lat = latLng.latitude;
                Double lon = latLng.longitude;

                //criamos o Toast
                Toast.makeText(MapsActivity.this,
                        "ObservaMOB Adicionado Lat:" + lat + " Long:" + lon,
                        Toast.LENGTH_SHORT).show();

                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Local Clicado")
                        .snippet("ObservaMOB")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.om))

                );

            }
        });

        // Add a marker in poa and move the camera
        LatLng poa = new LatLng(-30.0277, -51.2287);
        mMap.addMarker(new MarkerOptions()
                .position(poa)
                .title("PMPA")
                //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.pmpa))
        );
        // Add a marker in poa2 and move the camera
        LatLng poa2 = new LatLng(-30.034054,-51.2218582);
        mMap.addMarker(new MarkerOptions()
                .position(poa2)
                .title("Porto Alegre")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))

        );
        //Circle Options
        CircleOptions circulo = new CircleOptions();
        LatLng eptc = new LatLng(-30.0473819,-51.2197398);
        circulo.center(eptc);
        circulo.radius(500);//500 metros
        circulo.strokeWidth(10);
        circulo.strokeColor(Color.GRAY);
        circulo.fillColor(Color.argb(80, 255, 153, 0));
        mMap.addCircle(circulo);

        //PolygonOptions
        PolygonOptions poligono = new PolygonOptions();
        poligono.add(new LatLng(-30.04715457581213, -51.217392598241304));
        poligono.add(new LatLng(-30.046950261018317, -51.21569744214389));
        poligono.add(new LatLng(-30.047498195196766, -51.215643797963594));
        poligono.add(new LatLng(-30.047702508860354, -51.21721020802829));

        mMap.addPolygon(poligono);



        //mMap.moveCamera(CameraUpdateFactory.newLatLng(poa));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(poa, 14));
    }


}
