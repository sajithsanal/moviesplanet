#!/bin/sh
docker-compose build theaterinbound
docker-compose build theateroutbound
docker-compose build customeroutbound
minikube image load moviesplanet_theaterinbound
minikube image load moviesplanet_theateroutbound
minikube image load moviesplanet_customeroutbound
kubectl create -f mongodb-deployments/MongoDBDeployment.yaml
kubectl create -f mongodb-deployments/MongoDBService.yaml
kubectl create -f theater-inbound/TheaterInboundDeployment.yaml
kubectl create -f theater-inbound/TheaterInboundService.yaml
kubectl create -f theater-outbound/TheaterOutboundDeployment.yaml
kubectl create -f theater-outbound/TheaterOutboundService.yaml
kubectl create -f customer-outbound/CustomerOutboundDeployment.yaml
kubectl create -f customer-outbound/CustomerOutboundService.yaml

