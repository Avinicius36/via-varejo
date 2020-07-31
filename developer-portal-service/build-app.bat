echo ################## BUILD PROJECT ############################

echo # mvn clean install 
call mvn clean install -f . > mvn_clean_install.log

echo # docker build
docker build -t %DOCKER_REPOSITORY_NAME%/developer-portal-service .

echo # docker tag
docker tag %DOCKER_REPOSITORY_NAME%/developer-portal-service %DOCKER_REPOSITORY_NAME%/developer-portal-service:latest

echo # docker push
docker push %DOCKER_REPOSITORY_NAME%/developer-portal-service:latest

