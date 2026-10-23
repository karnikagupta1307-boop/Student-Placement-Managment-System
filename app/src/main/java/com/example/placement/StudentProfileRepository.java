package com.example.placement;

public interface StudentProfileRepository {
    interface Callback<T> { void onComplete(T value, String errorMessage); }
    void loadCurrentProfile(Callback<Student> callback);
    void saveProfile(Student profile, Callback<Student> callback);
}
