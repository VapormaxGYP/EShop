pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh './mvnw compile'
            }
        }
        stage('Deliver') {
            steps {
                sh './deploy.sh'
            }
        }
    }
}