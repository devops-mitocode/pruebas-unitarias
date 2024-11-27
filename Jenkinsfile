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
                 recordCoverage(tools: [[parser: 'JACOCO']])
             }
         }
     }
 }
