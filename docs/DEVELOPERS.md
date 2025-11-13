# HoldThatThought – Developer Guide

This document describes the current app state, design-to-code mapping, structure, interactions, and how to extend the UI for backend integration.

## Overview
- Kotlin + Jetpack Compose + Material 3, single-activity navigation.
- Ten Figma nodes implemented as screens, plus Register, AI Question, and AI Result.
- Theme tokens and shared components mirror the Figma design; assets are local PNGs in `drawable-nodpi` and a vector logo in `drawable`.

## Tech Stack
- Compose Material 3, Navigation Compose
- Kotlin 2.x, AGP 8.x (see Gradle for exact versions)
- Coil (images) – SVG fallback removed by policy

## Project Structure
- `app/src/main/java/com/example/comp4521_holdthatthought`
  - `MainActivity.kt` – NavHost and route wiring
  - `navigation/Screen.kt` – All app routes
  - `screens/` – Screen composables
  - `ui/components/` – Reusable UI components (buttons, chips, logo, image loader)
  - `ui/theme/` – Color tokens, Shapes, Typography, Theme
- `app/src/main/res/drawable-nodpi/` – PNG assets (covers, illustrations, avatar)
- `app/src/main/res/drawable/` – VectorDrawable(s), e.g. `logo_hold_that_thought.xml`
- `tools/import-assets.sh` – Helper to copy assets into `drawable-nodpi`
- `ASSETS.md` – Asset filenames and import tips

## Design → Screen Mapping
- Figma file: 3k8vGCkxEE5pbd0Gu71S35
- Node → Route → File
  - 4:3891 “1.3 Onboarding 5” → `node_4_3891` → `screens/Node4_3891Screen.kt`
  - 4:4106 “1.2 Onboarding 3” → `node_4_4106` → `screens/Node4_4106Screen.kt`
  - 4:4573 “1.1 Onboarding 3” → `node_4_4573` → `screens/Node4_4573Screen.kt`
  - 13:1155 “1.3 Onboarding 8” → `node_13_1155` → `screens/Node13_1155Screen.kt`
  - 5:812 “5 Menu” (Home grid) → `node_5_812` → `screens/Node5_812Screen.kt`
  - 6:947 “8.2 Detail News & Promo” → `node_6_947` → `screens/Node6_947Screen.kt`
  - 8:1053 (Article detail stub) → `node_8_1053` → `screens/Node8_1053Screen.kt`
  - 8:968 (Saved list) → `node_8_968` → `screens/Node8_968Screen.kt`
  - 9:1287 “9.1 My Account” → `node_9_1287` → `screens/Node9_1287Screen.kt`
  - 9:1333 “Settings” → `node_9_1333` → `screens/Node9_1333Screen.kt`
  - Register → `register` → `screens/RegisterScreen.kt`
  - AI Question → `ai_question` → `screens/AIQuestionResultScreens.kt`
  - AI Result → `ai_result` → `screens/AIQuestionResultScreens.kt`

## Navigation Flow (current)
- Start destination: `node_4_3891` (Onboarding 1)
  - 4:3891 (Get Started) → 4:4106
  - 4:4106 (Get Started) → 4:4573
  - 4:4573 (Continue) → Home
  - Any Onboarding “Sign in” → Register
  - Any Onboarding “Skip” → Home
- Home (bottom nav: Home, Articles, AI, Profile)
  - Home/Articles: header with Search/Notifications, tabs (All/Folder1/Folder2), 2-col grid
  - Grid item → Detail (6:947)
  - Detail (6:947): Back button; “AI Questions” → AI Question
  - AI tab: “Start” → AI Question → Submit → AI Result → Next/Cancel → back
  - Profile tab: My Account → “Settings” button → Settings (back button)
  - Register: Back button → previous screen; “Register” → Home

## Back Button Policy
- No back button on pages with “Skip” (Onboarding, AI Question, AI Result).
- Back button on: Detail (6:947), Register, Settings (9:1333).

## Assets
- Local PNGs live in `app/src/main/res/drawable-nodpi/`.
- Vector wordmark: `app/src/main/res/drawable/logo_hold_that_thought.xml` (preferred), or PNG fallback `logo_hold_that_thought.png`.
- Logo loader uses `logo_hold_that_thought` from `drawable`; if not present, it falls back to a text logo (no raw SVG fallback).
- Helper script: `tools/import-assets.sh [source_dir]` copies images into `drawable-nodpi`.

Required filenames (place in your source dir and run the script)
- `read_with_intention.png`
- `reflect_and_remember.png`
- `build_gentle_habit.png`
- `img_register_illustration.png`
- `cover_davinci.png`
- `cover_carrie.png`
- `cover_good_sister.png`
- `cover_waiting.png`
- `img_detail_hero.png`
- `img_avatar_default.png`
- `logo_hold_that_thought.png` (only if you don’t have the vector)

Notes
- Use `-nodpi` for bitmaps so Compose can size them without Android rescaling.
- Prefer the Vector Asset for the logo (Android Studio → Vector Asset). Keep the file name `logo_hold_that_thought`.

## Theme & Components
- Tokens: `ui/theme/Color.kt` (Primary/500, Primary/50, Gray/900…), `Shapes.kt` (6/8/12/16/24dp), `Type.kt` (weights/sizes tuned), `Theme.kt`.
- Components in `ui/components/`:
  - `Buttons.kt` – Primary/Secondary (44dp height, rounded corners)
  - `TagChip.kt` – pill-like text chip (used for Skip)
  - `ProgressDots.kt` – onboarding progress indicators
  - `Images.kt` – `ImageFromDrawableName`, `AvatarFromDrawableName`
  - `Brand.kt` – `Logo()` uses vector/PNG drawable

## Interaction Details
- Onboarding: titles, body text, progress dots, Skip/Primary/Secondary per Figma.
- Home: header row; tabs (visual only for now); card grid uses your covers.
- Detail: actions list; “AI Questions” opens AI flow; back button in top-left.
- AI Question: Skip (top-left TagChip) → cancel; Submit → AI Result.
- AI Result: “Correct” pill; Next/Cancel wired.
- My Account: change picture (placeholder), inputs w/ password eye toggle; Save Changes; Settings button.
- Settings: Notifications/Daily Reminders/Dark Mode toggles; back button.

## Backend Integration Points
- Replace screen state with ViewModels; all screens expose callbacks:
  - Onboarding: `onPrimary`, `onSecondary`, `onSkip`
  - Register: `onDone`, `onBack`
  - Home grid: `onItemClick`
  - Detail: `onBack`, `onAIQuestions`
  - AI: `onSubmit`, `onCancel`
  - Profile: `onOpenSettings`
  - Settings: `onBack`
- Swap local state with repository calls; keep route signatures stable.

## Build & Run
- Ensure Android Studio/Gradle sync succeeds.
- Run the `app` configuration (MainActivity). App starts at Onboarding.
- Minimum SDK 24; Target SDK 36.

## Known Gaps / TODOs
- Home tabs (All/Folder1/Folder2) are visual-only; wire to filters when data exists.
- Register and My Account do not persist data yet.
- Some body text on content screens is placeholder (await content source).
