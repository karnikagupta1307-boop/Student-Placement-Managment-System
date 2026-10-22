# PlaceMate — Student Placement Portal

This Java and XML Android app is a presentation UI with local mock state only. It has no Firebase, cloud storage, remote API, push notification, or real authentication integration. Drive, application, interview, offer, and notification records are sample data and are not verified vacancies or outcomes.

## Run

Open this folder in Android Studio, allow Gradle sync, choose the `app` configuration, and run on an emulator or Android device (min SDK 24). The project wrapper uses Gradle 9.4.1. This environment could not download that distribution, so an APK build could not be verified here.

## Demo login

- Email: `student@college.edu`
- Password: `Student123`

Registration, email verification, password reset, applications, document status, and profile progress are simulated locally. The document picker opens real local files but stores only display metadata in the current process; it does not upload them.

## Backend handoff

- Replace the credential check and `DemoSession.loggedIn` assignment in `ScreenActivity.login()` with Firebase Authentication.
- Replace the registration and verification flows in `ScreenActivity.register()` and `ScreenActivity.verify()` with Firebase account creation and email verification.
- Replace `DemoSession` / `MockDataProvider` access with Firestore-backed repositories for student profile, drives, applications, interviews, offers, and notifications.
- Replace `ScreenActivity.pickFile()` and `DemoSession.documents` metadata handling with Cloud Storage upload and Firestore document metadata.
- The profile wizard currently keeps its values in in-memory `DemoSession.profile`; save these fields through the student repository when integrating the backend.

All packages retain `com.example.placement`, application ID `com.example.placement`, min SDK 24, and the existing Gradle plugin/dependency setup.
