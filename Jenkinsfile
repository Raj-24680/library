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
                bat '"C:\\Program Files\\RedHat\\Podman\\podman.exe" build -t library-app .'
bat '"C:\\Program Files\\RedHat\\Podman\\podman.exe" rm -f library-app || true'
bat '"C:\\Program Files\\RedHat\\Podman\\podman.exe" run -d -p 8081:8080 --name library-app library-app'
            }
        }
    }
}
