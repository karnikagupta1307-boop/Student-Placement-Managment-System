package com.example.placement;

import java.util.List;

public interface OpportunityRepository {
    interface Callback<T> { void onComplete(T value, String errorMessage); }
    void loadOpportunities(Callback<List<PlacementDrive>> callback);
    void loadOpportunity(String id, Callback<PlacementDrive> callback);
}
