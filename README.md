# gradle-cucumber-reporting-plugin

Gradle plugin for Cucumber reporting tool [Cucumber Reporting](https://github.com/damianszczepanik/cucumber-reporting)

## Usage

### Applying the Plugin

To include, add the following to your build.gradle

    buildscript {
      repositories { jcenter() }

      dependencies {
        classpath 'ru.alfalab.gradle:cucumber-reporting-gradle-plugin:x.x.+'
      }
    }

    apply plugin: 'ru.alfalab.cucumber-reporting'

### Tasks Provided

`generateCucumberReport` generate Cucumber report

### Extensions Provided

    cucumberReporting {
        cucumberResultsDirectory = file("${project.buildDir}/cucumber") // cucumber results dir (eg. cucumber.json location)
        outputDirectory = file("${project.buildDir}/reports/cucumber") // directory for save html reports
    }
    
### Reports

Reports can be found in dir ${project.buildDir}/reports/cucumber/

### Test

Run `./gradlew test` an see results in console output or follow to `./build/test/`
This directory contain integration tests and their data.

### Build and publish

1. `./gradlew build` build
2. `./gradlew pTML` publish to maven local
