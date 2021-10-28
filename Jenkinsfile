pipeline {
     agent { label 'master' }

    options {
        disableConcurrentBuilds()
        skipDefaultCheckout()
        buildDiscarder(logRotator(numToKeepStr: '40'))
        timeout(time: 2, unit: 'HOURS')
    }

    stages {
        stage("Checkout"){
			steps {
    			git branch: 'master', credentialsId: '2eb78657-dc64-430e-9111-d53d705a422b', url: 'https://lojasrenner.visualstudio.com/Realize%20-%20Open%20Banking/_git/openbanking-e2e-tests'
			}
        }
                stage('AllTests openbanking') {
                    steps {
					bat "mvn -Dtest=AllTests test"
                	}
                }        
            }
        }
    }
