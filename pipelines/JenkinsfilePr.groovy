pipeline {
    agent any

    stages {
        stage('LoadCode') {
            steps {
                git branch: ghprbSourceBranch, credentialsId: 'bjls_id', url: 'https://github.com/bajiulishi/Samples'
            }
        }
        stage('Lint') {
             steps {
                sh './gradlew lint'
             }
        }
        stage('SonarQube') {
            steps {
                script {
                    sonarqubeScannerHome = tool 'SonarQubeScanner'
                }
                withSonarQubeEnv('SonarQube') {
                    sh "${sonarqubeScannerHome}/bin/sonar-scanner -X "+
                    "-Dsonar.host.url=https://2b18-112-20-99-161.ngrok-free.app " +
                    "-Dsonar.language=java " +
                    "-Dsonar.projectKey=Samples " +
                    "-Dsonar.projectName=Samples " +
                    "-Dsonar.projectVersion=2.0 " +
                    "-Dsonar.sources=app/src/ " +
                    "-Dsonar.sourceEncoding=UTF-8 " +
                    "-Dsonar.java.binaries=target/ " +
                    "-Dsonar.login=admin " +
                    "-Dsonar.password=bjls "
                }
            }
        }
        stage("SonarQube Quality") {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    waitForQualityGate abortPipeline: true,
                    credentialsId: 'sqa_54b70fa63a511657a2470c134f88f3374f5b0eb2'
                }
            }
        }
        stage('UnitTest') {
            steps {
                sh './gradlew test'
            }
        }
        stage('Build') {
             steps {
                sh './gradlew clean && rm -rf ./app/build/'
                sh './gradlew assembleDebug'
             }
        }
        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'app/build/outputs/**/*.apk', fingerprint: true
            }
        }
    }
}