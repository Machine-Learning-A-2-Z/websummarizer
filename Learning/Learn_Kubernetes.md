
# Learn Kubernetes

## 🚀 Introduction to Kubernetes
Kubernetes (K8s) is an open-source platform designed to automate the deployment, scaling, and management of containerized applications. It groups containers into logical units for easy management and discovery.

---

## 📦 Kubernetes Components

- **Pod**: The smallest and simplest Kubernetes object. A Pod represents a set of running containers.
- **Service**: A way to expose a set of Pods as a network service.
- **Deployment**: A controller that ensures a specified number of pod replicas are running.
- **Namespace**: A way to divide cluster resources between multiple users.

---

## 🧑‍💻 Kubernetes Installation

### 1. Install Minikube (Local Kubernetes Cluster)
Minikube allows you to run Kubernetes clusters locally.

```bash
curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64
chmod +x minikube-linux-amd64
sudo mv minikube-linux-amd64 /usr/local/bin/minikube
```

### 2. Install kubectl (Kubernetes CLI)
kubectl is a command-line tool for interacting with Kubernetes clusters.

```bash
curl -LO https://storage.googleapis.com/kubernetes-release/release/v1.21.0/bin/linux/amd64/kubectl
chmod +x kubectl
sudo mv kubectl /usr/local/bin/
```

---

## 🛠️ Kubernetes Basics

### Deploy a Simple Application

1. **Create a Pod:**
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: myapp-pod
spec:
  containers:
    - name: myapp
      image: nginx
```

2. **Apply the configuration:**
```bash
kubectl apply -f myapp-pod.yaml
```

3. **Check the Pod status:**
```bash
kubectl get pods
```

---

## 🔄 Kubernetes Services

To expose a pod to external traffic, create a Service.

```yaml
apiVersion: v1
kind: Service
metadata:
  name: myapp-service
spec:
  selector:
    app: myapp
  ports:
    - protocol: TCP
      port: 80
      targetPort: 80
```

To apply the service:

```bash
kubectl apply -f myapp-service.yaml
```

---

## 🎯 Advanced Topics

### Scaling Applications

To scale the deployment to 3 replicas:

```bash
kubectl scale deployment myapp --replicas=3
```

### Rolling Updates

Kubernetes supports rolling updates to ensure there is no downtime.

```bash
kubectl apply -f updated-myapp.yaml
```

---

## ✅ Conclusion

Kubernetes simplifies the management and orchestration of containerized applications in production environments. Learning Kubernetes enables you to scale your applications efficiently.
