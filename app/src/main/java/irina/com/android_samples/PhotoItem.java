package irina.com.android_samples;

import java.util.Map;

public class PhotoItem {

    User user;
    Map<String, String> urls;

    public String getImgUrl() {
        return urls.get("regular");
    }

    public String getName() {
        return user.name;
    }

    class User {

        String name;

    }

}
