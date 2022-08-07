pipeline {
    agent any
    tools {
        maven 'maven3.8.6'
    }
    stages {
        stage('Build') {
            steps {
                sh "mvn clean test package spring-boot:repackage"
            }
        }
        stage('Deploy') {
            steps {
                sh 'chmod +x ./Deploy.sh'
                withEnv(['JENKINS_NODE_COOKIE=dontKillMe']) {
                    sh './Deploy.sh'
                }
            }
        }
    }
}