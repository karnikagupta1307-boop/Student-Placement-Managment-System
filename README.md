# NMIMS Hyderabad Placement Portal

A Java and XML Android placement portal prototype for the NMIMS Hyderabad student and placement office workflows. The app uses the institution's red and white visual theme and runs without a backend.

## Technologies

- Java 11 and Android SDK (min SDK 24)
- AndroidX AppCompat, Material Components, Activity, and ConstraintLayout
- Native Android views and XML layout resources
- Local in-memory demonstration repositories; no Firebase, database, or API keys

## Application flow

`SplashActivity` opens briefly and hands off to the branded landing page in `WelcomeActivity`. The landing page offers distinct Student Login, student registration, and Administrator Login actions. Students open the Student Panel; the separate administrator route opens the local Admin demonstration workspace. Authentication and registration are presentation flows only. Passwords are not persisted, and demo entry is not authentication or role authorization.

## Student Panel

- Dashboard with profile completion, drive and application summaries, and quick navigation
- Profile screens and the existing 12-step profile wizard
- Editable work experience and internship history
- Local document and image selection through Android's system document picker
- Placement drive search and eligibility preview, job details, and a locally recorded demo application
- Application tracking, sample interviews, offers, notifications, and profile preview
- Projects require only a title and GitHub repository URL

The wizard keeps its current in-session progress in `DemoSession`. Selected files remain local content URIs and are not uploaded. Opportunity, application, interview, offer, and notification records are examples, not live university or employer data.

## Admin Panel

The local checkout contains a separate demonstration Admin workspace: student directory search/profile summary, company create/view/edit/delete with recruiter contact fields, placement drive create/view/edit/publish/unpublish/close/delete, application status updates, fixed sample interview/result examples, summary reports, and demo settings. These files are present in the current working tree but are not in the sole initial Git commit. No separate branch or history containing Nandini's implementation is available locally, and the repository page could not be retrieved during inspection. Therefore, this workspace is not claimed to be Nandini's original implementation. If her code is maintained elsewhere, integration needs that project's source checkout or an accessible branch/commit.

Admin limitations: student records and interview data are fixed demo samples; student search has no advanced filters; application review uses sample eligibility information and does not include a connected student profile record; results/offer status examples are informational; no official placement statistics can be derived.

Admin entry asks for an email to reach the demo workspace. The password field is not validated, and no administrator credentials are embedded or saved. A production integration must add server-side authorization before exposing administrative operations.

## Project structure

```text
app/src/main/java/com/example/placement/
  ScreenActivity.java             Student screens and wizard flow
  ProfileFeatureActivity.java     Profile detail, document, and preview features
  BaseActivity.java               Shared visual components and navigation
  DemoSession.java                In-memory student presentation state
  MockDataProvider.java           Student demo data access point
  *Service/*Repository.java       Authentication, profile, opportunity,
                                  application, notification, and admin contracts
  AdminDemoRepository.java        Local admin presentation data
  Admin*Activity.java             Separate admin entry, dashboard, and management screens
app/src/main/res/layout/           Activity root layouts
app/src/main/res/drawable/         Local icons and shared backgrounds
app/src/main/res/values/           Theme, palette, and app strings
```

The `AuthenticationService`, `StudentProfileRepository`, `OpportunityRepository`, `ApplicationRepository`, `NotificationRepository`, and `AdminRepository` interfaces define backend handoff contracts. The current screens use local presentation state through `MockDataProvider` and `DemoSession`; the backend developer will wire repository implementations into those screens. Intended Firebase collections are `students` (SAP ID and profile fields), `companies`, `placementDrives`, `applications`, `interviews`, `results`, `offers`, and `notifications`, keyed by stable document IDs and linked by student/drive/company IDs. Firebase Authentication should own identity and email verification; an administrator role must be checked against a pre-authorized server-controlled record. Store resume, profile photo, and certification objects in Firebase Storage and persist metadata/paths in the relevant student profile. Document selection currently retains local content URIs only. Do not reuse the demonstration login behavior for production access.

## Run and build

Open the project in Android Studio, sync Gradle, and run the `app` configuration on a device or emulator. To build a debug APK from the project root:

```powershell
.\gradlew.bat assembleDebug
```

The build command must be run in an environment with the configured Gradle 9.4.1 distribution available and the Android SDK installed.
