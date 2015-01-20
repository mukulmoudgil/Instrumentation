# Instrumentation
Instrumentation

echo # Instrumentation >> README.md
git init
git add README.md
git commit -m "first commit"
git remote add origin https://github.com/mukulmoudgil/Instrumentation.git
git push -u origin master

java -cp .;./lib/javassist-3.14.0-GA.jar;lib/antlr.jar;lib/antlr-2.7.6.jar;lib/asm.jar;lib/asm-attrs.jar;lib/axis.jar;lib/commons-collections.jar;lib/commons-collections-3.1.jar;lib/commons-discovery-0.2.jar;lib/commons-logging.jar;lib/dom4j.jar;lib/dom4j-1.6.1.jar;lib/hibernate3.jar;lib/hibernate-commons-annotations.jar;lib/hibernate-core.jar;lib/j2ee.jar;lib/javassist;lib/javassist-3.9.0.GA.jar;lib/jaxrpc.jar;lib/jta.jar;lib/jta-1.1.jar;lib/junit.jar;lib/log4j.jar;lib/mysql-connector-java-5.1.8-bin.jar;lib/saaj.jar;lib/slf4j-api.jar;lib/slf4j-log4j12.jar;lib/webserviceutils.jar;lib/postgresql-9.0-802.jdbc4.jar;lib/spring-core-4.1.4.RELEASE.jar;lib/spring/spring-core-4.1.4.RELEASE-javadoc.jar;lib/spring-core-4.1.4.RELEASE-sources.jar;lib/spring-beans-4.1.4.RELEASE.jar;lib/spring-oxm-4.1.4.RELEASE.jar;lib/spring-hibernate3-2.0-m4.jar;lib/spring-orm-4.1.4.RELEASE.jar;lib/spring-dao.jar;lib/spring-jdbc-4.1.4.RELEASE.jar;lib/spring-tx-4.1.4.RELEASE.jar; -javaagent:./bin/shi.jar com.Test.InsertTest



jar cvfm shi.jar ..\META-INF\manifest.mf .
