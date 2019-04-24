package irina.com.android_samples.dataSources.unsplash;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Map;

import irina.com.android_samples.interfaces.PhotoItem;

public class PhotoItemUnsplash implements PhotoItem {

    User user;
    Map<String, String> urls;

    @Override
    public String getImgUrl() {
        return urls.get("regular");
    }

    @Override
    public String getUserName() {
        return user.name;
    }

    @Override
    public String getLocation() {
        return StringUtils.isBlank(user.location) ? "Not provided" : user.location;
    }

    class User implements Serializable {

        String name;
        String location;

    }

}
