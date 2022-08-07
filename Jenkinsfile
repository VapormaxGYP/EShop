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
                sh './Deploy.sh'
            }
        }
    }
}