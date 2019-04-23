package irina.com.android_samples.interfaces;

import java.io.Serializable;

public interface PhotoItem extends Serializable {

    String getImgUrl();

    String getUserName();

    String getLocation();

}
