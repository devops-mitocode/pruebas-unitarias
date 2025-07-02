pipeline {
    agent any
    tools{
        maven 'maven3.9.10'
    }
    options {
        timeout(time: 10, unit: 'MINUTES')
        ansiColor('xterm')
    }
    triggers {
        githubPush()
        cron('H/30 * * * *')
    }
    stages {
//        stage('Checkout SCM') {
//            steps {
//                git branch: 'master', url: 'https://github.com/devops-mitocode/pruebas-unitarias.git' abc
//            }
//        }
        stage('Unit Tests') {
            steps {
                sh 'mvn clean test -Dstyle.color=always -Dtest=MascotaService05JUnitMockitoCoverageTest -Dmaven.test.failure.ignore=true -B -ntp'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Code Coverage') {
            steps {
                sh 'mvn jacoco:report -Dstyle.color=always -B -ntp'
            }
            post {
                always {
                    recordCoverage(
                            tools: [[parser: 'JACOCO']],
                            sourceCodeRetention: 'EVERY_BUILD'
                    )
                }
            }
        }
        stage('Package') {
            steps {
                sh 'mvn clean package -Dstyle.color=always -B -ntp'
            }
        }
    }
}