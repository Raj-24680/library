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
                bat 'podman build -t library-app .'
            }
        }

        stage('Deploy') {
            steps {
                bat 'podman rm -f library-app || true'
                bat 'podman run -d -p 8081:8080 --name library-app library-app'
            }
        }
    }
}
