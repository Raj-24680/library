pipeline {
    agent any

    stages {

        stage('Build & Package') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Build Image') {
            steps {
                bat 'docker build -t library-app .'
            }
        }

        stage('Deploy') {
            steps {
                bat 'docker rm -f library-app || true'
                bat 'docker run -d -p 8081:8081 --name library-app library-app'
            }
        }
    }
}
