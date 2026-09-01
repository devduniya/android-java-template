# Research / exploration

**When**: You need to find or understand something in the codebase and
aren't sure exactly where it lives, or want to confirm how a pattern is
used across the project.

Note: this is currently a **small template codebase** (~10 Java files) —
most lookups are fast even without a subagent. This still applies once the
codebase grows past the initial `greeting` slice.

## What to do

1. Call the `explorer` agent (Haiku, read-only) with a specific question,
   not a vague topic:
   - Good: "Find every place `GreetingRepository` is referenced and how
     it's implemented."
   - Bad: "Explore the data layer."
2. Set thoroughness in your ask:
   - **Quick** (single targeted lookup): "Where is `app_name` defined and
     used?"
   - **Medium** (a feature's full wiring): "Trace Greeting from entity to
     Activity."
   - **Very thorough** (cross-cutting, e.g. before a rename): "Find every
     file containing `com.example.myapp` so we can rename the package."
3. Take the file:line references back and decide next steps yourself (or
   hand to `feature-builder`/`build-runner`/`code-reviewer` as appropriate)
   — `explorer` doesn't edit or judge.

## Avoid

- Don't use `general-purpose` for this — `explorer` is cheaper (Haiku) and
  scoped correctly for search-only work in this repo.
- Don't ask `explorer` to review code quality or architecture — that's
  `code-reviewer`'s job (it needs judgment, not just search).
- Don't run the same exploration yourself AND delegate it — pick one.

## Example (this project)

> "Before renaming the package off `com.example.myapp`, find every file
> that references it (Java `package`/`import`, `build.gradle.kts`,
> `AndroidManifest.xml`, `strings.xml`) — very thorough."

This is exactly the kind of cross-cutting, "don't miss one" search
`explorer` is good for.
