package api.interfaces;

public interface Application {
    void forceStop();
    void clearData();
    Object open();
    String getPackageID();
    String getActivityID();
    String getAppVersion();

//    void pressDeviceButton(String BUTTON_TYPE);
}
