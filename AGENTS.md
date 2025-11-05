# Agent Guide – HoldThatThought

This file guides code assistants working in this repository. Follow these rules when modifying UI, navigation, and assets.

Scope: Entire repository.

## Coding Style & Structure
- Jetpack Compose + Material 3. Prefer composables and theming over XML views.
- Keep changes focused and minimal. Respect current naming and file locations.
- Add new screens under `app/src/main/java/.../screens/` and declare a route in `navigation/Screen.kt`.
- Reuse components from `ui/components/` and tokens from `ui/theme/`.

## Design Fidelity Rules
- Colors, shapes, and typography come from `ui/theme`. Do not hardcode unless adding a token.
- Buttons: use `PrimaryButton` / `SecondaryButton` (44dp height).
- Chips: use `TagChip` (e.g., Skip).
- Use `ImageFromDrawableName` for drawable resources and `AvatarFromDrawableName` for circular avatars.

## Navigation & Back Button Policy
- Do NOT add a back button to screens that already have “Skip” (Onboarding, AI Question, AI Result).
- Add a back button to: Detail (6:947), Register, and Settings (9:1333).
- Wire back via `navController.navigateUp()` only; avoid hard-coded routes for back.
- When adding screens, define clear callbacks (e.g., `onPrimary`, `onCancel`) and handle them in `MainActivity`.

## Assets
- All PNG assets live in `app/src/main/res/drawable-nodpi/`.
- The logo must be a VectorDrawable or PNG named `logo_hold_that_thought` in `res/drawable`.
- Do NOT add raw SVG fallbacks. If a new logo is needed, import through Android Studio’s Vector Asset tool.
- Use `tools/import-assets.sh` to copy files into `drawable-nodpi` with canonical names (see Developers guide Assets section).

## Figma & External Tools
- Prefer code derived from the Figma file, but MCP rate limits can apply.
- If you cannot fetch a node, proceed with placeholders but keep routes and callbacks stable.
- When copying content, keep text and layout consistent with the design.

## Previews & Runtime
- Avoid leaving `@Preview` composables wired into runtime flows. The app should run via `MainActivity` with the full navigation.

## Documentation
- Any change to navigation, theme tokens, or assets must update `docs/DEVELOPERS.md` and `ASSETS.md` where relevant.

## Non-negotiables
- No raw SVG fallback for logos.
- Follow the back-button policy above.
- Keep asset filenames and locations consistent (see ASSETS.md).
