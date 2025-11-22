# Roland’s Petitions – CT5171 Continuous Delivery Assignment

This repository contains my submission for the **CT5171 – Continuous Delivery of a Web Application** module.  
It includes the full web application and CI/CD pipeline using **GitHub**, **Jenkins**, **Maven**, **Tomcat**, and **Amazon EC2**.

---

## 📌 Project Overview

Roland’s Petitions is a minimalistic petition web application built with **Spring Boot**, using **Thymeleaf** for templating.  
The application allows users to:

- View all petitions  
- Create new petitions  
- Search petitions  
- View petition details  
- Sign petitions  
- Handle “no results” and error scenarios gracefully  

The application is packaged as a **WAR file** and deployed to **Apache Tomcat 10** running on an **Amazon EC2 instance** through a fully automated CI/CD pipeline.

---

## 🚀 CI/CD Pipeline (Jenkins)

A scripted Jenkins pipeline automatically:

1. **Checks out** the latest source code from GitHub  
2. **Builds** the project using Maven  
3. **Runs tests**  
4. **Packages** the application as `rolandspetitions.war`  
5. **Archives** the WAR artifact  
6. **Prompts the developer for manual approval** before deployment  
7. **Deploys** to Amazon EC2 using SSH with a dedicated deploy user  
8. **Restarts Tomcat** to activate the new version  

A **GitHub Webhook** triggers the pipeline automatically on each commit.

The full pipeline is defined in the `Jenkinsfile`.

---

## 🌍 Live Application

The web application is deployed on an Amazon EC2 instance and accessible at:
http://16.171.170.116:9090/rolandspetitions

## 📁 Repository Structure
/src
/main
/java → Spring Boot controllers & models
/resources
/templates → Thymeleaf HTML pages
/test → Unit tests
Jenkinsfile → Full CI/CD pipeline
pom.xml → Maven configuration
README.md


---

## 🛠️ Technologies Used

- Java 17  
- Spring Boot  
- Thymeleaf  
- Maven  
- Tomcat 10  
- Jenkins  
- GitHub Webhooks  
- Amazon EC2 (Ubuntu)  
- SSH Deployment  

---

## 📦 Build Instructions (Local)

Build:

```bash
mvn clean package

Run locally:
```bash
mvn spring-boot:run

Generate WAR:
```bash
mvn package -DskipTests

##📄 Assignment Requirements Covered
✔ Full Spring Boot Web Application

✔ CI/CD using Jenkins & Jenkinsfile

✔ Build / Test / Package pipeline

✔ Manual deployment approval

✔ Automatic deployment to EC2

✔ Running on Tomcat 10

✔ Triggered by GitHub Webhook

✔ Repository fully documented

##👤 Author
Roland Vasarhelyi
CT5171 – Cloud DevOps
University of Galway
