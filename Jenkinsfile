pipeline {
    agent {
        // Run on a build agent where we have the Android SDK installed
        label 'android'
    }
    options {
        // Stop the build early in case of compile or test failures
        skipStagesAfterUnstable()
    }
    stages {
        stage('Compile') {
            steps {
                // Compile the app and its dependencies
                sh './gradlew compileDebugSources'
            }
        }

        stage('Unit test') {
            steps {
                // Compile and run the unit tests for the app and its dependencies
                sh './gradlew testDebugUnitTest'

                // Analyse the test results and update the build result as appropriate
                junit '**/TEST-*.xml'
            }
        }
        stage('Build APK') {
            steps {
                // Finish building and packaging the APK
                sh './gradlew assembleDebug'

                // Archive the APKs so that they can be downloaded from Jenkins
                archiveArtifacts '**/*.apk'
            }
        }
        /* stage('Static analysis') {
            steps {
                // Run Lint and analyse the results
                sh './gradlew lintDebug'
                androidLint pattern: '**//* lint-results-*.xml'
            }
        } */
        stage('Beta') {
            when {
                branch 'support/jenkins'
            }
            stages {
                stage('deploy') {
                    steps {
                        sh './gradlew assembleRelease'
                    }
                }
            }
        }
    }

    post {
        success {
            // Notify developer team of the failure
            // mail to: 'sachinnarang126@gmail.com', subject: 'New build available!', body: 'Check it out!'
            echo 'Build success'
        }
        failure {
            // Notify developer team of the failure
            // mail to: 'sachinnarang126@gmail.com', subject: 'Oops!', body: "Build ${env.BUILD_NUMBER} failed; ${env.BUILD_URL}"
            echo 'Build fail'
        }
    }
}