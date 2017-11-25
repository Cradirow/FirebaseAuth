package Modules;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by HYB on 2017. 11. 26..
 */

public class Routing {
    public Distance distance;
    public Duration duration;
    public String endAddress;
    public LatLng endLocation;
    public String startAddress;
    public LatLng startLocation;

    public List<LatLng> points;
}
