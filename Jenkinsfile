pipeline {
   agent {
       docker {
           image 'maven:3.8.8-eclipse-temurin-17-alpine'
       }
   }
//     triggers {
// //         cron('* * * * *') // Ejecutar cada minuto
// //         cron('*/2 * * * *') // Ejecutar cada 2 minutos
//     }
   stages {
       stage('Compile') {
           steps {
               sh 'mvn clean compile -B -ntp'
           }
       }
       stage('Test') {
           steps {
               sh 'mvn test -Dtest=MascotaService05JUnitMockitoCoverageTest -B -ntp'
//                 sh 'mvn test -Dtest=MascotaService05JUnitMockitoCoverageTest -Dmaven.test.failure.ignore=true -B -ntp'
               junit 'target/surefire-reports/*.xml'
//                 jacoco()


               // Crear vista con columna Delta, ejecutar sin cobertura completa 3 veces y Delta muestra n/a. Incluso activando la cobertura completa.
//                 recordCoverage(tools: [[parser: 'JACOCO']])


               // Agrega, ejecuta sin cobertura y luego con cobertura completa
               discoverReferenceBuild()
               recordCoverage(tools: [[parser: 'JACOCO']])

           }
       }
       stage('Package') {
           steps {
               sh 'mvn package -DskipTests -B -ntp'
           }
       }
   }
   post{
       always{
           cleanWs()
       }
   }
}
