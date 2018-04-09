# cucumber-reporting-gradle-plugin

Gradle plugin for Cucumber reporting tool [Cucumber Reporting](https://github.com/damianszczepanik/cucumber-reporting)

## Project quality

[![Build Status](https://travis-ci.org/alfa-laboratory/cucumber-reporting-gradle-plugin.svg?branch=master)](https://travis-ci.org/alfa-laboratory/cucumber-reporting-gradle-plugin)
[![Coverage Status](https://coveralls.io/repos/github/alfa-laboratory/cucumber-reporting-gradle-plugin/badge.svg)](https://coveralls.io/github/alfa-laboratory/cucumber-reporting-gradle-plugin)

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
    
    // To stop long running bdd tasks running as part of the test task.
    test {
        exclude '**/*'
    }
    // Change the bdd task to be runCukes for explicit invocation.
    task runCukes(type: Test) {
        jacoco {
            enabled = false
        }
        testLogging.showStandardStreams = true
    }
    
    runCukes.finalizedBy(generateCucumberReport)

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

Run `./gradlew runCukes` an see results in console output or follow to `./build/test/`
This directory contain integration tests and their data.

### Build and publish

1. `./gradlew build` build
2. `./gradlew upload` publish to maven remote repo
