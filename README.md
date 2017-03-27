export DOCKER_IP=$(docker-machine ip poc-awb)
curl http://$DOCKER_IP:9000/clients/clients