 pipeline {
     agent {
        docker {
            image 'maven:3.8.8-eclipse-temurin-17-alpine'
        }
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
               timeout(time: 30, unit: 'SECONDS') {
                   waitForQualityGate abortPipeline: true
               }
           }
       }
     }
 }
