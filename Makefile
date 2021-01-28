BUILD_NUMBER := SNAPSHOT
RELEASE_VERSION := 1.0-${BUILD_NUMBER}

build:
	mvn clean build-helper:parse-version versions:set -DnewVersion=${RELEASE_VERSION} -DprocessAllModules -DgenerateBackupPoms=false
	mvn install -Drelease.version=${RELEASE_VERSION}

push:
	docker images
	docker push docker.io/vcosqui/organization-app:${RELEASE_VERSION}
