package Modules;

import java.util.List;
/**
 * Created by HYB on 2017. 11. 26..
 */

public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Routing> route);
}
