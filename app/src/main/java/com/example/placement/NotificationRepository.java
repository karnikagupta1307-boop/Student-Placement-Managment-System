package com.example.placement;

import java.util.List;

public interface NotificationRepository {
    interface Callback<T> { void onComplete(T value, String errorMessage); }
    void loadNotifications(Callback<List<NotificationItem>> callback);
    void markAsRead(String notificationId, Callback<Boolean> callback);
}
