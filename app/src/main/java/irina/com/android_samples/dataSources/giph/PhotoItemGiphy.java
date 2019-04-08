package irina.com.android_samples.dataSources.giph;

import java.util.Map;

import irina.com.android_samples.interfaces.PhotoItem;

public class PhotoItemGiphy implements PhotoItem {

    String username;
    Map<String, Map<String, String>> images;

    @Override
    public String getImgUrl() {
        return images.get("downsized_medium").get("url");
    }

    @Override
    public String getUserName() {
        return username;
    }

    @Override
    public String getLocation() {
        return "Not provided";
    }

}
