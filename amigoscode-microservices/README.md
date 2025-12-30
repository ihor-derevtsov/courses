## 🏗️ Architecture

![img.png](static/architecture.png)

## 🏗️ Eureka Server usage

![img.png](static/eureka-server.png)

To test Eureka Server I run one Eureka Server, one Customer microservice and **two** Fraud microservices with different ports. I do it with VM option e.g. `-Dserver.port=8021`:

![eureka-server-intellij-run-configurations.png](static/eureka-server-intellij-run-configurations.png)

Then I see that when I use FraudClient Eureka Server sends first request to `8021 Fraud`, but the second one is going to `8020 Fraud`, so it works.
