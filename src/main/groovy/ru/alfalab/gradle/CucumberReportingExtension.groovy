package ru.alfalab.gradle

import groovy.transform.CompileStatic
import org.gradle.api.Project

/**
 * Created by aleksandr on 14.11.16.
 */
@CompileStatic
class CucumberReportingExtension {
    private final Project project

    File cucumberResultsDirectory = new File(project.buildDir, 'cucumber')
    File outputDirectory = new File(project.buildDir, 'reports/cucumber')

    CucumberReportingExtension() {}

    CucumberReportingExtension(Project project) {
        this.project = project
    }
}
