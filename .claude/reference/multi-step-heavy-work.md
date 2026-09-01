# Multi-step / heavy work (big feature or refactor)

**When**: The task needs exploration + planning + execution + verification
— e.g. a feature with several moving pieces, or a refactor that touches
multiple layers (domain/data/presentation) at once.

## Order of operations

1. **Explore first** (if the current state isn't already clear): call
   `explorer` to map what exists today — relevant only once the codebase
   has grown past the single `greeting` slice, or before a cross-cutting
   change like a rename.
2. **Plan** in the main session using `/Plan` mode for anything touching
   architecture boundaries or multiple layers at once — this is where
   Opus-level reasoning might actually be warranted (see `CLAUDE.md`
   "Model & Agent Usage Rules"). Don't skip straight to code for
   multi-layer changes.
3. **Execute** with `feature-builder` for the actual domain → data →
   presentation implementation, following the `greeting` pattern.
4. **Verify** with `build-runner` — run `./gradlew assembleDebug` (and
   `./gradlew test` only if a test source set exists) after each
   meaningful chunk, not just at the very end, so failures are cheap to
   isolate.
5. **Review** with `code-reviewer` once the feature is functionally
   complete, before you consider it done — checks layering, correctness,
   and basic security.

## Parallelization

- `explorer` can run in parallel with planning if you already know two
  independent things need investigating (e.g. "how is Greeting wired" and
  "what does the current AndroidManifest look like") — launch both explorer
  calls together.
- Don't parallelize `feature-builder` and `build-runner` against the same
  files — execution and verification are sequential by nature here (verify
  after writing, not concurrently).
- `code-reviewer` should run after `feature-builder` finishes, not
  alongside it — it needs the finished diff.

## Avoid

- Don't jump straight to `feature-builder` for something touching multiple
  layers without a plan — this template's whole point is a strict layering
  discipline, and skipping the plan step is how that discipline erodes.
- Don't skip `build-runner` verification and call the feature "done" once
  `feature-builder` finishes writing files.
- Don't reach for Opus by default for "big" work — most multi-step feature
  work here is still Sonnet-shaped; Opus is only for genuine architecture
  reconsideration.

## Example (this project)

Adding a "Settings" feature that reads/writes a preference and reflects it
in the UI, touching all three layers plus `AndroidManifest.xml`:

1. `explorer` — confirm no existing settings-related code
2. `/Plan` — sketch the entity/repository/use-case/ViewModel shape
3. `feature-builder` — implement it following the `greeting` pattern
4. `build-runner` — `./gradlew assembleDebug`
5. `code-reviewer` — final check before commit
