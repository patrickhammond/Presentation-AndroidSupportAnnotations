# Android Support Annotations Summary

## What are they?

Hints you can leave in your code to help identify and eliminate bugs sooner.
Yay!!!

## Setup

```
dependencies {
    compile 'com.android.support:support-annotations:22.2.0'
}
```

## Demos

Not a complete list! See the resources at the end for the latest documentation.

### `@NonNull` and `@Nullable`

Motivations:
 - Avoiding needless `if (value == null) { /* dead code! */ }` checks.
 - Ensuring callers handle null conditions

### `@StringRes`, `@ColorRes`, etc

Motivations:
 - You've grown to hate passing `int` values throughout your code.
 - You've passed an `int` color identifier into something that expected an `int` RGBA color and lost half a day finding the bug.

### `@RequiresPermission`

Motivations:
 - Avoiding annoying runtime `java.lang.SecurityException: Permission Denial ...` crashes because of missing `AndroidManifest.xml` entries.
 - Very useful for library projects!

### `@CallSuper` and `@CheckResult`

Motivations:
 - You have a base class that has methods that can be overridden but still need to be called.
 - You have a method that returns a value that should not be ignored.

### `@VisibleForTesting`

Motivations:
- You have comments in your code that look like `// package scope for testing`

## Final thoughts

Don't annotate **everything**, just the things that are likely to cause issues.

## Resources

 - https://developer.android.com/tools/debugging/annotations.html
 - http://tools.android.com/tech-docs/support-annotations

