package irina.com.android_samples.interfaces;

public interface NetworkingManager {

    void getImages(NetworkingManagerResult result);
    void fetchNewItemsFromPosition(int lastPosition, NetworkingManagerResult result);

}
