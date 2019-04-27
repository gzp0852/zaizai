FROM java:8-alpine

MAINTAINER yaoyuan yuanyao@wistronits.com
# 放入文件 重命名
ADD target/filink-gateway-server-0.0.1-RELEASE.jar filink-gateway-server-0.0.1-RELEASE.jar

EXPOSE 9801

ENTRYPOINT ["java","-jar","/filink-gateway-server-0.0.1-RELEASE.jar","--spring.profiles.active=test"]


