package irina.com.android_samples.interfaces;

import java.io.Serializable;

public interface PhotoItem extends Serializable {

    String getID();
    String getImgUrl();
    String getUserName();
    String getLocation();

    // ORM
    void saveToDatabase();
    void deleteFromDatabase();
    boolean isSavedToDatabase();

}
