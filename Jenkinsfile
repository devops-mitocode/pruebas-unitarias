 pipeline {
     agent {
        docker {
            image 'maven:3.8.8-eclipse-temurin-17-alpine'
        }
     }
    triggers {
        cron('* * * * *')
    }
    parameters {
        string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')

        text(name: 'BIOGRAPHY', defaultValue: '', description: 'Enter some information about the person')

        booleanParam(name: 'TOGGLE', defaultValue: true, description: 'Toggle this value')

        choice(name: 'CHOICE', choices: ['One', 'Two', 'Three'], description: 'Pick something')

        password(name: 'PASSWORD', defaultValue: 'SECRET', description: 'Enter a password')
    }
     stages {
//          stage('Checkout SCM') {
//              steps {
//                  git branch: 'master', url: 'https://github.com/devops-mitocode/pruebas-unitarias.git'
//              }
//          }
         stage('Compile') {
             steps {
                 sh 'mvn clean compile -B -ntp'
             }
         }
         stage('Test') {
             steps {
                 sh 'mvn test -Dtest=MascotaService05JUnitMockitoCoverageTest -B -ntp'
                 junit 'target/surefire-reports/*.xml'

                 discoverReferenceBuild()
                 recordCoverage(tools: [[parser: 'JACOCO']])

//                 recordCoverage(
//                     tools: [[parser: 'JACOCO']],
//                     sourceCodeRetention: 'EVERY_BUILD',
//                     qualityGates: [
//                         [threshold: 30.0, metric: 'LINE'],
//                         [threshold: 30.0, metric: 'BRANCH']
//                     ]
//                 )

//                 recordCoverage(
//                     tools: [[parser: 'JACOCO']],
//                     sourceCodeRetention: 'EVERY_BUILD',
//                     qualityGates: [
//                             [threshold: 30.0, metric: 'LINE', criticality: 'FAILURE'],
//                             [threshold: 30.0, metric: 'BRANCH', criticality: 'FAILURE']
//                     ]
//                 )

             }
         }
       stage('SonarQube') {
           steps {
               withSonarQubeEnv('sonarqube'){
                   sh 'mvn sonar:sonar -B -ntp'
               }
           }
       }
       stage("Quality Gate"){
           steps{
               timeout(time: 60, unit: 'SECONDS') {
                   waitForQualityGate abortPipeline: true
               }
           }
       }
     }
 }
