# MyApp — Android Java Template

A native Android app (Java), organized as MVVM + Clean Architecture:
`domain` (entities/repository interfaces/use cases, no Android deps) →
`data` (repository implementations) → `presentation` (Activity + ViewModel).
The `greeting` slice (`domain/.../Greeting.java` → `presentation/GreetingViewModel.java`)
is the worked example — copy its shape for new features. This is currently a
**template**: small codebase, no tests wired up yet, meant to be renamed and
grown into a real app.

CI/CD lives only on the `deployment` branch, never `main` — see
[docs/BRANCH-PROTECTION.md](docs/BRANCH-PROTECTION.md). Never merge
`deployment` back into `main`.

## Coding conventions (detected from codebase)

- **Layering is strict**: `domain/` must never import `android.*` or
  `androidx.*`. `presentation/` (Activity) must never call a repository or
  use case directly — only observe the ViewModel's `LiveData`. `data/`
  implements `domain` repository interfaces.
- ViewModels call use cases on a background executor and expose results via
  `LiveData`; keep that pattern for new features rather than calling use
  cases synchronously on the main thread.
- Build files are Gradle Kotlin DSL (`build.gradle.kts`), not Groovy.
- Java 17 (`sourceCompatibility`/`targetCompatibility`), minSdk 24, targetSdk/compileSdk 34.
- Package root is `com.example.myapp` — a placeholder. Once renamed (see
  README "Rename before you start developing"), match that new package
  everywhere: `build.gradle.kts` namespace/applicationId, the folder path
  under `app/src/main/java/`, every `package`/`import` statement, and
  `strings.xml` `app_name`.
- No test source sets exist yet (no `app/src/test` or `app/src/androidTest`,
  no JUnit/Mockito in `build.gradle.kts`). Don't assume a test runner is
  available — check before recommending `./gradlew test`.

## Model & Agent Usage Rules

Decide the agent/model from the task shape below — don't ask the user each
time. If genuinely unsure which agent fits, ask in one line rather than guessing.

| Task shape | Agent | Model | Why |
|---|---|---|---|
| "Where is X / how does Y work / find the class that…" | `explorer` | Haiku | Pure search — grep/glob/read, no judgment needed |
| Reviewing a diff/PR for architecture leaks, bugs, security | `code-reviewer` | Sonnet | Needs real judgment but not deep architecture rework |
| Adding a new feature (entity → repo → use case → ViewModel → wiring) | `feature-builder` | Sonnet | Standard, well-patterned coding work — the `greeting` slice is the template |
| Gradle build/compile errors, dependency issues, `./gradlew` tasks | `build-runner` | Sonnet | Mechanical-to-moderate diagnosis against known Gradle/AGP errors |
| Renaming the template package/applicationId for a new project | main session (no subagent) | Sonnet | One-time task, not repeated within a project's lifetime |
| Rethinking the MVVM/Clean Architecture boundaries themselves | main session, `/Plan` mode | Opus (only if truly needed) | Rare; this template's architecture is already decided and documented above — don't reach for Opus for routine feature work |

Rules:
- **Never default to `general-purpose` for a simple search or lookup** — use
  `explorer` (or Grep/Glob directly if it's a single lookup you can do
  yourself in the main session).
- **Don't reach for a bigger model "to be safe."** Sonnet handles nearly
  everything in this project; Opus is for genuine architecture-level
  reasoning only, and even then prefer `/Plan` in the main session over a
  dedicated subagent.
- **Don't spawn a subagent for a one-off, single-file question** — answer it
  directly in the main session (see `.claude/reference/quick-task.md`).
- Before creating any *new* subagent beyond the ones in `.claude/agents/`,
  ask: will this genuinely repeat across sessions in this project? If not,
  don't create it.

## Context Management Rules

This is a small template project — most sessions are short (a feature slice,
a build fix, a review). Typical guidance:

- **`/clear`** at the start of unrelated work (e.g. finished the `greeting`
  feature, now starting an unrelated bug fix) — don't carry over stale
  feature context.
- **`/compact`** once a session has done 2-3+ rounds of edit→build→fix
  cycling on the *same* feature and is still going — keep the feature intent
  and file list, drop the intermediate build-error noise.
- **Resume (not `/clear`)** when picking the same feature back up within a
  day or two and the plan/progress is still valid — see
  `.claude/reference/resume-old-chat.md`.
- **Never let a single session span more than one feature slice** end to
  end without compacting in between — this codebase is small enough that
  bloated context costs more than it helps.

See `.claude/reference/` for situation-specific cheat sheets, indexed in
`.claude/reference/README.md`.
