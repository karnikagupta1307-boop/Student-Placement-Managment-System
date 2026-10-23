package com.example.placement;

import java.util.List;

public interface ApplicationRepository {
    interface Callback<T> { void onComplete(T value, String errorMessage); }
    void loadApplications(Callback<List<PlacementApplication>> callback);
    void submitApplication(PlacementApplication application, Callback<PlacementApplication> callback);
}
