package com.example.googlemaps;

import androidx.fragment.app.FragmentActivity;

import android.hardware.Camera;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivityApiKey extends FragmentActivity implements /*OnStreetViewPanoramaReadyCallback*/ OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerDragListener {

    private GoogleMap mMap;

    //StreetViewPanorama streetViewPanorama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_api_key);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        SupportStreetViewPanoramaFragment streetViewPanoramaFragment =
//                (SupportStreetViewPanoramaFragment) getSupportFragmentManager()
//                        .findFragmentById(R.id.streetviewpanorama);
//        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);
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

    //region Interfaz: OnMapReadyCallback
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Eventos mapa
        mMap.setOnMapClickListener(this);

        //Eventos marcador
        mMap.setOnMarkerDragListener(this);

        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); //Todo: tipo de mapa
        mMap.setTrafficEnabled(true);

        //"geo:25.659308,-100.186211?z=23"
        // Add a marker in Sydney and move the camera
        LatLng casa = new LatLng(25.659308, -100.186211);

        mMap.addMarker(new MarkerOptions()
                .position(casa)
                .title("Marker in casa")    //Aparecerá en info window cuando el usuario de clic sobre el marker
                .snippet("Está es mi casa todos son bienvenidos")    //Más info sobre el objeto
                .draggable(true)    //Hold marker(long clic) and you can drag it
//                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.heart))
        );

        mMap.moveCamera(CameraUpdateFactory.newLatLng(casa));

        //Damos un estilo al mapa creando una carpeta raw:
        try {
            boolean success = mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json_maps));
            if(!success) Toast.makeText(this, "Style map failed: 1", Toast.LENGTH_SHORT).show();
        }catch(Exception ex){
            Toast.makeText(this, "Style map failed: 2", Toast.LENGTH_SHORT).show();
        }
    }


    //endregion

    //region Interfaz: OnStreetViewPanoramaReadyCallback
//    @Override
//    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
//        this.streetViewPanorama = streetViewPanorama;
//
//        LatLng casa = new LatLng(25.659308, -100.186211);
//        streetViewPanorama.setPosition(casa);
//    }
    //endregion

    //Eventos sobre el mapa
    @Override
    public void onMapClick(LatLng latLng) {
        mMap.addMarker(
                new MarkerOptions()
                .position(latLng)
                .title("Nuevo marker")
        );

        //Para mover la camara y tener un UX cool
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    //Eventos sobre markers
    @Override
    public void onMarkerDragStart(Marker marker) {
        marker.hideInfoWindow();
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        //Cada movimiento donde se desplace el marker se ejecutará este método
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        LatLng posicion = marker.getPosition();
        marker.setSnippet(posicion.latitude + ", " + posicion.longitude);
        marker.showInfoWindow();
    }
}