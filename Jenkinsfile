pipeline {
    agent any

    environment {
        DEPLOY_USER = 'deploy'
        DEPLOY_HOST = '16.171.170.116'
        DEPLOY_PATH = '/opt/tomcat10/webapps'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/rolandgalway/ct5171-assignment.git'
            }
        }

        stage('Build WAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Archive WAR') {
            steps {
                archiveArtifacts artifacts: 'target/rolandspetitions.war', allowEmptyArchive: false
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                sshagent(['tomcat-deploy-key']) {
                    sh '''
                        echo "==== Removing old deployment ===="
                        ssh -o StrictHostKeyChecking=no ${DEPLOY_USER}@${DEPLOY_HOST} "sudo rm -rf ${DEPLOY_PATH}/rolandspetitions ${DEPLOY_PATH}/rolandspetitions.war"

                        echo "==== Copying new WAR to EC2 ===="
                        scp -o StrictHostKeyChecking=no target/rolandspetitions.war ${DEPLOY_USER}@${DEPLOY_HOST}:/tmp/

                        echo "==== Moving WAR into Tomcat directory (with proper permissions) ===="
                        ssh -o StrictHostKeyChecking=no ${DEPLOY_USER}@${DEPLOY_HOST} "sudo mv /tmp/rolandspetitions.war ${DEPLOY_PATH}/ && sudo chown tomcat:tomcat ${DEPLOY_PATH}/rolandspetitions.war"

                        echo "==== Restarting Tomcat ===="
                        ssh -o StrictHostKeyChecking=no ${DEPLOY_USER}@${DEPLOY_HOST} "sudo systemctl restart tomcat"
                    '''
                }
            }
        }
    }

    post {
        success {
            echo 'Deployment successful!'
        }
        failure {
            echo 'Deployment failed. Check Jenkins logs for details.'
        }
    }
}
