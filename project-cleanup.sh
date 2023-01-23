#!/bin/sh
kubectl delete svc customer-outbound-service
kubectl delete svc theater-inbound-service
kubectl delete svc theater-outbound-service

kubectl delete deploy customer-outbound-deployment
kubectl delete deploy theater-inbound-deployment
kubectl delete deploy theater-outbound-deployment

kubectl delete svc mongodb-service
kubectl delete deploy mongodb-deployment

minikube image rm moviesplanet_theateroutbound:latest
minikube image rm moviesplanet_theaterinbound:latest
minikube image rm moviesplanet_customeroutbound:lates