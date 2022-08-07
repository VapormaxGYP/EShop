pipeline {
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