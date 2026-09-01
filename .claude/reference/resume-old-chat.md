# Resuming an old/stale session

**When**: You're picking a session back up — could be the same day, could
be a few days later — and need to decide whether to resume it or start
fresh.

## Decide: resume vs. `/clear`

- **Resume** if: it's the same feature/bug slice, less than ~2 days old,
  and you remember roughly what was in progress (e.g. "finish the Counter
  feature ViewModel wiring").
- **Fresh `/clear`** if: the topic has changed, the session was long and
  meandering, or you're not sure what state the code is actually in —
  re-deriving from the code is cheaper than dragging forward a confused
  session.

## What to do when resuming

1. Don't assume Claude remembers exact file state — code may have changed
   since (by you, in Android Studio, or via git). Say what changed, if
   anything, since the last message.
2. Give a one-line recap of the goal and the last known step, e.g.:
   > "Resuming the Counter feature — domain/use case/repo impl were done,
   > we were mid-way on the ViewModel."
3. Ask Claude to confirm current file state before continuing (a quick
   `git status`/`git diff` or a targeted Read) rather than trusting stale
   memory of what was written — this catches drift from manual edits.
4. If the prior session ended on a build failure, restate whether it's
   still failing — don't assume it was fixed just because the session
   ended.

## Avoid

- Don't resume a session that was already large/compacted multiple times —
  start fresh instead; re-deriving from this small codebase is fast.
- Don't silently continue assuming nothing changed — this repo can be
  edited outside of Claude (Android Studio, manual XML edits).

## Example (this project)

> "Resuming from yesterday: we added the Counter domain layer and repo
> impl, but hadn't touched GreetingViewModel-equivalent CounterViewModel
> yet. Check current state of app/src/main/java/.../presentation/ before
> continuing."
