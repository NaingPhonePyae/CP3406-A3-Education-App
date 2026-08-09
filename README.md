# Space Education App — Code Documentation

## Overview

Android education app for elementary–middle school learners about the basic facts of solar system
and exoplanets.

Built with

- **Package:** `com.example.a3_education_app`
- **Min SDK:** 24 · **Target / Compile SDK:** 37

---

## Screens (assignment mapping)

| Requirement         | Screen(s)                                                          | Route                                                           |
|---------------------|--------------------------------------------------------------------|-----------------------------------------------------------------|
| Landing page        | Home                                                               | `Home`                                                          |
| Activity / learning | Solar System, Lessons, Quizzes, Exoplanet Explore, Exoplanet Blitz | `SolarSystem`, `Lessons`, `Quiz`, `Exoplanets`, `ExoplanetQuiz` |
| Settings            | Settings                                                           | `Settings`                                                      |
| User statistics     | Statistics                                                         | `Statistics`                                                    |

Other screens: Favorites, planet/lesson/exoplanet details.

---

## Architecture

```text
SpaceEducationApplication
└── DefaultAppContainer
    ├── ExoplanetRepository
    │   └── Retrofit / NASA TAP
    ├── UserPreferencesRepository
    │   └── DataStore
    └── FavoritesRepository
        └── Room
```

- Manual DI via `AppContainer` (no Hilt)
- UI: Jetpack Compose + Material 3
- Navigation: single-activity `NavHost` in `SpaceEducationApp.kt`
- Theme: Compose `Color.kt` / `Theme.kt` / `Type.kt`

---

## Main modules

### `data/`

| File                                            | Role                                                          |
|-------------------------------------------------|---------------------------------------------------------------|
| `AppContainer.kt`                               | Provides repositories                                         |
| `SolarSystemDataSource.kt`                      | Hardcoded planets + local drawables                           |
| `LessonDataSource.kt`                           | Lessons related to celestial bodies                           |
| `QuizQuestionDataSource.kt`                     | Per-planet quiz questions                                     |
| `ExoplanetRepository.kt`                        | Fetches exoplanet TAP results                                 |
| `UserPreferencesRepository.kt`                  | High scores, dark theme, lesson/quiz completion, Blitz length |
| `FavoritesRepository.kt` + Room entities/DAO/DB | Favorite exoplanets                                           |

---

### `network/`

| File                     | Role         |
|--------------------------|--------------|
| `ExoplanetApiService.kt` | Retrofit API |
| `Exoplanet.kt`           | JSON model   |

Base URL: `https://exoplanetarchive.ipac.caltech.edu/`

---

### `ui/`

| Package       | Role                              |
|---------------|-----------------------------------|
| `home/`       | Landing shortcuts                 |
| `solar/`      | Planet list + detail              |
| `lessons/`    | Lesson list/detail + completion   |
| `quiz/`       | Planet quizzes + Exoplanet Blitz  |
| `explore/`    | Search/filter exoplanets + detail |
| `favorites/`  | Saved exoplanets                  |
| `settings/`   | Dark theme + Blitz question count |
| `statistics/` | Progress dashboard                |
| `theme/`      | Colors, typography, theme VM      |

---

## Key features

### Solar system + lessons

- Local planet data and images (`R.drawable.*`)
- Lesson detail: facts, NASA source URL, Mark complete, Take quiz

---

### Quizzes

- One quiz per planet (hardcoded questions)
- **Exoplanet Blitz:** builds questions from NASA TAP sample
- High scores + quiz-completed flags in DataStore
- Progress shown on quiz list and Statistics

---

### Exoplanet Explore

- Search name/host, year/radius filters, result limit
- Detail screen + favorite toggle (Room)

---

### Settings

- Dark theme (same DataStore key as top-bar toggle)
- Blitz question count: 5 / 10 / 15

---

### Statistics

- Lessons completed
- Quizzes completed
- Favorites count
- Best scores list

---

## Blitz data note

Blitz does not query the full archive. It uses roughly:

`select top 40 ... order by disc_year desc`

Then shuffles that pool and takes the Settings count (5/10/15).

---

## Persistence keys (DataStore)

- `high_score_<quizId>`
- `dark_theme`
- `lesson_completed_<lessonId>`
- `quiz_completed_<quizId>`
- `blitz_question_count`

Room DB: favorite exoplanets.

---

## Navigation map (Cupcake-style)

`SpaceScreen` enum in `SpaceEducationApp.kt`:

- Home → SolarSystem / Exoplanets / Favorites / Lessons / Quiz / Statistics / Settings
- SolarSystem → SolarDetail/{bodyId} → Quiz/{planetId}
- Lessons → LessonDetail/{lessonId} → Quiz/{planetId}
- Exoplanets → ExoplanetDetail/{name}
- Favorites → ExoplanetDetail/{name}
- Quiz → Quiz/{planetId} or ExoplanetQuiz

Detail routes use `startsWith` in `currentScreen` for the top bar title.

---

## Testing

- Unit tests under `app/src/test/...` with fakes:
    - `FakeExoplanetRepository`
    - `FakeUserPreferencesRepository`
    - `FakeFavoritesRepository` (if present)
- Instrumented UI tests under `app/src/androidTest/...`
---

## Resources

- Strings: `app/src/main/res/values/strings.xml`
- Planet images: `app/src/main/res/drawable/`
- Launcher icon: `mipmap-*` + `drawable/ic_launcher_*` (Image Asset Studio)
- XML `themes.xml` / `colors.xml`: system shell / template leftovers — Compose theme drives UI
  colors
---

## How to run

1. Open project in Android Studio
2. Sync Gradle
3. Run `app` on emulator or device (needs internet for exoplanet features)
---

## Credits / sources

- Planet facts researched from NASA Science pages (content inspiration; not scraped HTML)
- Exoplanet data: NASA Exoplanet Archive TAP API
- Course patterns: Android Basics with Compose
---

