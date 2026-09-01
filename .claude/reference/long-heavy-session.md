# Long / heavy session

**When**: You've been in the same session through several edit → build →
fix cycles, or a feature has grown beyond the single slice you started
with, and responses start feeling slower or less precise.

## Signs it's time to `/compact`

- You've gone through 2-3+ rounds of `build-runner` failures on the same
  feature and are still iterating.
- The conversation has drifted through multiple files across domain/data/
  presentation and earlier ones are no longer being referenced.
- You're about to switch from building the feature to reviewing/polishing
  it (a natural phase boundary).
- Claude starts re-reading files it already read earlier in the session,
  or re-explaining decisions already settled.

## What to do

1. Run `/compact`. Before/after, make sure the essentials survive:
   - The feature's goal and its final intended shape (which layers, what
     the ViewModel exposes)
   - Which files are done vs. still in progress
   - Any deliberate deviations from the `greeting` pattern, and why
2. Explicitly drop: intermediate Gradle error output, earlier failed
   attempts, exploratory questions already answered.
3. If the session has been compacted more than once already and is *still*
   growing, prefer `/clear` and restate the goal fresh — this is a small
   template codebase; re-deriving context is cheap compared to dragging a
   heavily summarized session further.

## Signs quality is degrading (don't just push through)

- Claude proposes re-implementing something that already exists in the
  `greeting` example instead of matching it.
- Suggested fixes ignore the MVVM/Clean Architecture boundary rules from
  `CLAUDE.md`.
- Responses get vaguer about which specific file/line is affected.

If you see these, that's a stronger signal to `/compact` or `/clear` than
to keep pushing in the same context.

## Avoid

- Don't wait until the session is unusably large to compact — do it at a
  natural phase boundary (build done → moving to review).
- Don't compact mid-way through a single atomic edit (e.g. mid-diagnosis of
  one build error) — finish that step first.
