pipeline {
    agent any

    parameters {
        booleanParam(name: 'useSonar', defaultValue: true, description: 'Análise de código via SonarQube')
    }

    environment {
        SONARQUBE_SERVER = 'sonarqube'
        GITHUB_REPO = 'https://github.com/programadormovel/miniprojetospring2024.git'
        DO_SCANNER = true
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: env.GITHUB_REPO
            }
        }

        stage('Build') {
            when {
                branch 'main'
            }
            steps {
                withMaven(globalMavenSettingsConfig: '', jdk: '11', maven: '3.9.4', mavenSettingsConfig: '', publisherStrategy: 'EXPLICIT', traceability: true) {
                    echo "VALIDAÇÃO SONAR = ${env.useSonar}"
                    sh 'mvn clean install'
               }
            }
        }

        stage('SonarQube Analysis') {
            environment {
                SONAR_SCANNER_HOME = tool 'SonarQube Scanner'
            }
            steps {
                withSonarQubeEnv(env.SONARQUBE_SERVER) {
                    script{
                        if (params.useSonar){
                            sh "${env.SONAR_SCANNER_HOME}/bin/sonar-scanner -Dsonar.projectKey=miniprojetospring -Dsonar.host.url=http://192.168.0.2:9000 -Dsonar.login=sqp_3ceadd0b157eef45c001a8fe35a23d55d613f453 -Dsonar.sources=src/main/java/ -Dsonar.java.binaries=target/classes"
                        } else {
                            echo "VALIDAÇÃO SONAR NÃO SERÁ REALIZADA = ${env.useSonar}"
                        }
                    }
                }
            }
        }

//         stage("Quality Gate") {
//             steps {
//                 script{
//                     if (params.useSonar){
//                         timeout(time: 1, unit: 'MINUTES') {
//                             // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
//                             // true = set pipeline to UNSTABLE, false = don't
//                             waitForQualityGate abortPipeline: true
//                         }
//                     }
//                 }
//             }
//         }

        stage('Deploy') {
            steps {
                // Executar o deploy do projeto (modifique conforme necessário para seu ambiente)
                echo 'Deploy do projeto iniciado...'
                // Exemplo: script de deploy ou comando específico
                // sh 'bash deploy_script.sh'
            }
        }
    }

    post {
        always {
            // Enviar e-mails ou notificação de falha
            echo 'Pipeline finalizado!'
        }
        success {
            echo 'Deploy realizado com sucesso!'
        }
        failure {
            echo 'Falha no pipeline!'
        }
    }
}
