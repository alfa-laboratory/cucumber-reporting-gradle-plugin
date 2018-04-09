package ru.alfalab.gradle

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by aleksandr on 14.11.16.
 */
@Slf4j
@CompileStatic
class CucumberReportingPlugin implements Plugin<Project> {
    public static final String TASK_NAME = "generateCucumberReport"
    public static final String EXTENSION_NAME = "cucumberReporting"

    @Delegate
    Project project

    @Override
    void apply(Project project) {
        this.project = project

        def extension = project.extensions.create(EXTENSION_NAME, CucumberReportingExtension, project)

        CucumberReportingTask reportingTask = tasks.create (
            name: TASK_NAME,
            type: CucumberReportingTask,
            group: 'Documentation',
            description: 'Generate Cucumber report after tests execution',
            dependsOn: 'runCukes'
        ) as CucumberReportingTask

        afterEvaluate {
            reportingTask.cucumberResultsDirectory = extension.cucumberResultsDirectory
            reportingTask.outputDirectory = extension.outputDirectory
        }
    }
}
