# Starting a new chat

**When**: You're beginning a new, distinct piece of work (a new feature, a
new bug, a new build issue) with no relevant open context from a prior
session.

## What to do

1. If the last session was on unrelated work, run `/clear` first (don't
   carry over stale feature context into an unrelated task).
2. State the goal in one concrete sentence, not a vague topic. Include
   which layer it touches if you know (domain/data/presentation) — this
   helps route to the right agent without extra back-and-forth.
3. Let Claude pick the agent per the table in `CLAUDE.md` →
   "Model & Agent Usage Rules". You generally don't need to name an agent
   yourself — just describe the task shape:
   - A lookup/"where is" question → answered directly or via `explorer`
   - "Add a feature that does X" → `feature-builder`
   - "Review this before I commit" → `code-reviewer`
   - "Build is failing" → `build-runner`
4. If you *do* want to force a specific agent, say so explicitly
   ("use feature-builder for this") — otherwise trust the routing.

## Avoid

- Don't open with "what should I do" — state the concrete task.
- Don't pre-decide the model/agent unless you disagree with the routing
  table; let it be inferred.
- Don't paste the whole CLAUDE.md or file contents into your first
  message — Claude reads the repo directly.

## Example (this project)

> "Add a `Counter` feature: a domain entity holding an int, a use case to
> increment it, and a ViewModel/Activity wiring like the `greeting`
> example, with the count shown in the existing layout."

This routes straight to `feature-builder` since it names a concrete new
feature following the established pattern.
