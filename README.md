# datascienceplatform-oauth2
Services for Oauth2 

For setting up development environment:

1) Download and install spring tool suite (https://spring.io/tools/sts/all)
2) Download lombok.jar (https://projectlombok.org/download.html)
3) Install lombok into spring tool suite with java -jar lombok.jar
3) Start STS and import this project as existing project
4) Press alt F5 to update Maven
5) In STS, select Run as java application
6) Select DataSciencePlatformOAuth



esg-truststore.ts can be obtained with:
curl -L https://raw.githubusercontent.com/ESGF/esgf-dist/master/installer/certs/esg-truststore.ts > esg-truststore.ts

or use:
/usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/security/cacerts

Keystore can be generated with:
keytool -genkey -noprompt -keypass password -alias tomcat -keyalg RSA -storepass password -keystore /keystore/c4i_keystore.jks  -dname CN=`hostname`


```xml
<?xml version="1.0" encoding="UTF-8"?>
<adaguc-services>
    
  <external-home-url>https://compute-test.c3s-magic.eu:9000</external-home-url>
  
  <server>
    <port>9000</port>
  </server>
  
  <basedir>{ENV.HOME}/adaguc-services-base</basedir>
  
  <userworkspace>{ENV.HOME}/adaguc-services-space</userworkspace>
  
  <security>
    <truststorepassword>changeit</truststorepassword>
    <truststore>{ENV.HOME}/config/esg-truststore.ts</truststore>
    <trustrootscadirectory>{ENV.HOME}/.globus/certificates/</trustrootscadirectory>
    <keystore>{ENV.HOME}/impactportal/c4i_keystore.jks</keystore>
    <keystorepassword>password</keystorepassword>
    <keystoretype>JKS</keystoretype>
    <keyalias>tomcat</keyalias>
  </security>
   
</adaguc-services>
```


For creating a new package:

1) Adjust the version in pom.xml: 0.<sprint number>.version (this is named ${VERSION} from now on)
2) Type mvn package
3) in directory target the file ./target/demo-${VERSION}-SNAPSHOT.jar is created.
4) You can for example start this with java -jar demo-${VERSION}-SNAPSHOT.jar



