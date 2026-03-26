pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package'
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
