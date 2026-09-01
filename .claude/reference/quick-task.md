# Quick task / small fix

**When**: A one-line change, a single-file question, a quick "what does
this do" — small enough that spinning up a subagent costs more than it
saves.

## What to do

Just ask directly in the main session. Examples of quick-task shape in
this project:

- "Change the app name string" → edit `strings.xml` directly
- "Why does GreetingViewModel use an executor instead of calling the use
  case directly?" → answer from reading the one file
- "Fix this null check in GreetingRepositoryImpl" → direct Edit
- "What Java version does this target?" → answer from `build.gradle.kts`

No agent needed — Read/Edit/Grep directly in the main session.

## Why no subagent

- Subagent overhead (spinning up, re-establishing context) costs more
  tokens than just doing a single-file edit inline.
- `explorer`/`feature-builder`/etc. exist for *repeated* or *multi-step*
  work, not one-off lookups or edits.

## Avoid

- Don't launch `explorer` to find a file you can Glob/Grep yourself in one
  call.
- Don't launch `feature-builder` for a one-line tweak inside an existing
  feature — that's not "adding a feature," it's an edit.
- Don't ask "should I use an agent for this?" for something this small —
  just do it.

## Example (this project)

> "The greeting string is hardcoded in MainActivity — move it to
> strings.xml."

Direct Edit in the main session, no subagent.
