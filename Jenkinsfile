pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/rolandgalway/ct5171-assignment.git'
            }
        }

        stage('Build WAR') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Archive WAR') {
            steps {
                archiveArtifacts artifacts: '**/rolandspetitions.war', followSymlinks: false
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                sshagent(credentials: ['tomcat-deploy-key']) {
                    sh '''
                        # Remove old WAR and exploded folder
                        ssh -o StrictHostKeyChecking=no deploy@16.171.170.116 "rm -rf /opt/tomcat10/webapps/rolandspetitions /opt/tomcat10/webapps/rolandspetitions.war"

                        # Copy new WAR
                        scp -o StrictHostKeyChecking=no target/rolandspetitions.war deploy@16.171.170.116:/opt/tomcat10/webapps/

                        # Restart Tomcat
                        ssh -o StrictHostKeyChecking=no deploy@16.171.170.116 "sudo systemctl restart tomcat"
                    '''
                }
            }
        }
    }
}
