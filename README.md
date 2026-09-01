# MyApp

A native Android app (Java), organized as MVVM + Clean Architecture.

## Architecture

app/src/main/java/com/example/myapp/
  domain/          entities, repository interfaces, use cases — no
                    dependency on Android or AndroidX
  data/             repository implementations
  presentation/     MainActivity + ViewModel (the "VM" in MVVM) — the
                    ViewModel calls use cases on a background executor
                    and exposes a LiveData; the Activity only observes
                    that LiveData and never calls use cases or
                    repositories directly

The `greeting` feature (domain/.../Greeting.java through
presentation/GreetingViewModel.java, wired into MainActivity) is a
worked example showing the full path: Activity → ViewModel → use case →
repository. Copy that pattern for new features; delete it once you've
got real features in place.

## Rename before you start developing

- `app/build.gradle.kts` — `namespace` and `applicationId`
- Move the `com/example/myapp` package folder under
  `app/src/main/java/` to match, and update every `package` declaration
- `app/src/main/res/values/strings.xml` — `app_name`
- `settings.gradle.kts` — `rootProject.name`

## Local development

    ./gradlew assembleDebug

(or open the folder in Android Studio)

## Branches

- `main` — the app.
- `deployment` — the only branch with `.github/workflows/`. Pushing here
  builds debug/release, publishes a GitHub Release, and emails the
  download link.

To trigger a build:

    git checkout deployment
    git merge main
    git push

Never merge `deployment` back into `main` — that would carry the
workflow file with it.

## Required secrets (repo Settings → Secrets and variables → Actions)

Only read on the `deployment` branch.

| Secret | Needed for |
|---|---|
| `ANDROID_KEYSTORE_BASE64`, `ANDROID_KEYSTORE_PASSWORD`, `ANDROID_KEY_ALIAS`, `ANDROID_KEY_PASSWORD` | Signed release builds. Without these, only a debug APK is built (release build is skipped, not failed). |
| `RESEND_API_KEY` (secret) | Emailing the build link. |
| `MAIL_TO` (secret or variable) | Who receives the build email. |

See [`docs/BRANCH-PROTECTION.md`](docs/BRANCH-PROTECTION.md) for how to
lock `.github/workflows/` to the `deployment` branch only.
