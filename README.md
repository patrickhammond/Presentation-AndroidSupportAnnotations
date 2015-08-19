# Android Support Annotations Demo

Sample project demonstrating several of the Android Support Annotations.

- The `master` branch has unideal code.
- The `better_code` branch has _slightly_ less unideal code demonstrating support annotation usage.

Recommendation:
Open this project in Android Studio 1.3+ while exploring support annotation usage.

Note:
The code here is far from quality code, but simply meant to highlight a few of the support annotations.

## What are they?

Hints you can leave in your code to help identify and eliminate bugs sooner.  Yay!!!

## Why should I care?

Any time I discover a stupid bug that tooling could have helped prevent, in my head I hear Nelson mocking me.

https://www.youtube.com/embed/rX7wtNOkuHo

I don’t like to hear Nelson mocking me, so I aggressively look for ways to eliminate bugs.

## Setup

Add this to the appropriate `build.grade` in your project.
```
dependencies {
    compile ‘com.android.support:support-annotations:22.2.0’
}
```

## Demos

Not a complete list! See the resources at the end for the latest documentation.

### 1 - `@NonNull` and `@Nullable`

Motivations:
 - Avoiding needless `if (value == null) { /* dead code! */ }` checks.
 - Ensuring callers handle null conditions

### 2 - `@StringRes`, `@ColorRes`, etc

Motivations:
 - You’ve grown to hate passing `int` values throughout your code.
 - You’ve passed an `int` color identifier into something that expected an `int` RGBA color and lost half a day finding the bug.

### 3 - `@RequiresPermission`

Motivations:
 - Avoiding annoying runtime `java.lang.SecurityException: Permission Denial …` crashes because of missing `AndroidManifest.xml` entries.
 - Very useful for library projects!

### 4 - `@CallSuper` and `@CheckResult`

Motivations:
 - You have a base class that has methods that can be overridden but still need to be called.
 - You have a method that returns a value that should not be ignored.

### 5 - `@VisibleForTesting`

Motivations:
- You have comments in your code that look like `// package scope for testing`

## Final thoughts

Don’t annotate **everything**, just the things that are likely to cause issues.

## Resources

 - https://developer.android.com/tools/debugging/annotations.html
 - http://tools.android.com/tech-docs/support-annotations

License
=======

    Copyright 2015 Patrick Hammond

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
