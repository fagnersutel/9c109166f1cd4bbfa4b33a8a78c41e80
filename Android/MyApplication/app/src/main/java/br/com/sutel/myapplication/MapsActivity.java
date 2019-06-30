package br.com.sutel.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String[] permissoes = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    private LocationManager locationManager;
    private LocationListener locationListener;





    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // manter tela desbloqueada
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //Validar permissoes
        Permissoes.validarPermissoes(permissoes, this, 1);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        // Requisição assíncriona - o que permite fazer outras cargas de dados em paralelo ao carregamento do mapa
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
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    //método chamado após o mapa ser carregado
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


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



        //objeto responsável por gerenciar a localizacao  do usuario
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        //Implementacao location listener
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //## datahora ##
                long date = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy H:m:s");
                String dateString = sdf.format(date);
                String dataatual = dateString;
                //## datahora ##


                Log.d("Localizacao", "onLocationChanged: " + location.toString());
                Double lat = location.getLatitude();
                Double lon = location.getLongitude();
                Double alt = location.getAltitude();
                //mMap.clear();
                LatLng PoA = new LatLng(lat, lon);
                mMap.addMarker(new MarkerOptions().position(PoA).title("Lat: " + lat + " Lon: " + lon).snippet("Altitude: " + alt + " Data:" + dataatual + " Mili:" + date));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(PoA, 15));

                Toast.makeText(MapsActivity.this,
                        " Data:" + dataatual + " Milisec:" + date + "\nLat: " + lat + " Lon: " + lon,
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };


        //ATENCAO
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10, locationListener);
                //locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 15000, 15, locationListener);
        }




    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //permissao negada
        for (int permissaoResultado : grantResults)
            if (permissaoResultado == PackageManager.PERMISSION_DENIED) {
                //alerta
                alertaValidacaoPermissao();
            } else if (permissaoResultado == PackageManager.PERMISSION_GRANTED) {
                //recuperar a localizacao do usuario
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10, locationListener);
                        //locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 15000, 15, locationListener);
                }
            }
    }

    private void alertaValidacaoPermissao(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissões Negadas");
        builder.setMessage("Para utilizar o app é necessário aceitar as permissões");
        builder.setCancelable(false);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
