# Release Process

1. Define if it's a patch or feature release for the [formatter library](../formatter), the
   [template library](../template) and the [template processor library](../template-processor).
    1. For a patch release increase the 3rd number of the version name
    2. For a feature release increase the 2nd number of the version and set the 3rd number to `0`
2. Update the version 
   1. In the [README Installation section](../README.md#installation)
   2. In the [formatter build file](../formatter/build.gradle.kts) in the publishing configuration
   3. If needed in the [template build file](../template/build.gradle.kts) in the publishing configuration
   4. If needed in the [template processor build file](../template-processor/build.gradle.kts) in the publishing
      configuration
3. Commit the changes
4. Create a tag with the new version name as tag name
5. Push the commit and the tag
6. Create the GitHub release from the new tag
7. Publish the release in maven central
