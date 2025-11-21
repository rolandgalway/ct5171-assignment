pipeline {
    agent any

    environment {
        DEPLOY_USER = "deploy"
        EC2_HOST = "16.171.170.116"
        WAR_NAME = "rolandspetitions.war"
        REMOTE_TOMCAT = "/opt/tomcat10/webapps"
    }

    stages {

        stage('Get Code from GitHub') {
            steps {
                git branch: 'main', url: 'https://github.com/rolandgalway/ct5171-assignment.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package WAR') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Archive WAR') {
            steps {
                archiveArtifacts artifacts: "target/${WAR_NAME}", allowEmptyArchive: false
            }
        }

        stage('Approval Before Deployment') {
            steps {
                script {
                    timeout(time: 5, unit: 'MINUTES') {
                        input message: "Deploy ${WAR_NAME} to production EC2?"
                    }
                }
            }
        }

        stage('Deploy to Tomcat on EC2') {
            steps {
                sshagent(['deploy']) {
                    sh """
                        echo ==== Removing old deployment ====
                        ssh -o StrictHostKeyChecking=no ${DEPLOY_USER}@${EC2_HOST} sudo rm -rf ${REMOTE_TOMCAT}/rolandspetitions ${REMOTE_TOMCAT}/${WAR_NAME}

                        echo ==== Uploading new WAR ====
                        scp -o StrictHostKeyChecking=no target/${WAR_NAME} ${DEPLOY_USER}@${EC2_HOST}:/tmp/

                        echo ==== Moving WAR to Tomcat folder ====
                        ssh -o StrictHostKeyChecking=no ${DEPLOY_USER}@${EC2_HOST} sudo mv /tmp/${WAR_NAME} ${REMOTE_TOMCAT}/
                        ssh -o StrictHostKeyChecking=no ${DEPLOY_USER}@${EC2_HOST} sudo chown tomcat:tomcat ${REMOTE_TOMCAT}/${WAR_NAME}

                        echo ==== Restarting Tomcat ====
                        ssh -o StrictHostKeyChecking=no ${DEPLOY_USER}@${EC2_HOST} sudo systemctl restart tomcat
                    """
                }
            }
        }
    }

    post {
        success {
            echo 'Deployment successful!'
        }
        failure {
            echo 'Pipeline failed - check logs.'
        }
    }
}
